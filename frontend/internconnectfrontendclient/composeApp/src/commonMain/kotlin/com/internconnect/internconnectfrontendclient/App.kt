package com.internconnect.internconnectfrontendclient

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.domain.viewmodel.LogoutViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.StudentFindInternshipsViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.StudentMyApplicationsViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.StudentSavedInternshipsViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberDashboardViewModel
import com.internconnect.internconnectfrontendclient.theme.AppTheme
import com.internconnect.internconnectfrontendclient.ui.screen.LoginScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RegisterScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RoleRouter
import com.internconnect.internconnectfrontendclient.ui.screen.Welcome
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberAnalyticsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberCandidatesScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberDashboardScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberHomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberMessagesScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberPostInternshipScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberProfileScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentFindInternshipsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentHomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMessagesScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMyApplicationsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentProfileScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentSavedInternshipsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.InternshipDetailsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMakeInternshipApplicationScreen
import org.koin.compose.koinInject

object Routes {
	// Core/entry
	const val Welcome = "welcome"
	const val RoleRouter = "router/role"

	// Auth
	const val Login = "auth/login"
	const val Register = "auth/register"

	// Student
	const val StudentHome = "student/home"
	const val StudentProfile = "student/profile"
	const val StudentFindInternships = "student/find-internships"
	const val StudentMyApplications = "student/my-applications"
	const val StudentSavedInternships = "student/saved-internships"
	const val StudentMessages = "student/messages"
	const val MakeApplication = "student/make-application" // + /{id}

	// Shared
	const val InternshipDetails = "internship/details" // + /{id}

	// Company member
	const val CompanyMemberHome = "company-member/home"
	const val CompanyMemberProfile = "company-member/profile"
	const val CompanyMemberDashboard = "company-member/dashboard"
	const val CompanyMemberPostInternship = "company-member/post-internship"
	const val CompanyMemberCandidates = "company-member/candidates" // + /{id}
	const val CompanyMemberMessages = "company-member/messages"
	const val CompanyMemberAnalytics = "company-member/analytics"
}

@Composable
fun App() {
	val navController = rememberNavController()

	// Central in-memory store for internships so details/app screens can look up by id
	val internshipStore = remember { mutableStateOf<Map<String, InternshipJoined>>(emptyMap()) }
	fun upsertInternships(list: List<InternshipJoined>) {
		if (list.isEmpty()) return
		internshipStore.value = internshipStore.value + list.associateBy { it.id }
	}

	// Development switches: toggle dummy vs API-backed data per screen
	var useDummyFind by remember { mutableStateOf(true) }
	var useDummyApps by remember { mutableStateOf(true) }
	var useDummySaved by remember { mutableStateOf(true) }

	// Company switches
	var useDummyCompanyDashboard by remember { mutableStateOf(true) }
	var useDummyCompanyCandidates by remember { mutableStateOf(true) }
	var useDummyCompanyAnalytics by remember { mutableStateOf(true) }

	AppTheme {
		NavHost(navController = navController, startDestination = Routes.Welcome) {
			composable(Routes.Welcome) {
				Welcome(
					onRegister = { navController.navigate(Routes.Register) },
					onLogin = { navController.navigate(Routes.Login) },
					onAutoNavigateHome = {
						navController.navigate(Routes.RoleRouter) {
							popUpTo(Routes.Welcome) { inclusive = true }
						}
					}
				)
			}

			composable(Routes.Login) {
				LoginScreen(
					onSuccess = {
						navController.navigate(Routes.RoleRouter) {
							popUpTo(Routes.Welcome) { inclusive = true }
						}
					},
					onNavigateBack = { navController.popBackStack() },
					onNavigateRegister = { navController.navigate(Routes.Register) }
				)
			}

			composable(Routes.Register) {
				RegisterScreen(
					onSuccess = {
						navController.navigate(Routes.Login) {
							popUpTo(Routes.Welcome) { inclusive = true }
						}
					},
					onNavigateBack = { navController.popBackStack() }
				)
			}

			// Role router (decides student vs company)
			composable(Routes.RoleRouter) { RoleRouter(navController) }

			// Student flow
			composable(Routes.StudentHome) {
				val logoutVm: LogoutViewModel = koinInject()
				StudentHomeScreen(
					onFindInternships = { navController.navigate(Routes.StudentFindInternships) },
					onMyApplications = { navController.navigate(Routes.StudentMyApplications) },
					onSavedInternships = { navController.navigate(Routes.StudentSavedInternships) },
					onMessages = { navController.navigate(Routes.StudentMessages) },
					onProfile = { navController.navigate(Routes.StudentProfile) },
					onPreferences = { /* navController.navigate("student/preferences") */ },
					onLogout = {
						logoutVm.logout {
							navController.navigate(Routes.Welcome) { popUpTo(0) { inclusive = true } }
						}
					}
				)
			}

			composable(Routes.StudentProfile) { StudentProfileScreen(onBack = { navController.popBackStack() }) }

			composable(Routes.StudentFindInternships) {
				val vm: StudentFindInternshipsViewModel = koinInject()
				val state by vm.state.collectAsState()
				LaunchedEffect(useDummyFind) { vm.setUseDummy(useDummyFind); vm.load() }

				// keep store updated for details/app screens
				LaunchedEffect(state.internships) { upsertInternships(state.internships) }

				Column(Modifier.fillMaxSize()) {
					StudentFindInternshipsScreen(
						internships = state.internships,
						categories = state.categories,
						onBack = { navController.popBackStack() },
						onOpenDetails = { id -> navController.navigate("${Routes.InternshipDetails}/$id") },
						onApply = { id -> navController.navigate("${Routes.MakeApplication}/$id") }
					)
					if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
					state.error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(12.dp)) }
				}
			}

			// Shared details screen (student + company)
			composable(
				route = "${Routes.InternshipDetails}/{id}",
				arguments = listOf(navArgument("id") { type = NavType.StringType })
			) { backStackEntry ->
				val id = requireNotNull(backStackEntry.arguments?.getString("id"))
				val internship = internshipStore.value[id]
				InternshipDetailsScreen(
					internship = internship,
					onBack = { navController.popBackStack() },
					onApply = { targetId -> navController.navigate("${Routes.MakeApplication}/$targetId") }
				)
			}

			// Student application screen
			composable(
				route = "${Routes.MakeApplication}/{id}",
				arguments = listOf(navArgument("id") { type = NavType.StringType })
			) { backStackEntry ->
				val id = requireNotNull(backStackEntry.arguments?.getString("id"))
				val internship = internshipStore.value[id]
				StudentMakeInternshipApplicationScreen(
					internship = internship,
					onBack = { navController.popBackStack() },
					onSubmit = { /* submit logic; after success: */ navController.navigate(Routes.StudentMyApplications) }
				)
			}

			composable(Routes.StudentMyApplications) {
				val vm: StudentMyApplicationsViewModel = koinInject()
				val state by vm.state.collectAsState()
				LaunchedEffect(useDummyApps) { vm.setUseDummy(useDummyApps); vm.load() }

				Column(Modifier.fillMaxSize()) {
					StudentMyApplicationsScreen(
						applications = state.applications,
						onBack = { navController.popBackStack() },
						onExploreInternships = { navController.navigate(Routes.StudentFindInternships) }
					)
					if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
					state.error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(12.dp)) }
				}
			}

			composable(Routes.StudentSavedInternships) {
				val vm: StudentSavedInternshipsViewModel = koinInject()
				val state by vm.state.collectAsState()
				LaunchedEffect(useDummySaved) { vm.setUseDummy(useDummySaved); vm.load() }

				// keep store updated
				LaunchedEffect(state.saved) { upsertInternships(state.saved) }

				Column(Modifier.fillMaxSize()) {
					StudentSavedInternshipsScreen(
						saved = state.saved,
						onBack = { navController.popBackStack() },
						onOpenDetails = { id -> navController.navigate("${Routes.InternshipDetails}/$id") },
						onApply = { id -> navController.navigate("${Routes.MakeApplication}/$id") }
					)
					if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
					state.error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(12.dp)) }
				}
			}

			composable(Routes.StudentMessages) { StudentMessagesScreen(onBack = { navController.popBackStack() }) }

			// Company member flow
			composable(Routes.CompanyMemberHome) { CompanyMemberHomeScreen(onBack = { navController.popBackStack() }) }
			composable(Routes.CompanyMemberProfile) { CompanyMemberProfileScreen(onBack = { navController.popBackStack() }) }

			composable(Routes.CompanyMemberDashboard) {
				val vm: CompanyMemberDashboardViewModel = koinInject()
				val state by vm.state.collectAsState()
				LaunchedEffect(useDummyCompanyDashboard) { vm.setUseDummy(useDummyCompanyDashboard); vm.load() }

				// keep store updated for details
				LaunchedEffect(state.internships) { upsertInternships(state.internships) }

				CompanyMemberDashboardScreen(
					onBack = { navController.popBackStack() },
					onOpenCandidates = { internshipId -> navController.navigate("${Routes.CompanyMemberCandidates}/$internshipId") },
					onOpenDetails = { id -> navController.navigate("${Routes.InternshipDetails}/$id") }
				)
			}

			composable(Routes.CompanyMemberPostInternship) { CompanyMemberPostInternshipScreen(onBack = { navController.popBackStack() }) }

			// Company Member Candidates with required internship id
			composable(
				route = "${Routes.CompanyMemberCandidates}/{id}",
				arguments = listOf(navArgument("id") { type = NavType.StringType })
			) { backStackEntry ->
				val id = requireNotNull(backStackEntry.arguments?.getString("id"))
				CompanyMemberCandidatesScreen(
					internshipId = id,
					onBack = { navController.popBackStack() }
				)
			}

			composable(Routes.CompanyMemberMessages) { CompanyMemberMessagesScreen(onBack = { navController.popBackStack() }) }
			composable(Routes.CompanyMemberAnalytics) { CompanyMemberAnalyticsScreen(onBack = { navController.popBackStack() }) }
		}
	}
}
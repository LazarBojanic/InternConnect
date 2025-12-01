package com.internconnect.internconnectfrontendclient

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.internconnect.internconnectfrontendclient.domain.viewmodel.LogoutViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.StudentFindInternshipsViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.StudentMyApplicationsViewModel
import com.internconnect.internconnectfrontendclient.domain.viewmodel.StudentSavedInternshipsViewModel
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
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentInternshipDetailsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMessagesScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMyApplicationsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentProfileScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentSavedInternshipsScreen
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
	const val StudentInternshipDetails = "student/internship-details"
	const val StudentMyApplications = "student/my-applications"
	const val StudentSavedInternships = "student/saved-internships"
	const val StudentMessages = "student/messages"

	// Company member
	const val CompanyHome = "company/home"
	const val CompanyProfile = "company/profile"

	// new company member routes
	const val CompanyDashboard = "company/dashboard"
	const val CompanyPost = "company/post"
	const val CompanyCandidates = "company/candidates"
	const val CompanyMessages = "company/messages"
	const val CompanyAnalytics = "company/analytics"
}

@Composable
fun App() {
	val navController = rememberNavController()
	var selectedInternshipId by remember { mutableStateOf<String?>(null) }
	var selectedCompanyInternshipId by remember { mutableStateOf<String?>(null) }

	// Development switches: toggle dummy vs API-backed data per screen
	var useDummyFind by remember { mutableStateOf(true) }
	var useDummyApps by remember { mutableStateOf(true) }
	var useDummySaved by remember { mutableStateOf(true) }

	// Company switches (new)
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
					onSavedOpportunities = { navController.navigate(Routes.StudentSavedInternships) },
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

				Column(Modifier.fillMaxSize()) {
					/*Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
						Text("Dummy data")
						Switch(checked = useDummyFind, onCheckedChange = { useDummyFind = it })
					}*/
					StudentFindInternshipsScreen(
						internships = state.internships,
						categories = state.categories,
						onBack = { navController.popBackStack() },
						onOpenDetails = { id ->
							selectedInternshipId = id
							navController.navigate(Routes.StudentInternshipDetails)
						}
					)
					if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
					state.error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(12.dp)) }
				}
			}

			composable(Routes.StudentInternshipDetails) {
				val internship = when (selectedInternshipId) { else -> null }
				StudentInternshipDetailsScreen(
					internship = internship,
					onBack = { navController.popBackStack() },
					onUploadResume = { /* TODO */ },
					onApply = { navController.navigate(Routes.StudentMyApplications) }
				)
			}

			composable(Routes.StudentMyApplications) {
				val vm: StudentMyApplicationsViewModel = koinInject()
				val state by vm.state.collectAsState()
				LaunchedEffect(useDummyApps) { vm.setUseDummy(useDummyApps); vm.load() }

				Column(Modifier.fillMaxSize()) {
					/*Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
						Text("Dummy data")
						Switch(checked = useDummyApps, onCheckedChange = { useDummyApps = it })
					}*/
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

				Column(Modifier.fillMaxSize()) {
					/*Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
						Text("Dummy data")
						Switch(checked = useDummySaved, onCheckedChange = { useDummySaved = it })
					}*/
					StudentSavedInternshipsScreen(
						saved = state.saved,
						onBack = { navController.popBackStack() },
						onOpenDetails = { id ->
							selectedInternshipId = id
							navController.navigate(Routes.StudentInternshipDetails)
						}
					)
					if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
					state.error?.let { Text(it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(12.dp)) }
				}
			}

			composable(Routes.StudentMessages) { StudentMessagesScreen(onBack = { navController.popBackStack() }) }

			// Company flow
			composable(Routes.CompanyHome) {
				val logoutVm: LogoutViewModel = koinInject()
				CompanyMemberHomeScreen(
					onCompanyDashboard = { navController.navigate(Routes.CompanyDashboard) },
					onAnalytics = { navController.navigate(Routes.CompanyAnalytics) },
					onPostInternship = { navController.navigate(Routes.CompanyPost) },
					onCandidates = { navController.navigate(Routes.CompanyDashboard) }, // navigate to dashboard first to pick posting
					onMessages = { navController.navigate(Routes.CompanyMessages) },
					onProfile = { navController.navigate(Routes.CompanyProfile) },
					onPreferences = { /* navController.navigate("company/preferences") */ },
					onLogout = {
						logoutVm.logout {
							navController.navigate(Routes.Welcome) { popUpTo(0) { inclusive = true } }
						}
					}
				)
			}

			composable(Routes.CompanyProfile) { CompanyMemberProfileScreen(onBack = { navController.popBackStack() }) }

			// Company member details
			composable(Routes.CompanyDashboard) {
				CompanyMemberDashboardScreen(
					onBack = { navController.popBackStack() },
					onOpenCandidates = { id ->
						selectedCompanyInternshipId = id
						navController.navigate(Routes.CompanyCandidates)
					}
				)
			}

			composable(Routes.CompanyCandidates) {
				val id = selectedCompanyInternshipId ?: return@composable CompanyMemberCandidatesScreen(
					"",
					onBack = { navController.popBackStack() })
				CompanyMemberCandidatesScreen(
					internshipId = id,
					onBack = { navController.popBackStack() }
				)
			}

			composable(Routes.CompanyPost) { CompanyMemberPostInternshipScreen(onBack = { navController.popBackStack() }) }
			composable(Routes.CompanyMessages) { CompanyMemberMessagesScreen(onBack = { navController.popBackStack() }) }
			composable(Routes.CompanyAnalytics) { CompanyMemberAnalyticsScreen(onBack = { navController.popBackStack() }) }
		}
	}
}
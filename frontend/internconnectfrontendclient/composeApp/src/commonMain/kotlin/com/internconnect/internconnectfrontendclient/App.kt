package com.internconnect.internconnectfrontendclient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.internconnect.internconnectfrontendclient.data.dto.ApplicationDto
import com.internconnect.internconnectfrontendclient.data.dto.ApplicationStatus
import com.internconnect.internconnectfrontendclient.data.dto.response.InternshipDto
import com.internconnect.internconnectfrontendclient.theme.AppTheme
import com.internconnect.internconnectfrontendclient.ui.screen.LoginScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RegisterScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RoleRouter
import com.internconnect.internconnectfrontendclient.ui.screen.Welcome
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberHomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberProfileScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentFindInternshipsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentHomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentInternshipDetailsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMessagesScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentMyApplicationsScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentProfileScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentSavedOpportunitiesScreen


@Composable
fun App() {
	val navController = rememberNavController()
	var selectedInternshipId by remember { mutableStateOf<String?>(null) }
	AppTheme {
		NavHost(navController = navController, startDestination = "welcome") {
			composable("welcome") {
				Welcome(
					onRegister = { navController.navigate("register") },
					onLogin = { navController.navigate("login") },
					onAutoNavigateHome = {
						navController.navigate("roleRouter") {
							popUpTo("welcome") { inclusive = true }
						}
					}
				)
			}
			composable("login") {
				LoginScreen(
					onSuccess = {
						navController.navigate("roleRouter") {
							popUpTo("welcome") { inclusive = true }
						}
					},
					onNavigateBack = { navController.popBackStack() },
					onNavigateRegister = { navController.navigate("register") }
				)
			}
			composable("register") {
				RegisterScreen(
					onSuccess = {
						navController.navigate("login") {
							popUpTo("welcome") { inclusive = true }
						}
					},
					onNavigateBack = { navController.popBackStack() }
				)
			}

			// Role router
			composable("roleRouter") { RoleRouter(navController) }

			// Student flow
			composable("student/home") {
				StudentHomeScreen(
					onFindInternships = { navController.navigate("student/find") },
					onMyApplications = { navController.navigate("student/applications") },
					onSavedOpportunities = { navController.navigate("student/saved") },
					onMessages = { navController.navigate("student/messages") },
					onProfile = { navController.navigate("student/profile") },
					onPreferences = { /* navController.navigate("student/preferences") */ }
				)
			}
			composable("student/profile") { StudentProfileScreen(onBack = { navController.popBackStack() }) }

			// New student routes
			composable("student/find") {
				val sample = listOf(
					InternshipDto("1", "Android Intern", "Acme Corp", "Germany", "Berlin", "Build amazing Android apps.", "Software"),
					InternshipDto("2", "Data Intern", "Datafy", "USA", "Austin", "Help the team with ETL.", "Data"),
					InternshipDto("3", "Marketing Intern", "BrightCo", "UK", "London", "Social campaigns and growth.", "Marketing"),
				)
				val categories = sample.map { it.category }
				StudentFindInternshipsScreen(
					internships = sample,
					categories = categories,
					onBack = { navController.popBackStack() },
					onOpenDetails = { id ->
						// 2) Save selection then navigate without params
						selectedInternshipId = id
						navController.navigate("student/details")
					}
				)
			}
			composable("student/details") {
				val internship = when (selectedInternshipId) {
					"1" -> InternshipDto("1", "Android Intern", "Acme Corp", "Germany", "Berlin", "Build amazing Android apps.", "Software")
					"2" -> InternshipDto("2", "Data Intern", "Datafy", "USA", "Austin", "Help the team with ETL.", "Data")
					"3" -> InternshipDto("3", "Marketing Intern", "BrightCo", "UK", "London", "Social campaigns and growth.", "Marketing")
					else -> null
				}
				StudentInternshipDetailsScreen(
					internship = internship,
					onBack = { navController.popBackStack() },
					onUploadResume = { /* TODO */ },
					onApply = { navController.navigate("student/applications") }
				)
			}
			composable("student/applications") {
				val apps = listOf(
					ApplicationDto("1", "Android Intern", "Acme Corp", "Germany", "Berlin", ApplicationStatus.APPLIED),
					ApplicationDto("2", "Data Intern", "Datafy", "USA", "Austin", ApplicationStatus.REJECTED),
					ApplicationDto("3", "Marketing Intern", "BrightCo", "UK", "London", ApplicationStatus.ACCEPTED),
				)
				StudentMyApplicationsScreen(
					applications = apps,
					onBack = { navController.popBackStack() },
					onExploreInternships = { navController.navigate("student/find") }
				)
			}
			composable("student/saved") {
				val saved = listOf(
					InternshipDto(
						"1",
						"Android Intern",
						"Acme Corp",
						"Germany",
						"Berlin",
						"Build amazing Android apps.",
						"Software"
					),
					InternshipDto("3", "Marketing Intern", "BrightCo", "UK", "London", "Social campaigns and growth.", "Marketing")
				)
				StudentSavedOpportunitiesScreen(
					saved = saved,
					onBack = { navController.popBackStack() },
					onOpenDetails = { id -> navController.navigate("student/details/$id") }
				)
			}
			composable("student/messages") { StudentMessagesScreen(onBack = { navController.popBackStack() }) }

			// Company flow (unchanged)
			composable("company/home") {
				CompanyMemberHomeScreen(
					onCompanyDashboard = { /* navController.navigate("company/dashboard") */ },
					onAnalytics = { /* navController.navigate("company/analytics") */ },
					onPostInternship = { /* navController.navigate("company/post") */ },
					onCandidates = { /* navController.navigate("company/candidates") */ },
					onMessages = { /* navController.navigate("company/messages") */ },
					onProfile = { navController.navigate("company/profile") },
					onPreferences = { /* navController.navigate("company/preferences") */ }
				)
			}
			composable("company/profile") { CompanyMemberProfileScreen(onBack = { navController.popBackStack() }) }
		}
	}
}
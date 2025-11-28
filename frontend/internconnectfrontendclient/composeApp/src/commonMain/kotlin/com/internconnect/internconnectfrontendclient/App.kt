package com.internconnect.internconnectfrontendclient

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.internconnect.internconnectfrontendclient.theme.AppTheme
import com.internconnect.internconnectfrontendclient.ui.screen.LoginScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RegisterScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RoleRouter
import com.internconnect.internconnectfrontendclient.ui.screen.Welcome
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberHomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.companymember.CompanyMemberProfileScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentHomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.student.StudentProfileScreen


@Composable
fun App() {
	val navController = rememberNavController()
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
					onFindInternships = { /* navController.navigate("student/find") */ },
					onMyApplications = { /* navController.navigate("student/applications") */ },
					onSavedOpportunities = { /* navController.navigate("student/saved") */ },
					onMessages = { /* navController.navigate("student/messages") */ },
					onProfile = { navController.navigate("student/profile") },
					onPreferences = { /* navController.navigate("student/preferences") */ }
				)
			}
			composable("student/profile") { StudentProfileScreen(onBack = { navController.popBackStack() }) }

			// Company flow
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
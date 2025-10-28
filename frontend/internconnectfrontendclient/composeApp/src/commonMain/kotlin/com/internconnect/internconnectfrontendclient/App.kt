package com.internconnect.internconnectfrontendclient

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.internconnect.internconnectfrontendclient.theme.AppTheme
import com.internconnect.internconnectfrontendclient.ui.screen.HomeScreen
import com.internconnect.internconnectfrontendclient.ui.screen.LoginScreen
import com.internconnect.internconnectfrontendclient.ui.screen.RegisterScreen
import com.internconnect.internconnectfrontendclient.ui.screen.Welcome


@Composable
fun App() {
	val navController = rememberNavController()
	AppTheme {
		NavHost(navController = navController, startDestination = "welcome") {
			composable("welcome") {
				Welcome(
					onRegister = { navController.navigate("register") },
					onLogin = { navController.navigate("login") }
				)
			}
			composable("login") {
				LoginScreen(
					onSuccess = {
						navController.navigate("home") {
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
						navController.navigate("home") {
							popUpTo("welcome") { inclusive = true }
						}
					},
					onNavigateBack = { navController.popBackStack() }
				)
			}
			composable("home") { HomeScreen() }
		}
	}
}
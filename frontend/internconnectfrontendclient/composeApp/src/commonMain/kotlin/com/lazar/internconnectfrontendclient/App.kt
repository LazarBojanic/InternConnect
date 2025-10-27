package com.lazar.internconnectfrontendclient

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lazar.internconnectfrontendclient.theme.AppTheme
import com.lazar.internconnectfrontendclient.ui.screen.Home
import com.lazar.internconnectfrontendclient.ui.screen.Login
import com.lazar.internconnectfrontendclient.ui.screen.Register
import com.lazar.internconnectfrontendclient.ui.screen.Welcome


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
				Login(
					onLogin = {
						navController.navigate("home") {
							popUpTo("welcome") { inclusive = true }
						}
					}
				)
			}
			composable("register") {
				Register(
					onRegister = {
						navController.navigate("home") {
							popUpTo("welcome") { inclusive = true }
						}
					}
				)
			}
			composable("home") {
				Home()
			}
		}
	}
}

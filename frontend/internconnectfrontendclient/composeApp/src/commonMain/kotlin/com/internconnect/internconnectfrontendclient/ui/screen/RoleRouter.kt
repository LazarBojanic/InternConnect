package com.internconnect.internconnectfrontendclient.ui.screen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.navigation.NavController
import com.internconnect.internconnectfrontendclient.Routes
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileUiState
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import org.koin.compose.koinInject
@Composable
fun RoleRouter(navController: NavController) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	// Trigger load once when this screen first appears
	LaunchedEffect(Unit) { vm.load() }

	when {
		state.loading -> {
			Box(contentAlignment = Alignment.Center) { CircularProgressIndicator() }
		}
		state.error != null -> {
			// If there’s an error, you can decide what to do. For now, show the error.
			Text("Failed to detect role: ${state.error}", color = MaterialTheme.colorScheme.error)
		}
		state is ProfileUiState.CompanyMemberState -> {
			LaunchedEffect("company-member-route") {
				navController.navigate(Routes.CompanyMemberHome) {
					popUpTo(Routes.RoleRouter) { inclusive = true }
				}
			}
		}
		state is ProfileUiState.StudentState -> {
			LaunchedEffect("student-route") {
				navController.navigate(Routes.StudentHome) {
					popUpTo(Routes.RoleRouter) { inclusive = true }
				}
			}
		}
		else -> {
			// Fallback if state doesn’t match known types (should be rare)
			Text("Could not determine user role.")
		}
	}
}
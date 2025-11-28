package com.internconnect.internconnectfrontendclient.ui.screen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavController
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileUiState
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import org.koin.compose.koinInject
@Composable
fun RoleRouter(navController: NavController) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	LaunchedEffect(Unit) { vm.load() }

	Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		when {
			state.loading -> CircularProgressIndicator()
			state.error != null -> Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
			else -> {
				when (state) {
					is ProfileUiState.StudentState -> {
						LaunchedEffect("student-nav") {
							navController.navigate("student/home") {
								popUpTo("roleRouter") { inclusive = true }
							}
						}
					}
					is ProfileUiState.CompanyMemberState -> {
						LaunchedEffect("company-nav") {
							navController.navigate("company/home") {
								popUpTo("roleRouter") { inclusive = true }
							}
						}
					}
				}
			}
		}
	}
}
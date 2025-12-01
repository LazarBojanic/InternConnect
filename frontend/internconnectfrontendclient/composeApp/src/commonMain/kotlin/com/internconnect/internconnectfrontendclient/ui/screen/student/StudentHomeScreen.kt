package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileUiState
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.TwoColumnGrid
import org.koin.compose.koinInject

@Composable
fun StudentHomeScreen(
	onFindInternships: () -> Unit,
	onMyApplications: () -> Unit,
	onSavedInternships: () -> Unit,
	onMessages: () -> Unit,
	onProfile: () -> Unit,
	onPreferences: () -> Unit,
	onLogout: () -> Unit,
) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	LaunchedEffect(Unit) { vm.load() }
	Surface (modifier = Modifier.fillMaxSize()){
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Header(title = "Home")
			Spacer(Modifier.height(16.dp))

			when {
				state.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
				state.error != null -> Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
				state is ProfileUiState.StudentState -> {
					val student = (state as ProfileUiState.StudentState).data
					val hello = "Hello ${student?.user?.firstName}!"
					Surface(tonalElevation = 2.dp, shadowElevation = 2.dp, shape = MaterialTheme.shapes.large) {
						Column(Modifier.fillMaxWidth().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
							Text(text = hello, style = MaterialTheme.typography.titleLarge)
							Text(
								text = "Welcome to InternConnector",
								style = MaterialTheme.typography.bodyMedium,
								color = MaterialTheme.colorScheme.onSurfaceVariant
							)
						}
					}

					Spacer(Modifier.height(24.dp))

					TwoColumnGrid(
						labels = listOf(
							"Find Internships" to onFindInternships,
							"My Applications" to onMyApplications,
							"Saved Internships" to onSavedInternships,
							"Messages" to onMessages,
							"Profile" to onProfile,
							"Preferences" to onPreferences,
						)
					)
				}
				else -> {
					// In case a company member somehow landed here, show a generic info
					Text("Not a student account.")
				}
			}
			Spacer(Modifier.height(16.dp))
			Button(onClick = onLogout, modifier = Modifier.fillMaxWidth()) { Text("Logout") }
		}
	}

}
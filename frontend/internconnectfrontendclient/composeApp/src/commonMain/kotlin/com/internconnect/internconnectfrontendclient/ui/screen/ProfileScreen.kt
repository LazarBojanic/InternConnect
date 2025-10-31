package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileUiState
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Composable
private fun StudentProfileContent(state: ProfileUiState.StudentState) {
	Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		Text("Student", style = MaterialTheme.typography.titleMedium)
		Text("Full name: ${state.data?.fullName}")
		Text("Email: ${state.data?.email}")
		Text("User role: ${state.data?.userRole}")
		Text("Is email verified: ${state.data?.isEmailVerified}")
		Text("School name: ${state.data?.schoolName}")
		Text("Grade: ${state.data?.grade}")
		Text("Bio: ${state.data?.bio}")
		Text("Interests: ${state.data?.interests}")
		Text("Avatar URL: ${state.data?.avatarUrl}")

	}
}

@Composable
private fun CompanyMemberProfileContent(state: ProfileUiState.CompanyMemberState) {
	Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		Text("Company Member", style = MaterialTheme.typography.titleMedium)
		Text("Full name: ${state.data?.userFullName}")
		Text("Email: ${state.data?.userEmail}")
		Text("User role: ${state.data?.userRole}")
		Text("Is email verified: ${state.data?.isEmailVerified}")
		Text("User Status: ${state.data?.userStatus}")
		Text("Company Industry: ${state.data?.companyIndustry}")
		Text("Company Member Role: ${state.data?.companyMemberRole}")
		Text("Company Member Status: ${state.data?.companyMemberStatus}")
		Text("Joined At: ${state.data?.joinedAt}")
		Text("Website: ${state.data?.website}")
		Text("Logo URL: ${state.data?.logoUrl}")
		Text("HQ Country: ${state.data?.hqCountry}")
		Text("City: ${state.data?.city}")
		Text("About: ${state.data?.about}")
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	LaunchedEffect(Unit) {
		vm.load()

	}

	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Profile") },
				navigationIcon = {
					IconButton(onClick = onNavigateBack) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
					}
				}
			)
		}
	) { padding ->
		when {
			state.loading -> Box(Modifier.fillMaxSize().padding(WindowInsets.safeDrawing.asPaddingValues())) { CircularProgressIndicator(Modifier.padding(WindowInsets.safeDrawing.asPaddingValues()).padding(24.dp)) }
			state.error != null -> Box(Modifier.fillMaxSize().padding(WindowInsets.safeDrawing.asPaddingValues()).padding(padding)) { Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error) }
			else -> Box(Modifier.fillMaxSize().padding(WindowInsets.safeDrawing.asPaddingValues()).padding(padding)
				/*.padding(16.dp)*/
			) {
				when (state.role) {
					"STUDENT" -> StudentProfileContent(state = state as ProfileUiState.StudentState)
					"COMPANY_MEMBER" -> CompanyMemberProfileContent(state = state as ProfileUiState.CompanyMemberState)
					null -> Text("No user info available")
				}
			}
		}
	}
}
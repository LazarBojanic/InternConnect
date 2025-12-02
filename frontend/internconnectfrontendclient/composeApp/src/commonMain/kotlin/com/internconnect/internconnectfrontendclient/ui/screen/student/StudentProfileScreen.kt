package com.internconnect.internconnectfrontendclient.ui.screen.student
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileUiState
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.InfoRow
import com.internconnect.internconnectfrontendclient.ui.components.SectionCard
import com.internconnect.internconnectfrontendclient.ui.components.StatusChip
import org.koin.compose.koinInject
@Composable
fun StudentProfileScreen(onBack: () -> Unit) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()
	LaunchedEffect(Unit) { vm.load() }

	Surface(modifier = Modifier.fillMaxSize()) {
		Column(Modifier.fillMaxSize().padding(16.dp)) {
			Spacer(Modifier.height(16.dp))

			when {
				state.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
				state.error != null -> Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
				state is ProfileUiState.StudentState -> {
					val student = (state as ProfileUiState.StudentState).data

					ElevatedCard(shape = MaterialTheme.shapes.extraLarge, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
						Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
							Row(verticalAlignment = Alignment.CenterVertically) {
								val initials = student?.user?.firstName?.first().toString() + " " + student?.user?.lastName?.first().toString()
								val fullName = student?.user?.firstName + " " + student?.user?.lastName
								Box(
									modifier = Modifier.size(64.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
									contentAlignment = Alignment.Center
								) {
									Text(initials, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
								}
								Spacer(Modifier.width(16.dp))
								Column(Modifier.weight(1f)) {
									Text(text = fullName, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
									Text(text = student?.user?.email ?: "—", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
								}
							}
							Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
								StatusChip(text = student?.user?.role ?: "STUDENT")
								if (student?.user?.isEmailVerified == true) StatusChip(text = "Verified", container = MaterialTheme.colorScheme.tertiaryContainer)
								student?.user?.status?.takeIf { it.isNotBlank() }?.let { StatusChip(text = it) }
							}
						}
					}

					Spacer(Modifier.height(16.dp))

					// Education
					SectionCard(title = "Education") {
						InfoRow("School", student?.schoolName)
						InfoRow("Grade", student?.grade?.toString())
					}

					Spacer(Modifier.height(12.dp))

					// About
					SectionCard(title = "About") {
						InfoRow("Bio", student?.bio)
						InfoRow("Interests", student?.interests)
					}
				}
				else -> Text("Not a student profile.")
			}
		}
	}
}
package com.internconnect.internconnectfrontendclient.ui.screen.companymember

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
fun CompanyMemberProfileScreen(onBack: () -> Unit) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()
	LaunchedEffect(Unit) { vm.load() }

	Surface(modifier = Modifier.fillMaxSize()) {
		Column(Modifier.fillMaxSize().padding(16.dp)) {
			Header(title = "Profile", onBack = onBack)
			Spacer(Modifier.height(16.dp))

			when {
				state.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
				state.error != null -> Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
				state is ProfileUiState.CompanyMemberState -> {
					val companyMember = (state as ProfileUiState.CompanyMemberState).data
					ElevatedCard(shape = MaterialTheme.shapes.extraLarge, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
						Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
							Row(verticalAlignment = Alignment.CenterVertically) {
								val initials = companyMember?.user?.firstName?.first().toString() + " " + companyMember?.user?.lastName?.first().toString()
								val fullName = companyMember?.user?.firstName + " " + companyMember?.user?.lastName
								Box(
									modifier = Modifier.size(64.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
									contentAlignment = Alignment.Center
								) {
									Text(initials, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
								}
								Spacer(Modifier.width(16.dp))
								Column(Modifier.weight(1f)) {
									Text(text = fullName, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold))
									Text(text = companyMember?.user?.email ?: "", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
								}
							}
							Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
								StatusChip(text = companyMember?.user?.role ?: "COMPANY_MEMBER")
								if (companyMember?.user?.isEmailVerified == true) StatusChip(text = "Verified", container = MaterialTheme.colorScheme.tertiaryContainer)
								companyMember?.user?.status?.takeIf { it.isNotBlank() }?.let { StatusChip(text = it) }
							}
						}
					}

					Spacer(Modifier.height(16.dp))

					// Company
					SectionCard(title = "Company") {
						InfoRow("Company", companyMember?.company?.name)
						InfoRow("Industry", companyMember?.company?.industry)
						InfoRow("Role", companyMember?.role)
						InfoRow("Member status", companyMember?.status)
					}

					Spacer(Modifier.height(12.dp))

					// Location & Meta
					SectionCard(title = "Location & Meta") {
						InfoRow("Country", companyMember?.company?.country ?: "Unknown")
						InfoRow("City", companyMember?.company?.city ?: "Unknown")
						InfoRow("Joined", companyMember?.joinedAt ?: "Unknown")
						InfoRow("Website", companyMember?.company?.website ?: "Unknown")
					}

					Spacer(Modifier.height(12.dp))

					// About
					SectionCard(title = "About") {
						InfoRow("About", companyMember?.company?.about)
					}
				}
				else -> Text("Not a company member profile.")
			}
		}
	}
}
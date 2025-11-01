package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileUiState
import com.internconnect.internconnectfrontendclient.domain.viewmodel.ProfileViewModel
import org.koin.compose.koinInject

@Composable
private fun LabeledValue(label: String, value: String?) {
	Column(Modifier.fillMaxWidth()) {
		Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
		Spacer(Modifier.height(2.dp))
		Text(value.takeUnless { it.isNullOrBlank() } ?: "-", style = MaterialTheme.typography.bodyLarge)
	}
}

@Composable
private fun ProfileHeader(
	title: String,
	subtitle: String?,
	role: String?,
	verified: Boolean?
) {
	Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
		Box(
			Modifier
				.size(64.dp)
				.clip(CircleShape)
				.background(MaterialTheme.colorScheme.primary),
			contentAlignment = Alignment.Center
		) {
			Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
		}
		Spacer(Modifier.width(16.dp))
		Column(Modifier.weight(1f)) {
			Text(title, style = MaterialTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
			if (!subtitle.isNullOrBlank()) {
				Text(
					subtitle,
					style = MaterialTheme.typography.bodyMedium,
					color = MaterialTheme.colorScheme.onSurfaceVariant,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
			}
			Spacer(Modifier.height(6.dp))
			Row(verticalAlignment = Alignment.CenterVertically) {
				if (!role.isNullOrBlank()) {
					Surface(
						color = MaterialTheme.colorScheme.secondaryContainer,
						contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
						shape = RoundedCornerShape(100)
					) {
						Text(
							text = role,
							modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
							style = MaterialTheme.typography.labelMedium,
							fontWeight = FontWeight.Medium
						)
					}
				}
				if (verified == true) {
					Spacer(Modifier.width(8.dp))
					Row(verticalAlignment = Alignment.CenterVertically) {
						Icon(
							imageVector = Icons.Filled.CheckCircle,
							contentDescription = null,
							tint = MaterialTheme.colorScheme.primary,
							modifier = Modifier.size(18.dp)
						)
						Spacer(Modifier.width(4.dp))
						Text(
							"Verified",
							style = MaterialTheme.typography.labelMedium,
							color = MaterialTheme.colorScheme.primary
						)
					}
				}
			}
		}
	}
}

@Composable
private fun SectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
	ElevatedCard(
		modifier = Modifier.fillMaxWidth(),
		shape = RoundedCornerShape(16.dp)
	) {
		Column(Modifier.padding(16.dp)) {
			Text(title, style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(8.dp))
			content()
		}
	}
}

@Composable
private fun StudentProfileContent(state: ProfileUiState.StudentState) {
	val student = state.data
	Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
		ElevatedCard(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
			Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
				ProfileHeader(
					title = student?.fullName ?: "Student",
					subtitle = student?.email,
					role = student?.userRole,
					verified = student?.isEmailVerified
				)
			}
		}

		SectionCard(title = "About") {
			Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
				LabeledValue("School", student?.schoolName)
				LabeledValue("Grade", student?.grade.toString())
				LabeledValue("Bio", student?.bio)
				LabeledValue("Interests", student?.interests)
				LabeledValue("Avatar URL", student?.avatarUrl)
			}
		}
	}
}

@Composable
private fun CompanyMemberProfileContent(state: ProfileUiState.CompanyMemberState) {
	val companyMember = state.data
	Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
		ElevatedCard(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
			Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
				ProfileHeader(
					title = companyMember?.userFullName ?: "Company Member",
					subtitle = companyMember?.userEmail,
					role = companyMember?.userRole,
					verified = companyMember?.isEmailVerified
				)
			}
		}

		SectionCard(title = "Membership") {
			Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
				LabeledValue("Status", companyMember?.userStatus)
				LabeledValue("Joined", companyMember?.joinedAt)
			}
		}

		SectionCard(title = "Company") {
			Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
				LabeledValue("Industry", companyMember?.companyIndustry)
				LabeledValue("Role", companyMember?.companyMemberRole)
				LabeledValue("Member Status", companyMember?.companyMemberStatus)
				LabeledValue("Website", companyMember?.website)
				LabeledValue("Logo URL", companyMember?.logoUrl)
				LabeledValue("HQ Country", companyMember?.hqCountry)
				LabeledValue("City", companyMember?.city)
				LabeledValue("About", companyMember?.about)
			}
		}
	}
}

@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
	val vm: ProfileViewModel = koinInject()
	val state by vm.uiState.collectAsState()

	LaunchedEffect(Unit) { vm.load() }

	val scroll = rememberScrollState()

	Column(Modifier
		.fillMaxSize()
		.background(MaterialTheme.colorScheme.secondaryContainer)
	) {
		Spacer(Modifier.height(48.dp))
		IconButton(onClick = onNavigateBack) {
			Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
		}
		Box(Modifier.fillMaxSize().padding(4.dp)) {
			when {
				state.loading -> {
					Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
						CircularProgressIndicator()
					}
				}

				state.error != null -> {
					Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
						Text(
							"Error: ${state.error}",
							color = MaterialTheme.colorScheme.error,
							style = MaterialTheme.typography.bodyLarge
						)
					}
				}

				else -> {
					Column(
						Modifier
							.fillMaxSize()
							.verticalScroll(scroll)
							.padding(16.dp),
						verticalArrangement = Arrangement.spacedBy(16.dp)
					) {
						when (state.role) {
							"STUDENT" -> StudentProfileContent(state = state as ProfileUiState.StudentState)
							"COMPANY_MEMBER" -> CompanyMemberProfileContent(state = state as ProfileUiState.CompanyMemberState)
							null -> {
								ElevatedCard(Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
									Column(
										Modifier.padding(16.dp),
										horizontalAlignment = Alignment.CenterHorizontally
									) {
										Text("No user info available", style = MaterialTheme.typography.titleMedium)
										Spacer(Modifier.height(4.dp))
										Text(
											"We couldn’t find any profile data.",
											style = MaterialTheme.typography.bodyMedium,
											color = MaterialTheme.colorScheme.onSurfaceVariant
										)
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
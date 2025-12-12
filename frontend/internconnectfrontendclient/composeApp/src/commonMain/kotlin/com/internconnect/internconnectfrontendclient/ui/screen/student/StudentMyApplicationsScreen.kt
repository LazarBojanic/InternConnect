package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.ui.components.ApplicationStatusChip
import com.internconnect.internconnectfrontendclient.ui.components.Header


@Composable
fun StudentMyApplicationsScreen(
	applications: List<InternshipApplicationJoined>,
	onBack: () -> Unit,
	onExploreInternships: () -> Unit,
) {
	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Spacer(Modifier.height(16.dp))

		LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
			items(applications) { internshipApplication ->
				ElevatedCard(shape = MaterialTheme.shapes.large) {
					Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
						Text(internshipApplication.internship.title, style = MaterialTheme.typography.titleMedium)
						Text(internshipApplication.internship.company.name, style = MaterialTheme.typography.bodyMedium)
						Text("${internshipApplication.internship.company.country}, ${internshipApplication.internship.company.city}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
						ApplicationStatusChip(internshipApplication.status)
					}
				}
			}
		}

		Spacer(Modifier.height(12.dp))
		Button(onClick = onExploreInternships, modifier = Modifier.fillMaxWidth()) { Text("Explore new internships") }
	}
}
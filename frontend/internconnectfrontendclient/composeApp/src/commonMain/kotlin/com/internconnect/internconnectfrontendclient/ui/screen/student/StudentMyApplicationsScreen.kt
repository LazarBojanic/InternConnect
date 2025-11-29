package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.dto.ApplicationDto
import com.internconnect.internconnectfrontendclient.ui.components.ApplicationStatusChip
import com.internconnect.internconnectfrontendclient.ui.components.Header


@Composable
fun StudentMyApplicationsScreen(
	applications: List<ApplicationDto>,
	onBack: () -> Unit,
	onExploreInternships: () -> Unit,
) {
	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "My Applications", onBack = onBack)
		Spacer(Modifier.height(16.dp))

		LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
			items(applications) { app ->
				ElevatedCard(shape = MaterialTheme.shapes.large) {
					Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
						Text(app.position, style = MaterialTheme.typography.titleMedium)
						Text(app.company, style = MaterialTheme.typography.bodyMedium)
						Text("${app.country}, ${app.city}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
						ApplicationStatusChip(app.status)
					}
				}
			}
		}

		Spacer(Modifier.height(12.dp))
		Button(onClick = onExploreInternships, modifier = Modifier.fillMaxWidth()) { Text("Explore new internships") }
	}
}
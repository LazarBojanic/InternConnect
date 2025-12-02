package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberApplicationDetailsViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import org.koin.compose.koinInject

@Composable
fun CompanyMemberApplicationDetailsScreen(
	applicationId: String,
	internshipId: String,
	onBack: () -> Unit,
) {
	val vm: CompanyMemberApplicationDetailsViewModel = koinInject()
	val state by vm.state.collectAsState()
	var useDummy by remember { mutableStateOf(true) }

	LaunchedEffect(applicationId, internshipId, useDummy) {
		vm.setUseDummy(useDummy)
		vm.load(internshipId, applicationId)
	}

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Application Details", onBack = onBack)
		Spacer(Modifier.height(12.dp))
		/*Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
			Text("Dummy data")
			Switch(checked = useDummy, onCheckedChange = { useDummy = it })
		}*/
		Spacer(Modifier.height(8.dp))

		if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
		state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

		state.application?.let { app ->
			// Candidate Info
			Text("Candidate Info", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(6.dp))
			Text(app.student.user.firstName + " " + app.student.user.lastName)
			Text(app.student.user.email)
			Spacer(Modifier.height(12.dp))

			// Application Info
			Text("Application Info", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(6.dp))
			Text("Internship: ${app.internship.title}")
			Text("Company: ${app.internship.company.name}")
			Text("Status: ${app.status.name}")
			Spacer(Modifier.height(12.dp))

			// Resume placeholder
			Text("Resume", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(6.dp))
			OutlinedCard(Modifier.fillMaxWidth().height(120.dp)) {
				Box(Modifier.fillMaxSize().padding(12.dp)) { Text("Resume preview placeholder") }
			}
			Spacer(Modifier.height(12.dp))

			// Interview notes
			Text("Interview Notes", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(6.dp))
			var notes by remember { mutableStateOf("") }
			OutlinedTextField(value = notes, onValueChange = { notes = it }, modifier = Modifier.fillMaxWidth().height(120.dp))
			Spacer(Modifier.height(12.dp))

			// Status + Message
			Text("Set Status", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(6.dp))
			var selected by remember(app.id, app.status) { mutableStateOf(app.status.name) }
			Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
				StatusRadio(label = "APPLIED", selected = selected, onSelect = {
					selected = it; vm.updateStatus(app.id, it)
				})
				StatusRadio(label = "ACCEPTED", selected = selected, onSelect = {
					selected = it; vm.updateStatus(app.id, it)
				})
				StatusRadio(label = "REJECTED", selected = selected, onSelect = {
					selected = it; vm.updateStatus(app.id, it)
				})
			}
			Spacer(Modifier.height(8.dp))
			OutlinedButton(onClick = { /* unimplemented */ }, enabled = false) { Text("Message") }
		}
	}
}
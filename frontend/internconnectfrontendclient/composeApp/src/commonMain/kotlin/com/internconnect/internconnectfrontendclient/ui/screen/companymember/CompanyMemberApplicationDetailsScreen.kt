package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
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

	Column(Modifier.fillMaxSize()) {

		Column(Modifier.fillMaxSize().padding(16.dp)) {
			if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
			state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

			state.application?.let { app ->
				Text("Candidate Info", style = MaterialTheme.typography.titleMedium)
				Spacer(Modifier.height(6.dp))
				Text(app.student.user.firstName + " " + app.student.user.lastName)
				Text(app.student.user.email)
				Spacer(Modifier.height(12.dp))

				Text("Application Info", style = MaterialTheme.typography.titleMedium)
				Spacer(Modifier.height(6.dp))
				Text("Internship: ${app.internship.title}")
				Text("Company: ${app.internship.company.name}")
				Text("Status: ${app.status.name}")
				Spacer(Modifier.height(12.dp))

				Text("Resume", style = MaterialTheme.typography.titleMedium)
				Spacer(Modifier.height(6.dp))
				OutlinedCard(Modifier.fillMaxWidth().height(120.dp)) {
					Box(Modifier.fillMaxSize().padding(12.dp)) { Text("Resume preview placeholder") }
				}
				Spacer(Modifier.height(12.dp))

				Text("Interview Notes", style = MaterialTheme.typography.titleMedium)
				Spacer(Modifier.height(6.dp))
				var notes by remember { mutableStateOf("") }
				OutlinedTextField(value = notes, onValueChange = { notes = it }, modifier = Modifier.fillMaxWidth().height(120.dp))
				Spacer(Modifier.height(12.dp))

				Text("Set Status", style = MaterialTheme.typography.titleMedium)
				Spacer(Modifier.height(6.dp))
				var selected by remember(app.id, app.status) { mutableStateOf(app.status.name) }
				Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
					StatusRadio(label = "APPLIED", selected = selected, onSelect = { selected = it; vm.updateStatus(app.id, it) }, modifier = Modifier.weight(1f))
					StatusRadio(label = "ACCEPTED", selected = selected, onSelect = { selected = it; vm.updateStatus(app.id, it) }, modifier = Modifier.weight(1f))
					StatusRadio(label = "REJECTED", selected = selected, onSelect = { selected = it; vm.updateStatus(app.id, it) }, modifier = Modifier.weight(1f))
				}
				Spacer(Modifier.height(8.dp))
				OutlinedButton(onClick = { /* unimplemented */ }, enabled = false) { Text("Message") }
			}
		}
	}
}

@Composable
private fun StatusRadio(label: String, selected: String, onSelect: (String) -> Unit, modifier: Modifier = Modifier) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		RadioButton(selected = selected == label, onClick = { onSelect(label) })
		Spacer(Modifier.width(4.dp))
		Text(label, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.labelSmall)
	}
}
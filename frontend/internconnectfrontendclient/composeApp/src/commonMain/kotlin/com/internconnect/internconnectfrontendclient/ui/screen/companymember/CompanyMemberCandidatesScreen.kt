package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberCandidatesViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import org.koin.compose.koinInject

@Composable
fun CompanyMemberCandidatesScreen(
	internshipId: String,
	onBack: () -> Unit,
	onOpenDetails: (applicationId: String) -> Unit,
) {
	val vm: CompanyMemberCandidatesViewModel = koinInject()
	val state by vm.state.collectAsState()
	var useDummy by remember { mutableStateOf(true) }

	LaunchedEffect(internshipId, useDummy) { vm.setUseDummy(useDummy); vm.load(internshipId) }

	Column(Modifier.fillMaxSize()) {

		Column(Modifier.fillMaxSize().padding(16.dp)) {
			if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
			state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

			LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()) {
				items(state.applications) { app -> CandidateRow(
					app = app,
					onUpdate = { status -> vm.updateStatus(app.id, status) },
					onOpenDetails = { onOpenDetails(app.id) }
				) }
			}
		}
	}
}

@Composable
private fun CandidateRow(app: InternshipApplicationJoined, onUpdate: (String) -> Unit, onOpenDetails: () -> Unit) {
	ElevatedCard(Modifier.fillMaxWidth().clickable { onOpenDetails() }) {
		Column(Modifier.fillMaxWidth().padding(12.dp)) {
			Text(app.student.user.firstName + " " + app.student.user.lastName, style = MaterialTheme.typography.titleMedium)
			Text(app.student.user.email)
			Spacer(Modifier.height(6.dp))
			Text("Status: ${app.status.name}")
			Spacer(Modifier.height(6.dp))
			var selected by remember(app.id, app.status) { mutableStateOf(app.status.name) }
			Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
				StatusRadio(label = "APPLIED", selected = selected, onSelect = { selected = it; onUpdate(it) }, modifier = Modifier.weight(1f))
				StatusRadio(label = "ACCEPTED", selected = selected, onSelect = { selected = it; onUpdate(it) }, modifier = Modifier.weight(1f))
				StatusRadio(label = "REJECTED", selected = selected, onSelect = { selected = it; onUpdate(it) }, modifier = Modifier.weight(1f))
			}
			Spacer(Modifier.height(4.dp))
			OutlinedButton(onClick = { /* message (unimplemented) */ }, enabled = false) { Text("Message") }
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
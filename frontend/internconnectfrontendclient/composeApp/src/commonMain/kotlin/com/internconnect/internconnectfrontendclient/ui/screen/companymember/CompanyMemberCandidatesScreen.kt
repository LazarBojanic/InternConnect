package com.internconnect.internconnectfrontendclient.ui.screen.companymember
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipApplicationJoined
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberCandidatesViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import org.koin.compose.koinInject
@Composable
fun CompanyMemberCandidatesScreen(
	internshipId: String,
	onBack: () -> Unit,
) {
	val vm: CompanyMemberCandidatesViewModel = koinInject()
	val state by vm.state.collectAsState()
	var useDummy by remember { mutableStateOf(true) }

	LaunchedEffect(internshipId, useDummy) { vm.setUseDummy(useDummy); vm.load(internshipId) }

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Candidates", onBack = onBack)
		Spacer(Modifier.height(12.dp))
		/*Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
			Text("Dummy data")
			Switch(checked = useDummy, onCheckedChange = { useDummy = it })
		}*/
		Spacer(Modifier.height(8.dp))

		if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
		state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

		LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()) {
			items(state.applications) { app -> CandidateRow(app = app, onUpdate = { status -> vm.updateStatus(app.id, status) }) }
		}
	}
}

@Composable
private fun CandidateRow(app: InternshipApplicationJoined, onUpdate: (String) -> Unit) {
	ElevatedCard {
		Column(Modifier.fillMaxWidth().padding(12.dp)) {
			Text(app.student.user.firstName + " " + app.student.user.lastName, style = MaterialTheme.typography.titleMedium)
			Text(app.student.user.email)
			Spacer(Modifier.height(6.dp))
			Text("Status: ${app.status.name}")
			Spacer(Modifier.height(6.dp))
			Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
				Button(onClick = { onUpdate("APPROVED") }) { Text("Approve") }
				OutlinedButton(onClick = { onUpdate("REJECTED") }) { Text("Reject") }
			}
		}
	}
}
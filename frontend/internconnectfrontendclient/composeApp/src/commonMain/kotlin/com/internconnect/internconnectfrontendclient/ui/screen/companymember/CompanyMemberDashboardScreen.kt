package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberDashboardViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.InternshipCard
import com.internconnect.internconnectfrontendclient.ui.components.StatPad
import org.koin.compose.koinInject

@Composable
fun CompanyMemberDashboardScreen(
	onBack: () -> Unit,
	onOpenCandidates: (internshipId: String) -> Unit,
	onOpenDetails: (internshipId: String) -> Unit,
) {
	val vm: CompanyMemberDashboardViewModel = koinInject()
	val state by vm.state.collectAsState()
	var useDummy by remember { mutableStateOf(true) }

	LaunchedEffect(useDummy) { vm.setUseDummy(useDummy); vm.load() }

	Column(Modifier.fillMaxSize()) {

		Column(Modifier.fillMaxSize().padding(16.dp)) {
			Spacer(Modifier.height(8.dp))
			Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
				StatPad(label = "Posted Internships", value = state.postedCount)
				StatPad(label = "Active Applications", value = state.activeApplications)
				StatPad(label = "Pending Reviews", value = state.pendingReviews)
			}

			Spacer(Modifier.height(16.dp))

			when {
				state.loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
				state.error != null -> Text(state.error!!, color = MaterialTheme.colorScheme.error)
				else -> LazyColumn(
					verticalArrangement = Arrangement.spacedBy(12.dp),
					modifier = Modifier.fillMaxSize()
				) {
					items(state.internships) { item ->
						InternshipCard(
							internship = item,
							onApply = null, // company members don’t apply
							onDetails = { onOpenDetails(item.id) },
							onCandidates = { onOpenCandidates(item.id) },
							modifier = Modifier.fillMaxWidth()
						)
					}
				}
			}
		}
	}
}
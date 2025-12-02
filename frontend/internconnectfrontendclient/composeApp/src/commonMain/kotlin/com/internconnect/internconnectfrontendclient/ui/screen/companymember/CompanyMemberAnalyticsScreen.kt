package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.domain.viewmodel.CompanyMemberAnalyticsViewModel
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.InternshipCard
import com.internconnect.internconnectfrontendclient.ui.components.StatPad
import org.koin.compose.koinInject

@Composable
fun CompanyMemberAnalyticsScreen(onBack: () -> Unit) {
	val vm: CompanyMemberAnalyticsViewModel = koinInject()
	val state by vm.state.collectAsState()
	var useDummy by remember { mutableStateOf(true) }

	LaunchedEffect(useDummy) { vm.setUseDummy(useDummy); vm.load() }

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Analytics", onBack = onBack)
		Spacer(Modifier.height(12.dp))
		/*Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
			Text("Dummy data")
			Switch(checked = useDummy, onCheckedChange = { useDummy = it })
		}*/
		Spacer(Modifier.height(8.dp))

		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
			StatPad("Total Applicants", state.totalApplicants)
			StatPad("Accepted", state.byStatus["ACCEPTED"] ?: 0)
			StatPad("Pending", state.byStatus["APPLIED"] ?: 0)
		}
		Spacer(Modifier.height(16.dp))

		if (state.loading) LinearProgressIndicator(Modifier.fillMaxWidth())
		state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

		Spacer(Modifier.height(8.dp))
		Text("Statistics", style = MaterialTheme.typography.titleMedium)
		Spacer(Modifier.height(8.dp))
		// Graph placeholder
		OutlinedCard(Modifier.fillMaxWidth().height(160.dp)) { Box(Modifier.fillMaxSize().padding(12.dp)) { Text("Graph placeholder") } }

		Spacer(Modifier.height(16.dp))
		Text("Top internships", style = MaterialTheme.typography.titleMedium)
		Spacer(Modifier.height(8.dp))
		LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()) {
			items(state.topInternships) { item ->
				InternshipCard(internship = item, onApply = null, modifier = Modifier.fillMaxWidth())
			}
		}
	}
}
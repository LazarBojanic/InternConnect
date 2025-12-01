package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.InternshipCard

@Composable
fun StudentSavedInternshipsScreen(
	saved: List<InternshipJoined>,
	onBack: () -> Unit,
	onOpenDetails: (id: String) -> Unit,
) {
	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Saved Internships", onBack = onBack)
		Spacer(Modifier.height(16.dp))

		LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxSize()) {
			items(saved) { item ->
				InternshipCard(
					item,
					onApply = { onOpenDetails(item.id) },
					modifier = Modifier.fillMaxWidth()
				)
			}
		}
	}
}
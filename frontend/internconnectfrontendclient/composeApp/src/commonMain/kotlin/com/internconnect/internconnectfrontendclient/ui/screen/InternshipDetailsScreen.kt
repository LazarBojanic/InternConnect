package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.ui.components.Header

@Composable
fun InternshipDetailsScreen(
	internship: InternshipJoined?,
	onBack: () -> Unit,
	// Now nullable: if null, the Apply button is hidden
	onApply: ((String) -> Unit)?
) {
	Column(Modifier.fillMaxSize()) {
		Column(Modifier.fillMaxSize().padding(16.dp)) {
			if (internship == null) {
				Text("No internship selected", style = MaterialTheme.typography.bodyMedium)
				return@Column
			}

			Text(internship.title, style = MaterialTheme.typography.headlineSmall)
			Spacer(Modifier.height(4.dp))
			Text(internship.company.name, style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(12.dp))
			Text(internship.description, style = MaterialTheme.typography.bodyMedium)
			Spacer(Modifier.height(24.dp))

			if (onApply != null) {
				Button(onClick = { onApply.invoke(internship.id) }) {
					Text("Apply")
				}
			}
		}
	}
}
package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.ui.components.Header

@Composable
fun StudentMakeInternshipApplicationScreen(
	internship: InternshipJoined?,
	onBack: () -> Unit,
	onSubmit: (resumeText: String?) -> Unit,
) {
	var resumeText by remember { mutableStateOf("") }

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Spacer(Modifier.height(16.dp))

		if (internship == null) {
			Text("Not found", color = MaterialTheme.colorScheme.error)
			return
		}

		// Context header
		Text(internship.title, style = MaterialTheme.typography.titleMedium)
		Text(internship.company.name, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

		Spacer(Modifier.height(24.dp))
		Text("Resume", style = MaterialTheme.typography.titleMedium)
		Spacer(Modifier.height(8.dp))
		OutlinedTextField(
			value = resumeText,
			onValueChange = { resumeText = it },
			label = { Text("Paste a resume URL or summary (temp)") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(Modifier.height(8.dp))
		OutlinedButton(onClick = { /* file picker to come */ }) { Text("Upload file (coming soon)") }

		Spacer(Modifier.height(24.dp))
		Button(onClick = { onSubmit(resumeText.ifBlank { null }) }, modifier = Modifier.fillMaxWidth()) {
			Text("Submit Application")
		}
	}
}
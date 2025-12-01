package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.data.model.joined.InternshipJoined
import com.internconnect.internconnectfrontendclient.ui.components.Header

@Composable
fun StudentInternshipDetailsScreen(
	internship: InternshipJoined?,
	onBack: () -> Unit,
	onUploadResume: () -> Unit = {}, // Placeholder for a real picker
	onApply: (resumeText: String?) -> Unit,
) {
	var resumeText by remember { mutableStateOf("") }

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Internship Details", onBack = onBack)
		Spacer(Modifier.height(16.dp))

		if (internship == null) {
			Text("Not found", color = MaterialTheme.colorScheme.error)
			return
		}

		Text(internship.title, style = MaterialTheme.typography.headlineSmall)
		Text(internship.company.name, style = MaterialTheme.typography.titleMedium)
		Text("${internship.company.country}, ${internship.company.city}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
		Spacer(Modifier.height(8.dp))
		Text(internship.description, style = MaterialTheme.typography.bodyLarge)

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
		OutlinedButton(onClick = onUploadResume) { Text("Upload file (coming soon)") }

		Spacer(Modifier.height(24.dp))
		Button(onClick = { onApply(resumeText.ifBlank { null }) }, modifier = Modifier.fillMaxWidth()) {
			Text("Apply")
		}
	}
}
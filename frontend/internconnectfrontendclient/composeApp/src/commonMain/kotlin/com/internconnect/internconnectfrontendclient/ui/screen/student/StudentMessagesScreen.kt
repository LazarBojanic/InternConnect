package com.internconnect.internconnectfrontendclient.ui.screen.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.ImageStub

@Composable
fun StudentMessagesScreen(onBack: () -> Unit) {
	Column(Modifier.fillMaxSize()) {
		Header(title = "Messages", onBack = onBack)
		Column(Modifier.fillMaxSize().padding(16.dp)) {
			Text("Messages", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(8.dp))
			ImageStub(label = "Messages Screen Placeholder (drop screenshot here)")
		}
	}
}
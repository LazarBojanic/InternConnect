package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.ui.components.Header
import com.internconnect.internconnectfrontendclient.ui.components.ImageStub
import internconnectfrontendclient.composeapp.generated.resources.Res
import internconnectfrontendclient.composeapp.generated.resources.internconnect_logo
import internconnectfrontendclient.composeapp.generated.resources.messages

@Composable
fun CompanyMemberMessagesScreen(onBack: () -> Unit) {
	Column(Modifier.fillMaxSize()) {
		Column(Modifier.fillMaxSize().padding(16.dp)) {
			Text("Messages", style = MaterialTheme.typography.titleMedium)
			Spacer(Modifier.height(8.dp))
			// Replace with your real message screenshot resource when available
			ImageStub(
				resource = Res.drawable.messages,
				contentDescription = "Company Messages Screen Placeholder"
			)
		}
	}
}
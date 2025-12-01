package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.ui.components.Header

@Composable
fun CompanyMemberMessagesScreen(onBack: () -> Unit) {
	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Header(title = "Messages", onBack = onBack)
		Spacer(Modifier.height(16.dp))
		Text("Messages coming soon…")
	}
}
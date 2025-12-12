package com.internconnect.internconnectfrontendclient.ui.screen.companymember

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.internconnect.internconnectfrontendclient.ui.components.Header
import internconnectfrontendclient.composeapp.generated.resources.Res
import internconnectfrontendclient.composeapp.generated.resources.internconnect_logo
import internconnectfrontendclient.composeapp.generated.resources.messages
import org.jetbrains.compose.resources.painterResource

@Composable
fun CompanyMemberMessagesScreen(onBack: () -> Unit) {
	Column(Modifier.fillMaxSize()) {
		Column(Modifier.fillMaxSize().padding(16.dp)) {
			Spacer(Modifier.height(8.dp))
			// Replace with your real message screenshot resource when available
			val painter = painterResource(Res.drawable.messages)
			Image(
				painter = painter, contentDescription = "Company Member Messages Screen Placeholder",
				modifier = Modifier.fillMaxSize(),
				alignment = Alignment.Center,
				contentScale = ContentScale.FillBounds
			)
		}
	}
}
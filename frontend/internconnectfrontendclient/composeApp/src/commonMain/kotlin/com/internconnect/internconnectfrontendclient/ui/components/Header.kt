package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import internconnectfrontendclient.composeapp.generated.resources.Res
import internconnectfrontendclient.composeapp.generated.resources.internconnect_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header(
	title: String,
	onBack: (() -> Unit)? = null
) {
	Spacer(modifier = Modifier.height(36.dp))
	Surface(color = MaterialTheme.colorScheme.secondaryContainer) {
		Column(Modifier.fillMaxWidth()){
			Icon(
				painter = painterResource(Res.drawable.internconnect_logo),
				contentDescription = "InternConnector Logo",
				tint = androidx.compose.ui.graphics.Color.Unspecified,
				modifier = Modifier.size(28.dp)
			)
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 8.dp, vertical = 8.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Start
			) {
				if (onBack != null) {
					IconButton(onClick = onBack) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
					}
				}
				Text(
					text = title,
					style = MaterialTheme.typography.titleLarge,
					modifier = Modifier.padding(start = if (onBack != null) 4.dp else 8.dp)
				)
			}
		}

	}
}
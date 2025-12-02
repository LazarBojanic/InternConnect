package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.*
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
	// Edge-to-edge background via Surface color; content is padded from status bar.
	Surface(
		modifier = Modifier.fillMaxWidth(),
		color = MaterialTheme.colorScheme.secondaryContainer,
		tonalElevation = 0.dp,
		shadowElevation = 0.dp
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.statusBarsPadding()
				.padding(horizontal = 12.dp, vertical = 8.dp)
		) {
			Icon(
				painter = painterResource(Res.drawable.internconnect_logo),
				contentDescription = "InternConnector Logo",
				tint = androidx.compose.ui.graphics.Color.Unspecified,
				modifier = Modifier.size(42.dp)
			)
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp, bottom = 4.dp),
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
					modifier = Modifier.padding(start = if (onBack != null) 4.dp else 0.dp)
				)
			}
		}
	}
}

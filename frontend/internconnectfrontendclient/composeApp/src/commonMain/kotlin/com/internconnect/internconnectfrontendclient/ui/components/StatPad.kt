package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatPad(label: String, value: Int) {
	val yellow = Color(0xFFFFF176)
	Box(
		modifier = Modifier
			.height(84.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(yellow)
			.padding(12.dp)
	) {
		Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
			Text(label, style = MaterialTheme.typography.bodyMedium)
			Spacer(Modifier.height(6.dp))
			Text("$value", style = MaterialTheme.typography.titleLarge)
		}
	}
}
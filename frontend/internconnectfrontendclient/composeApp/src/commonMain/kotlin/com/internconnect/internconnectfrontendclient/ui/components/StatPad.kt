package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.StatPad(label: String, value: Int) {
	val yellow = Color(0xFFFFF176)
	Box(
		modifier = Modifier
			.weight(1f)
			.height(84.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(yellow)
			.then(Modifier) // keep chain flexible
	) {
		Column(modifier = Modifier
			.height(84.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(yellow)
			.padding(12.dp)
		) {
			Text(label, style = MaterialTheme.typography.bodyMedium)
			Spacer(Modifier.height(6.dp))
			Text("$value", style = MaterialTheme.typography.titleLarge)
		}
	}
}
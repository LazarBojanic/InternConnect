package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ActionCard(
	title: String,
	description: String,
	onClick: () -> Unit
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.heightIn(min = 88.dp)
			.clip(RoundedCornerShape(20.dp)),
		color = Color.White,
		tonalElevation = 2.dp,
		shadowElevation = 2.dp
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(WindowInsets.safeDrawing.asPaddingValues())
				/*.padding(16.dp)*/,
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Column(Modifier.weight(1f)) {
				Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
				Spacer(Modifier.height(4.dp))
				Text(description, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF4B5563))
			}
			Button(onClick = onClick, shape = RoundedCornerShape(12.dp)) { Text("Open") }
		}
	}
}
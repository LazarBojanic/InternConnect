package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ImageStub(
	label: String,
	modifier: Modifier = Modifier,
) {
	OutlinedCard(
		modifier = modifier
			.fillMaxWidth()
			.height(200.dp)
	) {
		Box(
			modifier = Modifier
				.background(Color(0xFFF7F7F7))
				.fillMaxWidth()
				.height(200.dp)
				.padding(12.dp),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = label,
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
			)
		}
	}
}
package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BoxButton(
	label: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Surface(
		onClick = onClick,
		modifier = modifier
			.fillMaxWidth()
			.heightIn(min = 56.dp)
			.clip(RoundedCornerShape(12.dp)),
		tonalElevation = 2.dp,
		shadowElevation = 2.dp,
		shape = RoundedCornerShape(12.dp),
		color = MaterialTheme.colorScheme.primary,
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp),
			contentAlignment = Alignment.Center
		) {
			Text(
				text = label,
				style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
				color = MaterialTheme.colorScheme.onPrimary
			)
		}
	}
}
package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SecondaryButton(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
) {
	OutlinedButton(
		onClick = onClick,
		enabled = enabled,
		border = BorderStroke(2.dp, Color.White),
		colors = ButtonDefaults.outlinedButtonColors(
			containerColor = Color.Transparent,
			contentColor = Color.White
		),
		modifier = Modifier
			.fillMaxWidth()
			.defaultMinSize(minHeight = 48.dp)
			/*.padding(horizontal = 16.dp)*/
			.then(modifier),
	) {
		Text(text)
	}
}

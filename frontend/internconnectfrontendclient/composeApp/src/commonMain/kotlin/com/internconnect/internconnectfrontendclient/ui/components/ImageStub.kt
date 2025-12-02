package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageStub(
	resource: DrawableResource,
	contentDescription: String,
	modifier: Modifier = Modifier,
	contentScale: ContentScale = ContentScale.Fit,
) {
	OutlinedCard(
		modifier = modifier
			.fillMaxWidth()
			.height(200.dp)
	) {
		Image(
			painter = painterResource(resource),
			contentDescription = contentDescription,
			modifier = Modifier
				.fillMaxWidth()
				.height(200.dp)
				.padding(8.dp),
			contentScale = contentScale
		)
	}
}
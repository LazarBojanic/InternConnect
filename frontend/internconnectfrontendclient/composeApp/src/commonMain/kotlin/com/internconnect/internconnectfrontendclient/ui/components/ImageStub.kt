package com.internconnect.internconnectfrontendclient.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageStub(
	resource: DrawableResource,
	contentDescription: String,
	modifier: Modifier = Modifier,
	forceContentScale: ContentScale? = null,
) {
	val painter = painterResource(resource)

	// Try to use the painter's intrinsic size to maintain aspect ratio.
	val intrinsic = painter.intrinsicSize
	val ratio: Float? = if (intrinsic.isSpecified && intrinsic.width > 0f && intrinsic.height > 0f) {
		intrinsic.height / intrinsic.width
	} else null

	OutlinedCard(
		modifier = modifier
			.fillMaxWidth()
	) {
		BoxWithConstraints {
			val appliedScale = forceContentScale ?: ContentScale.Fit
			val imgModifier = if (ratio != null) {
				// Preserve aspect ratio -> width determines height
				Modifier
					.fillMaxWidth()
					.then(Modifier.aspectRatio(ratio))
					.padding(8.dp)
			} else {
				// Fallback if intrinsic size is unknown
				Modifier
					.fillMaxWidth()
					.padding(8.dp)
			}

			Image(
				painter = painter,
				contentDescription = contentDescription,
				modifier = imgModifier,
				contentScale = appliedScale
			)
		}
	}
}
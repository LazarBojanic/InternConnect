package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internconnect.internconnectfrontendclient.ui.components.ActionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenProfile: () -> Unit) {

	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(title = { Text("InternConnect") })
		}
	) { padding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(colorScheme.secondaryContainer)
				.padding(padding)
				.padding(horizontal = 20.dp, vertical = 16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Surface(
				modifier = Modifier
					.fillMaxWidth()
					.height(180.dp)
					.clip(RoundedCornerShape(24.dp)),
				color = Color.White.copy(alpha = 0.12f)
			) {
				Box(Modifier.fillMaxSize()) {

					Text(
						text = "Welcome to InternConnect",
						modifier = Modifier.align(Alignment.Center),
						color = Color.White,
						fontSize = 22.sp,
						fontWeight = FontWeight.SemiBold,
						textAlign = TextAlign.Center
					)
				}
			}

			Spacer(Modifier.height(24.dp))

			Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
				ActionCard(
					title = "Profile",
					description = "View your profile information",
					onClick = onOpenProfile
				)

				ActionCard(
					title = "Browse",
					description = "Explore internships and companies",
					onClick = { /* TODO (future) */ }
				)

				ActionCard(
					title = "Messages",
					description = "Connect and chat",
					onClick = { /* TODO (future) */ }
				)
			}
		}
	}
}
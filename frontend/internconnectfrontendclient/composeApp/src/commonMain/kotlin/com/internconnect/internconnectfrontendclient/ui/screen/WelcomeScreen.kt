package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.internconnect.internconnectfrontendclient.ui.components.PrimaryButton
import com.internconnect.internconnectfrontendclient.ui.components.SecondaryButton
import internconnectfrontendclient.composeapp.generated.resources.Res
import internconnectfrontendclient.composeapp.generated.resources.internconnect_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun Welcome(
	onRegister: () -> Unit = {},
	onLogin: () -> Unit = {},
	modifier: Modifier = Modifier,
) {
	val logo = painterResource(Res.drawable.internconnect_logo)

	Surface(color = MaterialTheme.colorScheme.secondaryContainer, modifier = modifier.fillMaxSize()) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 24.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Spacer(Modifier.height(80.dp))

			Image(
				painter = logo,
				contentDescription = "InternConnect logo",
				modifier = Modifier.size(500.dp)
			)

			Spacer(Modifier.height(20.dp))

			Text(
				text = "InternConnect",
				color = MaterialTheme.colorScheme.surface,
				textAlign = TextAlign.Center,
				lineHeight = 34.sp,
				fontSize = 36.sp,
				fontWeight = FontWeight.ExtraBold,
				modifier = Modifier.fillMaxWidth()
			)

			Spacer(Modifier.height(28.dp))

			PrimaryButton(
				text = "Create account",
				onClick = onRegister,
			)

			Spacer(Modifier.height(12.dp))

			SecondaryButton(
				text = "Sign in",
				onClick = onLogin,
			)

			Spacer(Modifier.height(32.dp))
		}
	}
}
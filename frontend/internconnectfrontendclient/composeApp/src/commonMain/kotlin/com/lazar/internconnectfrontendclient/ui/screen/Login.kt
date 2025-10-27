package com.lazar.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Login(
	onLogin: () -> Unit = {},
	modifier: Modifier = Modifier,
){
	Text("Login")
	Button(
		onClick = onLogin,
		enabled = true,
		modifier = Modifier.padding(horizontal = 16.dp, vertical = 50.dp).then(modifier),
	){
		Text("Login")
	}
}
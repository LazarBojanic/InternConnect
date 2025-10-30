package com.internconnect.internconnectfrontendclient.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(){
	Text(
		text = "Home",
		modifier = Modifier
			.fillMaxWidth()
			.padding(20.dp)
	)
}
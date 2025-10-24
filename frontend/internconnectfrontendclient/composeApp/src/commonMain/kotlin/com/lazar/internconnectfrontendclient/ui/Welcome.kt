package com.lazar.internconnectfrontendclient.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.R
import internconnectfrontendclient.composeapp.generated.resources.Res
import internconnectfrontendclient.composeapp.generated.resources.internconnect_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun Welcome(){
	val logo = painterResource(Res.drawable.internconnect_logo)
	Column {
		Image(logo, "Internconnect Logo")
		Text("Dubai Intern Connect")
	}
}
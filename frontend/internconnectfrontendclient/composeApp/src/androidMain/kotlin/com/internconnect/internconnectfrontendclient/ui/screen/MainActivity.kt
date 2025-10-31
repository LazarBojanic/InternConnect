package com.internconnect.internconnectfrontendclient.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.internconnect.internconnectfrontendclient.AndroidApp
import com.internconnect.internconnectfrontendclient.App

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			App()
		}
	}

}
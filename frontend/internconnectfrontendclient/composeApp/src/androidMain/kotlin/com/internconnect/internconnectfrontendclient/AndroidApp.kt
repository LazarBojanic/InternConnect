package com.internconnect.internconnectfrontendclient

import android.app.Application
import com.internconnect.internconnectfrontendclient.di.androidModule
import com.internconnect.internconnectfrontendclient.di.initKoin

class AndroidApp : Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin(
			baseUrl = "http://72.61.23.122:8080",
			androidModule(this)
		)
	}
}
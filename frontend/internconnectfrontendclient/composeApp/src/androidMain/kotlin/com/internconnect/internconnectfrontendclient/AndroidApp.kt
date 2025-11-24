package com.internconnect.internconnectfrontendclient

import android.app.Application

class AndroidApp : Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin(
			baseUrl = "https://srv1092316.hstgr.cloud",
			androidModule(this)
		)
	}
}
package com.internconnect.util

import kotlinx.serialization.json.Json


class Util {
	companion object {
		fun jsonFormat(): Json {
			return Json {
				prettyPrint = true
				isLenient = true
			}
		}
	}
}
package com.internconnect.util

import kotlinx.serialization.json.Json
import java.time.Instant


class Util {
	companion object {
		fun jsonFormat(): Json {
			return Json {
				prettyPrint = true
			}
		}
	}

}
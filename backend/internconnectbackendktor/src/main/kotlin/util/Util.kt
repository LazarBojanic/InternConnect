package com.internconnect.util

import kotlinx.serialization.json.Json
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

class Util {
	companion object {
		@OptIn(ExperimentalTime::class)
		fun getCurrentTime(): LocalDateTime {
			val currentInstant: kotlin.time.Instant = kotlin.time.Clock.System.now()
			return currentInstant.toLocalDateTime(TimeZone.UTC)
		}
		fun jsonFormat(): Json {
			return Json {
				prettyPrint = true
			}
		}
	}

}
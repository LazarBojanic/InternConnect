package com.internconnect.util

import kotlinx.serialization.json.Json
import org.mindrot.jbcrypt.BCrypt
import java.time.Instant


class Util {
	companion object {
		fun jsonFormat(): Json {
			return Json {
				prettyPrint = true
			}
		}
		fun hashPassword(password: String): String {
			return BCrypt.hashpw(password, BCrypt.gensalt())
		}
		fun checkPassword(password: String, hashedPassword: String): Boolean {
			return BCrypt.checkpw(password, hashedPassword)
		}
		fun getCurrentTimestamp(): Long {
			return Instant.now().epochSecond
		}
	}

}
package com.internconnect.internconnectbackendktor.model.raw.internship

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.UUID

@Serializable
data class Internship(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	@Serializable(with = UUIDSerializer::class)
	val companyId: UUID,
	val title: String,
	val category: String,
	val description: String,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object {
		fun createNew(
			companyId: UUID,
			title: String,
			category: String,
			description: String,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		): Internship {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return Internship(
				id = UUID.randomUUID(),
				companyId = companyId,
				title = title,
				category = category,
				description = description,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}
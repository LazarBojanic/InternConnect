package com.internconnect.internconnectbackendktor.model.raw.company

import com.internconnect.internconnectbackendktor.util.ExposedBlobSerializer
import com.internconnect.internconnectbackendktor.util.InstantSerializer
import com.internconnect.internconnectbackendktor.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.*

@Serializable
data class Company(
	@Serializable(with = UUIDSerializer::class)
	val id: UUID,
	val name: String,
	val industry: String,
	val website: String?,
	val logoUrl: String?,
	val country: String?,
	val city: String?,
	val about: String?,
	@Serializable(with = InstantSerializer::class)
	val createdAt: Instant,
	@Serializable(with = InstantSerializer::class)
	val updatedAt: Instant
){
	companion object {
		fun createNew(
			name: String,
			industry: String,
			website: String?,
			logoUrl: String?,
			country: String?,
			city: String?,
			about: String?,
			createdAt: Instant? = null,
			updatedAt: Instant? = null
		) : Company {
			val now = Instant.now()
			val createdAt = createdAt ?: now
			val updatedAt = updatedAt ?: now
			return Company(
				id = UUID.randomUUID(),
				name = name,
				industry = industry,
				website = website,
				logoUrl = logoUrl,
				country = country,
				city = city,
				about = about,
				createdAt = createdAt,
				updatedAt = updatedAt
			)
		}
	}
}
package com.internconnect.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.InetAddress
import java.util.UUID

object InetAddressSerializer : KSerializer<InetAddress> {
	override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("InetAddress", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: InetAddress) = encoder.encodeString(value.toString())

	override fun deserialize(decoder: Decoder): InetAddress = InetAddress.getByName(decoder.decodeString())
}
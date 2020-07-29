package io.fluidsonic.stdlib

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@Serializable(with = CountrySerializer::class)
public /*inline*/ class CountryCode(public val value: String) {

	private val uppercaseValue = value.toUpperCase()


	init {
		freeze()
	}


	override fun equals(other: Any?): Boolean =
		other === this || (other is CountryCode && uppercaseValue == other.uppercaseValue)


	override fun hashCode(): Int =
		uppercaseValue.hashCode()


	override fun toString(): String =
		value
}


@Serializer(forClass = CountryCode::class)
internal object CountryCodeSerializer : KSerializer<CountryCode> {

	override val descriptor = PrimitiveSerialDescriptor("io.fluidsonic.stdlib.CountryCode", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		CountryCode(decoder.decodeString())


	override fun serialize(encoder: Encoder, value: CountryCode) {
		encoder.encodeString(value.value)
	}
}

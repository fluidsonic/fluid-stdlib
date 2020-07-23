package io.fluidsonic.stdlib

import kotlinx.serialization.*


@Serializable(with = CountrySerializer::class)
/*inline*/ class CountryCode(val value: String) {

	private val uppercaseValue = value.toUpperCase()


	init {
		freeze()
	}


	override fun equals(other: Any?) =
		other === this || (other is CountryCode && uppercaseValue == other.uppercaseValue)


	override fun hashCode() =
		uppercaseValue.hashCode()


	override fun toString() =
		value
}


@Serializer(forClass = CountryCode::class)
internal object CountryCodeSerializer : KSerializer<CountryCode> {

	override val descriptor = PrimitiveDescriptor("io.fluidsonic.stdlib.CountryCode", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		CountryCode(decoder.decodeString())


	override fun serialize(encoder: Encoder, value: CountryCode) {
		encoder.encodeString(value.value)
	}
}

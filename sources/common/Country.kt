package io.fluidsonic.stdlib

import io.fluidsonic.stdlib.Country_Static.allCountryCodes
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@Serializable(with = CountrySerializer::class)
public /*inline*/ class Country private constructor(
	public val code: CountryCode
) {

	init {
		freeze()
	}


	override fun equals(other: Any?): Boolean =
		other === this || (other is Country && code == other.code)


	override fun hashCode(): Int =
		code.hashCode()


	public val name: String
		get() = name(Locale.englishInUnitedStates)


	override fun toString(): String =
		name


	public companion object {

		public val all: Collection<Country> = allCountryCodes.map { Country(CountryCode(it.toUpperCase())) }

		private val allByCode = all.associateBy(Country::code)


		public fun byCode(code: CountryCode): Country? =
			allByCode[code]
	}
}


public expect fun Country.name(locale: Locale): String


internal expect object Country_Static {

	val allCountryCodes: Set<String>
}


@Serializer(forClass = Country::class)
internal object CountrySerializer : KSerializer<Country> {

	override val descriptor = PrimitiveSerialDescriptor("io.fluidsonic.stdlib.Country", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			Country.byCode(CountryCode(code)) ?: throw SerializationException("Invalid country code: $code")
		}


	override fun serialize(encoder: Encoder, value: Country) {
		encoder.encodeString(value.code.value)
	}
}

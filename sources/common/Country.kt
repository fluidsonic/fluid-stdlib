package io.fluidsonic.stdlib

import io.fluidsonic.stdlib.Country_Static.allCountryCodes
import kotlinx.serialization.*


@Serializable(with = CountrySerializer::class)
/*inline*/ class Country private constructor(
	val code: String
) {

	init {
		freeze()
	}


	override fun equals(other: Any?) =
		other === this || (other is Country && code == other.code)


	override fun hashCode() =
		code.hashCode()


	val name
		get() = name(Locale.englishInUnitedStates)


	override fun toString() =
		name


	companion object {

		val all: Collection<Country> = allCountryCodes.map { Country(it.toUpperCase()) }

		private val allByCode = all.associateBy(Country::code)


		fun byCode(code: String) =
			allByCode[code.toUpperCase()]
	}
}


expect fun Country.name(locale: Locale): String


internal expect object Country_Static {

	val allCountryCodes: Set<String>
}


@Serializer(forClass = Country::class)
internal object CountrySerializer : KSerializer<Country> {

	override val descriptor = PrimitiveDescriptor("io.fluidsonic.stdlib.Country", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			Country.byCode(code) ?: throw SerializationException("Unknown country code: $code")
		}


	override fun serialize(encoder: Encoder, value: Country) {
		encoder.encodeString(value.code)
	}
}

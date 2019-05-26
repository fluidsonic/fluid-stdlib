package com.github.fluidsonic.fluid.stdlib

import kotlinx.serialization.*
import kotlinx.serialization.internal.*


@Serializable(with = CountrySerializer::class)
/*inline*/ class Country private constructor(
	val code: String
) {

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


internal expect val allCountryCodes: Set<String>


expect fun Country.name(locale: Locale): String


@Serializer(forClass = Country::class)
internal object CountrySerializer : KSerializer<Country> {

	override val descriptor = StringDescriptor.withName("com.github.fluidsonic.fluid.stdlib.Country")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			Country.byCode(code) ?: throw SerializationException("Unknown country code: $code")
		}


	override fun serialize(encoder: Encoder, obj: Country) {
		encoder.encodeString(obj.code)
	}
}

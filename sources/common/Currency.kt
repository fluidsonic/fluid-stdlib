package com.github.fluidsonic.fluid.stdlib

import kotlinx.serialization.*
import kotlinx.serialization.internal.*


@Serializable(with = CurrencySerializer::class)
/*inline*/ class Currency internal constructor(
	val code: String
) {

	override fun equals(other: Any?) =
		other === this || (other is Currency && code == other.code)


	override fun hashCode() =
		code.hashCode()


	val name
		get() = name(Locale.englishInUnitedStates)


	override fun toString() =
		name


	companion object {

		val all: Collection<Currency> = allCurrencyCodes.map { Currency(it.toUpperCase()) }
		private val allByCode = all.associateBy(Currency::code)


		fun byCode(code: String) =
			allByCode[code.toUpperCase()]
	}
}


internal expect val allCurrencyCodes: Set<String>


expect fun Currency.name(locale: Locale): String


@Serializer(forClass = Currency::class)
internal object CurrencySerializer : KSerializer<Currency> {

	override val descriptor = StringDescriptor.withName("com.github.fluidsonic.fluid.stdlib.Currency")


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			Currency.byCode(code) ?: throw SerializationException("Unknown currency code: $code")
		}


	override fun serialize(encoder: Encoder, obj: Currency) {
		encoder.encodeString(obj.code)
	}
}

package com.github.fluidsonic.fluid.stdlib

import com.github.fluidsonic.fluid.stdlib.Currency_Static.allCurrencyCodes
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


expect fun Currency.name(locale: Locale): String


internal expect object Currency_Static {

	val allCurrencyCodes: Set<String>
}


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

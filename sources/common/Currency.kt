package io.fluidsonic.stdlib

import io.fluidsonic.locale.*
import io.fluidsonic.stdlib.Currency_Static.allCurrencyCodes
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


@Serializable(with = CurrencySerializer::class)
public /*inline*/ class Currency internal constructor(
	public val code: String
) {

	init {
		freeze()
	}


	override fun equals(other: Any?): Boolean =
		other === this || (other is Currency && code == other.code)


	override fun hashCode(): Int =
		code.hashCode()


	public val name: String
		get() = name(Locale.enUS)


	override fun toString(): String =
		name


	public companion object {

		public val all: Collection<Currency> = allCurrencyCodes.map { Currency(it.toUpperCase()) }

		private val allByCode = all.associateBy(Currency::code)


		public fun byCode(code: String): Currency? =
			allByCode[code.toUpperCase()]
	}
}


public expect fun Currency.name(locale: Locale): String


internal expect object Currency_Static {

	val allCurrencyCodes: Set<String>
}


@Serializer(forClass = Currency::class)
internal object CurrencySerializer : KSerializer<Currency> {

	override val descriptor = PrimitiveSerialDescriptor("io.fluidsonic.stdlib.Currency", PrimitiveKind.STRING)


	override fun deserialize(decoder: Decoder) =
		decoder.decodeString().let { code ->
			Currency.byCode(code) ?: throw SerializationException("Unknown currency code: $code")
		}


	override fun serialize(encoder: Encoder, value: Currency) {
		encoder.encodeString(value.code)
	}
}

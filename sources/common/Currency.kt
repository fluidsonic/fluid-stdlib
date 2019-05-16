package com.github.fluidsonic.fluid.stdlib


class Currency private constructor(
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

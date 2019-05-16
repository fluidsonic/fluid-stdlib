package com.github.fluidsonic.fluid.stdlib


class Country private constructor(
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

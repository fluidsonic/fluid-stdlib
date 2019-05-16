package com.github.fluidsonic.fluid.stdlib

import java.util.Locale as PlatformLocale


actual val allCountryCodes: Set<String> = PlatformLocale.getISOCountries().toSet()


actual fun Country.name(locale: Locale): String =
	PlatformLocale("", code).getDisplayCountry(locale.platform)

package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*
import platform.Foundation.NSLocale as PlatformLocale


actual fun Country.name(locale: Locale) =
	locale.toPlatform().localizedStringForCountryCode(code) ?: code


internal actual object Country_Static {

	@Suppress("UNCHECKED_CAST")
	actual val allCountryCodes: Set<String> = (PlatformLocale.ISOCountryCodes as List<String>).toHashSet()
}

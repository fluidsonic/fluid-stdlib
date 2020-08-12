package io.fluidsonic.stdlib

import io.fluidsonic.locale.*
import platform.Foundation.*
import platform.Foundation.NSLocale as PlatformLocale


public actual fun Country.name(locale: Locale): String =
	locale.toPlatform().localizedStringForCountryCode(code.value) ?: code.value


internal actual object Country_Static {

	@Suppress("UNCHECKED_CAST")
	actual val allCountryCodes: Set<String> = (PlatformLocale.ISOCountryCodes as List<String>).toHashSet()
}

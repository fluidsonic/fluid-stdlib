package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*
import platform.Foundation.NSLocale as PlatformLocale


@Suppress("UNCHECKED_CAST")
actual val allCountryCodes: Set<String> = (PlatformLocale.ISOCountryCodes as List<String>).toSet()


actual fun Country.name(locale: Locale): String =
	locale.platform.localizedStringForCountryCode(code) ?: code

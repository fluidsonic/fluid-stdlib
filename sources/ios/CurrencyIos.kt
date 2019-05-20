package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*
import platform.Foundation.NSLocale as PlatformLocale


@Suppress("UNCHECKED_CAST")
actual val allCurrencyCodes: Set<String> = (PlatformLocale.ISOCurrencyCodes as List<String>).toHashSet()


actual fun Currency.name(locale: Locale): String =
	locale.platform.localizedStringForCurrencyCode(code) ?: code

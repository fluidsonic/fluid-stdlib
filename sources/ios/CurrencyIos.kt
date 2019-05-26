package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*
import platform.Foundation.NSLocale as PlatformLocale


actual fun Currency.name(locale: Locale) =
	locale.platform.localizedStringForCurrencyCode(code) ?: code


internal actual object Currency_Static {

	@Suppress("UNCHECKED_CAST")
	actual val allCurrencyCodes: Set<String> = (PlatformLocale.ISOCurrencyCodes as List<String>).toHashSet()
}

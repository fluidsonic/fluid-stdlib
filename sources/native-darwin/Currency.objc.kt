package io.fluidsonic.stdlib

import platform.Foundation.*
import platform.Foundation.NSLocale as PlatformLocale


public actual fun Currency.name(locale: Locale): String =
	locale.toPlatform().localizedStringForCurrencyCode(code) ?: code


internal actual object Currency_Static {

	@Suppress("UNCHECKED_CAST")
	actual val allCurrencyCodes: Set<String> = (PlatformLocale.ISOCurrencyCodes as List<String>).toHashSet()
}

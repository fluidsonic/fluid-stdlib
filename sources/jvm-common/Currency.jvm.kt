package io.fluidsonic.stdlib

import java.util.Currency as PlatformCurrency


actual fun Currency.name(locale: Locale): String =
	PlatformCurrency.getInstance(code).getDisplayName(locale.toPlatform())


internal actual object Currency_Static {

	actual val allCurrencyCodes: Set<String> = PlatformCurrency.getAvailableCurrencies().mapTo(hashSetOf()) { it.currencyCode }
}


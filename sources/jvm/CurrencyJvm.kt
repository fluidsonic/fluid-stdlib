package com.github.fluidsonic.fluid.stdlib

import java.util.Currency as PlatformCurrency


actual val allCurrencyCodes: Set<String> = PlatformCurrency.getAvailableCurrencies().mapTo(hashSetOf()) { it.currencyCode }


actual fun Currency.name(locale: Locale): String =
	PlatformCurrency.getInstance(code).getDisplayName(locale.platform)

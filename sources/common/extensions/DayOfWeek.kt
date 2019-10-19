package io.fluidsonic.stdlib

import io.fluidsonic.time.*


expect fun DayOfWeek.displayName(locale: Locale, format: DayOfWeekFormat = DayOfWeekFormat.full): String


enum class DayOfWeekFormat {

	character,            // M T W T F S S
	characterStandalone,  // M T W T F S S
	short,                // Mo Tu We Th Fr Sa Su
	shortStandalone,      // Mo Tu We Th Fr Sa Su
	medium,               // Mon Tue Wed Thu Fri Sat Sun
	mediumStandalone,     // Mon Tue Wed Thu Fri Sat Sun
	full,                 // Monday Tuesday Wednesday Thursday Friday Saturday Sunday
	fullStandalone        // Monday Tuesday Wednesday Thursday Friday Saturday Sunday
}

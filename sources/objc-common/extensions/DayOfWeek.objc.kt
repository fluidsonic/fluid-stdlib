package com.github.fluidsonic.fluid.stdlib

import com.github.fluidsonic.fluid.time.*
import platform.Foundation.*


actual fun DayOfWeek.displayName(locale: Locale, format: DayOfWeekFormat): String {
	val platformLocale = locale.toPlatform()

	val formatter = NSDateFormatter()
	formatter.locale = platformLocale

	@Suppress("UNCHECKED_CAST")
	val names = when (format) {
		DayOfWeekFormat.character -> formatter.veryShortWeekdaySymbols
		DayOfWeekFormat.characterStandalone -> formatter.veryShortStandaloneWeekdaySymbols
		DayOfWeekFormat.full -> formatter.weekdaySymbols
		DayOfWeekFormat.fullStandalone -> formatter.standaloneWeekdaySymbols
		DayOfWeekFormat.medium -> formatter.shortWeekdaySymbols
		DayOfWeekFormat.mediumStandalone -> formatter.shortStandaloneWeekdaySymbols
		DayOfWeekFormat.short -> formatter.shortWeekdaySymbols
		DayOfWeekFormat.shortStandalone -> formatter.shortStandaloneWeekdaySymbols
	} as List<String>

	val name = names[when (this) {
		DayOfWeek.monday -> 1
		DayOfWeek.tuesday -> 2
		DayOfWeek.wednesday -> 3
		DayOfWeek.thursday -> 4
		DayOfWeek.friday -> 5
		DayOfWeek.saturday -> 6
		DayOfWeek.sunday -> 0
	}]

	return when (format) {
		DayOfWeekFormat.short, DayOfWeekFormat.shortStandalone ->
			if (platformLocale.languageCode == "en")
				name.truncatedTo(2)
			else
				name
		else ->
			name
	}
}

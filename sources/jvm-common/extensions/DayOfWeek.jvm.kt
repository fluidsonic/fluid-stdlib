package com.github.fluidsonic.fluid.stdlib

import com.github.fluidsonic.fluid.time.*


actual fun DayOfWeek.displayName(locale: Locale, format: DayOfWeekFormat): String {
	val textStyle = when (format) {
		DayOfWeekFormat.character -> PlatformTextStyle.NARROW
		DayOfWeekFormat.characterStandalone -> PlatformTextStyle.NARROW_STANDALONE
		DayOfWeekFormat.full -> PlatformTextStyle.FULL
		DayOfWeekFormat.fullStandalone -> PlatformTextStyle.FULL_STANDALONE
		DayOfWeekFormat.medium -> PlatformTextStyle.SHORT
		DayOfWeekFormat.mediumStandalone -> PlatformTextStyle.SHORT_STANDALONE
		DayOfWeekFormat.short -> PlatformTextStyle.SHORT
		DayOfWeekFormat.shortStandalone -> PlatformTextStyle.SHORT_STANDALONE
	}

	val platformLocale = locale.toPlatform()
	val name = toPlatform().getDisplayName(textStyle, platformLocale)!!

	return when (format) {
		DayOfWeekFormat.short, DayOfWeekFormat.shortStandalone ->
			if (platformLocale.language == "en")
				name.truncatedTo(2)
			else
				name
		else ->
			name
	}
}


fun PlatformDayOfWeek.toCommon() = when (this) {
	PlatformDayOfWeek.MONDAY -> DayOfWeek.monday
	PlatformDayOfWeek.TUESDAY -> DayOfWeek.tuesday
	PlatformDayOfWeek.WEDNESDAY -> DayOfWeek.wednesday
	PlatformDayOfWeek.THURSDAY -> DayOfWeek.thursday
	PlatformDayOfWeek.FRIDAY -> DayOfWeek.friday
	PlatformDayOfWeek.SATURDAY -> DayOfWeek.saturday
	PlatformDayOfWeek.SUNDAY -> DayOfWeek.sunday
}


fun DayOfWeek.toPlatform() =
	when (this) {
		DayOfWeek.monday -> PlatformDayOfWeek.MONDAY
		DayOfWeek.tuesday -> PlatformDayOfWeek.TUESDAY
		DayOfWeek.wednesday -> PlatformDayOfWeek.WEDNESDAY
		DayOfWeek.thursday -> PlatformDayOfWeek.THURSDAY
		DayOfWeek.friday -> PlatformDayOfWeek.FRIDAY
		DayOfWeek.saturday -> PlatformDayOfWeek.SATURDAY
		DayOfWeek.sunday -> PlatformDayOfWeek.SUNDAY
	}

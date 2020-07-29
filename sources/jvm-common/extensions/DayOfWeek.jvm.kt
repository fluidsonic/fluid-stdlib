package io.fluidsonic.stdlib

import io.fluidsonic.time.*


public actual fun DayOfWeek.displayName(locale: Locale, format: DayOfWeekFormat): String {
	val textStyle = when (format) {
		DayOfWeekFormat.character -> PlatformTextStyle.NARROW
		DayOfWeekFormat.characterStandalone ->
			if (System.getProperty("java.version").orEmpty().startsWith("1.8.")) // NARROW_STANDALONE is broken in Java 8
				PlatformTextStyle.NARROW
			else
				PlatformTextStyle.NARROW_STANDALONE
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


public fun PlatformDayOfWeek.toCommon(): DayOfWeek = when (this) {
	PlatformDayOfWeek.MONDAY -> DayOfWeek.monday
	PlatformDayOfWeek.TUESDAY -> DayOfWeek.tuesday
	PlatformDayOfWeek.WEDNESDAY -> DayOfWeek.wednesday
	PlatformDayOfWeek.THURSDAY -> DayOfWeek.thursday
	PlatformDayOfWeek.FRIDAY -> DayOfWeek.friday
	PlatformDayOfWeek.SATURDAY -> DayOfWeek.saturday
	PlatformDayOfWeek.SUNDAY -> DayOfWeek.sunday
}


public fun DayOfWeek.toPlatform(): PlatformDayOfWeek =
	when (this) {
		DayOfWeek.monday -> PlatformDayOfWeek.MONDAY
		DayOfWeek.tuesday -> PlatformDayOfWeek.TUESDAY
		DayOfWeek.wednesday -> PlatformDayOfWeek.WEDNESDAY
		DayOfWeek.thursday -> PlatformDayOfWeek.THURSDAY
		DayOfWeek.friday -> PlatformDayOfWeek.FRIDAY
		DayOfWeek.saturday -> PlatformDayOfWeek.SATURDAY
		DayOfWeek.sunday -> PlatformDayOfWeek.SUNDAY
	}

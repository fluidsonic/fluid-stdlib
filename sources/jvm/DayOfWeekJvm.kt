package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.format.*
import org.threeten.bp.DayOfWeek as PlatformDayOfWeek


val DayOfWeek.platform
	get() = when (this) {
		DayOfWeek.monday -> PlatformDayOfWeek.MONDAY
		DayOfWeek.tuesday -> PlatformDayOfWeek.TUESDAY
		DayOfWeek.wednesday -> PlatformDayOfWeek.WEDNESDAY
		DayOfWeek.thursday -> PlatformDayOfWeek.THURSDAY
		DayOfWeek.friday -> PlatformDayOfWeek.FRIDAY
		DayOfWeek.saturday -> PlatformDayOfWeek.SATURDAY
		DayOfWeek.sunday -> PlatformDayOfWeek.SUNDAY
	}


actual fun DayOfWeek.displayName(locale: Locale): String =
	platform.getDisplayName(TextStyle.FULL_STANDALONE, locale.platform)


fun PlatformDayOfWeek.toCommon() = when (this) {
	PlatformDayOfWeek.MONDAY -> DayOfWeek.monday
	PlatformDayOfWeek.TUESDAY -> DayOfWeek.tuesday
	PlatformDayOfWeek.WEDNESDAY -> DayOfWeek.wednesday
	PlatformDayOfWeek.THURSDAY -> DayOfWeek.thursday
	PlatformDayOfWeek.FRIDAY -> DayOfWeek.friday
	PlatformDayOfWeek.SATURDAY -> DayOfWeek.saturday
	PlatformDayOfWeek.SUNDAY -> DayOfWeek.sunday
}

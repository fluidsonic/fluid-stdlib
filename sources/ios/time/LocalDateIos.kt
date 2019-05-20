package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*


actual val LocalDate.dayOfWeek
	get() = atStartOfDay(TimeZone.utc).dayOfWeek


actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.hour = 0
	components.minute = 0
	components.second = 0
	components.nanosecond = 0
	components.timeZone = timeZone.platform

	return Clock.gregorian.platform.dateFromComponents(components)!!.toCommon()
}


actual fun LocalDate.minusDays(daysToSubtract: Long): LocalDate =
	plusDays(-daysToSubtract)


actual fun LocalDate.plusDays(daysToAdd: Long): LocalDate =
	Clock.gregorian.platform.dateByAddingUnit(NSDayCalendarUnit, value = daysToAdd, toDate = atStartOfDay(timeZone = TimeZone.utc).toPlatform(), options = 0)!!
		.toCommon()
		.toLocalDate(timeZone = TimeZone.utc)


internal fun LocalDate.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = year.value.toLong()
	components.month = month.value.toLong()
	components.day = day.value.toLong()
	return components
}

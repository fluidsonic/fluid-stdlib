package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*


actual val LocalDateTime.dayOfWeek
	get() = atTimeZone(TimeZone.utc).dayOfWeek


actual fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp {
	val components = toPlatformComponents()
	components.timeZone = timeZone.platform

	return Clock.gregorian.platform.dateFromComponents(components)!!.toCommon()
}


internal fun LocalDateTime.toPlatformComponents(): NSDateComponents {
	val components = NSDateComponents()
	components.year = date.year.value.toLong()
	components.month = date.month.value.toLong()
	components.day = date.day.value.toLong()
	components.hour = time.hour.value.toLong()
	components.minute = time.minute.value.toLong()
	components.second = time.second.value.toLong()
	components.nanosecond = time.nanosecond.value.toLong()
	return components
}

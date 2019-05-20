package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*
import platform.Foundation.NSCalendar as PlatformClock


actual class Clock internal constructor(
	val platform: PlatformClock
) {

	actual val timeZone
		get() = platform.timeZone.toCommon()


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? Clock)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		internal val gregorian = PlatformClock.calendarWithIdentifier(NSCalendarIdentifierGregorian)!!.toCommon()

		actual val system = PlatformClock.currentCalendar.toCommon()
	}
}


actual fun Clock.localDate() =
	timestamp().toLocalDate(timeZone)


actual fun Clock.localDateTime() =
	timestamp().toLocalDateTime(timeZone)


actual fun Clock.localTime() =
	timestamp().toLocalTime(timeZone)


actual fun Clock.timestamp() =
	NSDate().toCommon() // TODO we only support system clock for now


actual fun Clock.withTimeZone(timeZone: TimeZone): Clock {
	if (timeZone.platform == platform.timeZone) return this

	val platform = platform.mutableCopy() as PlatformClock
	platform.timeZone = timeZone.platform
	return platform.toCommon()
}


fun PlatformClock.toCommon() =
	Clock(this)

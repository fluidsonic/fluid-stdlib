package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.Clock as PlatformClock
import org.threeten.bp.DayOfWeek as PlatformDayOfWeek
import org.threeten.bp.LocalDate as PlatformLocalDate
import org.threeten.bp.LocalDateTime as PlatformLocalDateTime
import org.threeten.bp.LocalTime as PlatformLocalTime


actual class Clock internal constructor(
	val platform: PlatformClock
) {

	actual val timeZone
		get() = platform.zone.toCommon()


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? Clock)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		actual val system = PlatformClock.systemDefaultZone().toCommon()
	}
}


actual fun Clock.localDate() =
	PlatformLocalDate.now(platform).toCommon()


actual fun Clock.localDateTime() =
	PlatformLocalDateTime.now(platform).toCommon()


actual fun Clock.localTime() =
	PlatformLocalTime.now(platform).toCommon()


actual fun Clock.timestamp() =
	platform.instant().toCommon()


actual fun Clock.withTimeZone(timeZone: TimeZone) =
	platform.withZone(timeZone.platform).toCommon()


fun PlatformClock.toCommon() =
	Clock(this)

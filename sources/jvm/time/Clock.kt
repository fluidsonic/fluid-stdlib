package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.Clock as PlatformClock
import org.threeten.bp.DayOfWeek as PlatformDayOfWeek


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


	actual fun withTimeZone(timeZone: TimeZone) =
		platform.withZone(timeZone.platform).toCommon()


	actual companion object {

		actual val system = PlatformClock.systemDefaultZone().toCommon()
	}
}


fun PlatformClock.toCommon() =
	Clock(this)

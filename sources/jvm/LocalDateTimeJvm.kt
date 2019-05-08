package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.LocalDateTime as PlatformLocalDateTime


actual class LocalDateTime internal constructor(
	val platform: PlatformLocalDateTime
) : Comparable<LocalDateTime> {

	actual val day get() = platform.dayOfMonth
	actual val hour get() = platform.hour
	actual val minute get() = platform.minute
	actual val month get() = platform.month.toCommon()
	actual val second get() = platform.second
	actual val year get() = platform.year


	override fun compareTo(other: LocalDateTime) =
		platform.compareTo(other.platform)


	actual companion object
}


actual fun LocalDateTime(year: Int, month: Month, day: Int, hour: Int, minute: Int, second: Int): LocalDateTime =
	PlatformLocalDateTime.of(year, month.platform, day, hour, minute, second).toCommon()


actual fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp =
	platform.atZone(timeZone.platform).toInstant().toCommon()


fun PlatformLocalDateTime.toCommon() =
	LocalDateTime(this)

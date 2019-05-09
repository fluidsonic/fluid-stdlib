package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.DayOfWeek as PlatformDayOfWeek
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


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? LocalDateTime)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		actual fun now(clock: Clock, timeZone: TimeZone) =
			PlatformLocalDateTime.now(clock.platform.withZone(timeZone.platform)).toCommon()


		actual fun parse(text: CharSequence) =
			runCatching { PlatformLocalDateTime.parse(text).toCommon() }.getOrNull()
	}
}


actual fun LocalDateTime(year: Int, month: Month, day: Int, hour: Int, minute: Int, second: Int): LocalDateTime =
	PlatformLocalDateTime.of(year, month.platform, day, hour, minute, second).toCommon()


actual val LocalDateTime.dayOfWeek: DayOfWeek
	get() = PlatformDayOfWeek.from(platform).toCommon()


actual fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp =
	platform.atZone(timeZone.platform).toInstant().toCommon()


fun PlatformLocalDateTime.toCommon() =
	LocalDateTime(this)

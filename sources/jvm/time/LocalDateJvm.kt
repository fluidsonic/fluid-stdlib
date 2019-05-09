package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.DayOfWeek as PlatformDayOfWeek
import org.threeten.bp.LocalDate as PlatformLocalDate


actual class LocalDate internal constructor(
	val platform: PlatformLocalDate
) : Comparable<LocalDate> {

	actual val day get() = platform.dayOfMonth
	actual val month get() = platform.month.toCommon()
	actual val year get() = platform.year


	override fun compareTo(other: LocalDate) =
		platform.compareTo(other.platform)


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? LocalDate)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		actual fun parse(text: CharSequence) =
			runCatching { PlatformLocalDate.parse(text).toCommon() }.getOrNull()
	}
}


actual fun LocalDate(year: Int, month: Month, day: Int): LocalDate =
	PlatformLocalDate.of(year, month.platform, day).toCommon()


actual val LocalDate.dayOfWeek: DayOfWeek
	get() = PlatformDayOfWeek.from(platform).toCommon()


actual fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp =
	platform.atStartOfDay(timeZone.platform).toInstant().toCommon()


actual fun LocalDate.minusDays(daysToSubtract: Long): LocalDate =
	platform.minusDays(daysToSubtract).toCommon()


actual fun LocalDate.plusDays(daysToAdd: Long): LocalDate =
	platform.plusDays(daysToAdd).toCommon()


fun PlatformLocalDate.toCommon() =
	LocalDate(this)

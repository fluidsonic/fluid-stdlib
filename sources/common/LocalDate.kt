package com.github.fluidsonic.fluid.stdlib


expect class LocalDate : Comparable<LocalDate> {

	val day: Int
	val month: Month
	val year: Int


	companion object
}


expect fun LocalDate(year: Int, month: Month, day: Int): LocalDate

expect val LocalDate.dayOfWeek: DayOfWeek

expect fun LocalDate.atStartOfDay(timeZone: TimeZone): Timestamp
expect fun LocalDate.minusDays(daysToSubtract: Long): LocalDate
expect fun LocalDate.plusDays(daysToAdd: Long): LocalDate


fun LocalDate(year: Int, month: Int, day: Int) =
	LocalDate(year = year, month = Month(month), day = day)


fun LocalDate.atStartOfDay() =
	atTime(LocalTime.midnight)


fun LocalDate.atTime(time: LocalTime) =
	LocalDateTime(year = year, month = month, day = day, hour = time.hour, minute = time.minute, second = time.second)

package com.github.fluidsonic.fluid.stdlib


expect class LocalDate : Comparable<LocalDate> {

	val day: Int
	val month: Month
	val year: Int

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String


	companion object {

		fun now(clock: Clock = Clock.system, timeZone: TimeZone = clock.timeZone): LocalDate
		fun ofEpochDay(epochDay: Long): LocalDate
		fun parse(text: CharSequence): LocalDate?
	}
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


fun LocalDate.atTime(hour: Int, minute: Int, second: Int = 0) =
	LocalDateTime(year = year, month = month, day = day, hour = hour, minute = minute, second = second)


fun LocalDate.atTime(time: LocalTime) =
	atTime(hour = time.hour, minute = time.minute, second = time.second)

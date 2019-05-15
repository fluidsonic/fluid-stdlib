package com.github.fluidsonic.fluid.stdlib


expect class LocalTime : Comparable<LocalTime> {

	val hour: Int
	val minute: Int
	val second: Int

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String


	companion object {

		val max: LocalTime
		val midnight: LocalTime
		val min: LocalTime
		val noon: LocalTime

		fun now(clock: Clock = Clock.system, timeZone: TimeZone = clock.timeZone): LocalTime
		fun parse(text: CharSequence): LocalTime?
	}
}


expect fun LocalTime(hour: Int, minute: Int, second: Int = 0): LocalTime


fun LocalTime.atDate(year: Int, month: Month, day: Int) =
	LocalDateTime(year = year, month = month, day = day, hour = hour, minute = minute, second = second)


fun LocalTime.atDate(year: Int, month: Int, day: Int) =
	atDate(year = year, month = Month(month), day = day)


fun LocalTime.atDate(date: LocalDate) =
	atDate(year = date.year, month = date.month, day = date.day)

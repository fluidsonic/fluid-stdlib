package com.github.fluidsonic.fluid.stdlib


expect class LocalDateTime : Comparable<LocalDateTime> {

	val day: Int
	val hour: Int
	val minute: Int
	val month: Month
	val second: Int
	val year: Int


	companion object
}


expect fun LocalDateTime(year: Int, month: Month, day: Int, hour: Int, minute: Int, second: Int): LocalDateTime

expect fun LocalDateTime.atTimeZone(timeZone: TimeZone): Timestamp


fun LocalDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) =
	LocalDateTime(year = year, month = Month(month), day = day, hour = hour, minute = minute, second = second)

package com.github.fluidsonic.fluid.stdlib


expect class Timestamp : Comparable<Timestamp> {

	companion object
}


expect fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate
expect fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime
expect fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime

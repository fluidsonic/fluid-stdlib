package com.github.fluidsonic.fluid.stdlib


expect class Timestamp : Comparable<Timestamp> {

	val epochSecond: Long

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String


	companion object {

		fun now(clock: Clock = Clock.system, timeZone: TimeZone = clock.timeZone): Timestamp
		fun parse(text: CharSequence): Timestamp?
	}
}


expect fun Timestamp(epochSecond: Long): Timestamp

expect fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate
expect fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime
expect fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime

expect operator fun Timestamp.minus(duration: Duration): Timestamp
expect operator fun Timestamp.plus(duration: Duration): Timestamp

package com.github.fluidsonic.fluid.stdlib


expect class Clock {

	val timeZone: TimeZone

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String


	companion object {

		val system: Clock
	}
}


expect fun Clock.localDate(): LocalDate
expect fun Clock.localDateTime(): LocalDateTime
expect fun Clock.localTime(): LocalTime
expect fun Clock.timestamp(): Timestamp
expect fun Clock.withTimeZone(timeZone: TimeZone): Clock

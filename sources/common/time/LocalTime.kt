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

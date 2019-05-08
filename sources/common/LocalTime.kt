package com.github.fluidsonic.fluid.stdlib


expect class LocalTime : Comparable<LocalTime> {

	val hour: Int
	val minute: Int
	val second: Int


	companion object {

		val max: LocalTime
		val midnight: LocalTime
		val min: LocalTime
		val noon: LocalTime
	}
}


expect fun LocalTime(hour: Int, minute: Int, second: Int = 0): LocalTime

package com.github.fluidsonic.fluid.stdlib


enum class DayOfWeek {

	monday,
	tuesday,
	wednesday,
	thursday,
	friday,
	saturday,
	sunday;


	companion object
}


expect fun DayOfWeek.displayName(locale: Locale): String


operator fun DayOfWeek.minus(days: Int) =
	plus(-(days % 7))


operator fun DayOfWeek.plus(days: Int) =
	DayOfWeek.values()[(ordinal + (days % 7) + 7) % 7]

package com.github.fluidsonic.fluid.stdlib


expect class Duration : Comparable<Duration> {

	val seconds: Long

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String


	companion object {

		fun days(days: Long): Duration
		fun hours(hours: Long): Duration
		fun minutes(minutes: Long): Duration
		fun parse(text: CharSequence): Duration?
		fun seconds(seconds: Long): Duration
	}
}


val Duration.absolute
	get() = if (seconds < 0) Duration.seconds(-seconds) else this


val Duration.days
	get() = seconds / 86400


val Duration.hours
	get() = seconds / 3600


val Duration.minutes
	get() = seconds / 60

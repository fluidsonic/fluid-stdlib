package com.github.fluidsonic.fluid.stdlib


expect class Clock {

	val timeZone: TimeZone

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String

	fun withTimeZone(timeZone: TimeZone): Clock


	companion object {

		val system: Clock
	}
}

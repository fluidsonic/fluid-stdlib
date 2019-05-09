package com.github.fluidsonic.fluid.stdlib


expect class TimeZone {

	val id: String

	override fun equals(other: Any?): Boolean
	override fun hashCode(): Int
	override fun toString(): String


	companion object {

		val system: TimeZone
		val utc: TimeZone

		fun withId(id: String): TimeZone?
	}
}
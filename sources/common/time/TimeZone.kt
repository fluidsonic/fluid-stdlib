package com.github.fluidsonic.fluid.stdlib


expect class TimeZone {

	val id: String


	companion object {

		val utc: TimeZone


		fun withId(id: String): TimeZone?
	}
}

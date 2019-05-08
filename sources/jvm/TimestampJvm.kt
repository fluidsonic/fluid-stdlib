package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.*


actual class Timestamp(
	val platform: Instant
) : Comparable<Timestamp> {


	override fun compareTo(other: Timestamp) =
		platform.compareTo(other.platform)


	actual companion object
}


actual fun Timestamp.toLocalDate(timeZone: TimeZone) =
	platform.atZone(timeZone.platform).toLocalDate().toCommon()


actual fun Timestamp.toLocalDateTime(timeZone: TimeZone) =
	platform.atZone(timeZone.platform).toLocalDateTime().toCommon()


actual fun Timestamp.toLocalTime(timeZone: TimeZone) =
	platform.atZone(timeZone.platform).toLocalTime().toCommon()


fun Instant.toCommon() =
	Timestamp(this)

package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.*


actual class Timestamp(
	val platform: Instant
) : Comparable<Timestamp> {

	actual val epochSecond: Long
		get() = platform.epochSecond


	override fun compareTo(other: Timestamp) =
		platform.compareTo(other.platform)


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? Timestamp)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		actual fun parse(text: CharSequence) =
			runCatching { Instant.parse(text).toCommon() }.getOrNull()
	}
}


actual fun Timestamp(epochSecond: Long): Timestamp =
	Instant.ofEpochSecond(epochSecond).toCommon()


actual fun Timestamp.toLocalDate(timeZone: TimeZone) =
	platform.atZone(timeZone.platform).toLocalDate().toCommon()


actual fun Timestamp.toLocalDateTime(timeZone: TimeZone) =
	platform.atZone(timeZone.platform).toLocalDateTime().toCommon()


actual fun Timestamp.toLocalTime(timeZone: TimeZone) =
	platform.atZone(timeZone.platform).toLocalTime().toCommon()


fun Instant.toCommon() =
	Timestamp(this)

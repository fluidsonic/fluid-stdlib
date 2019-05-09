package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.LocalTime as PlatformLocalTime


actual class LocalTime internal constructor(
	val platform: PlatformLocalTime
) : Comparable<LocalTime> {

	actual val hour get() = platform.hour
	actual val minute get() = platform.minute
	actual val second get() = platform.second


	override fun compareTo(other: LocalTime) =
		platform.compareTo(other.platform)


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? LocalTime)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		actual val max = PlatformLocalTime.MAX.toCommon()
		actual val midnight = PlatformLocalTime.MIDNIGHT.toCommon()
		actual val min = PlatformLocalTime.MIN.toCommon()
		actual val noon = PlatformLocalTime.NOON.toCommon()


		actual fun parse(text: CharSequence) =
			runCatching { PlatformLocalTime.parse(text).toCommon() }.getOrNull()
	}
}

actual fun LocalTime(hour: Int, minute: Int, second: Int): LocalTime =
	PlatformLocalTime.of(hour, minute, second).toCommon()


fun PlatformLocalTime.toCommon() =
	LocalTime(this)

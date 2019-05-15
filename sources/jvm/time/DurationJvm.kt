package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.Duration as PlatformDuration


actual class Duration(
	val platform: PlatformDuration
) : Comparable<Duration> {

	actual val seconds: Long
		get() = platform.seconds


	override fun compareTo(other: Duration) =
		platform.compareTo(other.platform)


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? Duration)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {


		actual fun days(days: Long) =
			PlatformDuration.ofDays(days).toCommon()


		actual fun hours(hours: Long) =
			PlatformDuration.ofHours(hours).toCommon()


		actual fun minutes(minutes: Long) =
			PlatformDuration.ofMinutes(minutes).toCommon()


		actual fun parse(text: CharSequence) =
			runCatching { PlatformDuration.parse(text).toCommon() }.getOrNull()


		actual fun seconds(seconds: Long) =
			PlatformDuration.ofSeconds(seconds).toCommon()
	}
}


fun PlatformDuration.toCommon() =
	Duration(this)

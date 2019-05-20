package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*
import platform.Foundation.NSTimeZone as PlatformTimeZone


actual class TimeZone internal constructor(
	val platform: PlatformTimeZone
) {

	actual val id: String get() = platform.name


	actual override fun equals(other: Any?) =
		this === other || platform == (other as? TimeZone)?.platform


	actual override fun hashCode() =
		platform.hashCode()


	actual override fun toString() =
		platform.toString()


	actual companion object {

		actual val system = Clock.system.timeZone
		actual val utc = withId("UTC")!!


		actual fun withId(id: String) =
			PlatformTimeZone.timeZoneWithName(id)?.toCommon()
	}
}


fun PlatformTimeZone.toCommon() =
	TimeZone(this)

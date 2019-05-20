package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.ZoneId as PlatformTimeZone
import java.util.Locale as JavaLocale


actual class TimeZone internal constructor(
	val platform: PlatformTimeZone
) {

	actual val id: String get() = platform.id


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
			runCatching { PlatformTimeZone.of(id).toCommon() }.getOrNull()
	}
}


fun PlatformTimeZone.toCommon() =
	TimeZone(this)

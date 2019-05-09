package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.*
import java.util.Locale as JavaLocale


actual class TimeZone(
	val platform: ZoneId
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
			runCatching { ZoneId.of(id).toCommon() }.getOrNull()
	}
}


fun ZoneId.toCommon() =
	TimeZone(this)

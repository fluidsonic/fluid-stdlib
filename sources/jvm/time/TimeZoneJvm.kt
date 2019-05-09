package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.*
import java.util.Locale as JavaLocale


actual class TimeZone(
	val platform: ZoneId
) {

	actual val id: String get() = platform.id


	actual companion object {

		actual val utc = TimeZone(ZoneOffset.UTC)


		actual fun withId(id: String) =
			ZoneId.of(id)?.let(::TimeZone)
	}
}

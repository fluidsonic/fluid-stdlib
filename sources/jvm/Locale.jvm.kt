package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.chrono.*
import org.threeten.bp.format.*
import java.util.Locale as PlatformLocale


actual class Locale(
	private val platform: PlatformLocale
) {

	fun toPlatform() =
		platform


	actual companion object {

		actual val englishInUnitedStates = PlatformLocale.US.toCommon()
	}
}


fun PlatformLocale.toCommon() =
	Locale(this)


val PlatformLocale.usesTwelveHourClock
	get() = DateTimeFormatterBuilder.getLocalizedDateTimePattern(null, FormatStyle.MEDIUM, IsoChronology.INSTANCE, this).contains('a')

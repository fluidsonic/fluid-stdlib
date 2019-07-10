package com.github.fluidsonic.fluid.stdlib

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
	get() = PlatformDateTimeFormatterBuilder.getLocalizedDateTimePattern(
		null,
		PlatformFormatStyle.MEDIUM,
		PlatformIsoChronology.INSTANCE,
		this
	).contains('a')

package io.fluidsonic.stdlib

import java.util.Locale as PlatformLocale


public actual class Locale(
	private val platform: PlatformLocale
) {

	public fun toPlatform(): java.util.Locale =
		platform


	public actual companion object {

		public actual val englishInUnitedStates: Locale = PlatformLocale.US.toCommon()
	}
}


public fun PlatformLocale.toCommon(): Locale =
	Locale(this)


public val PlatformLocale.usesTwelveHourClock: Boolean
	get() = PlatformDateTimeFormatterBuilder.getLocalizedDateTimePattern(
		null,
		PlatformFormatStyle.MEDIUM,
		PlatformIsoChronology.INSTANCE,
		this
	).contains('a')

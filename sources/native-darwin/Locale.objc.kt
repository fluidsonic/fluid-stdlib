package io.fluidsonic.stdlib

import platform.Foundation.NSLocale as PlatformLocale


public actual class Locale(
	private val platform: PlatformLocale
) {

	init {
		freeze()
	}


	public fun toPlatform(): PlatformLocale =
		platform


	public actual companion object {

		public actual val englishInUnitedStates: Locale = PlatformLocale("en_US").toCommon()
	}
}


public fun PlatformLocale.toCommon(): Locale =
	Locale(this)

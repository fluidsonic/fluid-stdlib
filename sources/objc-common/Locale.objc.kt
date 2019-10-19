package io.fluidsonic.stdlib

import platform.Foundation.NSLocale as PlatformLocale


actual class Locale(
	private val platform: PlatformLocale
) {

	init {
		freeze()
	}


	fun toPlatform() =
		platform


	actual companion object {

		actual val englishInUnitedStates = PlatformLocale("en_US").toCommon()
	}
}


fun PlatformLocale.toCommon() =
	Locale(this)

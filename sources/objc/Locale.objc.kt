package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.NSLocale as PlatformLocale


actual class Locale(
	private val platform: PlatformLocale
) {

	fun toPlatform() =
		platform


	actual companion object {

		actual val englishInUnitedStates = PlatformLocale("en_US").toCommon()
	}
}


fun PlatformLocale.toCommon() =
	Locale(this)

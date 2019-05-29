package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.NSLocale as PlatformLocale


actual class Locale(
	val platform: PlatformLocale
) {

	actual companion object {

		actual val englishInUnitedStates = PlatformLocale("en_US").toCommon()
	}
}


fun PlatformLocale.toCommon() =
	Locale(this)

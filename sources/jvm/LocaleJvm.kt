package com.github.fluidsonic.fluid.stdlib

import java.util.Locale as PlatformLocale


actual class Locale(
	val platform: PlatformLocale
) {

	actual companion object {

		actual val englishInUnitedStates = Locale(PlatformLocale.US)
	}
}

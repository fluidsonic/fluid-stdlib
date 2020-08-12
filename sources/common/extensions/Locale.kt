package io.fluidsonic.stdlib

import io.fluidsonic.locale.*


private val enUS = Locale.parseOrNull("en-US")!!


internal val Locale.Companion.enUS: Locale
	get() = enUS

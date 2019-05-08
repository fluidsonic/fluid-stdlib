package com.github.fluidsonic.fluid.stdlib


@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
actual fun StringBuilder.replace(start: Int, end: Int, replacement: String): StringBuilder =
	replace(start, end, replacement)

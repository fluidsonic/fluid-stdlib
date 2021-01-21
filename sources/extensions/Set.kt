package io.fluidsonic.stdlib


public fun <Element> Set<Element>.intersects(other: Set<Element>): Boolean =
	any { other.contains(it) }

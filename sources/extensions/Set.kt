package io.fluidsonic.stdlib


/** Returns `true` if this set has any element in common with [other]. */
public fun <Element> Set<Element>.intersects(other: Set<Element>): Boolean =
	any { other.contains(it) }

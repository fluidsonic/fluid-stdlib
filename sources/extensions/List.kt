package io.fluidsonic.stdlib


/** Returns a list with the first element removed. */
public fun <Element> List<Element>.dropFirst(): List<Element> =
	drop(1)


/** Returns a list with the last element removed. */
public fun <Element> List<Element>.dropLast(): List<Element> =
	dropLast(1)

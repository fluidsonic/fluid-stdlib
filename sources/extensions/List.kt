package io.fluidsonic.stdlib


public fun <Element> List<Element>.dropFirst(): List<Element> =
	drop(1)


public fun <Element> List<Element>.dropLast(): List<Element> =
	dropLast(1)

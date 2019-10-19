package io.fluidsonic.stdlib


fun <Element> List<Element>.dropFirst() =
	drop(1)


fun <Element> List<Element>.dropLast() =
	dropLast(1)

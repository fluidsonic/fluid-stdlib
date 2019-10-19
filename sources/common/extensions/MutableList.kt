package io.fluidsonic.stdlib


fun <Element> MutableList<Element>.removeLast() =
	removeAt(size - 1)

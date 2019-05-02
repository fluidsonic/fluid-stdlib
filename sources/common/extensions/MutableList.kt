package com.github.fluidsonic.fluid.stdlib


fun <Element> MutableList<Element>.removeLast() =
	removeAt(size - 1)

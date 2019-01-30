package com.github.fluidsonic.fluid.stdlib


fun <Element> Set<Element>.intersects(other: Set<Element>) =
	any { other.contains(it) }

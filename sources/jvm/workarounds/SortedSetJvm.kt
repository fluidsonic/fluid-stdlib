package com.github.fluidsonic.fluid.stdlib

import java.util.SortedSet


actual typealias _SortedSet<E> = SortedSet<E>


actual fun <T> _sortedSetOf(vararg elements: T): _SortedSet<T> =
	sortedSetOf(*elements)

package com.github.fluidsonic.fluid.stdlib


// TODO make obsolete
internal expect class _SortedSet<E> : MutableSet<E>


internal expect fun <T> _sortedSetOf(vararg elements: T): _SortedSet<T>

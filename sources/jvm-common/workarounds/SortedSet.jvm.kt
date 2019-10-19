package io.fluidsonic.stdlib

import java.util.*


internal class _InternalTreeSet<E> : TreeSet<E>(), MutableSet<E>

internal actual typealias _SortedSet<E> = _InternalTreeSet<E>


internal actual fun <T> _sortedSetOf(vararg elements: T): _SortedSet<T> =
	elements.toCollection(_InternalTreeSet())

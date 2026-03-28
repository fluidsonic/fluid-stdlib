package io.fluidsonic.stdlib


// TODO make obsolete
internal expect class _SortedSet<E> : MutableSet<E> {

	override fun iterator(): MutableIterator<E>
	override fun add(element: E): Boolean
	override fun remove(element: E): Boolean
	override fun addAll(elements: Collection<E>): Boolean
	override fun removeAll(elements: Collection<E>): Boolean
	override fun retainAll(elements: Collection<E>): Boolean
	override fun clear()
	override fun isEmpty(): Boolean
	override fun contains(element: E): Boolean
	override fun containsAll(elements: Collection<E>): Boolean
	override val size: Int
}


internal expect fun <T> _sortedSetOf(vararg elements: T): _SortedSet<T>

package com.github.fluidsonic.fluid.stdlib


actual interface _SortedSet<E> : MutableSet<E>


// TODO remove
private class _SortedSetImpl<E> private constructor(
	private val container: MutableList<E>
) : _SortedSet<E> {

	constructor(vararg elements: E) : this(container = elements.toMutableList()) {
		sort()
	}


	override fun add(element: E): Boolean {
		if (container.contains(element)) return false
		container.add(element)

		sort()
		return true
	}


	override fun addAll(elements: Collection<E>): Boolean {
		var addedAny = false
		for (element in elements)
			if (add(element))
				addedAny = true

		if (!addedAny) return false

		sort()
		return true
	}


	override fun clear() {
		container.clear()
	}


	override fun contains(element: E) =
		container.contains(element)


	override fun containsAll(elements: Collection<E>) =
		container.containsAll(elements)


	override fun isEmpty() =
		container.isEmpty()


	override fun iterator() =
		container.iterator()


	override fun remove(element: E) =
		container.remove(element)


	override fun removeAll(elements: Collection<E>) =
		container.removeAll(elements)


	override fun retainAll(elements: Collection<E>) =
		container.retainAll(elements)


	override val size
		get() = container.size


	private fun sort() {
		(container as MutableList<Comparable<Any>>).sort()
	}
}


actual fun <T> _sortedSetOf(vararg elements: T): _SortedSet<T> =
	_SortedSetImpl(*elements)

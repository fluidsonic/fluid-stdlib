package io.fluidsonic.stdlib


internal actual class _SortedSet<E> private constructor(
	private val container: MutableList<E>,
) : MutableSet<E> {

	constructor(vararg elements: E) : this(container = elements.toMutableList()) {
		sort()
	}


	actual override fun add(element: E): Boolean {
		if (container.contains(element)) return false
		container.add(element)

		sort()
		return true
	}


	actual override fun addAll(elements: Collection<E>): Boolean {
		var addedAny = false
		for (element in elements)
			if (add(element))
				addedAny = true

		if (!addedAny) return false

		sort()
		return true
	}


	actual override fun clear() {
		container.clear()
	}


	actual override fun contains(element: E) =
		container.contains(element)


	actual override fun containsAll(elements: Collection<E>) =
		container.containsAll(elements)


	actual override fun isEmpty() =
		container.isEmpty()


	actual override fun iterator() =
		container.iterator()


	actual override fun remove(element: E) =
		container.remove(element)


	actual override fun removeAll(elements: Collection<E>) =
		container.removeAll(elements)


	actual override fun retainAll(elements: Collection<E>) =
		container.retainAll(elements)


	actual override val size
		get() = container.size


	private fun sort() {
		@Suppress("UNCHECKED_CAST")
		(container as MutableList<Comparable<Any>>).sort()
	}
}


internal actual fun <T> _sortedSetOf(vararg elements: T) =
	_SortedSet(*elements)

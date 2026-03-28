package io.fluidsonic.stdlib


internal actual class _TreeMap<K, V> actual constructor() : MutableMap<K, V> {

	private val container = hashMapOf<K, V>()
	private val _entries = EntrySet()
	private val _keys = KeySet()


	actual override fun clear() {
		container.clear()
	}


	actual override fun containsKey(key: K) =
		container.containsKey(key)


	actual override fun containsValue(value: V) =
		container.containsValue(value)


	actual override fun get(key: K) =
		container[key]


	actual override fun isEmpty() =
		container.isEmpty()


	actual override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() = _entries


	actual override val keys: MutableSet<K>
		get() = _keys


	actual override val values: MutableCollection<V>
		get() = object : AbstractMutableCollection<V>() {
			override val size get() = container.size

			override fun add(element: V): Boolean =
				throw UnsupportedOperationException()

			override fun iterator(): MutableIterator<V> {
				@Suppress("UNCHECKED_CAST")
				val sortedKeys = (container.keys as MutableSet<Comparable<Any>>).sorted() as List<K>
				val index = sortedKeys.iterator()

				return object : MutableIterator<V> {
					private var currentKey: K? = null
					private var hasCurrent = false

					override fun hasNext() = index.hasNext()

					override fun next(): V {
						val key = index.next()
						currentKey = key
						hasCurrent = true
						return container[key]!!
					}

					override fun remove() {
						if (!hasCurrent) return
						val key = currentKey ?: return
						currentKey = null
						hasCurrent = false
						container.remove(key)
					}
				}
			}
		}


	actual override fun put(key: K, value: V) =
		container.put(key, value)


	actual override fun putAll(from: Map<out K, V>) =
		container.putAll(from)


	actual override fun remove(key: K) =
		container.remove(key)


	actual override val size
		get() = container.size


	private inner class EntrySet : MutableSet<MutableMap.MutableEntry<K, V>> {

		override fun add(element: MutableMap.MutableEntry<K, V>) =
			throw UnsupportedOperationException()


		override fun addAll(elements: Collection<MutableMap.MutableEntry<K, V>>) =
			throw UnsupportedOperationException()


		override fun clear() {
			container.entries.clear()
		}


		override fun contains(element: MutableMap.MutableEntry<K, V>) =
			container.entries.contains(element)


		override fun containsAll(elements: Collection<MutableMap.MutableEntry<K, V>>) =
			container.entries.containsAll(elements)


		override fun isEmpty() =
			container.entries.isEmpty()


		override fun iterator(): MutableIterator<MutableMap.MutableEntry<K, V>> {
			@Suppress("UNCHECKED_CAST")
			val sortedEntries = (container.entries as Collection<MutableMap.MutableEntry<Comparable<Any>, V>>).sortedBy { it.key }
				as List<MutableMap.MutableEntry<K, V>>
			val iterator = sortedEntries.iterator()

			return object : MutableIterator<MutableMap.MutableEntry<K, V>> {

				private var current: MutableMap.MutableEntry<K, V>? = null


				override fun hasNext() =
					iterator.hasNext()


				override fun next(): MutableMap.MutableEntry<K, V> {
					val next = iterator.next()
					current = next
					return next
				}


				override fun remove() {
					val current = current ?: return
					this.current = null

					container.remove(current.key)
				}
			}
		}


		override fun remove(element: MutableMap.MutableEntry<K, V>) =
			container.entries.remove(element)


		override fun removeAll(elements: Collection<MutableMap.MutableEntry<K, V>>) =
			container.entries.removeAll(elements)


		override fun retainAll(elements: Collection<MutableMap.MutableEntry<K, V>>) =
			container.entries.retainAll(elements)


		override val size
			get() = container.entries.size
	}


	private inner class KeySet : MutableSet<K> {

		override fun add(element: K) =
			throw UnsupportedOperationException()


		override fun addAll(elements: Collection<K>) =
			throw UnsupportedOperationException()


		override fun clear() {
			container.clear()
		}


		override fun contains(element: K) =
			container.containsKey(element)


		override fun containsAll(elements: Collection<K>) =
			elements.all { container.containsKey(it) }


		override fun isEmpty() =
			container.isEmpty()


		override fun iterator(): MutableIterator<K> {
			@Suppress("UNCHECKED_CAST")
			val sortedKeys = (container.keys as MutableSet<Comparable<Any>>).sorted() as List<K>
			val iterator = sortedKeys.iterator()

			return object : MutableIterator<K> {

				private var current: K? = null
				private var hasCurrent = false


				override fun hasNext() =
					iterator.hasNext()


				override fun next(): K {
					val next = iterator.next()
					current = next
					hasCurrent = true
					return next
				}


				override fun remove() {
					if (!hasCurrent) return

					val current = current ?: return
					this.current = null
					this.hasCurrent = false

					container.remove(current)
				}
			}
		}


		override fun remove(element: K) =
			container.remove(element) != null


		override fun removeAll(elements: Collection<K>): Boolean {
			var removedAny = false
			for (element in elements)
				if (remove(element))
					removedAny = true

			return removedAny
		}


		override fun retainAll(elements: Collection<K>): Boolean {
			val elementToRemove = mutableListOf<K>()
			for (element in this)
				if (!elements.contains(element))
					elementToRemove += element

			if (elementToRemove.isEmpty()) return false

			for (element in elementToRemove)
				remove(element)

			return true
		}


		override val size
			get() = container.size
	}
}

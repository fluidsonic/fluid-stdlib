package com.github.fluidsonic.fluid.stdlib


// TODO remove
actual class _TreeMap<K, V> actual constructor() : MutableMap<K, V> {

	private val container = hashMapOf<K, V>()
	private val _entries = EntrySet()
	private val _keys = KeySet()


	override fun clear() {
		container.clear()
	}


	override fun containsKey(key: K) =
		container.containsKey(key)


	override fun containsValue(value: V) =
		container.containsValue(value)


	override fun get(key: K) =
		container[key]


	override fun isEmpty() =
		container.isEmpty()


	override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
		get() = _entries


	override val keys: MutableSet<K>
		get() = _keys


	override val values: MutableCollection<V>
		get() = throw UnsupportedOperationException()


	override fun put(key: K, value: V) =
		container.put(key, value)


	override fun putAll(from: Map<out K, V>) =
		container.putAll(from)


	override fun remove(key: K) =
		container.remove(key)


	override val size: Int
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
			val sortedEntries = (entries as Collection<MutableMap.MutableEntry<Comparable<Any>, V>>).sortedBy { it.key }
				as List<MutableMap.MutableEntry<K, V>>
			val iterator = sortedEntries.iterator()

			return object : MutableIterator<MutableMap.MutableEntry<K, V>>, Iterator<MutableMap.MutableEntry<K, V>> by iterator {

				var current: MutableMap.MutableEntry<K, V>? = null


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


		override val size =
			container.entries.size
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
			val sortedKeys = (keys as MutableSet<Comparable<Any>>).sorted() as List<K>
			val iterator = sortedKeys.iterator()

			return object : MutableIterator<K>, Iterator<K> by iterator {

				var current: K? = null


				override fun next(): K {
					val next = iterator.next()
					current = next
					return next
				}


				override fun remove() {
					val current = current ?: return
					this.current = null
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


		override val size =
			container.size
	}
}

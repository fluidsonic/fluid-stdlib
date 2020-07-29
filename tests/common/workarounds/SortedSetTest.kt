package tests

import io.fluidsonic.stdlib.*
import kotlin.test.*


class SortedSetTest {

	@Test
	fun testAdd() {
		val set = _sortedSetOf<Int>()
		assertEquals(true, set.add(5))
		assertEquals(listOf(5), set.toList())
		assertEquals(true, set.add(4))
		assertEquals(listOf(4, 5), set.toList())
		assertEquals(true, set.add(3))
		assertEquals(listOf(3, 4, 5), set.toList())
		assertEquals(true, set.add(1))
		assertEquals(listOf(1, 3, 4, 5), set.toList())
		assertEquals(true, set.add(2))
		assertEquals(listOf(1, 2, 3, 4, 5), set.toList())
		assertEquals(false, set.add(2))
		assertEquals(listOf(1, 2, 3, 4, 5), set.toList())
	}


	@Test
	fun testAddAll() {
		val set = _sortedSetOf<Int>()
		assertEquals(true, set.addAll(listOf(5, 1)))
		assertEquals(listOf(1, 5), set.toList())
		assertEquals(false, set.addAll(listOf(5, 1)))
		assertEquals(2, set.size)
		assertEquals(listOf(1, 5), set.toList())
	}


	@Test
	fun testClear() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		set.clear()
		assertEquals(true, set.isEmpty())
		assertEquals(0, set.size)
		assertEquals(emptyList(), set.toList())
	}


	@Test
	fun testContains() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		assertEquals(true, set.contains(1))
		assertEquals(true, set.contains(2))
		assertEquals(true, set.contains(3))
		assertEquals(true, set.contains(4))
		assertEquals(true, set.contains(5))
		assertEquals(false, set.contains(6))
	}


	@Test
	fun testContainsAll() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		assertEquals(true, set.containsAll(listOf(1)))
		assertEquals(true, set.containsAll(listOf(1, 2, 3, 4, 5)))
		assertEquals(false, set.containsAll(listOf(1, 2, 3, 4, 5, 6)))
	}


	@Test
	fun testCreation() {
		assertEquals(emptyList(), _sortedSetOf<Int>().toList())
		assertEquals(listOf(1, 2, 3, 4, 5), _sortedSetOf(5, 4, 3, 1, 2).toList())
	}


	@Test
	fun testIsEmpty() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		assertEquals(false, set.isEmpty())
		set.clear()
		assertEquals(true, set.isEmpty())
	}


	@Test
	fun testRemove() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		assertEquals(true, set.remove(3))
		assertEquals(listOf(1, 2, 4, 5), set.toList())
		assertEquals(false, set.remove(3))
		assertEquals(listOf(1, 2, 4, 5), set.toList())
	}


	@Test
	fun testRemoveAll() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		assertEquals(true, set.removeAll(listOf(2, 3)))
		assertEquals(listOf(1, 4, 5), set.toList())
		assertEquals(false, set.removeAll(listOf(2, 3)))
		assertEquals(listOf(1, 4, 5), set.toList())
	}


	@Test
	fun testRetainAll() {
		val set = _sortedSetOf(1, 2, 3, 4, 5)
		assertEquals(true, set.retainAll(listOf(2, 3)))
		assertEquals(listOf(2, 3), set.toList())
		assertEquals(true, set.retainAll(listOf(1, 4, 5)))
		assertEquals(emptyList(), set.toList())
		assertEquals(false, set.retainAll(listOf(1, 4, 5)))
		assertEquals(emptyList(), set.toList())
	}


	@Test
	fun testSize() {
		val set = _sortedSetOf<Int>()
		assertEquals(true, set.add(5))
		assertEquals(1, set.size)
		assertEquals(true, set.add(4))
		assertEquals(2, set.size)
		assertEquals(true, set.add(3))
		assertEquals(3, set.size)
		assertEquals(true, set.add(1))
		assertEquals(4, set.size)
		assertEquals(true, set.add(2))
		assertEquals(5, set.size)
		assertEquals(false, set.add(2))
		assertEquals(5, set.size)
	}
}

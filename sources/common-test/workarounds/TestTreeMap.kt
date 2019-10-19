package tests

import io.fluidsonic.stdlib.*
import kotlin.test.*


object TestTreeMap {

	@Test
	fun testCreation() {
		val map = _TreeMap<Any, Any>()
		assertEquals(0, map.size)
		assertEquals(true, map.isEmpty())
	}


	@Test
	fun testClear() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		map.clear()
		assertEquals(0, map.size)
		assertEquals(true, map.isEmpty())
	}


	@Test
	fun testContainsKey() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		assertEquals(true, map.containsKey(1))
		assertEquals(true, map.containsKey(2))
		assertEquals(true, map.containsKey(3))
		assertEquals(false, map.containsKey(4))
	}


	@Test
	fun testContainsValue() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		assertEquals(true, map.containsValue("z"))
		assertEquals(true, map.containsValue("c"))
		assertEquals(true, map.containsValue("a"))
		assertEquals(false, map.containsValue("x"))
	}


	@Test
	fun testEntries() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		assertEquals(listOf(1 to "z", 2 to "a", 3 to "c"), map.entries.map { it.key to it.value })
	}


	@Test
	fun testGet() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		assertEquals("z", map[1])
		assertEquals("a", map[2])
		assertEquals("c", map[3])
	}


	@Test
	fun testIsEmpty() {
		val map = _TreeMap<Int, String>()
		assertEquals(true, map.isEmpty())
		map[1] = "z"
		assertEquals(false, map.isEmpty())
		map.remove(1)
		assertEquals(true, map.isEmpty())
	}


	@Test
	fun testKeys() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		assertEquals(listOf(1, 2, 3), map.keys.toList())
	}


	@Ignore
	@Test
	fun testValues() {
		val map = _TreeMap<Int, String>()
		map[1] = "z"
		map[3] = "c"
		map[2] = "a"
		assertEquals(listOf("z", "a", "c"), map.values.toList())
	}


	@Test
	fun testPut() {
		val map = _TreeMap<Int, String>()
		assertEquals(emptyList(), map.entries.toList())
		map[1] = "z"
		assertEquals(listOf(1 to "z"), map.entries.map { it.key to it.value })
		map[3] = "c"
		assertEquals(listOf(1 to "z", 3 to "c"), map.entries.map { it.key to it.value })
		map[2] = "a"
		assertEquals(listOf(1 to "z", 2 to "a", 3 to "c"), map.entries.map { it.key to it.value })
	}


	@Test
	fun testPutAll() {
		val map = _TreeMap<Int, String>()
		assertEquals(emptyList(), map.entries.toList())
		map.putAll(mapOf(1 to "z", 3 to "c", 2 to "a"))
		assertEquals(listOf(1 to "z", 2 to "a", 3 to "c"), map.entries.map { it.key to it.value })
		map.putAll(mapOf(1 to "z", 3 to "c", 2 to "a"))
		assertEquals(listOf(1 to "z", 2 to "a", 3 to "c"), map.entries.map { it.key to it.value })
	}


	@Test
	fun testRemove() {
		val map = _TreeMap<Int, String>()
		map.putAll(mapOf(1 to "z", 3 to "c", 2 to "a"))
		assertEquals("z", map.remove(1))
		assertEquals(null, map.remove(1))
		assertEquals("a", map.remove(2))
		assertEquals(null, map.remove(2))
		assertEquals("c", map.remove(3))
		assertEquals(null, map.remove(3))
	}


	@Test
	fun testSize() {
		val map = _TreeMap<Int, String>()
		assertEquals(0, map.size)
		map[1] = "z"
		assertEquals(1, map.size)
		map[3] = "c"
		assertEquals(2, map.size)
		map[2] = "a"
		assertEquals(3, map.size)
		map[2] = "d"
		assertEquals(3, map.size)
	}
}

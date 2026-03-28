package io.fluidsonic.stdlib

import kotlin.test.*


class MapExtensionsTest {

	@Test
	fun toHashMap_preservesEntries() {
		val original = mapOf("a" to 1, "b" to 2, "c" to 3)
		val hashMap = original.toHashMap()
		assertEquals(expected = original, actual = hashMap)
	}

	@Test
	@Suppress("USELESS_IS_CHECK")
	fun toHashMap_returnsHashMapType() {
		val hashMap = mapOf("a" to 1).toHashMap()
		assertTrue(hashMap is HashMap)
	}

	@Test
	@Suppress("USELESS_IS_CHECK")
	fun toHashMap_emptyMap() {
		val hashMap = emptyMap<String, Int>().toHashMap()
		assertTrue(hashMap.isEmpty())
		assertTrue(hashMap is HashMap)
	}
}

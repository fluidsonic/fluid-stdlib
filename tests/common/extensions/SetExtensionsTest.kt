package io.fluidsonic.stdlib

import kotlin.test.*


class SetExtensionsTest {

	@Test
	fun intersects_withCommonElement() {
		assertTrue(setOf(1, 2, 3).intersects(setOf(3, 4, 5)))
	}

	@Test
	fun intersects_withNoCommonElement() {
		assertFalse(setOf(1, 2, 3).intersects(setOf(4, 5, 6)))
	}

	@Test
	fun intersects_withEmptySet() {
		assertFalse(setOf(1, 2, 3).intersects(emptySet()))
		assertFalse(emptySet<Int>().intersects(setOf(1, 2, 3)))
	}

	@Test
	fun intersects_bothEmpty() {
		assertFalse(emptySet<Int>().intersects(emptySet()))
	}
}

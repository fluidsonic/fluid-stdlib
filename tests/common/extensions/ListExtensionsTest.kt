package io.fluidsonic.stdlib

import kotlin.test.*


class ListExtensionsTest {

	@Test
	fun dropFirst_normalList() {
		assertEquals(expected = listOf(2, 3), actual = listOf(1, 2, 3).dropFirst())
	}

	@Test
	fun dropFirst_singleElement() {
		assertEquals(expected = emptyList(), actual = listOf(1).dropFirst())
	}

	@Test
	fun dropFirst_emptyList() {
		assertEquals(expected = emptyList(), actual = emptyList<Int>().dropFirst())
	}

	@Test
	fun dropLast_normalList() {
		assertEquals(expected = listOf(1, 2), actual = listOf(1, 2, 3).dropLast())
	}

	@Test
	fun dropLast_singleElement() {
		assertEquals(expected = emptyList(), actual = listOf(1).dropLast())
	}

	@Test
	fun dropLast_emptyList() {
		assertEquals(expected = emptyList(), actual = emptyList<Int>().dropLast())
	}
}

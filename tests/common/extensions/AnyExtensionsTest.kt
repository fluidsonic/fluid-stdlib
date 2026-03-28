package io.fluidsonic.stdlib

import kotlin.test.*


class AnyExtensionsTest {

	@Test
	fun ifNull_withNonNull() {
		val result: String = "hello".ifNull { "default" }
		assertEquals(expected = "hello", actual = result)
	}

	@Test
	fun ifNull_withNull() {
		val value: String? = null
		val result: String = value.ifNull { "default" }
		assertEquals(expected = "default", actual = result)
	}
}

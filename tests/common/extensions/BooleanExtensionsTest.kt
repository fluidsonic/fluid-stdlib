package io.fluidsonic.stdlib

import kotlin.test.*


class BooleanExtensionsTest {

	@Test
	fun ifTrue_whenTrue() {
		assertTrue(true.ifTrue { true })
		assertFalse(true.ifTrue { false })
	}

	@Test
	fun ifTrue_whenFalse() {
		assertFalse(false.ifTrue { true })
		assertFalse(false.ifTrue { false })
	}

	@Test
	fun ifFalse_whenFalse() {
		assertTrue(false.ifFalse { true })
		assertFalse(false.ifFalse { false })
	}

	@Test
	fun ifFalse_whenTrue() {
		assertTrue(true.ifFalse { true })
		assertTrue(true.ifFalse { false })
	}

	@Test
	fun thenTake_whenTrue() {
		assertEquals(expected = "value", actual = true.thenTake { "value" })
	}

	@Test
	fun thenTake_whenFalse() {
		assertNull(false.thenTake { "value" })
	}
}

package io.fluidsonic.stdlib

import kotlin.test.*


class MiscTest {

	@Test
	fun check_intInRange_passes() {
		check(value = 5, inRange = 1..10, name = "test")
	}

	@Test
	fun check_intInRange_fails() {
		assertFailsWith<IllegalStateException> {
			check(value = 15, inRange = 1..10, name = "test")
		}
	}

	@Test
	fun check_longInLongRange_passes() {
		check(value = 5L, inRange = 1L..10L, name = "test")
	}

	@Test
	fun check_longInLongRange_fails() {
		assertFailsWith<IllegalStateException> {
			check(value = 15L, inRange = 1L..10L, name = "test")
		}
	}

	@Test
	fun check_intInLongRange_passes() {
		check(value = 5, inRange = 1L..10L, name = "test")
	}

	@Test
	fun check_intInLongRange_fails() {
		assertFailsWith<IllegalStateException> {
			check(value = 15, inRange = 1L..10L, name = "test")
		}
	}

	@Test
	fun check_longInIntRange_passes() {
		check(value = 5L, inRange = 1..10, name = "test")
	}

	@Test
	fun check_longInIntRange_fails() {
		assertFailsWith<IllegalStateException> {
			check(value = 15L, inRange = 1..10, name = "test")
		}
	}

	@Test
	fun identity_returnsSameValue() {
		assertEquals(expected = 42, actual = identity(42))
		assertEquals(expected = "hello", actual = identity("hello"))
		val list = listOf(1, 2, 3)
		assertSame(expected = list, actual = identity(list))
	}
}

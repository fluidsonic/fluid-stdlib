package io.fluidsonic.stdlib

import kotlin.test.*


class MaybeTest {

	@Test
	fun of_wrapsValue() {
		val maybe = Maybe.of("hello")
		assertTrue(maybe.hasValue())
		assertEquals(expected = "hello", actual = maybe.get())
	}

	@Test
	fun of_wrapsNull() {
		val maybe = Maybe.of(null)
		assertTrue(maybe.hasValue())
		assertNull(maybe.get())
	}

	@Test
	fun ofNullable_withNonNull() {
		val maybe = Maybe.ofNullable("hello")
		assertTrue(maybe.hasValue())
		assertEquals(expected = "hello", actual = maybe.get())
	}

	@Test
	fun ofNullable_withNull() {
		val maybe = Maybe.ofNullable(null)
		assertTrue(maybe.isNothing())
		assertFalse(maybe.hasValue())
	}

	@Test
	fun nothing() {
		val maybe = Maybe.nothing
		assertTrue(maybe.isNothing())
		assertFalse(maybe.hasValue())
	}

	@Test
	fun get_onNothing_throws() {
		assertFailsWith<IllegalStateException> { Maybe.nothing.get() }
	}

	@Test
	fun getOrNull_withValue() {
		assertEquals(expected = "hello", actual = Maybe.of("hello").getOrNull())
	}

	@Test
	fun getOrNull_withNothing() {
		assertNull(Maybe.nothing.getOrNull())
	}

	@Test
	fun getOrElse_withValue() {
		assertEquals(expected = "hello", actual = Maybe.of("hello").getOrElse { "default" })
	}

	@Test
	fun getOrElse_withNothing() {
		assertEquals(expected = "default", actual = Maybe.nothing.getOrElse { "default" })
	}

	@Test
	fun map_withValue() {
		val result = Maybe.of(5).map { it * 2 }
		assertEquals(expected = 10, actual = result.get())
	}

	@Test
	fun map_withNothing() {
		val result = Maybe.nothing.map { error("should not be called") }
		assertTrue(result.isNothing())
	}

	@Test
	fun mapIfNotNull_withNonNullValue() {
		val maybe: Maybe<String?> = Maybe.of("hello")
		val result = maybe.mapIfNotNull { it.length }
		assertEquals(expected = 5, actual = result.get())
	}

	@Test
	fun mapIfNotNull_withNullValue() {
		val maybe: Maybe<String?> = Maybe.of(null)
		val result = maybe.mapIfNotNull { it.length }
		assertTrue(result.hasValue())
		assertNull(result.get())
	}

	@Test
	fun equality() {
		assertEquals(expected = Maybe.of("hello"), actual = Maybe.of("hello"))
		assertEquals(expected = Maybe.nothing, actual = Maybe.nothing)
		assertNotEquals(illegal = Maybe.of("hello"), actual = Maybe.nothing)
		assertNotEquals(illegal = Maybe.of("hello"), actual = Maybe.of("world"))
	}

	@Test
	fun toString_withValue() {
		assertEquals(expected = "hello", actual = Maybe.of("hello").toString())
	}

	@Test
	fun toString_withNothing() {
		assertEquals(expected = "<nothing>", actual = Maybe.nothing.toString())
	}
}

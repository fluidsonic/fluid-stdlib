package io.fluidsonic.stdlib

import kotlin.test.*


class StringExtensionsTest {

	@Test
	fun truncatedTo_shortString() {
		assertEquals(expected = "abc", actual = "abc".truncatedTo(10))
	}

	@Test
	fun truncatedTo_exactLength() {
		assertEquals(expected = "abc", actual = "abc".truncatedTo(3))
	}

	@Test
	fun truncatedTo_longerString() {
		assertEquals(expected = "ab", actual = "abcdef".truncatedTo(2))
	}

	@Test
	fun truncatedTo_withSuffix() {
		assertEquals(expected = "abc...", actual = "abcdefgh".truncatedTo(6, truncationSuffix = "..."))
	}

	@Test
	fun truncatedTo_maxLengthShorterThanSuffix() {
		assertEquals(expected = "...", actual = "abcdef".truncatedTo(2, truncationSuffix = "..."))
	}

	@Test
	fun truncatedTo_emptyString() {
		assertEquals(expected = "", actual = "".truncatedTo(5))
	}

	@Test
	fun truncatedTo_zeroLength() {
		assertEquals(expected = "", actual = "abc".truncatedTo(0))
	}
}

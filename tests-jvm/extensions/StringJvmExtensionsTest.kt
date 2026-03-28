package io.fluidsonic.stdlib

import kotlin.test.*


class StringJvmExtensionsTest {

	@Test
	fun unicodeLength_ascii() {
		assertEquals(expected = 5, actual = "hello".unicodeLength)
	}

	@Test
	fun unicodeLength_empty() {
		assertEquals(expected = 0, actual = "".unicodeLength)
	}

	@Test
	fun unicodeLength_emoji() {
		// A single emoji should count as 1 grapheme
		assertEquals(expected = 1, actual = "\uD83D\uDE00".unicodeLength) // grinning face
	}

	@Test
	fun unicodeLength_combinedCharacters() {
		// e + combining acute accent = 1 grapheme but 2 code points
		assertEquals(expected = 1, actual = "e\u0301".unicodeLength)
	}

	@Test
	fun unicodeSubstring_startIndex() {
		assertEquals(expected = "llo", actual = "hello".unicodeSubstring(2))
	}

	@Test
	fun unicodeSubstring_startIndexBeyondLength() {
		assertEquals(expected = "", actual = "hi".unicodeSubstring(5))
	}

	@Test
	fun unicodeSubstring_range() {
		assertEquals(expected = "ell", actual = "hello".unicodeSubstring(1, 4))
	}

	@Test
	fun unicodeSubstring_sameStartAndEnd() {
		assertEquals(expected = "", actual = "hello".unicodeSubstring(2, 2))
	}

	@Test
	fun unicodeSubstring_withEmoji() {
		val str = "a\uD83D\uDE00b"  // a + grinning face + b
		assertEquals(expected = "\uD83D\uDE00b", actual = str.unicodeSubstring(1))
		assertEquals(expected = "\uD83D\uDE00", actual = str.unicodeSubstring(1, 2))
	}
}

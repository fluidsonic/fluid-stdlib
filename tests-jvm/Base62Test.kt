package io.fluidsonic.stdlib

import kotlin.test.*


class Base62Test {

	@Test
	fun random_returnsCorrectLength() {
		assertEquals(expected = 0, actual = Base62.random(0).length)
		assertEquals(expected = 1, actual = Base62.random(1).length)
		assertEquals(expected = 10, actual = Base62.random(10).length)
		assertEquals(expected = 100, actual = Base62.random(100).length)
	}

	@Test
	fun random_containsOnlyAlphanumericCharacters() {
		val result = Base62.random(1000)
		assertTrue(result.all { it.isLetterOrDigit() })
	}

	@Test
	fun randomCharacter_returnsAlphanumericChar() {
		repeat(100) {
			val char = Base62.randomCharacter()
			assertTrue(char.isLetterOrDigit(), "Expected alphanumeric, got '$char'")
		}
	}

	@Test
	fun random_negativeLengthThrows() {
		assertFailsWith<IllegalStateException> {
			Base62.random(-1)
		}
	}
}

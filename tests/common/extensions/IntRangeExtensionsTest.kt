package io.fluidsonic.stdlib

import kotlin.test.*


class IntRangeExtensionsTest {

	@Test
	fun component1_and_component2() {
		val (first, last) = 1..5
		assertEquals(expected = 1, actual = first)
		assertEquals(expected = 5, actual = last)
	}

	@Test
	fun flipped() {
		val range = (1..5).flipped()
		assertEquals(expected = 5, actual = range.first)
		assertEquals(expected = 1, actual = range.last)
	}

	@Test
	fun mapBounds_toLongRange() {
		val transform: (Int) -> Long = { value -> value.toLong() * 2 }
		val result: LongRange = (1..5).mapBounds(transform)
		assertEquals(expected = 2L..10L, actual = result)
	}

	@Test
	fun intersection_overlapping() {
		val result = (1..5).intersection(3..7)
		assertNotNull(result)
		assertEquals(expected = 3, actual = result.startValue)
		assertEquals(expected = 5, actual = result.endValueExclusive)
	}

	@Test
	fun intersection_nonOverlapping() {
		assertNull((1..3).intersection(5..7))
	}

	@Test
	fun overlaps() {
		assertTrue((1..5).overlaps(3..7))
		assertTrue((3..7).overlaps(1..5))
		assertFalse((1..3).overlaps(5..7))
	}
}

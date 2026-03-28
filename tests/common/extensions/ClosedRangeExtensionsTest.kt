package io.fluidsonic.stdlib

import kotlin.test.*


class ClosedRangeExtensionsTest {

	@Test
	fun component1_and_component2() {
		val range: ClosedRange<String> = "a".."z"
		val (start, endInclusive) = range
		assertEquals(expected = "a", actual = start)
		assertEquals(expected = "z", actual = endInclusive)
	}

	@Test
	fun flipped() {
		val range: ClosedRange<String> = "a".."z"
		val flippedRange = range.flipped()
		assertEquals(expected = "z", actual = flippedRange.start)
		assertEquals(expected = "a", actual = flippedRange.endInclusive)
	}

	@Test
	fun mapBounds() {
		val range: ClosedRange<String> = "a".."c"
		val transform: (String) -> Long = { value -> value.length.toLong() }
		val mapped: LongRange = range.mapBounds(transform)
		assertEquals(expected = 1L..1L, actual = mapped)
	}

	@Test
	fun intersection_overlapping() {
		val a: ClosedRange<Int> = 1..5
		val b: ClosedRange<Int> = 3..7
		val result = a.intersection(b)
		assertNotNull(result)
		assertEquals(expected = 3, actual = result.start)
		assertEquals(expected = 5, actual = result.endInclusive)
	}

	@Test
	fun intersection_nonOverlapping() {
		val a: ClosedRange<Int> = 1..3
		val b: ClosedRange<Int> = 5..7
		assertNull(a.intersection(b))
	}

	@Test
	fun overlaps() {
		val a: ClosedRange<Int> = 1..5
		val b: ClosedRange<Int> = 3..7
		val c: ClosedRange<Int> = 6..8
		assertTrue(a.overlaps(b))
		assertTrue(b.overlaps(a))
		assertFalse(a.overlaps(c))
	}

	@Test
	fun toSequence() {
		val range: ClosedRange<Int> = 1..5
		val list = range.toSequence { if (it < 5) it + 1 else null }.toList()
		assertEquals(expected = listOf(1, 2, 3, 4, 5), actual = list)
	}

	@Test
	fun toSequence_emptyRange() {
		val range: ClosedRange<Int> = 5..3
		val list = range.toSequence { it + 1 }.toList()
		assertTrue(list.isEmpty())
	}
}

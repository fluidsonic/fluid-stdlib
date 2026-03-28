package io.fluidsonic.stdlib

import kotlin.test.*


class HalfOpenIntRangeTest {

	@Test
	fun construction() {
		val range = 1 rangeToExcluding 5
		assertEquals(expected = 1, actual = range.startValue)
		assertEquals(expected = 5, actual = range.endValueExclusive)
	}

	@Test
	fun contains_value() {
		val range = 1 rangeToExcluding 5
		assertFalse(range.contains(0))
		assertTrue(range.contains(1))
		assertTrue(range.contains(4))
		assertFalse(range.contains(5))
	}

	@Test
	fun contains_range() {
		val range = 1 rangeToExcluding 10
		assertTrue(range.contains(1 rangeToExcluding 10))
		assertTrue(range.contains(2 rangeToExcluding 9))
		assertFalse(range.contains(0 rangeToExcluding 5))
		assertFalse(range.contains(5 rangeToExcluding 11))
	}

	@Test
	fun isEmpty() {
		assertTrue((5 rangeToExcluding 5).isEmpty())
		assertTrue((5 rangeToExcluding 3).isEmpty())
		assertFalse((1 rangeToExcluding 5).isEmpty())
	}

	@Test
	fun iteration() {
		val range = 1 rangeToExcluding 4
		assertEquals(expected = listOf(1, 2, 3), actual = range.toList())
	}

	@Test
	fun iteration_emptyRange() {
		val range = 5 rangeToExcluding 5
		assertEquals(expected = emptyList(), actual = range.toList())
	}

	@Test
	fun componentDestructuring() {
		val range = 1 rangeToExcluding 5
		val (start, endExclusive) = range
		assertEquals(expected = 1, actual = start)
		assertEquals(expected = 5, actual = endExclusive)
	}

	@Test
	fun flipped() {
		val range = (1 rangeToExcluding 5).flipped()
		assertEquals(expected = 5, actual = range.startValue)
		assertEquals(expected = 1, actual = range.endValueExclusive)
	}

	@Test
	fun intersection_overlapping() {
		val result = (1 rangeToExcluding 5).intersection(3 rangeToExcluding 7)
		assertNotNull(result)
		assertEquals(expected = 3, actual = result.startValue)
		assertEquals(expected = 5, actual = result.endValueExclusive)
	}

	@Test
	fun intersection_nonOverlapping() {
		assertNull((1 rangeToExcluding 3).intersection(5 rangeToExcluding 7))
	}

	@Test
	fun overlaps() {
		assertTrue((1 rangeToExcluding 5).overlaps(3 rangeToExcluding 7))
		assertFalse((1 rangeToExcluding 3).overlaps(5 rangeToExcluding 7))
	}

	@Test
	fun mapBounds_toIntRange() {
		val result = (1 rangeToExcluding 5).mapBounds { it * 2 }
		assertEquals(expected = 2, actual = result.startValue)
		assertEquals(expected = 10, actual = result.endValueExclusive)
	}

	@Test
	fun mapBounds_toHalfOpenRange() {
		val transform: (Int) -> String = { value -> value.toString() }
		val result: HalfOpenRange<String> = (1 rangeToExcluding 5).mapBounds(transform)
		assertEquals(expected = "1", actual = result.start)
		assertEquals(expected = "5", actual = result.endExclusive)
	}

	@Test
	fun subtracting_noOverlap() {
		val result = (1 rangeToExcluding 5).subtracting(6 rangeToExcluding 8)
		assertEquals(expected = 1, actual = result.size)
	}

	@Test
	fun subtracting_middleRemoved() {
		val result = (1 rangeToExcluding 10).subtracting(3 rangeToExcluding 7)
		assertEquals(expected = 2, actual = result.size)
		assertEquals(expected = 1, actual = result[0].startValue)
		assertEquals(expected = 3, actual = result[0].endValueExclusive)
		assertEquals(expected = 7, actual = result[1].startValue)
		assertEquals(expected = 10, actual = result[1].endValueExclusive)
	}

	@Test
	fun toSequence() {
		val list = (1 rangeToExcluding 5).toSequence { it + 1 }.toList()
		assertEquals(expected = listOf(1, 2, 3, 4), actual = list)
	}

	@Test
	fun equality() {
		assertEquals(expected = 1 rangeToExcluding 5, actual = 1 rangeToExcluding 5)
		assertNotEquals(illegal = 1 rangeToExcluding 5, actual = 1 rangeToExcluding 6)
	}
}

package io.fluidsonic.stdlib

import kotlin.test.*


class HalfOpenRangeTest {

	@Test
	fun rangeToExcluding_creation() {
		val range = 1 rangeToExcluding 5
		assertEquals(expected = 1, actual = range.startValue)
		assertEquals(expected = 5, actual = range.endValueExclusive)
	}

	@Test
	fun contains_value() {
		val range = 1 rangeToExcluding 5
		assertFalse(range.contains(0))
		assertTrue(range.contains(1))
		assertTrue(range.contains(3))
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
	fun flipped() {
		val range = 1 rangeToExcluding 5
		val flippedRange = range.flipped()
		assertEquals(expected = 5, actual = flippedRange.startValue)
		assertEquals(expected = 1, actual = flippedRange.endValueExclusive)
	}

	@Test
	fun intersection_overlapping() {
		val a = 1 rangeToExcluding 5
		val b = 3 rangeToExcluding 7
		val result = a.intersection(b)
		assertNotNull(result)
		assertEquals(expected = 3, actual = result.startValue)
		assertEquals(expected = 5, actual = result.endValueExclusive)
	}

	@Test
	fun intersection_nonOverlapping() {
		val a = 1 rangeToExcluding 3
		val b = 5 rangeToExcluding 7
		assertNull(a.intersection(b))
	}

	@Test
	fun overlaps() {
		assertTrue((1 rangeToExcluding 5).overlaps(3 rangeToExcluding 7))
		assertTrue((3 rangeToExcluding 7).overlaps(1 rangeToExcluding 5))
		assertFalse((1 rangeToExcluding 3).overlaps(5 rangeToExcluding 7))
	}

	@Test
	fun mapBounds_toHalfOpenIntRange() {
		val range = "bb" rangeToExcluding "eee"
		val mapped: HalfOpenIntRange = range.mapBounds { value: String -> value.length }
		assertEquals(expected = 2, actual = mapped.startValue)
		assertEquals(expected = 3, actual = mapped.endValueExclusive)
	}

	@Test
	fun subtracting_noOverlap() {
		val result = (1 rangeToExcluding 5).subtracting(6 rangeToExcluding 8)
		assertEquals(expected = 1, actual = result.size)
		assertEquals(expected = 1, actual = result[0].startValue)
		assertEquals(expected = 5, actual = result[0].endValueExclusive)
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
	fun subtracting_completelyContained() {
		val result = (1 rangeToExcluding 5).subtracting(0 rangeToExcluding 10)
		assertTrue(result.isEmpty())
	}

	@Test
	fun toSequence() {
		val range = 1 rangeToExcluding 5
		val list = range.toSequence { it + 1 }.toList()
		assertEquals(expected = listOf(1, 2, 3, 4), actual = list)
	}

	@Test
	fun toSequence_emptyRange() {
		val range = 5 rangeToExcluding 5
		val list = range.toSequence { it + 1 }.toList()
		assertTrue(list.isEmpty())
	}

	@Test
	fun comparable_rangeToExcluding() {
		val range = "b" rangeToExcluding "e"
		assertTrue(range.contains("b"))
		assertTrue(range.contains("c"))
		assertTrue(range.contains("d"))
		assertFalse(range.contains("a"))
		assertFalse(range.contains("e"))
	}
}

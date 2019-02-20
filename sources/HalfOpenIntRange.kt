package com.github.fluidsonic.fluid.stdlib

import kotlin.math.max
import kotlin.math.min


data class HalfOpenIntRange(
	override val start: Int,
	override val endExclusive: Int
) : HalfOpenRange<Int>, Iterable<Int> {

	override operator fun contains(value: Int) =
		value in start .. (endExclusive - 1)


	override fun isEmpty() = start >= endExclusive


	override operator fun iterator(): IntIterator {
		if (start == endExclusive) {
			return emptyRange.iterator()
		}

		return (start .. (endExclusive - 1)).iterator()
	}


	companion object {

		@Suppress("EmptyRange")
		private val emptyRange = 1 .. 0
	}
}


fun HalfOpenIntRange.contains(other: HalfOpenIntRange) =
	contains(other.start) && other.endExclusive <= endExclusive


fun HalfOpenIntRange.flipped() =
	endExclusive rangeToExcluding start


fun HalfOpenIntRange.intersection(other: HalfOpenIntRange) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endExclusive, other.endExclusive) }


fun HalfOpenIntRange.overlaps(other: HalfOpenIntRange) =
	contains(other.start) || other.contains(start)


fun <R : Comparable<R>> HalfOpenIntRange.mapBounds(transform: (Int) -> R) =
	transform(start) rangeToExcluding transform(endExclusive)


fun HalfOpenIntRange.mapBounds(transform: (Int) -> Int) =
	transform(start) rangeToExcluding transform(endExclusive)


fun HalfOpenIntRange.subtracting(rangeToSubtract: HalfOpenIntRange): List<HalfOpenIntRange> {
	if (rangeToSubtract.start >= endExclusive || rangeToSubtract.endExclusive <= start) {
		return listOf(this)
	}

	val result = mutableListOf<HalfOpenIntRange>()
	if (rangeToSubtract.start > start) {
		result.add(start rangeToExcluding rangeToSubtract.start)
	}
	if (rangeToSubtract.endExclusive < endExclusive) {
		result.add(rangeToSubtract.endExclusive rangeToExcluding endExclusive)
	}

	return result
}


infix fun Int.rangeToExcluding(that: Int) =
	HalfOpenIntRange(this, that)

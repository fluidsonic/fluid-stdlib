package io.fluidsonic.stdlib

import kotlin.math.*


class HalfOpenIntRange(
	start: Int,
	endExclusive: Int
) : HalfOpenRange<Int>, Iterable<Int> {

	val startValue = start
	val endValueExclusive = endExclusive


	init {
		freeze()
	}


	@Deprecated(
		message = "use .endValueInclusive to avoid boxing",
		replaceWith = ReplaceWith("endValueExclusive"),
		level = DeprecationLevel.WARNING
	)
	override val endExclusive
		get() = endValueExclusive


	operator fun component1() =
		startValue


	operator fun component2() =
		endValueExclusive


	override operator fun contains(value: Int) =
		value in startValue until endValueExclusive


	override fun equals(other: Any?) =
		this === other ||
			(other is HalfOpenIntRange && startValue == other.startValue && endValueExclusive == other.endValueExclusive) ||
			(other is HalfOpenRange<*> && startValue == other.start && endValueExclusive == other.endExclusive)


	override fun hashCode() =
		hash { startValue x endValueExclusive }


	override fun isEmpty() =
		startValue >= endValueExclusive


	override operator fun iterator() =
		when (startValue) {
			endValueExclusive -> emptyRange.iterator()
			else -> (startValue until endValueExclusive).iterator()
		}


	@Deprecated(
		message = "use .startValue to avoid boxing",
		replaceWith = ReplaceWith("startValue"),
		level = DeprecationLevel.WARNING
	)
	override val start
		get() = startValue


	companion object {

		@Suppress("EmptyRange")
		private val emptyRange = 1 .. 0
	}
}


fun HalfOpenIntRange.contains(other: HalfOpenIntRange) =
	contains(other.startValue) && other.endValueExclusive <= endValueExclusive


fun HalfOpenIntRange.flipped() =
	endValueExclusive rangeToExcluding startValue


fun HalfOpenIntRange.intersection(other: HalfOpenIntRange) =
	overlaps(other).thenTake { max(startValue, other.startValue) rangeToExcluding min(endValueExclusive, other.endValueExclusive) }


fun HalfOpenIntRange.overlaps(other: HalfOpenIntRange) =
	contains(other.startValue) || other.contains(startValue)


inline fun HalfOpenIntRange.mapBounds(transform: (Int) -> Int) =
	transform(startValue) rangeToExcluding transform(endValueExclusive)


inline fun <R : Comparable<R>> HalfOpenIntRange.mapBounds(transform: (Int) -> R) =
	transform(startValue) rangeToExcluding transform(endValueExclusive)


fun HalfOpenIntRange.subtracting(rangeToSubtract: HalfOpenIntRange): List<HalfOpenIntRange> {
	if (rangeToSubtract.startValue >= endValueExclusive || rangeToSubtract.endValueExclusive <= startValue)
		return listOf(this)

	val result = mutableListOf<HalfOpenIntRange>()
	if (rangeToSubtract.startValue > startValue)
		result.add(startValue rangeToExcluding rangeToSubtract.startValue)
	if (rangeToSubtract.endValueExclusive < endValueExclusive)
		result.add(rangeToSubtract.endValueExclusive rangeToExcluding endValueExclusive)

	return result
}


fun HalfOpenIntRange.toSequence(nextFunction: (Int) -> Int?) =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(startValue) { start ->
			nextFunction(start)?.takeIf { value -> contains(value) }
		}
	}


infix fun Int.rangeToExcluding(that: Int) =
	HalfOpenIntRange(start = this, endExclusive = that)

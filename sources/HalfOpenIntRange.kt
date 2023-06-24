package io.fluidsonic.stdlib

import kotlin.math.*


public class HalfOpenIntRange(
	start: Int,
	endExclusive: Int
) : HalfOpenRange<Int>, Iterable<Int> {

	public val startValue: Int = start
	public val endValueExclusive: Int = endExclusive


	@Deprecated(
		message = "use .endValueInclusive to avoid boxing",
		replaceWith = ReplaceWith("endValueExclusive"),
		level = DeprecationLevel.WARNING
	)
	override val endExclusive: Int
		get() = endValueExclusive


	public operator fun component1(): Int =
		startValue


	public operator fun component2(): Int =
		endValueExclusive


	override operator fun contains(value: Int): Boolean =
		value in startValue until endValueExclusive


	override fun equals(other: Any?): Boolean =
		this === other ||
			(other is HalfOpenIntRange && startValue == other.startValue && endValueExclusive == other.endValueExclusive) ||
			(other is HalfOpenRange<*> && startValue == other.start && endValueExclusive == other.endExclusive)


	override fun hashCode(): Int {
		var result = startValue
		result = 31 * result + endValueExclusive

		return result
	}


	override fun isEmpty(): Boolean =
		startValue >= endValueExclusive


	override operator fun iterator(): IntIterator =
		when (startValue) {
			endValueExclusive -> emptyRange.iterator()
			else -> (startValue until endValueExclusive).iterator()
		}


	@Deprecated(
		message = "use .startValue to avoid boxing",
		replaceWith = ReplaceWith("startValue"),
		level = DeprecationLevel.WARNING
	)
	override val start: Int
		get() = startValue


	public companion object {

		@Suppress("EmptyRange")
		private val emptyRange = 1 .. 0
	}
}


public fun HalfOpenIntRange.contains(other: HalfOpenIntRange): Boolean =
	contains(other.startValue) && other.endValueExclusive <= endValueExclusive


public fun HalfOpenIntRange.flipped(): HalfOpenIntRange =
	endValueExclusive rangeToExcluding startValue


public fun HalfOpenIntRange.intersection(other: HalfOpenIntRange): HalfOpenIntRange? =
	overlaps(other).thenTake { max(startValue, other.startValue) rangeToExcluding min(endValueExclusive, other.endValueExclusive) }


public fun HalfOpenIntRange.overlaps(other: HalfOpenIntRange): Boolean =
	contains(other.startValue) || other.contains(startValue)


public inline fun HalfOpenIntRange.mapBounds(transform: (Int) -> Int): HalfOpenIntRange =
	transform(startValue) rangeToExcluding transform(endValueExclusive)


public inline fun <R : Comparable<R>> HalfOpenIntRange.mapBounds(transform: (Int) -> R): HalfOpenRange<R> =
	transform(startValue) rangeToExcluding transform(endValueExclusive)


public fun HalfOpenIntRange.subtracting(rangeToSubtract: HalfOpenIntRange): List<HalfOpenIntRange> {
	if (rangeToSubtract.startValue >= endValueExclusive || rangeToSubtract.endValueExclusive <= startValue)
		return listOf(this)

	val result = mutableListOf<HalfOpenIntRange>()
	if (rangeToSubtract.startValue > startValue)
		result.add(startValue rangeToExcluding rangeToSubtract.startValue)
	if (rangeToSubtract.endValueExclusive < endValueExclusive)
		result.add(rangeToSubtract.endValueExclusive rangeToExcluding endValueExclusive)

	return result
}


public fun HalfOpenIntRange.toSequence(nextFunction: (Int) -> Int?): Sequence<Int> =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(startValue) { start ->
			nextFunction(start)?.takeIf { value -> contains(value) }
		}
	}


public infix fun Int.rangeToExcluding(that: Int): HalfOpenIntRange =
	HalfOpenIntRange(start = this, endExclusive = that)

package io.fluidsonic.stdlib

import kotlin.math.*


/** A half-open range of [Int] values, specialized to avoid boxing. */
public class HalfOpenIntRange(
	start: Int,
	endExclusive: Int
) : HalfOpenRange<Int>, Iterable<Int> {

	/** The inclusive lower bound. */
	public val startValue: Int = start

	/** The exclusive upper bound. */
	public val endValueExclusive: Int = endExclusive


	@Deprecated(
		message = "use .endValueInclusive to avoid boxing",
		replaceWith = ReplaceWith("endValueExclusive"),
		level = DeprecationLevel.WARNING
	)
	override val endExclusive: Int
		get() = endValueExclusive


	/** Destructuring component for [startValue]. */
	public operator fun component1(): Int =
		startValue


	/** Destructuring component for [endValueExclusive]. */
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


/** Returns `true` if this range fully contains [other]. */
public fun HalfOpenIntRange.contains(other: HalfOpenIntRange): Boolean =
	contains(other.startValue) && other.endValueExclusive <= endValueExclusive


/** Returns a new range with start and end swapped. */
public fun HalfOpenIntRange.flipped(): HalfOpenIntRange =
	endValueExclusive rangeToExcluding startValue


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun HalfOpenIntRange.intersection(other: HalfOpenIntRange): HalfOpenIntRange? =
	overlaps(other).thenTake { max(startValue, other.startValue) rangeToExcluding min(endValueExclusive, other.endValueExclusive) }


/** Returns `true` if this range overlaps with [other]. */
public fun HalfOpenIntRange.overlaps(other: HalfOpenIntRange): Boolean =
	contains(other.startValue) || other.contains(startValue)


/** Transforms both bounds using [transform] and returns a new [HalfOpenIntRange]. */
public inline fun HalfOpenIntRange.mapBounds(transform: (Int) -> Int): HalfOpenIntRange =
	transform(startValue) rangeToExcluding transform(endValueExclusive)


/** Transforms both bounds using [transform] and returns a new [HalfOpenRange]. */
public inline fun <R : Comparable<R>> HalfOpenIntRange.mapBounds(transform: (Int) -> R): HalfOpenRange<R> =
	transform(startValue) rangeToExcluding transform(endValueExclusive)


/** Returns the portions of this range that remain after subtracting [rangeToSubtract]. */
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


/** Converts this range to a [Sequence] by repeatedly applying [nextFunction] starting from [HalfOpenIntRange.startValue]. */
public fun HalfOpenIntRange.toSequence(nextFunction: (Int) -> Int?): Sequence<Int> =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(startValue) { start ->
			nextFunction(start)?.takeIf { value -> contains(value) }
		}
	}


/** Creates a [HalfOpenIntRange] from `this` (inclusive) to [that] (exclusive). */
public infix fun Int.rangeToExcluding(that: Int): HalfOpenIntRange =
	HalfOpenIntRange(start = this, endExclusive = that)

package io.fluidsonic.stdlib

// TODO add all special cases for Char, Double, Float, Long, UInt, ULong


/** A range defined by a [start] (inclusive) and [endExclusive] bound. */
public interface HalfOpenRange<Bound : Comparable<Bound>> {

	/** The inclusive lower bound. */
	public val start: Bound

	/** The exclusive upper bound. */
	public val endExclusive: Bound


	/** Returns `true` if [value] is within this range. */
	public operator fun contains(value: Bound): Boolean =
		value >= start && value < endExclusive


	/** Returns `true` if this range is empty (start >= endExclusive). */
	public fun isEmpty(): Boolean =
		start >= endExclusive


	public companion object
}


/** Returns `true` if this range fully contains [other]. */
public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.contains(other: HalfOpenRange<Bound>): Boolean =
	contains(other.start) && other.endExclusive <= endExclusive


/** Returns a new range with start and end swapped. */
public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.flipped(): HalfOpenRange<Bound> =
	endExclusive rangeToExcluding start


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.intersection(other: HalfOpenRange<Bound>): HalfOpenRange<Bound>? =
	overlaps(other).thenTake { maxOf(start, other.start) rangeToExcluding minOf(endExclusive, other.endExclusive) }


/** Returns `true` if this range overlaps with [other]. */
public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.overlaps(other: HalfOpenRange<Bound>): Boolean =
	contains(other.start) || other.contains(start)


/** Transforms both bounds using [transform] and returns a new [HalfOpenIntRange]. */
public inline fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.mapBounds(transform: (Bound) -> Int): HalfOpenIntRange =
	transform(start) rangeToExcluding transform(endExclusive)


/** Transforms both bounds using [transform] and returns a new [HalfOpenRange]. */
public inline fun <Bound : Comparable<Bound>, R : Comparable<R>> HalfOpenRange<Bound>.mapBounds(transform: (Bound) -> R): HalfOpenRange<R> =
	transform(start) rangeToExcluding transform(endExclusive)


/** Returns the portions of this range that remain after subtracting [rangeToSubtract]. */
public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.subtracting(rangeToSubtract: HalfOpenRange<Bound>): List<HalfOpenRange<Bound>> {
	if (rangeToSubtract.start >= endExclusive || rangeToSubtract.endExclusive <= start)
		return listOf(this)

	val result = mutableListOf<HalfOpenRange<Bound>>()
	if (rangeToSubtract.start > start)
		result.add(start rangeToExcluding rangeToSubtract.start)
	if (rangeToSubtract.endExclusive < endExclusive)
		result.add(rangeToSubtract.endExclusive rangeToExcluding endExclusive)

	return result
}


/** Converts this range to a [Sequence] by repeatedly applying [nextFunction] starting from [HalfOpenRange.start]. */
public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.toSequence(nextFunction: (Bound) -> Bound?): Sequence<Bound> =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(start) { start ->
			nextFunction(start)?.takeIf { value -> contains(value) }
		}
	}


private class HalfOpenComparableRange<Bound : Comparable<Bound>>(
	override val start: Bound,
	override val endExclusive: Bound
) : HalfOpenRange<Bound> {

	operator fun component1() =
		start


	operator fun component2() =
		endExclusive


	override fun equals(other: Any?) =
		this === other || (other is HalfOpenRange<*> && start == other.start && endExclusive == other.endExclusive)


	override fun hashCode(): Int {
		var result = start.hashCode()
		result = 31 * result + endExclusive.hashCode()

		return result
	}


	override fun toString() =
		"$start ..< $endExclusive"
}


/** Creates a [HalfOpenRange] from `this` (inclusive) to [that] (exclusive). */
public infix fun <Bound : Comparable<Bound>> Bound.rangeToExcluding(that: Bound): HalfOpenRange<Bound> =
	HalfOpenComparableRange(this, that)

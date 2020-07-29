package io.fluidsonic.stdlib

// TODO add all special cases for Char, Double, Float, Long, UInt, ULong


public interface HalfOpenRange<Bound : Comparable<Bound>> {

	public val start: Bound
	public val endExclusive: Bound


	public operator fun contains(value: Bound): Boolean =
		value >= start && value < endExclusive


	public fun isEmpty(): Boolean =
		start >= endExclusive


	public companion object
}


public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.contains(other: HalfOpenRange<Bound>): Boolean =
	contains(other.start) && other.endExclusive <= endExclusive


public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.flipped(): HalfOpenRange<Bound> =
	endExclusive rangeToExcluding start


public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.intersection(other: HalfOpenRange<Bound>): HalfOpenRange<Bound>? =
	overlaps(other).thenTake { maxOf(start, other.start) rangeToExcluding minOf(endExclusive, other.endExclusive) }


public fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.overlaps(other: HalfOpenRange<Bound>): Boolean =
	contains(other.start) || other.contains(start)


public inline fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.mapBounds(transform: (Bound) -> Int): HalfOpenIntRange =
	transform(start) rangeToExcluding transform(endExclusive)


public inline fun <Bound : Comparable<Bound>, R : Comparable<R>> HalfOpenRange<Bound>.mapBounds(transform: (Bound) -> R): HalfOpenRange<R> =
	transform(start) rangeToExcluding transform(endExclusive)


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


	override fun hashCode() =
		hash { start x endExclusive }


	override fun toString() =
		"$start ..< $endExclusive"
}


public infix fun <Bound : Comparable<Bound>> Bound.rangeToExcluding(that: Bound): HalfOpenRange<Bound> =
	HalfOpenComparableRange(this, that)

package com.github.fluidsonic.fluid.stdlib

// TODO add all special cases for Char, Double, Float, Long, UInt, ULong


interface HalfOpenRange<Bound : Comparable<Bound>> {

	val start: Bound
	val endExclusive: Bound


	operator fun contains(value: Bound) =
		value >= start && value < endExclusive


	fun isEmpty() = start >= endExclusive


	companion object
}


fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.contains(other: HalfOpenRange<Bound>) =
	contains(other.start) && other.endExclusive <= endExclusive


fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.flipped() =
	endExclusive rangeToExcluding start


fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.intersection(other: HalfOpenRange<Bound>) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endExclusive, other.endExclusive) }


fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.overlaps(other: HalfOpenRange<Bound>) =
	contains(other.start) || other.contains(start)


fun <Bound : Comparable<Bound>, R : Comparable<R>> HalfOpenRange<Bound>.mapBounds(transform: (Bound) -> R) =
	transform(start) rangeToExcluding transform(endExclusive)


fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.subtracting(rangeToSubtract: HalfOpenRange<Bound>): List<HalfOpenRange<Bound>> {
	if (rangeToSubtract.start >= endExclusive || rangeToSubtract.endExclusive <= start) {
		return listOf(this)
	}

	val result = mutableListOf<HalfOpenRange<Bound>>()
	if (rangeToSubtract.start > start) {
		result.add(start rangeToExcluding rangeToSubtract.start)
	}
	if (rangeToSubtract.endExclusive < endExclusive) {
		result.add(rangeToSubtract.endExclusive rangeToExcluding endExclusive)
	}

	return result
}


fun <Bound : Comparable<Bound>> HalfOpenRange<Bound>.toSequence(nextFunction: (Bound) -> Bound?) =
	generateSequence(start) { start ->
		nextFunction(start)?.takeIf { value -> contains(value) }
	}


private data class HalfOpenComparableRange<Bound : Comparable<Bound>>(
	override val start: Bound,
	override val endExclusive: Bound
) : HalfOpenRange<Bound> {

	init {
		require(start <= endExclusive)
	}


	override fun toString() = "$start ..< $endExclusive"
}


infix fun <Bound : Comparable<Bound>> Bound.rangeToExcluding(that: Bound): HalfOpenRange<Bound> =
	HalfOpenComparableRange(this, that)

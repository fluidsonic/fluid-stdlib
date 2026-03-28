package io.fluidsonic.stdlib

import kotlin.jvm.*


/** Destructuring component for [ClosedRange.start]. */
public operator fun <Bound : Comparable<Bound>> ClosedRange<Bound>.component1(): Bound =
	start


/** Destructuring component for [ClosedRange.endInclusive]. */
public operator fun <Bound : Comparable<Bound>> ClosedRange<Bound>.component2(): Bound =
	endInclusive


/** Returns a new range with start and end swapped. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.flipped(): ClosedRange<Bound> =
	endInclusive .. start


/** Transforms both bounds using [transform]. */
public fun <Bound : Comparable<Bound>, R : Comparable<R>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> R): ClosedRange<R> =
	transform(start) .. transform(endInclusive)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToDouble")
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Double): ClosedFloatingPointRange<Double> =
	transform(start) .. transform(endInclusive)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToFloat")
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Float): ClosedFloatingPointRange<Float> =
	transform(start) .. transform(endInclusive)


/** Transforms both bounds using [transform]. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Int): IntRange =
	transform(start) .. transform(endInclusive)


/** Transforms both bounds using [transform]. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Long): LongRange =
	transform(start) .. transform(endInclusive)


/** Transforms both bounds using [transform]. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> UInt): UIntRange =
	transform(start) .. transform(endInclusive)


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.intersection(other: ClosedRange<Bound>): ClosedRange<Bound>? =
	overlaps(other).thenTake { maxOf(start, other.start) .. minOf(endInclusive, other.endInclusive) }


/** Returns `true` if this range overlaps with [other]. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.overlaps(other: ClosedRange<Bound>): Boolean =
	contains(other.start) || other.contains(start)


/** Converts this range to a [Sequence] by repeatedly applying [next] starting from [ClosedRange.start]. */
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.toSequence(next: (Bound) -> Bound?): Sequence<Bound> =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(start) { start ->
			next(start)?.takeIf { value -> contains(value) }
		}
	}

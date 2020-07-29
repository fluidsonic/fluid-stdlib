package io.fluidsonic.stdlib

import kotlin.jvm.*


public operator fun <Bound : Comparable<Bound>> ClosedRange<Bound>.component1(): Bound =
	start


public operator fun <Bound : Comparable<Bound>> ClosedRange<Bound>.component2(): Bound =
	endInclusive


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.flipped(): ClosedRange<Bound> =
	endInclusive .. start


public fun <Bound : Comparable<Bound>, R : Comparable<R>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> R): ClosedRange<R> =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToDouble")
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Double): ClosedFloatingPointRange<Double> =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToFloat")
public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Float): ClosedFloatingPointRange<Float> =
	transform(start) .. transform(endInclusive)


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Int): IntRange =
	transform(start) .. transform(endInclusive)


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Long): LongRange =
	transform(start) .. transform(endInclusive)


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> UInt): UIntRange =
	transform(start) .. transform(endInclusive)


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> ULong): ULongRange =
	transform(start) .. transform(endInclusive)


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.intersection(other: ClosedRange<Bound>): HalfOpenRange<Bound>? =
	overlaps(other).thenTake { maxOf(start, other.start) rangeToExcluding minOf(endInclusive, other.endInclusive) }


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.overlaps(other: ClosedRange<Bound>): Boolean =
	contains(other.start) || other.contains(start)


public fun <Bound : Comparable<Bound>> ClosedRange<Bound>.toSequence(next: (Bound) -> Bound?): Sequence<Bound> =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(start) { start ->
			next(start)?.takeIf { value -> contains(value) }
		}
	}

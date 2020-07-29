package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


public operator fun ULongRange.component1(): ULong =
	first


public operator fun ULongRange.component2(): ULong =
	last


public fun ULongRange.flipped(): ULongRange =
	last .. first


public fun <R : Comparable<R>> ULongRange.mapBounds(transform: (ULong) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
public fun ULongRange.mapBounds(transform: (ULong) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
public fun ULongRange.mapBounds(transform: (ULong) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


public fun ULongRange.mapBounds(transform: (ULong) -> Int): IntRange =
	transform(first) .. transform(last)


public fun ULongRange.mapBounds(transform: (ULong) -> Long): LongRange =
	transform(first) .. transform(last)


public fun ULongRange.mapBounds(transform: (ULong) -> UInt): UIntRange =
	transform(first) .. transform(last)


public fun ULongRange.mapBounds(transform: (ULong) -> ULong): ULongRange =
	transform(first) .. transform(last)


public fun ULongRange.intersection(other: ULongRange): HalfOpenRange<ULong>? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


public fun ULongRange.overlaps(other: ULongRange): Boolean =
	contains(other.first) || other.contains(first)

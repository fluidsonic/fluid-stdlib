package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


public operator fun UIntRange.component1(): UInt =
	first


public operator fun UIntRange.component2(): UInt =
	last


public fun UIntRange.flipped(): UIntRange =
	last .. first


public fun <R : Comparable<R>> UIntRange.mapBounds(transform: (UInt) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
public fun UIntRange.mapBounds(transform: (UInt) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
public fun UIntRange.mapBounds(transform: (UInt) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


public fun UIntRange.mapBounds(transform: (UInt) -> Int): IntRange =
	transform(first) .. transform(last)


public fun UIntRange.mapBounds(transform: (UInt) -> Long): LongRange =
	transform(first) .. transform(last)


public fun UIntRange.mapBounds(transform: (UInt) -> UInt): UIntRange =
	transform(first) .. transform(last)


public fun UIntRange.mapBounds(transform: (UInt) -> ULong): ULongRange =
	transform(first) .. transform(last)


public fun UIntRange.intersection(other: UIntRange): HalfOpenRange<UInt>? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


public fun UIntRange.overlaps(other: UIntRange): Boolean =
	contains(other.first) || other.contains(first)

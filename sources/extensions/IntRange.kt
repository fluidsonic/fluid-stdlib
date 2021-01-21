package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


public operator fun IntRange.component1(): Int =
	first


public operator fun IntRange.component2(): Int =
	last


public fun IntRange.flipped(): IntRange =
	last .. first


public fun <R : Comparable<R>> IntRange.mapBounds(transform: (Int) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
public fun IntRange.mapBounds(transform: (Int) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
public fun IntRange.mapBounds(transform: (Int) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


public fun IntRange.mapBounds(transform: (Int) -> Int): IntRange =
	transform(first) .. transform(last)


public fun IntRange.mapBounds(transform: (Int) -> Long): LongRange =
	transform(first) .. transform(last)


public fun IntRange.mapBounds(transform: (Int) -> UInt): UIntRange =
	transform(first) .. transform(last)


public fun IntRange.mapBounds(transform: (Int) -> ULong): ULongRange =
	transform(first) .. transform(last)


public fun IntRange.intersection(other: IntRange): HalfOpenIntRange? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


public fun IntRange.overlaps(other: IntRange): Boolean =
	contains(other.first) || other.contains(first)

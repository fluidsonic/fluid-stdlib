package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


public operator fun LongRange.component1(): Long =
	first


public operator fun LongRange.component2(): Long =
	last


public fun LongRange.flipped(): LongRange =
	last .. first


public fun <R : Comparable<R>> LongRange.mapBounds(transform: (Long) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
public fun LongRange.mapBounds(transform: (Long) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
public fun LongRange.mapBounds(transform: (Long) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


public fun LongRange.mapBounds(transform: (Long) -> Int): IntRange =
	transform(first) .. transform(last)


public fun LongRange.mapBounds(transform: (Long) -> Long): LongRange =
	transform(first) .. transform(last)


public fun LongRange.mapBounds(transform: (Long) -> UInt): UIntRange =
	transform(first) .. transform(last)


public fun LongRange.mapBounds(transform: (Long) -> ULong): ULongRange =
	transform(first) .. transform(last)


public fun LongRange.intersection(other: LongRange): HalfOpenRange<Long>? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


public fun LongRange.overlaps(other: LongRange): Boolean =
	contains(other.first) || other.contains(first)

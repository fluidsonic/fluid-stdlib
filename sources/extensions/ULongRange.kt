package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


/** Destructuring component for [first]. */
public operator fun ULongRange.component1(): ULong =
	first


/** Destructuring component for [last]. */
public operator fun ULongRange.component2(): ULong =
	last


/** Returns a new range with first and last swapped. */
public fun ULongRange.flipped(): ULongRange =
	last .. first


/** Transforms both bounds using [transform]. */
public fun <R : Comparable<R>> ULongRange.mapBounds(transform: (ULong) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToDouble")
public fun ULongRange.mapBounds(transform: (ULong) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToFloat")
public fun ULongRange.mapBounds(transform: (ULong) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun ULongRange.mapBounds(transform: (ULong) -> Int): IntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun ULongRange.mapBounds(transform: (ULong) -> Long): LongRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun ULongRange.mapBounds(transform: (ULong) -> UInt): UIntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun ULongRange.mapBounds(transform: (ULong) -> ULong): ULongRange =
	transform(first) .. transform(last)


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun ULongRange.intersection(other: ULongRange): HalfOpenRange<ULong>? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


/** Returns `true` if this range overlaps with [other]. */
public fun ULongRange.overlaps(other: ULongRange): Boolean =
	contains(other.first) || other.contains(first)

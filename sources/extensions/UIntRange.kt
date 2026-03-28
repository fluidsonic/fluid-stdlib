package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


/** Destructuring component for [first]. */
public operator fun UIntRange.component1(): UInt =
	first


/** Destructuring component for [last]. */
public operator fun UIntRange.component2(): UInt =
	last


/** Returns a new range with first and last swapped. */
public fun UIntRange.flipped(): UIntRange =
	last .. first


/** Transforms both bounds using [transform]. */
public fun <R : Comparable<R>> UIntRange.mapBounds(transform: (UInt) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToDouble")
public fun UIntRange.mapBounds(transform: (UInt) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToFloat")
public fun UIntRange.mapBounds(transform: (UInt) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun UIntRange.mapBounds(transform: (UInt) -> Int): IntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun UIntRange.mapBounds(transform: (UInt) -> Long): LongRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun UIntRange.mapBounds(transform: (UInt) -> UInt): UIntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun UIntRange.mapBounds(transform: (UInt) -> ULong): ULongRange =
	transform(first) .. transform(last)


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun UIntRange.intersection(other: UIntRange): HalfOpenRange<UInt>? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


/** Returns `true` if this range overlaps with [other]. */
public fun UIntRange.overlaps(other: UIntRange): Boolean =
	contains(other.first) || other.contains(first)

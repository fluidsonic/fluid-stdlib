package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


/** Destructuring component for [first]. */
public operator fun IntRange.component1(): Int =
	first


/** Destructuring component for [last]. */
public operator fun IntRange.component2(): Int =
	last


/** Returns a new range with first and last swapped. */
public fun IntRange.flipped(): IntRange =
	last .. first


/** Transforms both bounds using [transform]. */
public fun <R : Comparable<R>> IntRange.mapBounds(transform: (Int) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToDouble")
public fun IntRange.mapBounds(transform: (Int) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToFloat")
public fun IntRange.mapBounds(transform: (Int) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun IntRange.mapBounds(transform: (Int) -> Int): IntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun IntRange.mapBounds(transform: (Int) -> Long): LongRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun IntRange.mapBounds(transform: (Int) -> UInt): UIntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun IntRange.mapBounds(transform: (Int) -> ULong): ULongRange =
	transform(first) .. transform(last)


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun IntRange.intersection(other: IntRange): HalfOpenIntRange? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


/** Returns `true` if this range overlaps with [other]. */
public fun IntRange.overlaps(other: IntRange): Boolean =
	contains(other.first) || other.contains(first)

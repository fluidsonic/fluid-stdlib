package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


/** Destructuring component for [first]. */
public operator fun LongRange.component1(): Long =
	first


/** Destructuring component for [last]. */
public operator fun LongRange.component2(): Long =
	last


/** Returns a new range with first and last swapped. */
public fun LongRange.flipped(): LongRange =
	last .. first


/** Transforms both bounds using [transform]. */
public fun <R : Comparable<R>> LongRange.mapBounds(transform: (Long) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToDouble")
public fun LongRange.mapBounds(transform: (Long) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToFloat")
public fun LongRange.mapBounds(transform: (Long) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun LongRange.mapBounds(transform: (Long) -> Int): IntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun LongRange.mapBounds(transform: (Long) -> Long): LongRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun LongRange.mapBounds(transform: (Long) -> UInt): UIntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun LongRange.mapBounds(transform: (Long) -> ULong): ULongRange =
	transform(first) .. transform(last)


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun LongRange.intersection(other: LongRange): HalfOpenRange<Long>? =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


/** Returns `true` if this range overlaps with [other]. */
public fun LongRange.overlaps(other: LongRange): Boolean =
	contains(other.first) || other.contains(first)

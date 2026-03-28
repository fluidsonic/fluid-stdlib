package io.fluidsonic.stdlib

import kotlin.jvm.*


/** Destructuring component for [first]. */
public operator fun CharRange.component1(): Char =
	first


/** Destructuring component for [last]. */
public operator fun CharRange.component2(): Char =
	last


/** Returns a new range with first and last swapped. */
public fun CharRange.flipped(): CharRange =
	last .. first


/** Transforms both bounds using [transform]. */
public fun <R : Comparable<R>> CharRange.mapBounds(transform: (Char) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToDouble")
public fun CharRange.mapBounds(transform: (Char) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
@JvmName("mapBoundsToFloat")
public fun CharRange.mapBounds(transform: (Char) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun CharRange.mapBounds(transform: (Char) -> Int): IntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun CharRange.mapBounds(transform: (Char) -> Long): LongRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun CharRange.mapBounds(transform: (Char) -> UInt): UIntRange =
	transform(first) .. transform(last)


/** Transforms both bounds using [transform]. */
public fun CharRange.mapBounds(transform: (Char) -> ULong): ULongRange =
	transform(first) .. transform(last)


/** Returns the intersection with [other], or `null` if the ranges do not overlap. */
public fun CharRange.intersection(other: CharRange): HalfOpenRange<Char>? =
	overlaps(other).thenTake { maxOf(first, other.first) rangeToExcluding minOf(last, other.last) }


/** Returns `true` if this range overlaps with [other]. */
public fun CharRange.overlaps(other: CharRange): Boolean =
	contains(other.first) || other.contains(first)

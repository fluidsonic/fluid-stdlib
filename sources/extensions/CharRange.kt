package io.fluidsonic.stdlib

import kotlin.jvm.*


public operator fun CharRange.component1(): Char =
	first


public operator fun CharRange.component2(): Char =
	last


public fun CharRange.flipped(): CharRange =
	last .. first


public fun <R : Comparable<R>> CharRange.mapBounds(transform: (Char) -> R): ClosedRange<R> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
public fun CharRange.mapBounds(transform: (Char) -> Double): ClosedFloatingPointRange<Double> =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
public fun CharRange.mapBounds(transform: (Char) -> Float): ClosedFloatingPointRange<Float> =
	transform(first) .. transform(last)


public fun CharRange.mapBounds(transform: (Char) -> Int): IntRange =
	transform(first) .. transform(last)


public fun CharRange.mapBounds(transform: (Char) -> Long): LongRange =
	transform(first) .. transform(last)


public fun CharRange.mapBounds(transform: (Char) -> UInt): UIntRange =
	transform(first) .. transform(last)


public fun CharRange.mapBounds(transform: (Char) -> ULong): ULongRange =
	transform(first) .. transform(last)


public fun CharRange.intersection(other: CharRange): HalfOpenRange<Char>? =
	overlaps(other).thenTake { maxOf(first, other.first) rangeToExcluding minOf(last, other.last) }


public fun CharRange.overlaps(other: CharRange): Boolean =
	contains(other.first) || other.contains(first)

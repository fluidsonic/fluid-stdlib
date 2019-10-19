package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


operator fun LongRange.component1() =
	first


operator fun LongRange.component2() =
	last


fun LongRange.flipped() =
	last .. first


fun <R : Comparable<R>> LongRange.mapBounds(transform: (Long) -> R) =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
fun LongRange.mapBounds(transform: (Long) -> Double) =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
fun LongRange.mapBounds(transform: (Long) -> Float) =
	transform(first) .. transform(last)


fun LongRange.mapBounds(transform: (Long) -> Int) =
	transform(first) .. transform(last)


fun LongRange.mapBounds(transform: (Long) -> Long) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun LongRange.mapBounds(transform: (Long) -> UInt) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun LongRange.mapBounds(transform: (Long) -> ULong) =
	transform(first) .. transform(last)


fun LongRange.intersection(other: LongRange) =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


fun LongRange.overlaps(other: LongRange) =
	contains(other.first) || other.contains(first)

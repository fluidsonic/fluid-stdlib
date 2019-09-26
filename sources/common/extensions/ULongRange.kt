package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.*
import kotlin.math.*


@ExperimentalUnsignedTypes
operator fun ULongRange.component1() =
	first


@ExperimentalUnsignedTypes
operator fun ULongRange.component2() =
	last


@ExperimentalUnsignedTypes
fun ULongRange.flipped() =
	last .. first


@ExperimentalUnsignedTypes
fun <R : Comparable<R>> ULongRange.mapBounds(transform: (ULong) -> R) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToDouble")
fun ULongRange.mapBounds(transform: (ULong) -> Double) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToFloat")
fun ULongRange.mapBounds(transform: (ULong) -> Float) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> Int) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> Long) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> UInt) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> ULong) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun ULongRange.intersection(other: ULongRange) =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


@ExperimentalUnsignedTypes
fun ULongRange.overlaps(other: ULongRange) =
	contains(other.first) || other.contains(first)

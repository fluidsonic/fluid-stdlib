package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.JvmName
import kotlin.math.max
import kotlin.math.min


@ExperimentalUnsignedTypes
fun ULongRange.flipped() =
	endInclusive .. start


@ExperimentalUnsignedTypes
fun <R : Comparable<R>> ULongRange.mapBounds(transform: (ULong) -> R) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToDouble")
fun ULongRange.mapBounds(transform: (ULong) -> Double) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToFloat")
fun ULongRange.mapBounds(transform: (ULong) -> Float) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> Int) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun ULongRange.mapBounds(transform: (ULong) -> ULong) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun ULongRange.intersection(other: ULongRange) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


@ExperimentalUnsignedTypes
fun ULongRange.overlaps(other: ULongRange) =
	contains(other.start) || other.contains(start)

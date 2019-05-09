package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.*
import kotlin.math.*


@JvmName("flippedDouble")
fun ClosedFloatingPointRange<Double>.flipped() =
	endInclusive .. start


@JvmName("flippedFloat")
fun ClosedFloatingPointRange<Float>.flipped() =
	endInclusive .. start


@JvmName("mapFloatBounds")
fun <R : Comparable<R>> ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> R) =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
fun <R : Comparable<R>> ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> R) =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBoundsToDouble")
fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Double) =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBoundsToDouble")
fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Double) =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBoundsToFloat")
fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Float) =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBoundsToFloat")
fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Float) =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBounds")
fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Int) =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Int) =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBounds")
fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Long) =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapFloatBounds")
fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapDoubleBounds")
fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapFloatBounds")
fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> ULong) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapDoubleBounds")
fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> ULong) =
	transform(start) .. transform(endInclusive)


@JvmName("intersectionFloat")
fun ClosedFloatingPointRange<Float>.intersection(other: ClosedFloatingPointRange<Float>) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


@JvmName("intersectionDouble")
fun ClosedFloatingPointRange<Double>.intersection(other: ClosedFloatingPointRange<Double>) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


@JvmName("overlapsFloat")
fun ClosedFloatingPointRange<Float>.overlaps(other: ClosedFloatingPointRange<Float>) =
	contains(other.start) || other.contains(start)


@JvmName("overlapsDouble")
fun ClosedFloatingPointRange<Double>.overlaps(other: ClosedFloatingPointRange<Double>) =
	contains(other.start) || other.contains(start)

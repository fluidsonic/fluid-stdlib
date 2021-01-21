package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


@JvmName("component1Double")
public operator fun ClosedFloatingPointRange<Double>.component1(): Double =
	start


@JvmName("component1Float")
public operator fun ClosedFloatingPointRange<Float>.component1(): Float =
	start


@JvmName("component2Double")
public operator fun ClosedFloatingPointRange<Double>.component2(): Double =
	endInclusive


@JvmName("component2Float")
public operator fun ClosedFloatingPointRange<Float>.component2(): Float =
	endInclusive


@JvmName("flippedDouble")
public fun ClosedFloatingPointRange<Double>.flipped(): ClosedFloatingPointRange<Double> =
	endInclusive .. start


@JvmName("flippedFloat")
public fun ClosedFloatingPointRange<Float>.flipped(): ClosedFloatingPointRange<Float> =
	endInclusive .. start


@JvmName("mapFloatBounds")
public fun <R : Comparable<R>> ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> R): ClosedRange<R> =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
public fun <R : Comparable<R>> ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> R): ClosedRange<R> =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBoundsToDouble")
public fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Double): ClosedFloatingPointRange<Double> =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBoundsToDouble")
public fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Double): ClosedFloatingPointRange<Double> =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBoundsToFloat")
public fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Float): ClosedFloatingPointRange<Float> =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBoundsToFloat")
public fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Float): ClosedFloatingPointRange<Float> =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBounds")
public fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Int): IntRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
public fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Int): IntRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBounds")
public fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> Long): LongRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
public fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> Long): LongRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBounds")
public fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> UInt): UIntRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
public fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> UInt): UIntRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapFloatBounds")
public fun ClosedFloatingPointRange<Float>.mapBounds(transform: (Float) -> ULong): ULongRange =
	transform(start) .. transform(endInclusive)


@JvmName("mapDoubleBounds")
public fun ClosedFloatingPointRange<Double>.mapBounds(transform: (Double) -> ULong): ULongRange =
	transform(start) .. transform(endInclusive)


@JvmName("intersectionFloat")
public fun ClosedFloatingPointRange<Float>.intersection(other: ClosedFloatingPointRange<Float>): HalfOpenRange<Float>? =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


@JvmName("intersectionDouble")
public fun ClosedFloatingPointRange<Double>.intersection(other: ClosedFloatingPointRange<Double>): HalfOpenRange<Double>? =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


@JvmName("overlapsFloat")
public fun ClosedFloatingPointRange<Float>.overlaps(other: ClosedFloatingPointRange<Float>): Boolean =
	contains(other.start) || other.contains(start)


@JvmName("overlapsDouble")
public fun ClosedFloatingPointRange<Double>.overlaps(other: ClosedFloatingPointRange<Double>): Boolean =
	contains(other.start) || other.contains(start)

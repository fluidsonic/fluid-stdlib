package com.github.fluidsonic.fluid.stdlib


fun IntRange.flipped() =
	endInclusive .. start


fun <R : Comparable<R>> IntRange.mapBounds(transform: (Int) -> R) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToDouble")
fun IntRange.mapBounds(transform: (Int) -> Double) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToFloat")
fun IntRange.mapBounds(transform: (Int) -> Float) =
	transform(start) .. transform(endInclusive)


fun IntRange.mapBounds(transform: (Int) -> Int) =
	transform(start) .. transform(endInclusive)


fun IntRange.mapBounds(transform: (Int) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun IntRange.mapBounds(transform: (Int) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun IntRange.mapBounds(transform: (Int) -> ULong) =
	transform(start) .. transform(endInclusive)


fun IntRange.intersection(other: IntRange) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


fun IntRange.overlaps(other: IntRange) =
	contains(other.start) || other.contains(start)

package com.github.fluidsonic.fluid.stdlib


fun LongRange.flipped() =
	endInclusive .. start


fun <R : Comparable<R>> LongRange.mapBounds(transform: (Long) -> R) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToDouble")
fun LongRange.mapBounds(transform: (Long) -> Double) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToFloat")
fun LongRange.mapBounds(transform: (Long) -> Float) =
	transform(start) .. transform(endInclusive)


fun LongRange.mapBounds(transform: (Long) -> Int) =
	transform(start) .. transform(endInclusive)


fun LongRange.mapBounds(transform: (Long) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun LongRange.mapBounds(transform: (Long) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun LongRange.mapBounds(transform: (Long) -> ULong) =
	transform(start) .. transform(endInclusive)


fun LongRange.intersection(other: LongRange) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


fun LongRange.overlaps(other: LongRange) =
	contains(other.start) || other.contains(start)

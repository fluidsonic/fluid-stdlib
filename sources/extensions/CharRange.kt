package com.github.fluidsonic.fluid.stdlib


fun CharRange.flipped() =
	endInclusive .. start


fun <R : Comparable<R>> CharRange.mapBounds(transform: (Char) -> R) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToDouble")
fun CharRange.mapBounds(transform: (Char) -> Double) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToFloat")
fun CharRange.mapBounds(transform: (Char) -> Float) =
	transform(start) .. transform(endInclusive)


fun CharRange.mapBounds(transform: (Char) -> Int) =
	transform(start) .. transform(endInclusive)


fun CharRange.mapBounds(transform: (Char) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun CharRange.mapBounds(transform: (Char) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun CharRange.mapBounds(transform: (Char) -> ULong) =
	transform(start) .. transform(endInclusive)


fun CharRange.intersection(other: CharRange) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


fun CharRange.overlaps(other: CharRange) =
	contains(other.start) || other.contains(start)

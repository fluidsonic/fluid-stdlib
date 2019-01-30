package com.github.fluidsonic.fluid.stdlib


@ExperimentalUnsignedTypes
fun UIntRange.flipped() =
	endInclusive .. start


@ExperimentalUnsignedTypes
fun <R : Comparable<R>> UIntRange.mapBounds(transform: (UInt) -> R) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToDouble")
fun UIntRange.mapBounds(transform: (UInt) -> Double) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToFloat")
fun UIntRange.mapBounds(transform: (UInt) -> Float) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> Int) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> ULong) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun UIntRange.intersection(other: UIntRange) =
	overlaps(other).thenTake { max(start, other.start) rangeToExcluding min(endInclusive, other.endInclusive) }


@ExperimentalUnsignedTypes
fun UIntRange.overlaps(other: UIntRange) =
	contains(other.start) || other.contains(start)

package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.*
import kotlin.math.*


@ExperimentalUnsignedTypes
fun UIntRange.flipped() =
	last .. first


@ExperimentalUnsignedTypes
fun <R : Comparable<R>> UIntRange.mapBounds(transform: (UInt) -> R) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToDouble")
fun UIntRange.mapBounds(transform: (UInt) -> Double) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
@JvmName("mapBoundsToFloat")
fun UIntRange.mapBounds(transform: (UInt) -> Float) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> Int) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> Long) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> UInt) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun UIntRange.mapBounds(transform: (UInt) -> ULong) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun UIntRange.intersection(other: UIntRange) =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


@ExperimentalUnsignedTypes
fun UIntRange.overlaps(other: UIntRange) =
	contains(other.first) || other.contains(first)

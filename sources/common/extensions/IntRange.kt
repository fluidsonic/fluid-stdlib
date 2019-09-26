package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.*
import kotlin.math.*


operator fun IntRange.component1() =
	first


operator fun IntRange.component2() =
	last


fun IntRange.flipped() =
	last .. first


fun <R : Comparable<R>> IntRange.mapBounds(transform: (Int) -> R) =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
fun IntRange.mapBounds(transform: (Int) -> Double) =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
fun IntRange.mapBounds(transform: (Int) -> Float) =
	transform(first) .. transform(last)


fun IntRange.mapBounds(transform: (Int) -> Int) =
	transform(first) .. transform(last)


fun IntRange.mapBounds(transform: (Int) -> Long) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun IntRange.mapBounds(transform: (Int) -> UInt) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun IntRange.mapBounds(transform: (Int) -> ULong) =
	transform(first) .. transform(last)


fun IntRange.intersection(other: IntRange) =
	overlaps(other).thenTake { max(first, other.first) rangeToExcluding min(last, other.last) }


fun IntRange.overlaps(other: IntRange) =
	contains(other.first) || other.contains(first)

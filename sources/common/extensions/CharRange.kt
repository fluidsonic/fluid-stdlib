package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.*


operator fun CharRange.component1() =
	first


operator fun CharRange.component2() =
	last


fun CharRange.flipped() =
	last .. first


fun <R : Comparable<R>> CharRange.mapBounds(transform: (Char) -> R) =
	transform(first) .. transform(last)


@JvmName("mapBoundsToDouble")
fun CharRange.mapBounds(transform: (Char) -> Double) =
	transform(first) .. transform(last)


@JvmName("mapBoundsToFloat")
fun CharRange.mapBounds(transform: (Char) -> Float) =
	transform(first) .. transform(last)


fun CharRange.mapBounds(transform: (Char) -> Int) =
	transform(first) .. transform(last)


fun CharRange.mapBounds(transform: (Char) -> Long) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun CharRange.mapBounds(transform: (Char) -> UInt) =
	transform(first) .. transform(last)


@ExperimentalUnsignedTypes
fun CharRange.mapBounds(transform: (Char) -> ULong) =
	transform(first) .. transform(last)


fun CharRange.intersection(other: CharRange) =
	overlaps(other).thenTake { maxOf(first, other.first) rangeToExcluding minOf(last, other.last) }


fun CharRange.overlaps(other: CharRange) =
	contains(other.first) || other.contains(first)

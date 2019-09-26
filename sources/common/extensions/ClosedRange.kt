package com.github.fluidsonic.fluid.stdlib

import kotlin.jvm.*


operator fun <Bound : Comparable<Bound>> ClosedRange<Bound>.component1() =
	start


operator fun <Bound : Comparable<Bound>> ClosedRange<Bound>.component2() =
	endInclusive


fun <Bound : Comparable<Bound>> ClosedRange<Bound>.flipped() =
	endInclusive .. start


fun <Bound : Comparable<Bound>, R : Comparable<R>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> R) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToDouble")
fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Double) =
	transform(start) .. transform(endInclusive)


@JvmName("mapBoundsToFloat")
fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Float) =
	transform(start) .. transform(endInclusive)


fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Int) =
	transform(start) .. transform(endInclusive)


fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> Long) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> UInt) =
	transform(start) .. transform(endInclusive)


@ExperimentalUnsignedTypes
fun <Bound : Comparable<Bound>> ClosedRange<Bound>.mapBounds(transform: (Bound) -> ULong) =
	transform(start) .. transform(endInclusive)


fun <Bound : Comparable<Bound>> ClosedRange<Bound>.intersection(other: ClosedRange<Bound>) =
	overlaps(other).thenTake { maxOf(start, other.start) rangeToExcluding minOf(endInclusive, other.endInclusive) }


fun <Bound : Comparable<Bound>> ClosedRange<Bound>.overlaps(other: ClosedRange<Bound>) =
	contains(other.start) || other.contains(start)


fun <Bound : Comparable<Bound>> ClosedRange<Bound>.toSequence(next: (Bound) -> Bound?) =
	when {
		isEmpty() -> emptySequence()
		else -> generateSequence(start) { start ->
			next(start)?.takeIf { value -> contains(value) }
		}
	}

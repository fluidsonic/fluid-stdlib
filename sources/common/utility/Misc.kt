package io.fluidsonic.stdlib


@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Int, inRange: IntRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Int, inRange: LongRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Long, inRange: IntRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Long, inRange: LongRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


@Suppress("NOTHING_TO_INLINE")
public inline fun <Value : Comparable<Value>> check(value: Value, inRange: ClosedRange<Value>, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


public fun <T> identity(value: T): T =
	value

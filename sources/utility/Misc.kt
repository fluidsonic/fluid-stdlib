package io.fluidsonic.stdlib


/** Checks that [value] is within [inRange], throwing [IllegalStateException] with [name] if not. */
@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Int, inRange: IntRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


/** Checks that [value] is within [inRange], throwing [IllegalStateException] with [name] if not. */
@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Int, inRange: LongRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


/** Checks that [value] is within [inRange], throwing [IllegalStateException] with [name] if not. */
@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Long, inRange: IntRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


/** Checks that [value] is within [inRange], throwing [IllegalStateException] with [name] if not. */
@Suppress("NOTHING_TO_INLINE")
public inline fun check(value: Long, inRange: LongRange, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


/** Checks that [value] is within [inRange], throwing [IllegalStateException] with [name] if not. */
@Suppress("NOTHING_TO_INLINE")
public inline fun <Value : Comparable<Value>> check(value: Value, inRange: ClosedRange<Value>, name: String): Unit =
	check(value in inRange) { "$name must be in range $inRange: $value" }


/** Returns the given [value] unchanged. Useful as a function reference for identity mappings. */
public fun <T> identity(value: T): T =
	value

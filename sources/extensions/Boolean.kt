package io.fluidsonic.stdlib


/** Returns `true` if this is already `true`, otherwise returns the result of [block]. */
public inline fun Boolean.ifFalse(block: () -> Boolean): Boolean =
	this || block()


/** Returns the result of [block] if this is `true`, otherwise returns `false`. */
public inline fun Boolean.ifTrue(block: () -> Boolean): Boolean =
	this && block()


/** Returns the result of [block] if this is `true`, otherwise returns `null`. */
public inline fun <Result> Boolean.thenTake(block: () -> Result): Result? =
	if (this) block() else null

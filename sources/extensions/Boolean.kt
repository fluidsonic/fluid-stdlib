package io.fluidsonic.stdlib


public inline fun Boolean.ifFalse(block: () -> Boolean): Boolean =
	this || block()


public inline fun Boolean.ifTrue(block: () -> Boolean): Boolean =
	this && block()


public inline fun <Result> Boolean.thenTake(block: () -> Result): Result? =
	if (this) block() else null

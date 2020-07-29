package io.fluidsonic.stdlib


public inline fun <Result> Boolean.thenTake(block: () -> Result): Result? =
	if (this) block() else null

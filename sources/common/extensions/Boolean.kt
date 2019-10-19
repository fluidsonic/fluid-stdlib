package io.fluidsonic.stdlib


inline fun <Result> Boolean.thenTake(block: () -> Result) =
	if (this) block() else null

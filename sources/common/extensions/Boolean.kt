package com.github.fluidsonic.fluid.stdlib


inline fun <Result> Boolean.thenTake(block: () -> Result) =
	if (this) block() else null

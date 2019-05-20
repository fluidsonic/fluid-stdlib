package com.github.fluidsonic.fluid.stdlib

import platform.Foundation.*


fun Duration.toPlatform(): NSTimeInterval =
	seconds.value.toDouble() + (partialNanoseconds.value.toDouble() / 1_000_000_000.0)

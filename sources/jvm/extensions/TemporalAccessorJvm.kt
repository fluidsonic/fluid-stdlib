package com.github.fluidsonic.fluid.stdlib

import java.time.temporal.*


fun TemporalAccessor.toEpochMilli() =
	(getLong(ChronoField.INSTANT_SECONDS) * 1000) + (getLong(ChronoField.NANO_OF_SECOND) / 1000000)

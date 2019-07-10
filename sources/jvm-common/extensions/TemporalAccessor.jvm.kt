package com.github.fluidsonic.fluid.stdlib


fun PlatformTemporalAccessor.toEpochMilli() =
	(getLong(PlatformChronoField.INSTANT_SECONDS) * 1000) + (getLong(PlatformChronoField.NANO_OF_SECOND) / 1000000)

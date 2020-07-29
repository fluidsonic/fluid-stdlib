package io.fluidsonic.stdlib


public fun PlatformTemporalAccessor.toEpochMilli(): Long =
	(getLong(PlatformChronoField.INSTANT_SECONDS) * 1000) + (getLong(PlatformChronoField.NANO_OF_SECOND) / 1000000)

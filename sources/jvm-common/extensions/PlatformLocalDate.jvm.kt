package io.fluidsonic.stdlib


public fun PlatformLocalDate.atEndOfDay(): PlatformLocalDateTime = atTime(PlatformLocalTime.MAX)


public fun PlatformLocalDate.atEndOfDay(zone: PlatformZoneId): PlatformZonedDateTime {
	var localDateTime = atTime(PlatformLocalTime.MAX)
	if (zone !is PlatformZoneOffset) {
		val rules = zone.rules
		val transition = rules.getTransition(localDateTime)
		if (transition != null && transition.isGap) {
			localDateTime = transition.dateTimeBefore
		}
	}

	return PlatformZonedDateTime.of(localDateTime, zone)
}

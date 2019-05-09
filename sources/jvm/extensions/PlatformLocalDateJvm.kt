package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.*
import org.threeten.bp.LocalDate as PlatformLocalDate
import org.threeten.bp.LocalDateTime as PlatformLocalDateTime
import org.threeten.bp.LocalTime as PlatformLocalTime


fun PlatformLocalDate.atEndOfDay(): PlatformLocalDateTime = atTime(PlatformLocalTime.MAX)


fun PlatformLocalDate.atEndOfDay(zone: ZoneId): ZonedDateTime {
	var localDateTime = atTime(PlatformLocalTime.MAX)
	if (zone !is ZoneOffset) {
		val rules = zone.rules
		val transition = rules.getTransition(localDateTime)
		if (transition != null && transition.isGap) {
			localDateTime = transition.dateTimeBefore
		}
	}

	return ZonedDateTime.of(localDateTime, zone)
}

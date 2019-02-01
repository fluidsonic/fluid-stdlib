package com.github.fluidsonic.fluid.stdlib

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime


fun LocalDate.atEndOfDay(): LocalDateTime = atTime(LocalTime.MAX)


fun LocalDate.atEndOfDay(zone: ZoneId): ZonedDateTime {
	var localDateTime = atTime(LocalTime.MAX)
	if (zone !is ZoneOffset) {
		val rules = zone.rules
		val transition = rules.getTransition(localDateTime)
		if (transition != null && transition.isGap) {
			localDateTime = transition.dateTimeBefore
		}
	}

	return ZonedDateTime.of(localDateTime, zone)
}

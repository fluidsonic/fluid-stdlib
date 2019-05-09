package com.github.fluidsonic.fluid.stdlib

import java.time.chrono.*
import java.time.format.*
import java.util.Locale


inline fun Locale.ifInvalid(defaultValue: () -> Locale?) =
	if (language.isNullOrEmpty()) defaultValue() else this


val Locale.usesTwelveHourClock
	get() = DateTimeFormatterBuilder.getLocalizedDateTimePattern(null, FormatStyle.MEDIUM, IsoChronology.INSTANCE, this).contains('a')

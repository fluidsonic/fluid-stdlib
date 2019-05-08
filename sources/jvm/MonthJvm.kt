package com.github.fluidsonic.fluid.stdlib

import org.threeten.bp.Month as PlatformMonth


val Month.platform
	get() = when (this) {
		Month.january -> PlatformMonth.JANUARY
		Month.february -> PlatformMonth.FEBRUARY
		Month.march -> PlatformMonth.MARCH
		Month.april -> PlatformMonth.APRIL
		Month.may -> PlatformMonth.MAY
		Month.june -> PlatformMonth.JUNE
		Month.july -> PlatformMonth.JULY
		Month.august -> PlatformMonth.AUGUST
		Month.september -> PlatformMonth.SEPTEMBER
		Month.october -> PlatformMonth.OCTOBER
		Month.november -> PlatformMonth.NOVEMBER
		Month.december -> PlatformMonth.DECEMBER
	}


fun PlatformMonth.toCommon() = when (this) {
	PlatformMonth.JANUARY -> Month.january
	PlatformMonth.FEBRUARY -> Month.february
	PlatformMonth.MARCH -> Month.march
	PlatformMonth.APRIL -> Month.april
	PlatformMonth.MAY -> Month.may
	PlatformMonth.JUNE -> Month.june
	PlatformMonth.JULY -> Month.july
	PlatformMonth.AUGUST -> Month.august
	PlatformMonth.SEPTEMBER -> Month.september
	PlatformMonth.OCTOBER -> Month.october
	PlatformMonth.NOVEMBER -> Month.november
	PlatformMonth.DECEMBER -> Month.december
}

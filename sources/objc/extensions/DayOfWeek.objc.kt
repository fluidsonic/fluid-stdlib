package com.github.fluidsonic.fluid.stdlib

import com.github.fluidsonic.fluid.time.*
import platform.Foundation.*


actual fun DayOfWeek.displayName(locale: Locale): String {
	val formatter = NSDateFormatter()
	formatter.locale = locale.platform

	@Suppress("UNCHECKED_CAST")
	val names = formatter.standaloneWeekdaySymbols as List<String>

	return when (this) {
		DayOfWeek.monday -> names[1]
		DayOfWeek.tuesday -> names[2]
		DayOfWeek.wednesday -> names[3]
		DayOfWeek.thursday -> names[4]
		DayOfWeek.friday -> names[5]
		DayOfWeek.saturday -> names[6]
		DayOfWeek.sunday -> names[0]
	}
}

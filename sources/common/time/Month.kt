package com.github.fluidsonic.fluid.stdlib


enum class Month {

	january,
	february,
	march,
	april,
	may,
	june,
	july,
	august,
	september,
	october,
	november,
	december;


	companion object {

		fun of(value: Int) =
			(value in 1 .. 12).thenTake { values()[value - 1] }
	}
}


@Suppress("NAMED_ARGUMENTS_NOT_ALLOWED")
fun Month.Companion.now(clock: Clock = Clock.system, timeZone: TimeZone = clock.timeZone) =
	LocalDate.now(clock = clock, timeZone = timeZone).month


fun Month(value: Int) =
	Month.of(value) ?: error("invalid month value: $value")


val Month.value
	get() = ordinal + 1

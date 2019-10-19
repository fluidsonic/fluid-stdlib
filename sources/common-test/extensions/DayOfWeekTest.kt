package tests

import io.fluidsonic.stdlib.*
import io.fluidsonic.time.*
import kotlin.test.*


object DayOfWeekTest {

	private val locale = Locale.englishInUnitedStates


	@Test
	fun testDisplayNameCharacter() {
		assertEquals(expected = "M", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.character))
		assertEquals(expected = "T", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.character))
		assertEquals(expected = "W", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.character))
		assertEquals(expected = "T", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.character))
		assertEquals(expected = "F", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.character))
		assertEquals(expected = "S", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.character))
		assertEquals(expected = "S", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.character))

		assertEquals(expected = "M", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
		assertEquals(expected = "T", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
		assertEquals(expected = "W", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
		assertEquals(expected = "T", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
		assertEquals(expected = "F", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
		assertEquals(expected = "S", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
		assertEquals(expected = "S", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.characterStandalone))
	}


	@Test
	fun testDisplayNameShort() {
		assertEquals(expected = "Mo", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.short))
		assertEquals(expected = "Tu", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.short))
		assertEquals(expected = "We", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.short))
		assertEquals(expected = "Th", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.short))
		assertEquals(expected = "Fr", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.short))
		assertEquals(expected = "Sa", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.short))
		assertEquals(expected = "Su", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.short))

		assertEquals(expected = "Mo", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
		assertEquals(expected = "Tu", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
		assertEquals(expected = "We", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
		assertEquals(expected = "Th", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
		assertEquals(expected = "Fr", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
		assertEquals(expected = "Sa", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
		assertEquals(expected = "Su", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.shortStandalone))
	}


	@Test
	fun testDisplayNameMedium() {
		assertEquals(expected = "Mon", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.medium))
		assertEquals(expected = "Tue", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.medium))
		assertEquals(expected = "Wed", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.medium))
		assertEquals(expected = "Thu", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.medium))
		assertEquals(expected = "Fri", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.medium))
		assertEquals(expected = "Sat", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.medium))
		assertEquals(expected = "Sun", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.medium))

		assertEquals(expected = "Mon", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
		assertEquals(expected = "Tue", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
		assertEquals(expected = "Wed", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
		assertEquals(expected = "Thu", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
		assertEquals(expected = "Fri", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
		assertEquals(expected = "Sat", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
		assertEquals(expected = "Sun", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.mediumStandalone))
	}


	@Test
	fun testDisplayNameFull() {
		assertEquals(expected = "Monday", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.full))
		assertEquals(expected = "Tuesday", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.full))
		assertEquals(expected = "Wednesday", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.full))
		assertEquals(expected = "Thursday", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.full))
		assertEquals(expected = "Friday", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.full))
		assertEquals(expected = "Saturday", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.full))
		assertEquals(expected = "Sunday", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.full))

		assertEquals(expected = "Monday", actual = DayOfWeek.monday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
		assertEquals(expected = "Tuesday", actual = DayOfWeek.tuesday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
		assertEquals(expected = "Wednesday", actual = DayOfWeek.wednesday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
		assertEquals(expected = "Thursday", actual = DayOfWeek.thursday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
		assertEquals(expected = "Friday", actual = DayOfWeek.friday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
		assertEquals(expected = "Saturday", actual = DayOfWeek.saturday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
		assertEquals(expected = "Sunday", actual = DayOfWeek.sunday.displayName(locale = locale, format = DayOfWeekFormat.fullStandalone))
	}
}

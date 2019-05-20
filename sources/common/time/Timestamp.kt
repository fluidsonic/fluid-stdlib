package com.github.fluidsonic.fluid.stdlib


class Timestamp private constructor(
	val secondsSince1970: Seconds,
	val partialNanosecond: NanosecondOfSecond
) : Comparable<Timestamp> {

	override fun compareTo(other: Timestamp): Int {
		var result = secondsSince1970.compareTo(other.secondsSince1970)
		if (result == 0)
			result = partialNanosecond.compareTo(other.partialNanosecond)

		return result
	}


	fun durationSince(other: Timestamp) =
		this - other


	fun durationUntil(other: Timestamp) =
		other.durationSince(this)


	override fun equals(other: Any?) =
		this === other || (
			other is Timestamp
				&& secondsSince1970 == other.secondsSince1970
				&& partialNanosecond == other.partialNanosecond
			)


	override fun hashCode() =
		secondsSince1970.hashCode() xor partialNanosecond.hashCode()


	val millisecondsSince1970
		get() = secondsSince1970.toMilliseconds() + Nanoseconds(partialNanosecond.value).toMilliseconds() // FIXME


	operator fun minus(duration: Duration) =
		of(secondsSince1970 = secondsSince1970 - duration.seconds, nanoseconds = Nanoseconds(partialNanosecond.value) + duration.partialNanoseconds)


	operator fun minus(other: Timestamp) =
		Duration.of(seconds = secondsSince1970 - other.secondsSince1970, nanoseconds = partialNanosecond - other.partialNanosecond)


	operator fun plus(duration: Duration) =
		of(secondsSince1970 = secondsSince1970 + duration.seconds, nanoseconds = Nanoseconds(partialNanosecond.value) + duration.partialNanoseconds)


	override fun toString() =
		buildString(capacity = 30) { toString(this) }


	// TODO this is not correct in all cases
	fun toString(builder: StringBuilder) {
		toLocalDateTime(TimeZone.utc).toString(builder)
		builder.append('Z')
	}


	companion object {

		val firstIn1970 = unchecked(secondsSince1970 = Seconds.zero)


		fun now(clock: Clock = Clock.system) =
			clock.timestamp()


		fun of(millisecondsSince1970: Milliseconds, nanoseconds: Nanoseconds = Nanoseconds.zero) =
			of(secondsSince1970 = millisecondsSince1970.toSeconds(), nanoseconds = nanoseconds)


		fun of(secondsSince1970: Seconds, nanoseconds: Nanoseconds = Nanoseconds.zero): Timestamp {
			var totalSecondsSince1970 = secondsSince1970
			var partialNanoseconds = nanoseconds

			if (!partialNanoseconds.isZero) {
				totalSecondsSince1970 += partialNanoseconds.toSeconds()
				partialNanoseconds %= Nanoseconds.perSecond.value
			}

			return unchecked(secondsSince1970 = totalSecondsSince1970, partialNanosecond = partialNanoseconds)
		}


		// TODO this is not correct in all cases
		fun parse(text: CharSequence): Timestamp? {
			if (!text.endsWith('Z')) return null

			val splitIndex = text.indexOf('T')
			if (splitIndex < 0 || splitIndex >= text.length - 2) return null

			val date = LocalDate.parse(text.substring(startIndex = 0, endIndex = splitIndex)) ?: return null
			val time = LocalTime.parse(text.substring(startIndex = splitIndex + 1, endIndex = text.length - 1)) ?: return null

			return LocalDateTime.of(date, time).atTimeZone(TimeZone.utc)
		}


		internal fun unchecked(secondsSince1970: Long, partialNanosecond: Long = 0) =
			unchecked(secondsSince1970 = Seconds(secondsSince1970), partialNanosecond = Nanoseconds(partialNanosecond))


		internal fun unchecked(secondsSince1970: Seconds, partialNanosecond: Nanoseconds = Nanoseconds.zero) =
			Timestamp(secondsSince1970 = secondsSince1970, partialNanosecond = NanosecondOfSecond.unchecked(partialNanosecond.value))
	}
}


expect val Timestamp.dayOfWeek: DayOfWeek

expect fun Timestamp.toLocalDate(timeZone: TimeZone): LocalDate
expect fun Timestamp.toLocalDateTime(timeZone: TimeZone): LocalDateTime
expect fun Timestamp.toLocalTime(timeZone: TimeZone): LocalTime

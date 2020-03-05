package io.fluidsonic.stdlib


@OptIn(ExperimentalStdlibApi::class)
internal actual fun StringBuilder.replace(start: Int, end: Int, replacement: String) = apply {
	check(start in 0 .. length) { "start must be in 0 .. $length: $start" }
	check(end in start .. length) { "end must be in $start .. $length: $end" }

	when {
		start == end -> Unit

		start == 0 ->
			when (end) {
				length -> {
					clear()
					append(replacement)
				}
				else -> {
					val string = substring(startIndex = end)

					clear()
					ensureCapacity(replacement.length + string.length)
					append(replacement)
					append(string)
				}
			}

		end == length -> {
			val string = substring(startIndex = 0, endIndex = start)

			clear()
			ensureCapacity(string.length + replacement.length)
			append(string)
			append(replacement)
		}

		else -> {
			val before = substring(startIndex = 0, endIndex = start)
			val after = substring(startIndex = end, endIndex = length)

			clear()
			ensureCapacity(before.length + replacement.length + after.length)
			append(before)
			append(replacement)
			append(after)
		}
	}
}

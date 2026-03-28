package io.fluidsonic.stdlib


/** Returns this string truncated to [maximumLength] characters, appending [truncationSuffix] if truncated. */
public fun String.truncatedTo(maximumLength: Int, truncationSuffix: String = ""): String {
	require(maximumLength >= 0) { "maximumLength must be >= 0" }

	if (length <= maximumLength) {
		return this
	}
	if (maximumLength <= truncationSuffix.length) {
		return truncationSuffix.take(truncationSuffix.length)
	}

	return take(maximumLength - truncationSuffix.length) + truncationSuffix
}

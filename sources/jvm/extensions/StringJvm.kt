package com.github.fluidsonic.fluid.stdlib

import java.text.BreakIterator
import java.util.Locale


val String.unicodeLength: Int
	get() {
		val characterIterator = BreakIterator.getCharacterInstance(Locale.ROOT)
		characterIterator.setText(this)

		var graphemeCount = 0
		while (characterIterator.next() != BreakIterator.DONE) {
			graphemeCount += 1
		}

		return graphemeCount
	}


fun String.unicodeSubstring(startIndex: Int): String {
	require(startIndex >= 0) { "startIndex must be >= 0" }

	val characterIterator = BreakIterator.getCharacterInstance(Locale.ROOT)
	characterIterator.setText(this)

	val startCharacterIndex: Int
	if (startIndex > 0) {
		startCharacterIndex = characterIterator.next(startIndex)
		if (startCharacterIndex == BreakIterator.DONE) {
			return ""
		}
	}
	else {
		startCharacterIndex = 0
	}

	return substring(startIndex = startCharacterIndex)
}


fun String.unicodeSubstring(startIndex: Int, endIndex: Int): String {
	require(startIndex >= 0) { "startIndex must be >= 0" }
	require(endIndex >= startIndex) { "endIndex must be >= startIndex" }

	if (endIndex == startIndex) {
		return ""
	}

	val characterIterator = BreakIterator.getCharacterInstance(Locale.ROOT)
	characterIterator.setText(this)

	val startCharacterIndex: Int
	if (startIndex > 0) {
		startCharacterIndex = characterIterator.next(startIndex)
		if (startCharacterIndex == BreakIterator.DONE) {
			return ""
		}
	}
	else {
		startCharacterIndex = 0
	}

	val endCharacterIndex = characterIterator.next(endIndex - startIndex)
	if (endCharacterIndex == BreakIterator.DONE) {
		return substring(startIndex = startCharacterIndex)
	}

	return substring(startIndex = startCharacterIndex, endIndex = endCharacterIndex)
}

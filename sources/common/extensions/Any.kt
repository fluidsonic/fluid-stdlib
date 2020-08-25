package io.fluidsonic.stdlib

import kotlin.contracts.*


internal expect inline fun <T> T.freeze(): T


public inline fun <T : Any> T?.ifNull(defaultValue: () -> T): T {
	contract {
		callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
	}

	return this ?: defaultValue()
}

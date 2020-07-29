package io.fluidsonic.stdlib

import kotlin.contracts.*


internal expect inline fun <T> T.freeze(): T


public inline fun <T : Any> T?.ifNull(onNull: () -> T): T {
	contract {
		callsInPlace(onNull, InvocationKind.AT_MOST_ONCE)
	}

	return if (this !== null) this else onNull()
}

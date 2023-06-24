package io.fluidsonic.stdlib

import kotlin.contracts.*


@Deprecated("To be removed as it avoids IDE and compiler checks.", replaceWith = ReplaceWith("this as T"))
public inline fun <reified T : Any> Any.cast(): T {
	contract {
		returns() implies (this@cast is T)
	}

	return this as T
}


@Deprecated("To be removed as it avoids IDE and compiler checks.", replaceWith = ReplaceWith("this as? T"))
public inline fun <reified T : Any> Any.castOrNull(): T? {
	contract {
		returnsNotNull() implies (this@castOrNull is T)
	}

	return this as? T
}


public inline fun <T : Any> T?.ifNull(defaultValue: () -> T): T {
	contract {
		callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
	}

	return this ?: defaultValue()
}

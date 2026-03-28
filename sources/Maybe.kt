package io.fluidsonic.stdlib


/** An optional-like wrapper that distinguishes between "no value" and "a value that may be null". */
public class Maybe<out Value> @PublishedApi internal constructor(
	@PublishedApi internal val _value: Any?,
) {

	override fun equals(other: Any?): Boolean =
		this === other || (other is Maybe<*> && _value == other._value)


	override fun hashCode(): Int =
		_value.hashCode()


	/** Returns the contained value, or `null` if this is [nothing]. */
	@Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
	public inline fun getOrNull(): Value? =
		if (hasValue()) _value as Value
		else null


	/** Returns `true` if this instance contains a value (i.e. is not [nothing]). */
	@Suppress("NOTHING_TO_INLINE")
	public inline fun hasValue(): Boolean =
		!isNothing()


	/** Returns the contained value, or throws [IllegalStateException] if this is [nothing]. */
	@Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
	public inline fun get(): Value =
		if (hasValue()) _value as Value
		else error("Cannot get value from Maybe.nothing. Check with .hasValue() first.")


	/** Returns `true` if this instance represents the absence of a value. */
	@Suppress("NOTHING_TO_INLINE")
	public inline fun isNothing(): Boolean =
		_value === nothingValue


	/** Transforms the contained value using [mapping], or returns [nothing] if no value is present. */
	@Suppress("UNCHECKED_CAST")
	public inline fun <TransformedValue> map(mapping: (Value) -> TransformedValue): Maybe<TransformedValue> =
		if (hasValue()) Maybe(mapping(_value as Value))
		else nothing


	override fun toString(): String =
		if (_value === nothingValue) "<nothing>"
		else _value.toString()


	public companion object {

		@PublishedApi
		internal val nothingValue: Any = Any()

		/** A [Maybe] instance representing the absence of a value. */
		public val nothing: Maybe<Nothing> = Maybe(nothingValue)


		/** Wraps the given [value] in a [Maybe]. */
		@Suppress("NOTHING_TO_INLINE")
		public inline fun <Value> of(value: Value): Maybe<Value> =
			Maybe(value)


		/** Wraps the given [value] in a [Maybe], treating `null` as [nothing]. */
		@Suppress("NOTHING_TO_INLINE")
		public inline fun <Value : Any> ofNullable(value: Value?): Maybe<Value> =
			Maybe(value ?: nothingValue)
	}
}


/** Returns the contained value, or the result of [default] if this is [Maybe.nothing]. */
@Suppress("UNCHECKED_CAST")
public inline fun <Value> Maybe<Value>.getOrElse(default: () -> Value): Value =
	if (hasValue()) _value as Value
	else default()


/** Transforms the contained value using [mapping] if it is non-null, preserving `null` and [Maybe.nothing] as-is. */
@Suppress("UNCHECKED_CAST")
public inline fun <Value : Any, TransformedValue> Maybe<Value?>.mapIfNotNull(
	mapping: (Value) -> TransformedValue?,
): Maybe<TransformedValue?> =
	map { it?.let(mapping) }

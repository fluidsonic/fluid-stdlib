package io.fluidsonic.stdlib


@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class Maybe<out Value> @PublishedApi internal constructor(
	@PublishedApi internal val _value: Any?
) {

	@Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
	public inline fun getOrNull(): Value? =
		if (hasValue()) _value as Value
		else null


	@Suppress("NOTHING_TO_INLINE")
	public inline fun hasValue(): Boolean =
		!isNothing()


	@Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
	public inline fun get(): Value =
		if (hasValue()) _value as Value
		else error("Cannot get value from Maybe.nothing. Check with .hasValue() first.")


	@Suppress("NOTHING_TO_INLINE")
	public inline fun isNothing(): Boolean =
		_value === nothingValue


	@Suppress("UNCHECKED_CAST")
	public inline fun <MappedValue : Any> map(mapping: (Value) -> MappedValue?): Maybe<MappedValue> =
		if (hasValue()) Maybe(mapping(_value as Value))
		else nothing


	override fun toString(): String =
		if (_value === nothingValue) "<nothing>"
		else _value.toString()


	public companion object {

		@PublishedApi
		internal val nothingValue = Any()

		public val nothing: Maybe<Nothing> = Maybe(nothingValue)


		@Suppress("NOTHING_TO_INLINE")
		public inline fun <Value> of(value: Value): Maybe<Value> =
			Maybe(value)


		@Suppress("NOTHING_TO_INLINE")
		public inline fun <Value : Any> ofNullable(value: Value?): Maybe<Value> =
			Maybe(value ?: nothingValue)
	}
}


@Suppress("UNCHECKED_CAST")
public inline fun <Value> Maybe<Value>.getOrElse(default: () -> Value): Value =
	if (hasValue()) _value as Value
	else default()


@Suppress("UNCHECKED_CAST")
public inline fun <Value : Any, MappedValue : Any> Maybe<Value?>.mapIfNotNull(mapping: (Value) -> MappedValue?): Maybe<MappedValue?> =
	map { it?.let(mapping) }

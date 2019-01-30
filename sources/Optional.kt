package com.github.fluidsonic.fluid.stdlib


data class Optional<out Value : Any>(
	val value: Value? = null
) {

	inline fun <MappedValue : Any> mapValue(mapping: (Value) -> MappedValue?) =
		Optional(value?.let(mapping))


	companion object {

		private val empty = Optional<Any>()


		@Suppress("UNCHECKED_CAST")
		fun <Value : Any> empty() = empty as Optional<Value>
	}
}

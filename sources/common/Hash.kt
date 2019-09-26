package com.github.fluidsonic.fluid.stdlib


inline fun hash(block: HashScope.() -> Int) =
	HashScope.block()


@Suppress("NOTHING_TO_INLINE")
object HashScope {

	inline infix fun Any?.x(hashable: Any?) =
		(31 * hashCode()) + hashable.hashCode()

	inline infix fun Any?.x(hashable: Int) =
		(31 * hashCode()) + hashable

	inline infix fun Int.x(hashable: Any?) =
		(31 * this) + hashable.hashCode()

	inline infix fun Int.x(hashable: Int) =
		(31 * this) + hashable
}

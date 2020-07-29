package io.fluidsonic.stdlib


public inline fun hash(block: HashScope.() -> Int): Int =
	HashScope.block()


@Suppress("NOTHING_TO_INLINE")
public object HashScope {

	public inline infix fun Any?.x(hashable: Any?): Int =
		(31 * hashCode()) + hashable.hashCode()

	public inline infix fun Any?.x(hashable: Int): Int =
		(31 * hashCode()) + hashable

	public inline infix fun Int.x(hashable: Any?): Int =
		(31 * this) + hashable.hashCode()

	public inline infix fun Int.x(hashable: Int): Int =
		(31 * this) + hashable
}

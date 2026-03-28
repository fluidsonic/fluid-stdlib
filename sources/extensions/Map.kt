package io.fluidsonic.stdlib


/** Returns a [HashMap] containing all entries of this map. */
public fun <Key, Value> Map<Key, Value>.toHashMap(): HashMap<Key, Value> =
	HashMap(this)

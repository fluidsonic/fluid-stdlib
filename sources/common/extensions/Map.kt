package io.fluidsonic.stdlib


public fun <Key, Value> Map<Key, Value>.toHashMap(): HashMap<Key, Value> =
	HashMap(this)

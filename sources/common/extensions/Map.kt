package io.fluidsonic.stdlib


fun <Key, Value> Map<Key, Value>.toHashMap(): HashMap<Key, Value> =
	HashMap(this)

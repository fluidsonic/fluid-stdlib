package com.github.fluidsonic.fluid.stdlib


fun <Key, Value> Map<Key, Value>.toHashMap(): HashMap<Key, Value> =
	HashMap(this)

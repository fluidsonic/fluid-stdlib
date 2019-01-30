package com.github.fluidsonic.fluid.stdlib

import java.util.HashMap


fun <Key, Value> Map<Key, Value>.toHashMap(): HashMap<Key, Value> =
	HashMap(this)

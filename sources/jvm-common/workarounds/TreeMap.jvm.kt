package com.github.fluidsonic.fluid.stdlib

import java.util.*


internal class _InternalTreeMap<K, V> : TreeMap<K, V>(), MutableMap<K, V>

internal actual typealias _TreeMap<K, V> = _InternalTreeMap<K, V>

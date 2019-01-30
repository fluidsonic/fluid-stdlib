package com.github.fluidsonic.fluid.stdlib


fun <Value : Comparable<Value>> max(a: Value, b: Value) =
	if (a > b) a else b


fun <Value : Comparable<Value>> max(a: Value, b: Value, c: Value, vararg rest: Value) =
	rest.fold(max(max(a, b), c)) { a1, b1 -> max(a1, b1) }


fun <Value : Comparable<Value>> min(a: Value, b: Value) =
	if (b < a) b else a


fun <Value : Comparable<Value>> min(a: Value, b: Value, c: Value, vararg rest: Value) =
	rest.fold(min(min(a, b), c)) { a1, b1 -> max(a1, b1) }

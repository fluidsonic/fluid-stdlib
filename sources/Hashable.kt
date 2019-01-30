package com.github.fluidsonic.fluid.stdlib


/** marker interface indicating that a type is really hashable */
interface Hashable : Equatable {

	override fun hashCode(): Int
}

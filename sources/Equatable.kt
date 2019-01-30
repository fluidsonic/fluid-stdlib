package com.github.fluidsonic.fluid.stdlib


/** marker interface indicating that a type is really equatable */
interface Equatable {

	override fun equals(other: Any?): Boolean
}

package io.fluidsonic.stdlib

import java.math.*


/** Returns `true` if this [BigDecimal] represents an integer value (no fractional part). */
public val BigDecimal.isInteger: Boolean
	get() = scale() <= 0 || signum() == 0 || remainder(BigDecimal.ONE) == BigDecimal.ZERO


/** Returns `true` if this [BigDecimal] is equal to zero. */
public val BigDecimal.isZero: Boolean
	get() = compareTo(BigDecimal.ZERO) == 0

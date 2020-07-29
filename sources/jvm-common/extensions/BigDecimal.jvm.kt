package io.fluidsonic.stdlib

import java.math.*


public val BigDecimal.isInteger: Boolean
	get() = scale() <= 0 || signum() == 0 || remainder(BigDecimal.ONE) == BigDecimal.ZERO


public val BigDecimal.isZero: Boolean
	get() = compareTo(BigDecimal.ZERO) == 0

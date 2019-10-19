package io.fluidsonic.stdlib

import java.math.*


val BigDecimal.isInteger
	get() = scale() <= 0 || signum() == 0 || remainder(BigDecimal.ONE) == BigDecimal.ZERO


val BigDecimal.isZero
	get() = compareTo(BigDecimal.ZERO) == 0

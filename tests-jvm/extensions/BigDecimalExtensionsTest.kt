package io.fluidsonic.stdlib

import java.math.*
import kotlin.test.*


class BigDecimalExtensionsTest {

	@Test
	fun isInteger_forIntegerValues() {
		assertTrue(BigDecimal("5").isInteger)
		assertTrue(BigDecimal("0").isInteger)
		assertTrue(BigDecimal("-3").isInteger)
		assertTrue(BigDecimal("100").isInteger)
		assertTrue(BigDecimal("100").isInteger)
	}

	@Test
	fun isInteger_forNonIntegerValues() {
		assertFalse(BigDecimal("5.5").isInteger)
		assertFalse(BigDecimal("0.1").isInteger)
		assertFalse(BigDecimal("-3.14").isInteger)
	}

	@Test
	fun isZero_forZeroValues() {
		assertTrue(BigDecimal.ZERO.isZero)
		assertTrue(BigDecimal("0").isZero)
		assertTrue(BigDecimal("0.00").isZero)
	}

	@Test
	fun isZero_forNonZeroValues() {
		assertFalse(BigDecimal("1").isZero)
		assertFalse(BigDecimal("-1").isZero)
		assertFalse(BigDecimal("0.001").isZero)
	}
}

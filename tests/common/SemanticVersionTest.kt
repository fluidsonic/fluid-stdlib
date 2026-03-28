package io.fluidsonic.stdlib

import kotlin.test.*


class SemanticVersionTest {

	@Test
	fun parse_validFullVersion() {
		val version = SemanticVersion.parse("1.2.3")
		assertNotNull(version)
		assertEquals(expected = 1, actual = version.major)
		assertEquals(expected = 2, actual = version.minor)
		assertEquals(expected = 3, actual = version.patch)
	}

	@Test
	fun parse_validMajorMinor() {
		val version = SemanticVersion.parse("1.0")
		assertNotNull(version)
		assertEquals(expected = 1, actual = version.major)
		assertEquals(expected = 0, actual = version.minor)
		assertEquals(expected = 0, actual = version.patch)
	}

	@Test
	fun parse_validMajorOnly() {
		val version = SemanticVersion.parse("1")
		assertNotNull(version)
		assertEquals(expected = 1, actual = version.major)
		assertEquals(expected = 0, actual = version.minor)
		assertEquals(expected = 0, actual = version.patch)
	}

	@Test
	fun parse_zeroVersion() {
		val version = SemanticVersion.parse("0.0.0")
		assertNotNull(version)
		assertEquals(expected = 0, actual = version.major)
		assertEquals(expected = 0, actual = version.minor)
		assertEquals(expected = 0, actual = version.patch)
	}

	@Test
	fun parse_invalidLeadingZero() {
		assertNull(SemanticVersion.parse("01.2.3"))
		assertNull(SemanticVersion.parse("1.02.3"))
		assertNull(SemanticVersion.parse("1.2.03"))
	}

	@Test
	fun parse_invalidFormat() {
		assertNull(SemanticVersion.parse(""))
		assertNull(SemanticVersion.parse("abc"))
		assertNull(SemanticVersion.parse("1.2.3.4"))
		assertNull(SemanticVersion.parse("-1.0.0"))
		assertNull(SemanticVersion.parse("1.2.3-beta"))
	}

	@Test
	fun comparison() {
		assertTrue(SemanticVersion(1, 0, 0) < SemanticVersion(2, 0, 0))
		assertTrue(SemanticVersion(1, 0, 0) < SemanticVersion(1, 1, 0))
		assertTrue(SemanticVersion(1, 0, 0) < SemanticVersion(1, 0, 1))
		assertTrue(SemanticVersion(1, 2, 3) == SemanticVersion(1, 2, 3))
		assertTrue(SemanticVersion(2, 0, 0) > SemanticVersion(1, 9, 9))
	}

	@Test
	fun toString_formatsCorrectly() {
		assertEquals(expected = "1.2.3", actual = SemanticVersion(1, 2, 3).toString())
		assertEquals(expected = "0.0.0", actual = SemanticVersion(0, 0, 0).toString())
		assertEquals(expected = "10.20.30", actual = SemanticVersion(10, 20, 30).toString())
	}

	@Test
	fun equality() {
		assertEquals(expected = SemanticVersion(1, 2, 3), actual = SemanticVersion(1, 2, 3))
		assertNotEquals(illegal = SemanticVersion(1, 2, 3), actual = SemanticVersion(1, 2, 4))
	}

	@Test
	fun negativeVersionRejected() {
		assertFailsWith<IllegalArgumentException> { SemanticVersion(-1, 0, 0) }
		assertFailsWith<IllegalArgumentException> { SemanticVersion(0, -1, 0) }
		assertFailsWith<IllegalArgumentException> { SemanticVersion(0, 0, -1) }
	}
}

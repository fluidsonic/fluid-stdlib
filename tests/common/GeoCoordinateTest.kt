package io.fluidsonic.stdlib

import kotlin.math.*
import kotlin.test.*


class GeoCoordinateTest {

	@Test
	fun construction() {
		val coord = GeoCoordinate(latitude = 48.8566, longitude = 2.3522)
		assertEquals(expected = 48.8566, actual = coord.latitude)
		assertEquals(expected = 2.3522, actual = coord.longitude)
	}

	@Test
	fun validation_nonFiniteLatitudeThrows() {
		assertFailsWith<IllegalArgumentException> { GeoCoordinate(latitude = Double.NaN, longitude = 0.0) }
		assertFailsWith<IllegalArgumentException> { GeoCoordinate(latitude = Double.POSITIVE_INFINITY, longitude = 0.0) }
		assertFailsWith<IllegalArgumentException> { GeoCoordinate(latitude = Double.NEGATIVE_INFINITY, longitude = 0.0) }
	}

	@Test
	fun validation_nonFiniteLongitudeThrows() {
		assertFailsWith<IllegalArgumentException> { GeoCoordinate(latitude = 0.0, longitude = Double.NaN) }
		assertFailsWith<IllegalArgumentException> { GeoCoordinate(latitude = 0.0, longitude = Double.POSITIVE_INFINITY) }
		assertFailsWith<IllegalArgumentException> { GeoCoordinate(latitude = 0.0, longitude = Double.NEGATIVE_INFINITY) }
	}

	@Test
	fun distanceTo_knownCities() {
		// Paris to London is approximately 343.5 km
		val paris = GeoCoordinate(latitude = 48.8566, longitude = 2.3522)
		val london = GeoCoordinate(latitude = 51.5074, longitude = -0.1278)
		val distance = paris.distanceTo(london)
		// Allow 5km tolerance for the Haversine approximation
		assertTrue(abs(distance - 343_560.0) < 5_000.0, "Distance Paris-London should be ~343km, was ${distance / 1000}km")
	}

	@Test
	fun distanceTo_samePoint() {
		val coord = GeoCoordinate(latitude = 0.0, longitude = 0.0)
		assertEquals(expected = 0.0, actual = coord.distanceTo(coord))
	}

	@Test
	fun toString_formatsCorrectly() {
		val coord = GeoCoordinate(latitude = 48.8566, longitude = 2.3522)
		assertEquals(expected = "48.8566,2.3522", actual = coord.toString())
	}
}

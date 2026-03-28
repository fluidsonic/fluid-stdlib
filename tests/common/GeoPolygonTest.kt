package io.fluidsonic.stdlib

import kotlin.test.*


class GeoPolygonTest {

	@Test
	fun construction_withThreePoints() {
		val points = listOf(
			GeoCoordinate(0.0, 0.0),
			GeoCoordinate(1.0, 0.0),
			GeoCoordinate(0.0, 1.0),
		)
		val polygon = GeoPolygon(points)
		assertEquals(expected = points, actual = polygon.points)
	}

	@Test
	fun construction_withMoreThanThreePoints() {
		val points = listOf(
			GeoCoordinate(0.0, 0.0),
			GeoCoordinate(1.0, 0.0),
			GeoCoordinate(1.0, 1.0),
			GeoCoordinate(0.0, 1.0),
		)
		val polygon = GeoPolygon(points)
		assertEquals(expected = 4, actual = polygon.points.size)
	}

	@Test
	fun construction_withFewerThanThreePointsThrows() {
		assertFailsWith<IllegalArgumentException> { GeoPolygon(emptyList()) }
		assertFailsWith<IllegalArgumentException> {
			GeoPolygon(listOf(GeoCoordinate(0.0, 0.0)))
		}
		assertFailsWith<IllegalArgumentException> {
			GeoPolygon(listOf(GeoCoordinate(0.0, 0.0), GeoCoordinate(1.0, 0.0)))
		}
	}
}

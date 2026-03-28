package io.fluidsonic.stdlib


/** A polygon defined by a list of [GeoCoordinate] points (at least three). */
public data class GeoPolygon(
	val points: List<GeoCoordinate>
) {

	init {
		require(points.size >= 3) { "A polygon must have at least 3 points" }
	}
}

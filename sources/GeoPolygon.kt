package com.github.fluidsonic.fluid.stdlib


data class GeoPolygon(
	val points: List<GeoCoordinate>
) {

	init {
		require(points.size >= 3) { "A polygon must have at least 3 points" }
	}
}

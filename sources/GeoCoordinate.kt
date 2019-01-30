package com.github.fluidsonic.fluid.stdlib


data class GeoCoordinate(
	val latitude: Double,
	val longitude: Double
) {

	init {
		require(latitude.isFinite()) { "latitude must be a finite value" }
		require(longitude.isFinite()) { "longitude must be a finite value" }
	}

	/**
	 * Computes the distance between two geo coordinate in meters.

	 * @param coordinate the other coordinate
	 * *
	 * @return the distance between this and the other geo coordinate in meters.
	 */
	fun distanceTo(coordinate: GeoCoordinate): Double {
		val sinHalfLatitudeDistance = Math.sin(Math.toRadians(coordinate.latitude - latitude) * 0.5)
		val sinHalfLongitudeDistance = Math.sin(Math.toRadians(coordinate.longitude - longitude) * 0.5)
		val a = sinHalfLatitudeDistance * sinHalfLatitudeDistance + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(coordinate.latitude)) * sinHalfLongitudeDistance * sinHalfLongitudeDistance
		val b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

		return earthRadius * b
	}


	val isInNorthernHemisphere
		get() = latitude > 0.0


	val isInSouthernHemisphere
		get() = latitude < 0.0


	val isOnEquator
		get() = latitude == 0.0


	override fun toString() =
		"$latitude,$longitude"


	companion object {

		private const val earthRadius = 6378137.0 // in meters
	}
}

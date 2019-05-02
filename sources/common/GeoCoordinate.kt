package com.github.fluidsonic.fluid.stdlib

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


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
		val sinHalfLatitudeDistance = sin(degreesToRadians(coordinate.latitude - latitude) * 0.5)
		val sinHalfLongitudeDistance = sin(degreesToRadians(coordinate.longitude - longitude) * 0.5)
		val a = sinHalfLatitudeDistance * sinHalfLatitudeDistance + cos(degreesToRadians(latitude)) * cos(degreesToRadians(coordinate.latitude)) * sinHalfLongitudeDistance * sinHalfLongitudeDistance
		val b = 2 * atan2(sqrt(a), sqrt(1 - a))

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

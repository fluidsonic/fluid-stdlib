package com.github.fluidsonic.fluid.stdlib

import kotlinx.serialization.*
import kotlin.math.*


@Serializable
data class GeoCoordinate(
	val latitude: Double,
	val longitude: Double
) {

//	TODO reactivate once https://github.com/JetBrains/kotlin-native/issues/3019 is fixed
//	init {
//		require(latitude.isFinite()) { "latitude must be a finite value" }
//		require(longitude.isFinite()) { "longitude must be a finite value" }
//	}

	/**
	 * Computes the distance between two geo coordinates in meters.

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


	override fun toString() =
		"$latitude,$longitude"


	companion object {

		private const val earthRadius = 6378137.0 // in meters
	}
}

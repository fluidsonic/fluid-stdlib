package io.fluidsonic.stdlib


public data class SemanticVersion(
	val major: Int,
	val minor: Int = 0,
	val patch: Int = 0
) : Comparable<SemanticVersion> {

	init {
		require(major >= 0) { "major must not be negative" }
		require(minor >= 0) { "minor must not be negative" }
		require(patch >= 0) { "patch must not be negative" }
	}


	override fun compareTo(other: SemanticVersion): Int {
		val majorComparison = major.compareTo(other.major)
		if (majorComparison != 0) {
			return majorComparison
		}

		val minorComparison = minor.compareTo(other.minor)
		if (minorComparison != 0) {
			return minorComparison
		}

		return patch.compareTo(other.patch)
	}


	override fun toString(): String =
		"$major.$minor.$patch"


	public companion object {

		private val pattern = Regex("(0|[1-9]\\d*)(?:\\.(0|[1-9]\\d*)(?:\\.(0|[1-9]\\d*))?)?")


		public fun parse(string: String): SemanticVersion? {
			val match = pattern.matchEntire(string) ?: return null

			return try {
				SemanticVersion(
					major = match.groupValues[1].toInt(),
					minor = match.groupValues[2].ifEmpty { null }?.toInt() ?: 0,
					patch = match.groupValues[3].ifEmpty { null }?.toInt() ?: 0
				)
			}
			catch (e: IllegalArgumentException) {
				null
			}
		}
	}
}

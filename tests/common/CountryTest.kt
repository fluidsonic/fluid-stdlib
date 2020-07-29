package tests

import io.fluidsonic.stdlib.*
import kotlin.test.*


class CountryTest {

	@Test
	fun testSerializer() = assertJsonSerialization(
		value = Country.byCode(CountryCode("US"))!!,
		json = """ "US" """,
		serializer = Country.serializer()
	)
}

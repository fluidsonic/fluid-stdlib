package tests

import io.fluidsonic.stdlib.*
import kotlin.test.*


class CurrencyTest {

	@Test
	fun testSerializer() = assertJsonSerialization(
		value = Currency.byCode("EUR")!!,
		json = """ "EUR" """,
		serializer = Currency.serializer()
	)
}

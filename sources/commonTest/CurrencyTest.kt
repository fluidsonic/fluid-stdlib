package tests

import com.github.fluidsonic.fluid.stdlib.*
import kotlin.test.*


object CurrencyTest {

	@Test
	fun testSerializer() = assertJsonSerialization(
		value = Currency.byCode("EUR")!!,
		json = """ "EUR" """
	)
}

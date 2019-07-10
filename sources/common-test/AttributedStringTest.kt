package tests

import com.github.fluidsonic.fluid.stdlib.*
import kotlin.test.*


object AttributedStringTest {

	private object TestAttribute : StringAttribute<Unit>


	@Test
	fun testAttributeAt() {
		val string = buildAttributedString {
			append("abc")
			append("def", attributes = stringAttributesOf(TestAttribute with Unit))
			append("ghi")
		}

		assertEquals(string.string, "abcdefghi")
		assertEquals(null, string.attribute(TestAttribute, at = 0))
		assertEquals(null, string.attribute(TestAttribute, at = 1))
		assertEquals(null, string.attribute(TestAttribute, at = 2))
		assertEquals(Unit, string.attribute(TestAttribute, at = 3))
		assertEquals(Unit, string.attribute(TestAttribute, at = 4))
		assertEquals(Unit, string.attribute(TestAttribute, at = 5))
		assertEquals(null, string.attribute(TestAttribute, at = 6))
		assertEquals(null, string.attribute(TestAttribute, at = 7))
		assertEquals(null, string.attribute(TestAttribute, at = 8))
	}
}

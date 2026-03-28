package tests

import io.fluidsonic.stdlib.*
import kotlin.test.*


class AttributedStringTest {

	private object TestAttribute : StringAttribute<Unit>
	private object ColorAttribute : StringAttribute<String>


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


	@Test
	fun buildAttributedString_empty() {
		val result = buildAttributedString {}
		assertEquals(expected = "", actual = result.string)
		assertFalse(result.hasAttributes)
	}


	@Test
	fun buildAttributedString_withText() {
		val result = buildAttributedString {
			append("hello world")
		}
		assertEquals(expected = "hello world", actual = result.string)
		assertFalse(result.hasAttributes)
	}


	@Test
	fun buildAttributedString_withAttributes() {
		val result = buildAttributedString {
			append("hello", attributes = stringAttributesOf(ColorAttribute with "red"))
		}
		assertEquals(expected = "hello", actual = result.string)
		assertTrue(result.hasAttributes)
		assertEquals(expected = "red", actual = result.attribute(ColorAttribute, at = 0))
	}


	@Test
	fun buildAttributedString_fromExistingString() {
		val result = buildAttributedString("hello") {
			addAttribute(ColorAttribute, "blue", from = 0, to = 5)
		}
		assertEquals(expected = "hello", actual = result.string)
		assertEquals(expected = "blue", actual = result.attribute(ColorAttribute, at = 0))
	}


	@Test
	fun toAttributedString_plain() {
		val result = "hello".toAttributedString()
		assertEquals(expected = "hello", actual = result.string)
		assertFalse(result.hasAttributes)
	}


	@Test
	fun toAttributedString_withAttributes() {
		val attrs = stringAttributesOf(ColorAttribute with "green")
		val result = "hello".toAttributedString(attrs)
		assertEquals(expected = "hello", actual = result.string)
		assertTrue(result.hasAttributes)
		assertEquals(expected = "green", actual = result.attribute(ColorAttribute, at = 0))
	}


	@Test
	fun enumerateComponents_noAttributes() {
		val str = AttributedString("hello")
		val components = mutableListOf<Triple<Int, Int, Boolean>>()
		str.enumerateComponents { start, end, attributes ->
			components.add(Triple(start, end, attributes.isEmpty()))
		}
		assertEquals(expected = 1, actual = components.size)
		assertEquals(expected = Triple(0, 5, true), actual = components[0])
	}


	@Test
	fun enumerateComponents_withAttributes() {
		val str = buildAttributedString {
			append("abc")
			append("def", attributes = stringAttributesOf(TestAttribute with Unit))
			append("ghi")
		}
		val components = mutableListOf<Triple<Int, Int, Boolean>>()
		str.enumerateComponents { start, end, attributes ->
			components.add(Triple(start, end, attributes.isEmpty()))
		}
		assertEquals(expected = 3, actual = components.size)
		assertEquals(expected = Triple(0, 3, true), actual = components[0])
		assertEquals(expected = Triple(3, 6, false), actual = components[1])
		assertEquals(expected = Triple(6, 9, true), actual = components[2])
	}


	@Test
	fun builder_appendAttributedString() {
		val inner = buildAttributedString {
			append("world", attributes = stringAttributesOf(ColorAttribute with "red"))
		}
		val result = buildAttributedString {
			append("hello ")
			append(inner)
		}
		assertEquals(expected = "hello world", actual = result.string)
		assertNull(result.attribute<ColorAttribute, String>(ColorAttribute, at = 0))
		assertEquals(expected = "red", actual = result.attribute(ColorAttribute, at = 6))
	}


	@Test
	fun builder_appendMultipleSegments() {
		val result = buildAttributedString {
			append("hello ")
			append("world", attributes = stringAttributesOf(ColorAttribute with "red"))
		}
		assertEquals(expected = "hello world", actual = result.string)
		assertNull(result.attribute<ColorAttribute, String>(ColorAttribute, at = 0))
		assertEquals(expected = "red", actual = result.attribute(ColorAttribute, at = 6))
	}


	@Test
	fun builder_addAttribute() {
		val result = buildAttributedString("hello") {
			addAttribute(ColorAttribute, "blue", from = 0, to = 3)
		}
		assertEquals(expected = "blue", actual = result.attribute(ColorAttribute, at = 0))
		assertEquals(expected = "blue", actual = result.attribute(ColorAttribute, at = 2))
		assertNull(result.attribute<ColorAttribute, String>(ColorAttribute, at = 3))
	}


	@Test
	fun hasAttributes_withNoAttributes() {
		assertFalse(AttributedString("hello").hasAttributes)
	}


	@Test
	fun hasAttributes_withAttributes() {
		val str = "hello".toAttributedString(stringAttributesOf(TestAttribute with Unit))
		assertTrue(str.hasAttributes)
	}


	@Test
	fun equality_sameStringNoAttributes() {
		val a = "hello".toAttributedString()
		val b = "hello".toAttributedString()
		assertEquals(expected = a, actual = b)
	}


	@Test
	fun equality_differentStrings() {
		val a = "hello".toAttributedString()
		val b = "world".toAttributedString()
		assertNotEquals(illegal = a, actual = b)
	}


	@Test
	fun toString_returnsPlainString() {
		val str = buildAttributedString {
			append("hello", attributes = stringAttributesOf(TestAttribute with Unit))
		}
		assertEquals(expected = "hello", actual = str.toString())
	}


	@Test
	fun charSequence_length() {
		val str = AttributedString("hello")
		assertEquals(expected = 5, actual = str.length)
	}


	@Test
	fun charSequence_get() {
		val str = AttributedString("hello")
		assertEquals(expected = 'h', actual = str[0])
		assertEquals(expected = 'o', actual = str[4])
	}
}

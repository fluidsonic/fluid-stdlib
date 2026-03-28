package io.fluidsonic.stdlib

import kotlin.test.*


class StacktraceTest {

	@Test
	fun stackTrace_returnsNonEmptyList() {
		val trace = stackTrace()
		assertTrue(trace.isNotEmpty())
	}

	@Test
	fun stackTrace_skipCountWorks() {
		val fullTrace = stackTrace()
		val skippedTrace = stackTrace(skipCount = 2)
		assertTrue(skippedTrace.size < fullTrace.size, "Skipped trace should be shorter than full trace")
	}
}

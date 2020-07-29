package io.fluidsonic.stdlib


public fun stackTrace(skipCount: Int = 0): List<StackTraceElement> =
	Thread.currentThread().stackTrace.drop(skipCount + 2) // +1 to exclude this, +1 to exclude Thread.getStackTrace()

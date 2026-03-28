package io.fluidsonic.stdlib


/** Returns the current stack trace, optionally skipping the first [skipCount] frames. */
public fun stackTrace(skipCount: Int = 0): List<StackTraceElement> =
	Thread.currentThread().stackTrace.drop(skipCount + 2) // +1 to exclude this, +1 to exclude Thread.getStackTrace()

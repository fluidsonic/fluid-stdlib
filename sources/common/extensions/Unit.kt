package com.github.fluidsonic.fluid.stdlib

import kotlinx.serialization.*
import kotlinx.serialization.internal.*


@Suppress("unused")
fun Unit.serializer(): KSerializer<Unit> =
	UnitSerializer

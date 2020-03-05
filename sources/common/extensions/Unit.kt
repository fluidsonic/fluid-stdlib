package io.fluidsonic.stdlib

import kotlinx.serialization.*
import kotlinx.serialization.builtins.*


@Suppress("unused")
fun Unit.serializer(): KSerializer<Unit> =
	UnitSerializer()

package com.github.fluidsonic.fluid.stdlib

import kotlinx.serialization.*


@Serializable
data /*inline*/ class Cents(val value: Long)


@Serializer(forClass = Cents::class)
internal object CentsSerializer : KSerializer<Cents> {

	override fun deserialize(decoder: Decoder) =
		Cents(decoder.decodeLong())


	override fun serialize(encoder: Encoder, obj: Cents) {
		encoder.encodeLong(obj.value)
	}
}

package io.fluidsonic.stdlib

import kotlinx.serialization.*
import kotlin.math.*


@Serializable
data /*inline*/ class Cents(val value: Long) : Comparable<Cents> {

	constructor(value: Int) : this(value.toLong())


	init {
		freeze()
	}


	val absolute: Cents
		get() = Cents(value.absoluteValue)


	override operator fun compareTo(other: Cents) =
		value.compareTo(other.value)


	operator fun div(other: Int) =
		Cents(value / other)


	operator fun div(other: Long) =
		Cents(value / other)


	operator fun div(other: Cents) =
		value / other.value


	val isNegative
		get() = value < 0


	val isPositive
		get() = value < 0


	val isZero
		get() = value == 0L


	operator fun minus(other: Cents) =
		Cents(value - other.value)


	operator fun plus(other: Cents) =
		Cents(value + other.value)


	operator fun rem(other: Int) =
		Cents(value % other)


	operator fun rem(other: Long) =
		Cents(value % other)


	operator fun rem(other: Cents) =
		value % other.value


	operator fun times(other: Int) =
		Cents(value * other)


	operator fun times(other: Long) =
		Cents(value * other)


	override fun toString() =
		value.toString()


	operator fun unaryMinus() =
		Cents(-value)


	companion object {

		val zero = Cents(0L)
	}
}


operator fun Int.times(other: Cents) =
	other.times(this)


operator fun Long.times(other: Cents) =
	other.times(this)


@Serializer(forClass = Cents::class)
internal object CentsSerializer : KSerializer<Cents> {

	override fun deserialize(decoder: Decoder) =
		Cents(decoder.decodeLong())


	override fun serialize(encoder: Encoder, obj: Cents) {
		encoder.encodeLong(obj.value)
	}
}

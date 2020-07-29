package io.fluidsonic.stdlib

import kotlinx.serialization.*
import kotlinx.serialization.encoding.*
import kotlin.math.*


@Serializable
public data /*inline*/ class Cents(val value: Long) : Comparable<Cents> {

	public constructor(value: Int) : this(value.toLong())


	init {
		freeze()
	}


	val absolute: Cents
		get() = Cents(value.absoluteValue)


	override operator fun compareTo(other: Cents): Int =
		value.compareTo(other.value)


	public operator fun div(other: Int): Cents =
		Cents(value / other)


	public operator fun div(other: Long): Cents =
		Cents(value / other)


	public operator fun div(other: Cents): Long =
		value / other.value


	public val isNegative: Boolean
		get() = value < 0


	public val isPositive: Boolean
		get() = value < 0


	public val isZero: Boolean
		get() = value == 0L


	public operator fun minus(other: Cents): Cents =
		Cents(value - other.value)


	public operator fun plus(other: Cents): Cents =
		Cents(value + other.value)


	public operator fun rem(other: Int): Cents =
		Cents(value % other)


	public operator fun rem(other: Long): Cents =
		Cents(value % other)


	public operator fun rem(other: Cents): Long =
		value % other.value


	public operator fun times(other: Int): Cents =
		Cents(value * other)


	public operator fun times(other: Long): Cents =
		Cents(value * other)


	override fun toString(): String =
		value.toString()


	public operator fun unaryMinus(): Cents =
		Cents(-value)


	public companion object {

		public val zero: Cents = Cents(0L)
	}
}


public operator fun Int.times(other: Cents): Cents =
	other.times(this)


public operator fun Long.times(other: Cents): Cents =
	other.times(this)


@Serializer(forClass = Cents::class)
internal object CentsSerializer : KSerializer<Cents> {

	override fun deserialize(decoder: Decoder) =
		Cents(decoder.decodeLong())


	override fun serialize(encoder: Encoder, value: Cents) {
		encoder.encodeLong(value.value)
	}
}

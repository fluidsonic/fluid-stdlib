package io.fluidsonic.stdlib

import kotlinx.serialization.*


@Serializable
data class Money(
	val amount: Cents,
	val currency: Currency
) {

	init {
		freeze()
	}
}

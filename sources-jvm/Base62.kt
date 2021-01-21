package io.fluidsonic.stdlib

import java.security.*


public object Base62 {

	private val characters = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
	private val random = SecureRandom()


	public fun random(length: Int): String {
		check(length >= 0) { "'length' must not be negative" }

		if (length == 0)
			return ""

		return buildString {
			repeat(length) {
				append(randomCharacter())
			}
		}
	}


	public fun randomCharacter(): Char =
		characters[random.nextInt(characters.size)]
}

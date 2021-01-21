package io.fluidsonic.stdlib

import kotlin.jvm.*
import kotlin.math.*


public class AttributedString private constructor(
	public val string: String,
	private val attributesByRange: Map<Range, StringAttributeMap>?,
	@Suppress("UNUSED_PARAMETER") damnJvm: Unit
) : CharSequence {

	public constructor(string: String, attributes: StringAttributeMap = emptyStringAttributes()) : this(
		string = string,
		attributesByRange = if (string.isNotEmpty() && !attributes.isEmpty()) {
			_TreeMap<Range, StringAttributeMap>().apply {
				put(Range(0, string.length), attributes.toImmutable())
			}
		}
		else {
			null
		},
		damnJvm = Unit
	)


	init {
		freeze()
	}


	@JvmName("_attribute")
	@PublishedApi
	internal fun attribute(attribute: StringAttribute<*>, at: Int): Any? {
		checkIndex(at)

		val attributesByRange = attributesByRange ?: return null
		for ((range, rangeAttributes) in attributesByRange) {
			if (range.endExclusive <= at) continue
			if (range.start > at) break

			return rangeAttributes[attribute]
		}

		return null
	}


	public inline fun <Attribute, reified Value> attribute(attribute: Attribute, at: Int): Value?
		where Attribute : StringAttribute<Value>, Value : Any =
		attribute(attribute as StringAttribute<*>, at = at) as? Value


	public fun attributes(at: Int): StringAttributeMap {
		checkIndex(at)

		val attributesByRange = attributesByRange ?: return emptyStringAttributes()

		val attributes = mutableStringAttributesOf()
		for ((range, rangeAttributes) in attributesByRange) {
			if (range.endExclusive <= at) continue
			if (range.start > at) break

			attributes.putAll(rangeAttributes)
		}

		return attributes
	}


	private fun checkIndex(index: Int) {
		if (index < 0 || index >= length) {
			throw IndexOutOfBoundsException("Index $index is out of bounds 0 ..< $length")
		}
	}


	@JvmName("_enumerateAttribute")
	@PublishedApi
	internal fun enumerateAttribute(attribute: StringAttribute<*>, block: (value: Any, range: Range) -> Unit) {
		val attributesByRange = attributesByRange ?: return
		for ((range, rangeAttributes) in attributesByRange) {
			val value = rangeAttributes[attribute] ?: continue

			block(value, range)
		}
	}


	public inline fun <Attribute, reified Value> enumerateAttribute(attribute: Attribute, crossinline block: (value: Value, range: Range) -> Unit)
		where Attribute : StringAttribute<Value>, Value : Any {
		enumerateAttribute(attribute as StringAttribute<*>) { value, range ->
			if (value is Value) {
				block(value, range)
			}
		}
	}


	public fun enumerateComponents(block: (start: Int, end: Int, attributes: StringAttributeMap) -> Unit) {
		val attributesByRange = attributesByRange
		if (attributesByRange == null || attributesByRange.isEmpty() || string.isEmpty()) {
			block(0, string.length, emptyStringAttributes())
			return
		}

		val ends = attributesByRange.keys.flatMapTo(_sortedSetOf(string.length)) { listOf(it.start, it.endExclusive) }

		var start = 0
		for (end in ends) {
			if (start >= end) {
				continue
			}

			val attributes = attributes(at = start)
			block(start, end, attributes)

			start = end
		}
	}


	override fun equals(other: Any?): Boolean {
		if (this === other) {
			return true
		}
		if (other !is AttributedString) {
			return false
		}

		return string == other.string && attributesByRange == other.attributesByRange // TODO must normalize attributes
	}


	override fun get(index: Int): Char =
		string[index]


	public val hasAttributes: Boolean
		get() = attributesByRange?.values?.any { it.isNotEmpty() } ?: false


	override fun hashCode(): Int =
		string.hashCode() xor (attributesByRange?.hashCode() ?: 0) // TODO must normalize attributes


	override val length: Int
		get() = string.length


	override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
		string.subSequence(startIndex, endIndex)


	public fun toBuilder(): Builder =
		Builder(string = string, attributesByRange = attributesByRange)


	public fun toDebugString(): String =
		buildString {
			append(string)
			append('\n')

			if (attributesByRange != null) {
				for ((range, rangeAttributes) in attributesByRange) {
					append(range.toString())
					append(": ")
					append(rangeAttributes.toString())
					append('\n')
				}
			}
		}


	override fun toString(): String =
		string


	public companion object;


	public data class Range(
		val start: Int,
		val endExclusive: Int
	) : Comparable<Range> {

		init {
			require(endExclusive >= start)

			freeze()
		}


		override fun compareTo(other: Range): Int {
			val startDiff = start.compareTo(other.start)
			if (startDiff != 0) {
				return startDiff
			}

			return endExclusive.compareTo(other.endExclusive)
		}


		override fun toString(): String =
			"$start ..< $endExclusive"


		public companion object
	}


	public class Builder internal constructor(string: String = "", attributesByRange: Map<Range, StringAttributeMap>? = null) : CharSequence {

		private val attributesByRange: _TreeMap<MutableRange, MutableStringAttributeMap> = attributesByRange
			?.entries
			?.associateTo(_TreeMap()) { it.key.toMutable() to it.value.toMutable() }
			?: _TreeMap()

		private val stringBuilder = StringBuilder(string)


		public fun <Attribute, Value> addAttribute(attribute: Attribute, value: Value, from: Int, to: Int)
			where Attribute : StringAttribute<Value>, Value : Any {
			addAttributes(stringAttributesOf(attribute with value), from = from, to = to)
		}


		public fun addAttributes(attributes: StringAttributeMap, from: Int, to: Int) {
			checkRange(from, to, forAppend = false)

			if (from == to || attributes.isEmpty()) {
				return
			}

			val modificationRange = MutableRange(from, to)

			val rangesToAdd = mutableListOf<Pair<MutableRange, MutableStringAttributeMap>>()
			val rangesToRemove = mutableListOf<MutableRange>()

			// split up ranges affected by our modification so we can change the attributes of our modification range independently
			for ((range, rangeAttributes) in attributesByRange) {
				if (range == modificationRange) {
					// no need to split a perfectly fitting range as we'll simply add our attributes later on
					continue
				}

				if (!range.intersects(modificationRange)) {
					// no need to split a range which is outside of our modification
					continue
				}

				val affectedAttributeNames = rangeAttributes.attributes.intersect(attributes.attributes)
				if (affectedAttributeNames.isEmpty()) {
					// no need to split a range which is not affected by the attributes we're modifying
					continue
				}

				if (range.start < modificationRange.start) {
					// create partial range left to our modification
					val rangeLeftToModification = MutableRange(range.start, modificationRange.start)
					rangesToAdd += rangeLeftToModification to rangeAttributes.toMutable()
				}
				if (range.endExclusive > modificationRange.endExclusive) {
					// create partial range right to our modification
					val rangeRightToModification = MutableRange(modificationRange.endExclusive, range.endExclusive)
					rangesToAdd += rangeRightToModification to rangeAttributes.toMutable()
				}

				val rangeInsideModification = MutableRange(max(range.start, modificationRange.start), min(range.endExclusive, modificationRange.endExclusive))
				rangeAttributes.removeAttributes(affectedAttributeNames)

				if (rangeInsideModification != range) {
					rangesToRemove += range

					if (rangeAttributes.isNotEmpty()) {
						rangesToAdd += rangeInsideModification to rangeAttributes
					}
				}
			}

			attributesByRange.keys.removeAll(rangesToRemove)

			for ((range, rangeAttributes) in rangesToAdd) {
				val existingRangeAttributes = attributesByRange.put(range, rangeAttributes)
				if (existingRangeAttributes != null) {
					rangeAttributes.putAll(existingRangeAttributes)
				}
			}

			// finally, apply our new attributes
			val rangeAttributes = attributesByRange[modificationRange]
			if (rangeAttributes != null) {
				rangeAttributes.putAll(attributes)
			}
			else {
				attributesByRange[modificationRange] = attributes.toMutable()
			}
		}


		public fun append(attributedString: AttributedString) {
			replace(start = length, endExclusive = length, newValue = attributedString)
		}


		public fun append(string: String, attributes: StringAttributeMap = emptyStringAttributes(), extendingPreviousAttributes: Boolean = false) {
			if (string.isEmpty()) {
				return
			}

			val initialLength = stringBuilder.length
			stringBuilder.append(string)
			val newLength = stringBuilder.length

			if (extendingPreviousAttributes) {
				attributesByRange.keys
					.filter { it.endExclusive == initialLength }
					.forEach { it.endExclusive = newLength }
			}

			addAttributes(attributes, from = initialLength, to = newLength)
		}


		private fun checkIndex(index: Int, forAppend: Boolean) {
			if (index < 0 || index > length || (!forAppend && index == length)) {
				throw IndexOutOfBoundsException("Index $index is out of bounds 0 ..< $length")
			}
		}


		private fun checkRange(start: Int, end: Int, forAppend: Boolean) {
			if (start < 0 || start > length || (!forAppend && start == length) || end < start) {
				throw IndexOutOfBoundsException("range $start ..< $end is out of bounds 0 ..< $length")
			}
		}


		override fun get(index: Int): Char =
			stringBuilder[index]


		override val length: Int
			get() = stringBuilder.length


		public fun replace(start: Int, endExclusive: Int, newValue: AttributedString) {
			replace(start = start, endExclusive = endExclusive, newValue = newValue.string)

			newValue.enumerateComponents { startInNewValue, endInNewValue, attributes ->
				if (attributes.isEmpty()) {
					return@enumerateComponents
				}

				addAttributes(attributes, from = start + startInNewValue, to = start + endInNewValue)
			}
		}


		public fun replace(start: Int, endExclusive: Int, newValue: String, attributes: StringAttributeMap = emptyStringAttributes()) {
			checkRange(start, endExclusive, forAppend = true)

			val substringLengthBeforeMutation = endExclusive - start
			val substringLengthAfterMutation = newValue.length
			if (substringLengthBeforeMutation == 0 && substringLengthAfterMutation == 0) {
				// replace nothing with nothing
				return
			}

			if (start == length) {
				// insertion at the tail is can be done through append
				append(newValue, attributes = attributes, extendingPreviousAttributes = true)
				return
			}

			val endBeforeMutation = endExclusive
			val lengthBeforeMutation = stringBuilder.length

			stringBuilder.replaceRange(startIndex = start, endIndex = endExclusive, replacement = newValue)

			val lengthAfterMutation = stringBuilder.length
			val substringLengthOffset = lengthAfterMutation - lengthBeforeMutation
			val endAfterMutation = endBeforeMutation + substringLengthOffset

			if (start == 0 && endExclusive == 0) {
				// for insertion at head position we just have to offset all ranges while extending ranges starting
				// at head position

				for (range in attributesByRange.keys) {
					if (range.start > 0) {
						range.start += substringLengthOffset
					}

					range.endExclusive += substringLengthOffset
				}
			}
			else {
				val rangesToRemove = mutableListOf<MutableRange>()
				val rangesToAdd = mutableListOf<Pair<MutableRange, MutableStringAttributeMap>>()

				val insertionUsesAttributesOfPrecedingCharacter = (substringLengthBeforeMutation == 0 && start > 0)
				val insertionUsesAttributesOfFollowingCharacter = (substringLengthBeforeMutation == 0 && start == 0)

				for ((range, rangeAttributes) in attributesByRange) {
					if (range.endExclusive <= start) {
						// range ends left to our mutation so it's not affected unless we're doing an insertion in
						// which case we use the preceding character's attributes for the inserted substring

						if (!insertionUsesAttributesOfPrecedingCharacter || range.endExclusive != start) {
							continue
						}
					}

					if (range.start >= endExclusive) {
						// range starts right to our mutation so it can simply be offset unless we're doing an
						// insertion at head position in which case we use the following character's attributes for
						// the inserted substring

						if (!insertionUsesAttributesOfFollowingCharacter || range.start != endBeforeMutation) {
							range.start += substringLengthOffset
							range.endExclusive += substringLengthOffset
							continue
						}
					}

					// changing the ranges inside our mutation can cause collisions so we first remove them and then
					// add mutated ranges again
					rangesToRemove += range

					var rangeStart = range.start
					var rangeEnd = range.endExclusive

					// the first replaced character's attributes should be extended across the whole replaced string
					// unless we have an insertion, in which case the preceding or following character's attributes
					// will be extended

					if (insertionUsesAttributesOfFollowingCharacter && rangeStart == endBeforeMutation) {
						// range starts directly following our mutation and we have an insertion at the head position
						// so we extend that range all the way to the head position
						rangeStart = 0
					}
					else if (rangeStart > start) {
						// range starts after first replaced character so we adjust it to start after the mutation
						rangeStart = endAfterMutation
					}
					else if (rangeEnd <= endExclusive) {
						// range ends inside our mutation so extend it to the end of the mutation
						rangeEnd = endAfterMutation
					}
					else {
						// range ends right to our mutation so just extend it
						rangeEnd += substringLengthOffset
					}

					if (rangeEnd <= rangeStart) {
						// range does no longer contain any characters so just remove it
						continue
					}

					rangesToAdd += MutableRange(rangeStart, rangeEnd) to rangeAttributes
				}


				attributesByRange.keys.removeAll(rangesToRemove)

				for ((range, rangeAttributes) in rangesToAdd) {
					val existingRangeAttributes = attributesByRange.put(range, rangeAttributes)
					if (existingRangeAttributes != null) {
						rangeAttributes.putAll(existingRangeAttributes)
					}
				}
			}

			if (substringLengthAfterMutation > 0) {
				addAttributes(attributes, start, endAfterMutation)
			}
		}


		@OptIn(ExperimentalStdlibApi::class)
		public fun replace(oldValue: String, newValue: AttributedString) {
			var index = stringBuilder.lastIndexOf(oldValue)
			while (index >= 0) {
				val oldValueLength = oldValue.length
				replace(index, index + oldValueLength, newValue)

				if (index == 0)
					break

				index = stringBuilder.lastIndexOf(oldValue, index - 1)
			}
		}


		@OptIn(ExperimentalStdlibApi::class)
		public fun replace(oldValue: String, newValue: String, attributes: StringAttributeMap = emptyStringAttributes()) {
			var index = stringBuilder.lastIndexOf(oldValue)
			while (index >= 0) {
				val oldValueLength = oldValue.length
				replace(index, index + oldValueLength, newValue, attributes = attributes)

				if (index == 0)
					break

				index = stringBuilder.lastIndexOf(oldValue, index - 1)
			}
		}


		public val string: String
			get() = stringBuilder.toString()


		override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
			stringBuilder.subSequence(startIndex, endIndex)


		public fun toAttributedString(): AttributedString =
			AttributedString(
				string = string,
				attributesByRange = attributesByRange.entries.associateTo(_TreeMap()) { Pair(it.key.toImmutable(), it.value.toImmutable()) },
				damnJvm = Unit
			)


		private fun Range.toMutable() =
			MutableRange(start, endExclusive)


		override fun toString(): String =
			string


		public companion object;


		private data class MutableRange(
			var start: Int,
			var endExclusive: Int
		) : Comparable<MutableRange> {

			init {
				require(endExclusive >= start)
			}


			override fun compareTo(other: MutableRange): Int {
				val startDiff = start.compareTo(other.start)
				if (startDiff != 0) {
					return startDiff
				}

				return endExclusive.compareTo(other.endExclusive)
			}


			fun intersects(other: MutableRange) =
				(start < other.endExclusive && endExclusive > other.start)


			fun toImmutable() = Range(start, endExclusive)
		}
	}
}


public fun buildAttributedString(builderAction: AttributedString.Builder.() -> Unit): AttributedString =
	AttributedString.Builder().apply { builderAction() }.toAttributedString()


public fun buildAttributedString(string: String, builderAction: AttributedString.Builder.() -> Unit): AttributedString =
	AttributedString.Builder(string = string).apply { builderAction() }.toAttributedString()


public fun String.toAttributedString(attributes: StringAttributeMap = emptyStringAttributes()): AttributedString =
	AttributedString(this, attributes = attributes)

package io.fluidsonic.stdlib


/** A typed key for an attribute that can be applied to an [AttributedString]. */
@Suppress("unused")
public interface StringAttribute<Value : Any>


/** An immutable map of [StringAttribute] keys to their values. */
public interface StringAttributeMap {

	/** The set of all attribute keys in this map. */
	public val attributes: Set<StringAttribute<*>>

	/** Returns `true` if this map contains the given [attribute]. */
	public fun containsAttribute(attribute: StringAttribute<*>): Boolean =
		attributes.contains(attribute)

	/** The set of all attribute-value entries in this map. */
	public val entries: Set<Map.Entry<StringAttribute<*>, Any>>

	/** Returns `true` if this map contains no attributes. */
	public fun isEmpty(): Boolean =
		attributes.isEmpty()

	/** Returns an iterator over the attribute keys in this map. */
	public operator fun iterator(): Iterator<StringAttribute<*>> =
		attributes.iterator()

	/** The number of attributes in this map. */
	public val size: Int
		get() = attributes.size

	/** Returns an immutable copy of this map. */
	public fun toImmutable(): StringAttributeMap =
		when (size) {
			0 -> EmptyStringAttributeMap
			1 -> entries.first().let { SingleElementStringAttributeMap(attribute = it.key, value = it.value) }
			else -> StringAttributeHashMap(entries.associateTo(HashMap(size)) { it.key to it.value })
		}

	/** Returns an immutable [Map] representation of this attribute map. */
	public fun toImmutableMap(): Map<StringAttribute<*>, Any> =
		toMap().toHashMap()

	/** Returns a [Map] representation of this attribute map. */
	public fun toMap(): Map<StringAttribute<*>, Any>

	/** Returns a mutable copy of this map. */
	public fun toMutable(): MutableStringAttributeMap =
		MutableStringAttributeHashMap(entries.associateTo(HashMap(size)) { it.key to it.value })

	/** Returns a mutable [Map] representation of this attribute map. */
	public fun toMutableMap(): MutableMap<StringAttribute<*>, Any> =
		toMap().toHashMap()

	/** Returns the value for the given [attribute], or `null` if not present. */
	public fun valueForAttribute(attribute: StringAttribute<*>): Any?


	public companion object
}


/** A mutable map of [StringAttribute] keys to their values. */
public interface MutableStringAttributeMap : StringAttributeMap {

	/** Removes all attributes from this map. */
	public fun clear()

	override operator fun iterator(): MutableIterator<StringAttribute<*>>

	/** Sets the [value] for the given [attribute], returning the previous value or `null`. */
	public fun put(attribute: StringAttribute<*>, value: Any): Any?

	/** Copies all attributes from [attributes] into this map. */
	public fun putAll(attributes: StringAttributeMap) {
		for ((attribute, value) in attributes.entries) {
			put(attribute, value)
		}
	}

	/** Removes the given [attribute], returning the previous value or `null`. */
	public fun removeAttribute(attribute: StringAttribute<*>): Any?


	public companion object
}


private object EmptyStringAttributeMap : StringAttributeMap {

	override val attributes: Set<StringAttribute<*>>
		get() = emptySet()


	override fun containsAttribute(attribute: StringAttribute<*>) =
		false


	override val entries: Set<Map.Entry<StringAttribute<*>, Any>>
		get() = emptySet()


	override fun isEmpty() =
		true


	override fun iterator() =
		attributes.iterator()


	override val size
		get() = 0


	override fun toImmutable() =
		this


	override fun toImmutableMap() =
		emptyMap<StringAttribute<*>, Any>()


	override fun toMap() =
		emptyMap<StringAttribute<*>, Any>()


	override fun toMutable() =
		MutableStringAttributeHashMap()


	override fun toMutableMap() =
		mutableMapOf<StringAttribute<*>, Any>()


	override fun toString() =
		"{}"


	override fun valueForAttribute(attribute: StringAttribute<*>): Nothing? =
		null
}


private class SingleElementStringAttributeMap(
	attribute: StringAttribute<*>, value: Any
) : StringAttributeMap {

	private val map = mapOf(attribute to value)


	override val attributes: Set<StringAttribute<*>>
		get() = map.keys


	override fun containsAttribute(attribute: StringAttribute<*>) =
		map.containsKey(attribute)


	override val entries: Set<Map.Entry<StringAttribute<*>, Any>>
		get() = map.entries


	override fun isEmpty() =
		map.isEmpty()


	override val size
		get() = 1


	override fun toImmutable() =
		this


	override fun toImmutableMap() =
		map


	override fun toMap() =
		map


	override fun toMutable(): MutableStringAttributeMap =
		MutableStringAttributeHashMap(map.toHashMap())


	override fun toString() =
		map.toString()


	override fun valueForAttribute(attribute: StringAttribute<*>) =
		map[attribute]
}


private class StringAttributeHashMap(
	val map: HashMap<StringAttribute<*>, Any>
) : StringAttributeMap {

	override val attributes: Set<StringAttribute<*>>
		get() = map.keys


	override fun containsAttribute(attribute: StringAttribute<*>) =
		map.containsKey(attribute)


	override val entries: Set<Map.Entry<StringAttribute<*>, Any>>
		get() = map.entries


	override fun isEmpty() =
		map.isEmpty()


	override val size
		get() = map.size


	override fun toImmutable() =
		this


	override fun toImmutableMap() =
		map


	override fun toMap() =
		map


	override fun toMutable(): MutableStringAttributeMap =
		MutableStringAttributeHashMap(map.toHashMap())


	override fun toString() =
		map.toString()


	override fun valueForAttribute(attribute: StringAttribute<*>) =
		map[attribute]
}


private class MutableStringAttributeHashMap(
	val map: HashMap<StringAttribute<*>, Any> = hashMapOf()
) : MutableStringAttributeMap {

	override val attributes: Set<StringAttribute<*>>
		get() = map.keys


	override fun clear() {
		map.clear()
	}


	override fun containsAttribute(attribute: StringAttribute<*>) =
		map.containsKey(attribute)


	override val entries: Set<Map.Entry<StringAttribute<*>, Any>>
		get() = map.entries


	override fun isEmpty() =
		map.isEmpty()


	override fun iterator() =
		map.keys.iterator()


	override val size
		get() = map.size


	override fun put(attribute: StringAttribute<*>, value: Any) =
		map.put(attribute, value)


	override fun putAll(attributes: StringAttributeMap) {
		when (attributes) {
			is StringAttributeHashMap -> map.putAll(attributes.map)
			is MutableStringAttributeHashMap -> map.putAll(attributes.map)
			else -> super.putAll(attributes)
		}
	}


	override fun removeAttribute(attribute: StringAttribute<*>) =
		map.remove(attribute)


	override fun toImmutable(): StringAttributeMap =
		StringAttributeHashMap(map.toHashMap())


	override fun toMap(): Map<StringAttribute<*>, Any> =
		map


	override fun toMutable(): MutableStringAttributeMap =
		MutableStringAttributeHashMap(map.toHashMap())


	override fun toString() =
		map.toString()


	override fun valueForAttribute(attribute: StringAttribute<*>) =
		map[attribute]
}


/** Returns an empty [StringAttributeMap]. */
public fun emptyStringAttributes(): StringAttributeMap =
	EmptyStringAttributeMap


/** Returns an empty [MutableStringAttributeMap]. */
public fun mutableStringAttributesOf(): MutableStringAttributeMap =
	MutableStringAttributeHashMap()


/** Returns a [MutableStringAttributeMap] containing the given [pair]. */
public fun mutableStringAttributesOf(pair: Pair<StringAttribute<*>, Any>): MutableStringAttributeMap =
	MutableStringAttributeHashMap(hashMapOf(pair))


/** Returns a [MutableStringAttributeMap] containing the given [pairs]. */
public fun mutableStringAttributesOf(vararg pairs: Pair<StringAttribute<*>, Any>): MutableStringAttributeMap =
	MutableStringAttributeHashMap(hashMapOf(*pairs))


/** Returns an empty [StringAttributeMap]. */
public fun stringAttributesOf(): StringAttributeMap =
	emptyStringAttributes()


/** Returns a [StringAttributeMap] containing the given [pair]. */
public fun stringAttributesOf(pair: Pair<StringAttribute<*>, Any>): StringAttributeMap =
	SingleElementStringAttributeMap(attribute = pair.first, value = pair.second)


/** Returns a [StringAttributeMap] containing the given [pairs]. */
public fun stringAttributesOf(vararg pairs: Pair<StringAttribute<*>, Any>): StringAttributeMap =
	pairs.toAttributes()


/** Returns the typed value for [attribute], or `null` if not present. */
public inline operator fun <Attribute, reified Value> StringAttributeMap.get(attribute: Attribute): Value?
	where Attribute : StringAttribute<out Value>, Value : Any =
	valueForAttribute(attribute as StringAttribute<*>) as? Value


/** Returns `true` if this map contains at least one attribute. */
public fun StringAttributeMap.isNotEmpty(): Boolean =
	!isEmpty()


/** Sets the attribute-value [pair] in this map, returning the previous value or `null`. */
public fun MutableStringAttributeMap.put(pair: Pair<StringAttribute<*>, Any>): Any? =
	put(pair.first, pair.second)


/** Puts all attribute-value [pairs] into this map. */
public fun MutableStringAttributeMap.putAll(pairs: Array<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


/** Puts all attribute-value [pairs] into this map. */
public fun MutableStringAttributeMap.putAll(pairs: Iterable<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


/** Puts all attribute-value [pairs] into this map. */
public fun MutableStringAttributeMap.putAll(pairs: Sequence<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


/** Removes all given [attributes] from this map. */
public fun MutableStringAttributeMap.removeAttributes(attributes: Array<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


/** Removes all given [attributes] from this map. */
public fun MutableStringAttributeMap.removeAttributes(attributes: Iterable<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


/** Removes all given [attributes] from this map. */
public fun MutableStringAttributeMap.removeAttributes(attributes: Sequence<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


/** Sets the typed [value] for [attribute], returning the previous value or `null`. */
public inline operator fun <Attribute, reified Value> MutableStringAttributeMap.set(attribute: Attribute, value: Value): Value?
	where Attribute : StringAttribute<out Value>, Value : Any =
	put(attribute = attribute, value = value) as? Value


/** Converts this array of attribute-value pairs to a [StringAttributeMap]. */
public fun Array<out Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	when (size) {
		0 -> emptyStringAttributes()
		1 -> stringAttributesOf(this[0])
		else -> StringAttributeHashMap(hashMapOf(*this))
	}


/** Converts this iterable of attribute-value pairs to a [StringAttributeMap]. */
public fun Iterable<Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap {
	if (this is Collection) {
		when (size) {
			0 -> return emptyStringAttributes()
			1 -> return stringAttributesOf(if (this is List) this[0] else iterator().next())
		}
	}

	return StringAttributeHashMap(toMap(hashMapOf()))
}


/** Converts this sequence of attribute-value pairs to a [StringAttributeMap]. */
public fun Sequence<Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	StringAttributeHashMap(toMap(hashMapOf()))


/** Creates a pair of this [StringAttribute] and its [value], for use with [stringAttributesOf]. */
public infix fun <Attribute, Value> Attribute.with(value: Value): Pair<Attribute, Value>
	where Attribute : StringAttribute<Value>, Value : Any = this to value

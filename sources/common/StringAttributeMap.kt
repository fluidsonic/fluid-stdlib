package io.fluidsonic.stdlib


@Suppress("unused")
public interface StringAttribute<Value : Any>


public interface StringAttributeMap {

	public val attributes: Set<StringAttribute<*>>

	public fun containsAttribute(attribute: StringAttribute<*>): Boolean =
		attributes.contains(attribute)

	public val entries: Set<Map.Entry<StringAttribute<*>, Any>>

	public fun isEmpty(): Boolean =
		attributes.isEmpty()

	public operator fun iterator(): Iterator<StringAttribute<*>> =
		attributes.iterator()

	public val size: Int
		get() = attributes.size

	public fun toImmutable(): StringAttributeMap =
		when (size) {
			0 -> EmptyStringAttributeMap
			1 -> entries.first().let { SingleElementStringAttributeMap(attribute = it.key, value = it.value) }
			else -> StringAttributeHashMap(entries.associateTo(HashMap(size)) { it.key to it.value })
		}

	public fun toImmutableMap(): Map<StringAttribute<*>, Any> =
		toMap().toHashMap()

	public fun toMap(): Map<StringAttribute<*>, Any>

	public fun toMutable(): MutableStringAttributeMap =
		MutableStringAttributeHashMap(entries.associateTo(HashMap(size)) { it.key to it.value })

	public fun toMutableMap(): MutableMap<StringAttribute<*>, Any> =
		toMap().toHashMap()

	public fun valueForAttribute(attribute: StringAttribute<*>): Any?


	public companion object
}


public interface MutableStringAttributeMap : StringAttributeMap {

	public fun clear()

	override operator fun iterator(): MutableIterator<StringAttribute<*>>

	public fun put(attribute: StringAttribute<*>, value: Any): Any?

	public fun putAll(attributes: StringAttributeMap) {
		for ((attribute, value) in attributes.entries) {
			put(attribute, value)
		}
	}

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


	init {
		freeze()
	}


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

	init {
		freeze()
	}


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


public fun emptyStringAttributes(): StringAttributeMap =
	EmptyStringAttributeMap


public fun mutableStringAttributesOf(): MutableStringAttributeMap =
	MutableStringAttributeHashMap()


public fun mutableStringAttributesOf(pair: Pair<StringAttribute<*>, Any>): MutableStringAttributeMap =
	MutableStringAttributeHashMap(hashMapOf(pair))


public fun mutableStringAttributesOf(vararg pairs: Pair<StringAttribute<*>, Any>): MutableStringAttributeMap =
	MutableStringAttributeHashMap(hashMapOf(*pairs))


public fun stringAttributesOf(): StringAttributeMap =
	emptyStringAttributes()


public fun stringAttributesOf(pair: Pair<StringAttribute<*>, Any>): StringAttributeMap =
	SingleElementStringAttributeMap(attribute = pair.first, value = pair.second)


public fun stringAttributesOf(vararg pairs: Pair<StringAttribute<*>, Any>): StringAttributeMap =
	pairs.toAttributes()


public inline operator fun <Attribute, reified Value> StringAttributeMap.get(attribute: Attribute): Value?
	where Attribute : StringAttribute<out Value>, Value : Any =
	valueForAttribute(attribute as StringAttribute<*>) as? Value


public fun StringAttributeMap.isNotEmpty(): Boolean =
	!isEmpty()


public fun MutableStringAttributeMap.put(pair: Pair<StringAttribute<*>, Any>): Any? =
	put(pair.first, pair.second)


public fun MutableStringAttributeMap.putAll(pairs: Array<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


public fun MutableStringAttributeMap.putAll(pairs: Iterable<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


public fun MutableStringAttributeMap.putAll(pairs: Sequence<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


public fun MutableStringAttributeMap.removeAttributes(attributes: Array<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


public fun MutableStringAttributeMap.removeAttributes(attributes: Iterable<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


public fun MutableStringAttributeMap.removeAttributes(attributes: Sequence<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


public inline operator fun <Attribute, reified Value> MutableStringAttributeMap.set(attribute: Attribute, value: Value): Value?
	where Attribute : StringAttribute<out Value>, Value : Any =
	put(attribute = attribute, value = value) as? Value


public fun Array<out Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	when (size) {
		0 -> emptyStringAttributes()
		1 -> stringAttributesOf(this[0])
		else -> StringAttributeHashMap(hashMapOf(*this))
	}


public fun Iterable<Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap {
	if (this is Collection) {
		when (size) {
			0 -> return emptyStringAttributes()
			1 -> return stringAttributesOf(if (this is List) this[0] else iterator().next())
		}
	}

	return StringAttributeHashMap(toMap(hashMapOf()))
}


public fun Sequence<Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	StringAttributeHashMap(toMap(hashMapOf()))


public infix fun <Attribute, Value> Attribute.with(value: Value): Pair<Attribute, Value>
	where Attribute : StringAttribute<Value>, Value : Any = this to value

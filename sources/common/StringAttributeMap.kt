package com.github.fluidsonic.fluid.stdlib


@Suppress("unused")
interface StringAttribute<Value : Any>


interface StringAttributeMap {

	val attributes: Set<StringAttribute<*>>

	fun containsAttribute(attribute: StringAttribute<*>) =
		attributes.contains(attribute)

	val entries: Set<Map.Entry<StringAttribute<*>, Any>>

	fun isEmpty() =
		attributes.isEmpty()

	operator fun iterator(): Iterator<StringAttribute<*>> =
		attributes.iterator()

	val size: Int
		get() = attributes.size

	fun toImmutable(): StringAttributeMap =
		when (size) {
			0 -> EmptyStringAttributeMap
			1 -> entries.first().let { SingleElementStringAttributeMap(attribute = it.key, value = it.value) }
			else -> StringAttributeHashMap(entries.associateTo(HashMap(size)) { it.key to it.value })
		}

	fun toImmutableMap(): Map<StringAttribute<*>, Any> =
		toMap().toHashMap()

	fun toMap(): Map<StringAttribute<*>, Any>

	fun toMutable(): MutableStringAttributeMap =
		MutableStringAttributeHashMap(entries.associateTo(HashMap(size)) { it.key to it.value })

	fun toMutableMap(): MutableMap<StringAttribute<*>, Any> =
		toMap().toHashMap()

	fun valueForAttribute(attribute: StringAttribute<*>): Any?


	companion object
}


interface MutableStringAttributeMap : StringAttributeMap {

	fun clear()

	override operator fun iterator(): MutableIterator<StringAttribute<*>>

	fun put(attribute: StringAttribute<*>, value: Any): Any?

	fun putAll(attributes: StringAttributeMap) {
		for ((attribute, value) in attributes.entries) {
			put(attribute, value)
		}
	}

	fun removeAttribute(attribute: StringAttribute<*>): Any?


	companion object
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
	internal val map: HashMap<StringAttribute<*>, Any>
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
	internal val map: HashMap<StringAttribute<*>, Any> = hashMapOf()
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


fun emptyStringAttributes(): StringAttributeMap =
	EmptyStringAttributeMap


fun mutableStringAttributesOf(): MutableStringAttributeMap =
	MutableStringAttributeHashMap()


fun mutableStringAttributesOf(pair: Pair<StringAttribute<*>, Any>): MutableStringAttributeMap =
	MutableStringAttributeHashMap(hashMapOf(pair))


fun mutableStringAttributesOf(vararg pairs: Pair<StringAttribute<*>, Any>): MutableStringAttributeMap =
	MutableStringAttributeHashMap(hashMapOf(*pairs))


fun stringAttributesOf() =
	emptyStringAttributes()


fun stringAttributesOf(pair: Pair<StringAttribute<*>, Any>): StringAttributeMap =
	SingleElementStringAttributeMap(attribute = pair.first, value = pair.second)


fun stringAttributesOf(vararg pairs: Pair<StringAttribute<*>, Any>) =
	pairs.toAttributes()


inline operator fun <Attribute, reified Value> StringAttributeMap.get(attribute: Attribute): Value?
	where Attribute : StringAttribute<out Value>, Value : Any =
	valueForAttribute(attribute as StringAttribute<*>) as? Value


fun StringAttributeMap.isNotEmpty() =
	!isEmpty()


fun MutableStringAttributeMap.put(pair: Pair<StringAttribute<*>, Any>) =
	put(pair.first, pair.second)


fun MutableStringAttributeMap.putAll(pairs: Array<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


fun MutableStringAttributeMap.putAll(pairs: Iterable<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


fun MutableStringAttributeMap.putAll(pairs: Sequence<Pair<StringAttribute<*>, Any>>) {
	for (pair in pairs) {
		put(pair)
	}
}


fun MutableStringAttributeMap.removeAttributes(attributes: Array<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


fun MutableStringAttributeMap.removeAttributes(attributes: Iterable<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


fun MutableStringAttributeMap.removeAttributes(attributes: Sequence<StringAttribute<*>>) {
	for (attribute in attributes) {
		removeAttribute(attribute)
	}
}


inline operator fun <Attribute, reified Value> MutableStringAttributeMap.set(attribute: Attribute, value: Value): Value?
	where Attribute : StringAttribute<out Value>, Value : Any =
	put(attribute = attribute, value = value) as? Value


fun Array<out Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	when (size) {
		0 -> emptyStringAttributes()
		1 -> stringAttributesOf(this[0])
		else -> StringAttributeHashMap(hashMapOf(*this))
	}


fun Iterable<Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap {
	if (this is Collection) {
		when (size) {
			0 -> return emptyStringAttributes()
			1 -> return stringAttributesOf(if (this is List) this[0] else iterator().next())
		}
	}

	return StringAttributeHashMap(toMap(hashMapOf()))
}


fun Sequence<Pair<StringAttribute<*>, Any>>.toAttributes(): StringAttributeMap =
	StringAttributeHashMap(toMap(hashMapOf()))


infix fun <Attribute, Value> Attribute.with(value: Value)
	where Attribute : StringAttribute<Value>, Value : Any = this to value

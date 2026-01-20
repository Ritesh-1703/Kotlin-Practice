fun main(args: Array<String>) {
    val map = mapOf(1 to "One", 2 to "Two")
    println(map)

    val mutableMap = mutableMapOf(1 to "One", 2 to "Two")
    mutableMap[3] = "Three" 
    println(mutableMap)

    val hashMap = hashMapOf(3 to "Three", 1 to "One", 2 to "Two")
    println(hashMap)

    val linkedMap = linkedMapOf(3 to "Three", 1 to "One", 2 to "Two")
    println(linkedMap)

    val sortedMap = sortedMapOf(3 to "Three", 1 to "One", 2 to "Two")
    println(sortedMap)

    println(map[1])
    println(map.get(2))

    map.containsKey(1)
map.containsValue("One")

println(map.size)

mutableMap.put(2, "Two")
mutableMap[1] = "ONE"
mutableMap.remove(2)

println(mutableMap)

map.forEach { key, value ->
    println("$key = $value")
}

for (entry in mutableMap.entries) {
    println("${entry.key} -> ${entry.value}")
}

val map2 = mapOf(1 to 10, 2 to 20, 3 to 30)

val result = map2.filter { (key, value) ->
    value > 15
}
println(result)


val marks = mapOf(
    "Ritesh" to 78,
    "Amit" to 45,
    "Neha" to 90
)

marks
    .filterValues { it >= 50 }
    .forEach { name, mark ->
        println("$name passed with $mark")
    }
    

}
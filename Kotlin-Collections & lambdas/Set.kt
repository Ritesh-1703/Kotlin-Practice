fun main(args: Array<String>) {

    val set = setOf(1, 2, 3, 2)
    println(set)
    
    val set1 = mutableSetOf(1, 2, 3)
    set1.add(4)

    val set2 = hashSetOf(3, 1, 2)
    println(set1)
    println(set2)

    val set3 = linkedSetOf(3, 1, 2)
    println(set3)

    val set4 = sortedSetOf(3, 1, 2)
    println(set4)

    println(set2.contains(20)) 
    println(set2.size)
    
    for (item in set) {
    println(item)
    }

    var set5 = mutableSetOf(17,3,84,9,4,0,7,35,46,8,2)
    println(set5)

    set5.forEach {
    println(it)
}

val even = set5.filter { it % 2 == 0 }

println(even)

set5.filterNot { it % 2 == 0 }
    println(set5)

  println(set5.filterIndexed { index, value ->index % 2 == 0 })



val mixed = setOf(1, "Kotlin", 2.5)

val strings = mixed.filterIsInstance<String>()
println(strings)

val squares = set5.map { it * it }
println(squares)

val a = setOf(1, 2)
val b = setOf(2, 3)

val union = a union b
println(union)

val intersection = a intersect b
println(intersection)

val diff = a subtract b
println(diff)

val users = listOf("Ritesh", "Amit", "Ritesh")

val uniqueUsers = users.toSet()
println(uniqueUsers)
}
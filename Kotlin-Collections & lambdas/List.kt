


fun main(args: Array<String>) {


    val list = listOf(10, 20, 30)

    var list2 = mutableListOf(1, 2, 3)
    list2.add(4)

    println(list2) 


    val numbers = listOf(1, 2, 3, 4, 5)
    // val doubled = numbers.map { it * 2 }
    println(doubled) // Output: [2, 4, 6, 8, 10]

    println(numbers[0])     
    println(numbers.get(1))   

    println(numbers.contains(20)) 

    val list3 = mutableListOf(1, 2, 3)

        list3.add(4)
        list3.remove(2)
        list3[0] = 10

        println(list3)

        for (item in list) {
            println(item)
        }

    list2.forEach {
    println(it)
}


    list2.forEachIndexed { index, value ->
    println("Index $index = $value")
}

val nums = listOf(1, 2, 3, 4, 5)

val even = nums.filter { it % 2 == 0 }

println(even)

val result = nums.filterNot { it % 2 == 0 }

println(result)


val mixed = listOf(1, "Kotlin", 2.5)

val strings = mixed.filterIsInstance<String>()
println(strings)


val squares = nums.map { it * it }
println(squares)

nums.mapIndexed { index, value ->
    "$index:$value"
}
println(nums)


val list2 = listOf(listOf(1, 2), listOf(3, 4))

val result = list.flatMap { it }


println(result)

// list2.removeIf { it > 3 }
// list2.replaceAll { it * 2 }
// println(list2)


nums
    .filter { it % 2 == 0 }
    .map { it * 10 }
    .forEach { println(it) }

val marks = listOf(35, 67, 80, 45)

marks
    .filter { it >= 40 }
    .forEach { println("Pass: $it") }

}
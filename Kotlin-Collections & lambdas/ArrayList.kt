fun main(args: Array<String>) {
    val list = ArrayList<Any>()
    list.add(1)
    list.add(2)
    list.add(3) 
    println(list)
    val list2 = arrayListOf("A", "B", "C")
    println(list2)

    val list3 = ArrayList(listOf(1, 2, 3))
    println(list3)

 list.add("D")
list.add(1, "B1")
println(list)

println(list[0])
println(list.get(1))

list[0] = "A1"
list.set(1, "B2")
println(list)

list.remove("B")
list.removeAt(0)
println(list)
println(list.size)


println(list.indexOf(2)) 

println(list.lastIndexOf(2))

for (item in list) {
    println(item)
}

list.forEach {
    println(it)
}

list.forEachIndexed { index, value ->
    println("$index = $value")
}

val filteredList = list.filter { 
    (it is Int) && (it > 1)
}

println(filteredList)

val even = list.filter { it is Int && it % 2 == 0 }
println(even)

val squares = list3.map { it * it }
println(squares)

}
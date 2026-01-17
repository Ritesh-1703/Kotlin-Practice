class Person {
    var name: String = ""
    var age: Int = 0
}

fun main(args: Array<String>) {
    val person = Person()
    person.name = "Ram"
    person.age = 30

    println("Name: ${person.name}")
    println("Age: ${person.age}")
}
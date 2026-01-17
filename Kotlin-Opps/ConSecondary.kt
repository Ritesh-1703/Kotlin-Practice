class Student {
    var name: String
    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

fun main() {
    val s1 = Student("Ritesh", 22)
    println(s1.name)  
    println(s1.age)  
}
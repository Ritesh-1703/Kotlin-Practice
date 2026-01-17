class Person(val name: String, var age: Int){
    init {
        println("Person Object Created ${name}")
        println("Name: ${name}")
        println("Age: ${age}")
    }
}

fun main(args: Array<String>) {
    val person = Person("Shyam",25)
    
}
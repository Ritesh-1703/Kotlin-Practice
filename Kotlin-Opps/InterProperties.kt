interface Person {
    val name: String
}

class Student : Person {
    override val name = "Ritesh"
}
fun main() {
    val student = Student()
    println("Student Name: ${student.name}")
}
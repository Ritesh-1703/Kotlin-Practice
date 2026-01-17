abstract class Person(val name: String) {
    abstract fun role()
}

class Student(name: String) : Person(name) {
    override fun role() {
        println("$name is a student")
    }
}
class Teacher(name: String) : Person(name) {
    override fun role() {
        println("$name is a teacher")
    }
}

fun main() {
    val student = Student("Virat")
    val teacher = Teacher("Mrs. Anushka")
    student.role()
    teacher.role()
}
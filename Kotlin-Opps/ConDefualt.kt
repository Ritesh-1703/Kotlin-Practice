class Student(val name: String = "Unknown", val age: Int = 18){

    init{
        println("Student Name: $name")
        println("Student Age: $age")
    }
}

fun main() {
    val s1 = Student()
    val s2 = Student("Ritesh")
    val s3 = Student("Ritesh", 22)
}

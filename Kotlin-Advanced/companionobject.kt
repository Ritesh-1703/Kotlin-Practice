class Student {
    companion object {
        var college = "SPPU"
        fun showCollege() {
            println(college)
        }
    }
}



class Counter {
    companion object {
        var count = 0
    }

    fun increment() {
        count++
    }
}
fun main() {

     // Accessing companion object members without creating an instance
    println("College Name: ${Student.college}")
    Student.showCollege()


    val c1 = Counter()
    val c2 = Counter()

    c1.increment()
    c2.increment()

    println(Counter.count)
}
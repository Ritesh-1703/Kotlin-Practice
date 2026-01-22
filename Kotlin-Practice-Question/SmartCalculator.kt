class SmartCalculator {
    
    fun add(a : Int, b:Int): Int{
        println("add(Int , Int): $a + $b = ${a+b}")
        return a+b
    }

    fun add(a: Int, b:Int , c:Int):Int{
        println("add(Int , Int, Int): $a + $b + $c = ${a+b+c}")
        return a+b+c
    
    }

    fun add(a: Double, b:Double): Double{
        println("add(Double , Double): $a + $b = ${a+b}")
        return a+b
    }

    fun add(vararg numbers: Int): Int {
        val sum = numbers.sum()
        println("add(vararg numbers: Int): ${numbers.joinToString(" + ")} = $sum")
        return sum
    }

    fun add(a: Int = 0, b: Int = 0 , c: Int = 0, d: Int = 0): Int {
        println("add(Int = 0, Int = 0, Int = 0, Int = 0): $a + $b + $c + $d = ${a+b+c+d}")
        return a+b+c+d
    }

    fun <T: Number>addGeneric(a: T, b: T): Double {
        val sum = a.toDouble() + b.toDouble()
        println("addGeneric(Number, Number): $a + $b = $sum")
        return sum
    }

    operator fun plus(other: SmartCalculator): SmartCalculator {
        println("plus(SmartCalculator): $this + $other")
        return this
    }

    fun SmartCalculator.add(numbers: List<Int>): Int {
        val sum = numbers.sum()
        println("Extension add(List<Int>): ${numbers.joinToString(" + ")} = $sum")
        return sum
    }

    
}

fun testFunctionOverloading(){
    println("Function Overloading Tests:")

    val calculator = SmartCalculator()

    println("\n-- Basic Overloading --")
    calculator.add(5, 10)

    println("\n-- Overloading with Different Three Parameters --")
    calculator.add(5, 10, 15)

    println("\n-- Overloading with Double Parameters --")
    calculator.add(5.5, 10.5)

    println("\n-- Overloading with Varargs varable arguments --")
    calculator.add(1, 2, 3, 4, 5,6)

    println("\n-- Overloading with Default Parameters --")
    calculator.add(a = 5, c = 15)
    calculator.add()
    calculator.add(10, 20, 30, 40)

    println("\n-- Generic Function Overloading --")
    calculator.addGeneric(5, 10)
    calculator.addGeneric(5.5, 10.5)

    println("\n-- Operator Overloading --")
    val calc2 = SmartCalculator()
    calculator + calc2

    println("\n-- Extension Function Overloading --")
    var numbersList = listOf(1, 2, 3, 4, 5)
    calculator.add(numbersList)

}

fun main(args: Array<String>) {
    testFunctionOverloading()
}
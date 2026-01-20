fun main(args: Array<String>) {
    val add = { a: Int, b: Int -> a + b }
println(add(2, 3))   // 5


val multiply: (Int, Int) -> Int = { a, b ->
    a * b
}
println(multiply(4, 5))  // 20
 

fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)

}

val sumResult = operateOnNumbers(6, 7, add)
println(sumResult)  // 13

val productResult = operateOnNumbers(6, 7, multiply)
println(productResult)  // 42

val greet: () -> Unit = {
    println("Hello Kotlin")
}
greet()  // Hello Kotlin

val operation = { a: Int, b: Int ->
    println("Adding")
    a + b   // last line is return value
}
println(operation(3, 4))  // Adding 7

val check = { x: Int ->
    if (x > 0) "Positive" else "Negative"
}
println(check(5))   // Positive
println(check(-3))  // Negative

val list = listOf(1, 2, 3)

list.forEach { println(it) }
list.forEachIndexed { index, value ->
    println("Index: $index, Value: $value")
}
    val even = list.filter { it % 2 == 0 }
    println("Even numbers: $even")

    val square = list.map { it * it }
    println("Squares: $square")

    println("Any number > 5: ${list.any { it > 5 }}")
    println("All numbers > 0: ${list.all { it > 0 }}")
    println("None numbers < 0: ${list.none { it < 0 }}")


val grade = { marks: Int ->
    when {
        marks >= 90 -> "A"
        marks >= 60 -> "B"
        else -> "C"
    }
}
println("Grade: ${grade(85)}")  // Grade: B


//Nullable lambda

val name: String? = "Ritesh"

name?.let {
    println(it.length)
}

val nullableLambda: ((Int, Int) -> Int)? =  null
nullableLambda?.let {
    val result = it(2, 3)
    println("Result: $result")
}?: println("Lambda is null")

nullableLambda?.invoke(2, 3)?.let {
    println("Result using invoke: $it")
}


//Lambda with let, apply, run, also

val message: String? = "Hello Kotlin"

message?.let {
    println("Message length using let: ${it.length}")
}
    val upperCaseMessage = message?.apply {
        this.uppercase()
    }

    println("Uppercase message using apply: $upperCaseMessage")         

    val runResult = message?.run {
        this.length
    }

    println("Message length using run: $runResult")

    message?.also {
        println("Original message using also: $it")
    }?.let {
        it.reversed()
    }?.also {
        println("Reversed message using also: $it")
    }
}
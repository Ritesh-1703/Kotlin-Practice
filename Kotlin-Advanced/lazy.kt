// class lazyClass{
//     val lazyValue: String by lazy {
//         println("Computed!")
//         "Hello, Lazy!"
//     }

// fun printLazyValue() {
//         println(lazyValue)
//     }


// }

// fun main() {
//     val instance = lazyClass()
//     println("Before accessing lazyValue")
//     instance.printLazyValue() // Output: Computed! Hello, Lazy!
//     instance.printLazyValue() // Output: Hello, Lazy!

// }

val data by lazy {
    println("Initializing data...")
    "Kotlin Lazy"
}

fun main() {
    println("Program started")
    println(data)
    println(data)
}

// Data class for Pattern
data class Pattern(
    val name: String,
    val description: String,
    val generate: (Int) -> Unit
)

fun interactivePatternGenerator() {
    val patterns = listOf(
        Pattern("Triangle", "Increasing numbers triangle") { n ->
            for (i in 1..n) {
                for (j in 1..i) print("$j ")
                println()
            }
        },
        Pattern("Reverse Triangle", "Decreasing numbers") { n ->
            for (i in n downTo 1) {
                for (j in 1..i) print("$j ")
                println()
            }
        },
        Pattern("Square", "Number square") { n ->
            for (i in 1..n) {
                for (j in 1..n) print("${i*j} ".padStart(3))
                println()
            }
        },
        Pattern("Floyd's Triangle", "Continuous numbers") { n ->
            var num = 1
            for (i in 1..n) {
                for (j in 1..i) {
                    print("${num++} ".padStart(3))
                }
                println()
            }
        }
    )
    
    println("\n🎨 PATTERN GENERATOR MENU")
    println("-".repeat(30))
    
    patterns.forEachIndexed { index, pattern ->
        println("${index + 1}. ${pattern.name}")
        println("   ${pattern.description}")
    }
    
    while (true) {
        print("\nChoose pattern (1-${patterns.size}) or 0 to exit: ")
        var choice = readLine()?.toIntOrNull()
        
        if (choice == 0) {
            println("Goodbye! 👋")
            break
        }

        if (choice == 0 || choice !in 1..patterns.size) {
            println("Invalid choice!")
            continue
        }
        
        val patternChoice = choice!!
        print("Enter size (1-10): ")
        val size = readLine()?.toIntOrNull() ?: 5
        
        if (size !in 1..10) {
            println("Size must be 1-10!")
            continue
        }
        
        println("\n" + "=".repeat(30))
        val selectedPattern = patterns[patternChoice - 1]
        println("${selectedPattern.name} Pattern (Size: $size)")
        println("=".repeat(30))
        selectedPattern .generate(size)
    }
}
fun main(args: Array<String>) {
    interactivePatternGenerator()
}
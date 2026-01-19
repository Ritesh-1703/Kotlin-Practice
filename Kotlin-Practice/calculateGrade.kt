// Using Sealed Class for better type safety
sealed class Grade {
    data object APlus : Grade()
    data object A : Grade()
    data object AMinus : Grade()
    data object BPlus : Grade()
    data object B : Grade()
    data object BMinus : Grade()
    data object CPlus : Grade()
    data object C : Grade()
    data object CMinus : Grade()
    data object DPlus : Grade()
    data object D : Grade()
    data object DMinus : Grade()
    data object F : Grade()
    
    // Extension function to convert to String
    fun toDisplayString(): String = when (this) {
        APlus -> "A+"
        A -> "A"
        AMinus -> "A-"
        BPlus -> "B+"
        B -> "B"
        BMinus -> "B-"
        CPlus -> "C+"
        C -> "C"
        CMinus -> "C-"
        DPlus -> "D+"
        D -> "D"
        DMinus -> "D-"
        F -> "F"
    }
}

// Data class for Student
data class Student(
    val name: String,
    val marks: Int,
    val grade: Grade
) {
    fun display() {
        println("${name.padEnd(15)} : ${marks.toString().padEnd(3)} -> ${grade.toDisplayString()}")
    }
}

fun main() {
    println("=".repeat(50))
    println("🏫 GRADE CALCULATOR SYSTEM")
    println("=".repeat(50))
    
    // Test with different students
    val students = listOf(
        Student("Rahul Sharma", 98, calculateGradeSealed(98)),
        Student("Priya Patel", 85, calculateGradeSealed(85)),
        Student("Amit Kumar", 73, calculateGradeSealed(73)),
        Student("Sneha Singh", 62, calculateGradeSealed(62)),
        Student("Vikram Roy", 55, calculateGradeSealed(55)),
        Student("Neha Gupta", 91, calculateGradeSealed(91))
    )
    
    println("\n📋 Student Grades:")
    println("-".repeat(40))
    students.forEach { it.display() }
    
    // Interactive mode
    println("\n" + "=".repeat(50))
    interactiveGradeCalculator()
}

// Approach 1: Using when with ranges (Compact)
fun calculateGradeCompact(marks: Int): String {
    return when (marks) {
        in 90..100 -> when (marks % 10) {
            in 7..9 -> "A+"
            in 3..6 -> "A"
            else -> "A-"
        }
        in 80..89 -> when (marks % 10) {
            in 7..9 -> "B+"
            in 3..6 -> "B"
            else -> "B-"
        }
        in 70..79 -> when (marks % 10) {
            in 7..9 -> "C+"
            in 3..6 -> "C"
            else -> "C-"
        }
        in 60..69 -> when (marks % 10) {
            in 7..9 -> "D+"
            in 3..6 -> "D"
            else -> "D-"
        }
        else -> "F"
    }
}

// Approach 2: Using nested when (More readable)
fun calculateGradeNested(marks: Int): String {
    return when {
        marks >= 90 -> {
            when {
                marks >= 97 -> "A+"
                marks >= 93 -> "A"
                else -> "A-"
            }
        }
        marks >= 80 -> {
            when {
                marks >= 87 -> "B+"
                marks >= 83 -> "B"
                else -> "B-"
            }
        }
        marks >= 70 -> {
            when {
                marks >= 77 -> "C+"
                marks >= 73 -> "C"
                else -> "C-"
            }
        }
        marks >= 60 -> {
            when {
                marks >= 67 -> "D+"
                marks >= 63 -> "D"
                else -> "D-"
            }
        }
        else -> "F"
    }
}

// Approach 3: Using Sealed Class (Most type-safe)
fun calculateGradeSealed(marks: Int): Grade {
    return when (marks) {
        in 97..100 -> Grade.APlus
        in 93..96 -> Grade.A
        in 90..92 -> Grade.AMinus
        in 87..89 -> Grade.BPlus
        in 83..86 -> Grade.B
        in 80..82 -> Grade.BMinus
        in 77..79 -> Grade.CPlus
        in 73..76 -> Grade.C
        in 70..72 -> Grade.CMinus
        in 67..69 -> Grade.DPlus
        in 63..66 -> Grade.D
        in 60..62 -> Grade.DMinus
        else -> Grade.F
    }
}

// Interactive function
fun interactiveGradeCalculator() {
    println("\n🎯 INTERACTIVE GRADE CALCULATOR")
    println("-".repeat(30))
    
    while (true) {
        print("\nEnter marks (0-100) or 'exit': ")
        val input = readLine()
        
        if (input.equals("exit", ignoreCase = true)) {
            println("Goodbye! 👋")
            break
        }
        
        val marks = input?.toIntOrNull()
        
        if (marks == null || marks !in 0..100) {
            println("❌ Please enter valid marks between 0-100")
            continue
        }
        
        println("\n📊 Grade Results:")
        println("-".repeat(30))
        println("Compact Method  : ${calculateGradeCompact(marks)}")
        println("Nested Method   : ${calculateGradeNested(marks)}")
        
        val gradeSealed = calculateGradeSealed(marks)
        println("Sealed Class    : ${gradeSealed.toDisplayString()}")
        
        // Performance check
        println("\n💡 Performance Remarks:")
        when (gradeSealed) {
            Grade.APlus, Grade.A, Grade.AMinus -> println("Excellent! Keep it up! 🌟")
            Grade.BPlus, Grade.B, Grade.BMinus -> println("Good work! Can improve more! 👍")
            Grade.CPlus, Grade.C, Grade.CMinus -> println("Average. Need to work harder! 📚")
            Grade.DPlus, Grade.D, Grade.DMinus -> println("Poor. Serious improvement needed! ⚠️")
            Grade.F -> println("Failed. Must retake the exam! ❌")
        }
    }
}
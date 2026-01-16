class InfixEx{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            
            println("Using infix function to add 7 and 3: ${7 add 3}")
        }

        infix fun Int.add(other: Int): Int {
            return this + other
        }
    }
}
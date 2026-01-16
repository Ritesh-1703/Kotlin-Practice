class FunExtension{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val text = "Hello"
            println("Original text: $text")
            println("Reversed text: ${text.reverseText()}")

        }

        fun String.reverseText(): String {
            return this.reversed()
        }
    }
}
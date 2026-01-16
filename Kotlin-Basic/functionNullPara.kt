class NullPara{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            printLength("Kotlin")
            printLength(null)

            showMessage()
        }

        fun printLength(text: String?) {
            println(text?.length ?: "No text")
        }

        fun showMessage(): Unit {
            println("Welcome")
        }   

    }
}
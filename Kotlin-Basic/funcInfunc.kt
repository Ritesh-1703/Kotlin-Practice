class FuncInFunc{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            outerFunction()
        }

        fun outerFunction() {

            fun innerFunction() {
                println("Inner Function")
            }

            innerFunction()
        }

    }
}
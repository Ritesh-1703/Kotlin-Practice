class LabledLoop {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            
            abc@ for (i in 1..3) {
                    for (j in 1..3) {
                        if (i == 2 && j == 2) {
                            break@abc
                        }
                        println("i=$i j=$j")
                    }
                }
                println("Exited the outer loop")
           
        }

    }  
    
}


class ContinueAndBreak{
    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            for(i in 1..10){
                if(i==5){
                    continue
                }
                println("Value of i: $i")
            }

            for(i in 1..10){
                if(i==5){
                    break
                }
                println("Value of i: $i")
            }
        }
    }
}
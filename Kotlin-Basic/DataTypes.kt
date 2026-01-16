class DataTypes{
    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            val byteVar: Byte = 127
            val shortVar: Short = 32767
            val intVar: Int = 2147483647
            val longVar: Long = 9223372036854775807L

            val floatVar: Float = 3.14F
            val doubleVar: Double = 3.141592653589793

            val charVar: Char = 'K'
            val stringVar: String = "Kotlin Data Types"

            val booleanVar: Boolean = true

            println("Byte Value: $byteVar")
            println("Short Value: $shortVar")
            println("Int Value: $intVar")
            println("Long Value: $longVar")
            println("Float Value: $floatVar")
            println("Double Value: $doubleVar")
            println("Char Value: $charVar")
            println("String Value: $stringVar")
            println("Boolean Value: $booleanVar")
        }
    }
}
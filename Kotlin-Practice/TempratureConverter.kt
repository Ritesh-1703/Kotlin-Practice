data class TempratureConverter(
    val value: Double,
    val unit: String
    
){
    fun display(){
        println("Temperature: ${"%.2f".format(value)} $unit")
    }
}


fun main(args: Array<String>) {
    println("Temperature Converter")

    while(true){
        println("Choose an option:")
        println("1. Convert Temperature")
        println("2. Exit")
        print("Enter your choice: ")
        val choice = readLine()

        when(choice){
            "1"-> convertTemperatureInput()
            "2"-> {
                println("Exiting...")
                return
            }
            else-> println("Invalid choice, please try again.")
        }
    }
    

}

fun convertTemperatureInput(){

try{

    print("Enter temperature : ")
    val value = readLine()?.toDoubleOrNull()

    println("Enter the unit (C for Celsius, F for Fahrenheit, K for Kelvin): ")
    val unit = readLine()?.uppercase()    

    if(value==null || unit==null){
        println("Invalid input")
        return 
    }

    val converter= TempratureConverter(value,unit)
    converter.display()

    when(unit){
        "C"->{
            val faran= (value * 9/5) + 32
            val kelvin= value + 273.15
            println("$value °C = ${"%.2f".format(faran)} °F")
            println("$value °C = ${"%.2f".format(kelvin)} K")
        }
        "F"->{
            val celsius= (value - 32) * 5/9
            val kelvin= celsius + 273.15
            println("$value °F = ${"%.2f".format(celsius)} °C")
            println("$value °F = ${"%.2f".format(kelvin)} K")
        }
        "K"->{
            val celsius= value - 273.15
            val faran= (celsius * 9/5) + 32
            println("$value K = ${"%.2f".format(celsius)} °C")
            println("$value K = ${"%.2f".format(faran)} °F")
        }
        else->{
            println("Invalid unit")
        }
    }

}catch(e:Exception){
    println("Error Invalid Input: ${e.message}")
}
    
}



// Concepts Covered in This Code
// when expression - as both statement and expression

// Null safety - ?., ?: operators

// String formatting - "%.2f".format(value)

// Data class - for structured data

// Exception handling - try-catch block

// Functions - with return types

// Destructuring - val (celsius, fahrenheit, kelvin) = ...
interface Vehicle {
    fun start()
}

class  Bike : Vehicle {
    override fun start() {
        println("Bike started")
    }    
}

fun main() {
    val myBike= Bike()
    myBike.start()
}   
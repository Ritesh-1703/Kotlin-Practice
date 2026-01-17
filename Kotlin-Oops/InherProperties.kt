open class Vehicle {
    var speed = 60
}

class Bike : Vehicle() {
    fun showSpeed() {
        println("Bike speed is $speed km/h")
    }
}
fun main() {
    val myBike = Bike()
    myBike.showSpeed()
}
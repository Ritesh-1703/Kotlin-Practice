interface Vehicle {
    fun start()
    
    fun stop() {
        println("Vehicle stopped")
    }
}

class Bike : Vehicle {
    override fun start() {
        println("Bike started")
    }
}

class Car : Vehicle {
    override fun start() {
        println("Car started")
    }
    
    override fun stop() {
        println("Car stopped with advanced braking system")
    }
}

fun main() {
    val bike = Bike()
    bike.start()
    bike.stop()

    val car = Car()
    car.start()
    car.stop()
}
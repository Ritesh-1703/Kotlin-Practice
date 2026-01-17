abstract class Vehicle {
    abstract fun rentPerDay(): Double
}

class Car : Vehicle() {
    override fun rentPerDay(): Double {
        return 1500.0
    }
}

class Bike : Vehicle() {
    override fun rentPerDay(): Double {
        return 500.0
    }
}

fun calculateRent(vehicle: Vehicle, days: Int) {
    println("Total Rent: ₹${vehicle.rentPerDay() * days}")
}
fun main() {
    val car = Car()
    val bike = Bike()

    calculateRent(car, 3)
    calculateRent(bike, 5)
}
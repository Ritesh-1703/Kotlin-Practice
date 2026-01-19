// enum class Status {
//     SUCCESS,
//     ERROR,
//     LOADING
// }

enum class Result(val code: Int) {
    SUCCESS(200),
    ERROR(500),
    NOT_FOUND(404)
}


fun handleStatus(status: Result) {
    when (status) {
        Result.SUCCESS -> println("Operation was successful.${status.code}")
        Result.ERROR -> println("An error occurred.${status.code}")
        Result.NOT_FOUND -> println("Resource not found.${status.code}")
    }
}


enum class Direction {
    NORTH, SOUTH, EAST, WEST;

    fun move() {
        println("Moving $name")
    }
}
fun navigate(direction: Direction) {
    when (direction) {
        Direction.NORTH -> println("Heading North")
        Direction.SOUTH -> println("Heading South")
        Direction.EAST -> println("Heading East")
        Direction.WEST -> println("Heading West")
    }
}
fun mainNavigate() {
    val dir = Direction.EAST
    dir.move()
    navigate(dir)
}

fun main() {
    val currentStatus = Result.SUCCESS
    val anotherStatus = Result.ERROR
    val errorStatus = Result.NOT_FOUND
    handleStatus(currentStatus)
    handleStatus(anotherStatus)
    handleStatus(errorStatus)

    mainNavigate()
}
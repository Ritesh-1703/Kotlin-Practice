class Database private constructor() {
    companion object {
        val instance = Database()
    }
}

fun main() {
    val db1 = Database.instance
    val db2 = Database.instance

    println("Are both instances the same? ${db1 === db2}")
}
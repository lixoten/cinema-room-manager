// Stage 2/5: Sold_
package cinema

fun main() {
//    println("""
//Cinema:
//  1 2 3 4 5 6 7 8
//1 S S S S S S S S
//2 S S S S S S S S
//3 S S S S S S S S
//4 S S S S S S S S
//5 S S S S S S S S
//6 S S S S S S S S
//7 S S S S S S S S
//    """.trimIndent()
//    )
    var premiumPrice = 10
    var cheapPrice = 8
    var income = 0

    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    val totalSeats = rows * seats

    if (totalSeats < 60) {
        income = totalSeats * premiumPrice
    } else if (rows % 2 == 0) {
        income = (totalSeats / 2) * premiumPrice + (totalSeats / 2) * cheapPrice
    } else {
        income = ((rows/2)*seats) * premiumPrice + ((rows - (rows/2))*seats) * cheapPrice
    }

    println("Total income:")
    println("$$income")
}
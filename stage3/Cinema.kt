// Stage 3/5: Tickets
package cinema

const val MAX_SEATS = 60

const val PREMIUM_SEAT_INDICATOR: Char = 'P'
const val PREMIUM_SEAT_PRICE: Int = 10

const val CHEAP_SEAT_INDICATOR: Char = 'C'
const val CHEAP_SEAT_PRICE: Int  = 8

const val EMPTY_SEAT_INDICATOR: Char = 'S'
const val RESERVED_SEAT_INDICATOR: Char = 'B'

fun calculateTicketPrice(seatChoice: Char): Int {
    return if (seatChoice == PREMIUM_SEAT_INDICATOR) PREMIUM_SEAT_PRICE else CHEAP_SEAT_PRICE

}

/**
 *  We create and return the cinema seat Array
 *  Determine the price point for each seat
 *  --- If 60 seats or under then all seats are P "Premium for $10 each"
 *  --- If over 60 seats the first 1/2 rows are P "Premium for $10 each" and the rest are "Cheap for $8 each"
 *  --- Note: If ODD number of rows the back rows get an extra cheap row of seats
 */
fun createList(totalRows: Int, seatsInRow: Int): MutableList<MutableList<Char>> {
    val totalSeats = totalRows * seatsInRow

    val cinema = MutableList(totalRows) { MutableList(seatsInRow) { PREMIUM_SEAT_INDICATOR } }

    if (totalSeats > MAX_SEATS ) {
        val p = totalRows / 2

        for (tempRow in cinema.indices) {
            for (i in cinema[tempRow].indices) {
                if (tempRow > p - 1){
                    cinema[tempRow][i] = CHEAP_SEAT_INDICATOR
                }
            }
        }
    }

    return cinema
}

/**
 * Print out Layout
 * if seatNo and rowNo are zero no SEAT selection has been made. else it has
 * return seat Selection. this value will be either a "P" or "C"
 * When iterating through ARRAY print "S" for all unless it is selected seat then print "B"
 */
fun printRowSeatLayout(cinema: MutableList<MutableList<Char>>, totalSeats: Int, rowNo: Int, seatNo: Int): Char  {
    println("\nCinema:\n")
    var i = 0
    repeat(totalSeats) {
        print("${++i} ")
    }
    println()

    var selectedSeat = PREMIUM_SEAT_INDICATOR // default

    var seatIdx: Int
    for (rowIdx in cinema.indices){
        seatIdx = 0
        print(rowIdx + 1) // Printing row # in left column
        for (seat in cinema[rowIdx]){
            ++seatIdx
            if (rowIdx + 1 == rowNo && seatIdx == seatNo) {
                print(" $RESERVED_SEAT_INDICATOR")
                selectedSeat = seat
            } else {
                print(" $EMPTY_SEAT_INDICATOR")
            }
        }
        println()
    }
    println()

    return selectedSeat
}


fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    val cinema = createList(rows, seats)

    printRowSeatLayout(cinema, seats, 0, 0)

    println("Enter a row number:")
    val rowNo = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNo = readln().toInt()

    val seatChoice = printRowSeatLayout(cinema, seats, rowNo, seatNo)

    val ticketPrice: Int = calculateTicketPrice(seatChoice)

    println("Ticket price:")
    println("$$ticketPrice")
}
// Stage 4/5: Menu, please!
package cinema

const val MAX_SEATS = 60
const val PREMIUM_SEAT_PRICE: Int = 10
const val CHEAP_SEAT_PRICE: Int  = 8
const val EMPTY_SEAT_INDICATOR: Char = 'S'
const val RESERVED_SEAT_INDICATOR: Char = 'B'


fun calculateTicketPrice(cinema: MutableList<MutableList<Char>>, totalRows: Int, seatsInRow: Int, rowNo: Int, seatNo: Int): Int {
    cinema[rowNo - 1][seatNo - 1] = RESERVED_SEAT_INDICATOR

    val price: Int

    val totalSeats = totalRows * seatsInRow
    price = if (totalSeats > MAX_SEATS ) {
        if (rowNo <= totalRows / 2) PREMIUM_SEAT_PRICE else CHEAP_SEAT_PRICE
    } else {
        PREMIUM_SEAT_PRICE
    }

    return price
}


fun buyTicket(cinema: MutableList<MutableList<Char>>, totalRows: Int, seatsInRow: Int) {
    println("Enter a row number:")
    val rowNo = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNo = readln().toInt()

    val ticketPrice: Int = calculateTicketPrice(cinema, totalRows, seatsInRow, rowNo, seatNo)

    printRowSeatLayout(cinema, seatsInRow)

    println("Ticket price:")
    println("$$ticketPrice\n")
}


fun printRowSeatLayout(cinema: MutableList<MutableList<Char>>, totalSeats: Int) {
    println("\nCinema:")
    print(" ")
    print(" ")
    var i = 0
    repeat(totalSeats) {
        print("${++i} ")
    }
    println()

    for (rowIdx in cinema.indices){
        print(rowIdx + 1) // Printing row # in left column
        for (seat in cinema[rowIdx]){
            print(" $seat")
        }
        println()
    }
    println()
}


fun exitProcess(){
    return
}


fun main() {
    println("Enter the number of rows:")
    val totalRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()

    val cinema = MutableList(totalRows) { MutableList(seatsInRow) { EMPTY_SEAT_INDICATOR } }

    do {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("0. Exit")
        val menu = readln().toInt()

        when (menu) {
            1 -> printRowSeatLayout(cinema, seatsInRow)
            2 -> buyTicket(cinema, totalRows, seatsInRow)
            0 -> exitProcess()
        }
    } while (menu != 0)
}
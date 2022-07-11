// Stage 5/5: Errors_
package cinema

const val MAX_SEATS = 60
const val PREMIUM_SEAT_PRICE: Int = 10
const val CHEAP_SEAT_PRICE: Int  = 8
const val EMPTY_SEAT_INDICATOR: Char = 'S'
const val RESERVED_SEAT_INDICATOR: Char = 'B'


fun calculateTicketPrice(totalRows: Int, seatsInRow: Int, rowNo: Int, seatNo: Int): Int {
    val price: Int

    val totalSeats = totalRows * seatsInRow
    price = if (totalSeats > MAX_SEATS ) {
        if (rowNo <= totalRows / 2) PREMIUM_SEAT_PRICE else CHEAP_SEAT_PRICE
    } else  PREMIUM_SEAT_PRICE

    return price
}


fun buyTicket(cinema: MutableList<MutableList<Char>>, totalRows: Int, seatsInRow: Int) {
    var ticketPrice = 0
    var done = false
    do {
        println("")

        println("Enter a row number:")
        val rowNo = readln().toInt()
        println("Enter a seat number in that row:")
        val seatNo = readln().toInt()

        try {
            val available = checkAvailability(cinema, rowNo, seatNo)
            if (available) {
                cinema[rowNo - 1][seatNo - 1] = RESERVED_SEAT_INDICATOR
                ticketPrice = calculateTicketPrice(totalRows, seatsInRow, rowNo, seatNo)
                done = true
            } else {
                println("\nThat ticket has already been purchased!")
            }

        } catch (e: IndexOutOfBoundsException) {
            println("\nWrong input!")
        }
    } while (!done)

    println("")
    println("Ticket price: $$ticketPrice")
}


fun checkAvailability(cinema: MutableList<MutableList<Char>>, rowNo: Int, seatNo: Int): Boolean {
    return cinema[rowNo - 1][seatNo - 1] == EMPTY_SEAT_INDICATOR
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
}


fun statistics(cinema: MutableList<MutableList<Char>>, totalRows: Int, seatsInRow: Int) {
    var totalTicketsSold = 0
    var percentage = 0.00
    var currentIncome = 0
    var totalIncome = 0

    for (rowIdx in cinema.indices){
        for (seatInx in cinema[rowIdx].indices){
            if (cinema[rowIdx][seatInx] == 'B') {
                totalTicketsSold++
                currentIncome += calculateTicketPrice(totalRows, seatsInRow, rowIdx + 1, seatInx + 1)
            }
        }
    }

    totalIncome = totalIncomeCalculation(totalRows, seatsInRow)

    try {
        percentage = totalTicketsSold.toDouble() / (totalRows * seatsInRow) * 100

    } catch (e: ArithmeticException) {
        println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDD")
    }

    val formatPercentage = "%.2f".format(percentage)

    println("")
    println("Number of purchased tickets: $totalTicketsSold")
    println("Percentage: $formatPercentage%") // 0.00%
    println("Current income: $$currentIncome") // $0
    println("Total income: $$totalIncome") //$360
}


fun totalIncomeCalculation(totalRows: Int, seatsInRow: Int): Int {
    val totalSeats = totalRows * seatsInRow

    return if (totalSeats < 60) {
        totalSeats * PREMIUM_SEAT_PRICE
    } else if (totalRows % 2 == 0) {
        (totalSeats / 2) * PREMIUM_SEAT_PRICE + (totalSeats / 2) * CHEAP_SEAT_PRICE
    } else {
        (((totalRows-1)/2)*seatsInRow) * PREMIUM_SEAT_PRICE + (((totalRows+1)/2)*seatsInRow) * CHEAP_SEAT_PRICE
    }
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
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val menu = readln().toInt()

        when (menu) {
            1 -> printRowSeatLayout(cinema, seatsInRow)
            2 -> buyTicket(cinema, totalRows, seatsInRow)
            3 -> statistics(cinema, totalRows, seatsInRow)
            0 -> exitProcess()
        }
    } while (menu != 0)
}
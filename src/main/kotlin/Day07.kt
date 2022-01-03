import kotlin.math.abs

class Day07 {
    fun execute() {
        val inputString = Utils.getInput("day07.txt")
        val positions = inputString.split(',').map {
            it.toInt()
        }

        part01(positions)
        part02(positions)
    }

    private fun part01(positions: List<Int>) {
        val max = positions.maxOrNull() ?: 0

        var minFuel = Integer.MAX_VALUE
        for (i in 0..max) {
            val fuel = positions.map {
               abs(it - i)
            }.sum()
            if (fuel < minFuel) {
                minFuel = fuel
            }
        }
        println("Part 1: $minFuel")
    }

    private fun part02(positions: List<Int>) {
        val max = positions.maxOrNull() ?: 0

        var minFuel = Integer.MAX_VALUE
        for (i in 0..max) {
            val fuel = positions.map {
                val distance = abs(it - i)
                calculateFuelOfDistance(distance)
            }.sum()
            if (fuel < minFuel) {
                minFuel = fuel
            }
        }
        println("Part 2: $minFuel")
    }

    private fun calculateFuelOfDistance(distance: Int): Int {
        if (distance % 2 == 0) {
            return (distance/2) * (distance+1)
        } else {
            return (distance/2) * (distance+1) + (distance / 2 + 1)
        }
        // f(1) = 1
        // f(2) = 3
        // f(3) = 6
        // f(4) = 10
        // f(5) = 15
        // f(6) = 21

    }
}
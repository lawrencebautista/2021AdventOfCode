class Day01 {
    fun execute() {
        val inputString = Utils.getInput("day01.txt")
        val numbers = inputString.split('\n').map {
            it.toInt()
        }
        part1(numbers)
        part2(numbers)
    }

    private fun part1(numbers: List<Int>) {
        var prevNumber = Int.MIN_VALUE
        var count = 0
        numbers.forEachIndexed { index, num ->
            if (index != 0 && num > prevNumber) {
                count++
            }
            prevNumber = num
        }
        println("Part 1: $count")
    }

    private fun part2(numbers: List<Int>) {
        var prevSum = Int.MIN_VALUE
        var count = 0
        numbers.forEachIndexed { index, num ->
            if (index + 2 < numbers.size) {
                val currentSum = num + numbers[index + 1] + numbers[index + 2]
                if (index != 0) {
                    if (currentSum > prevSum) {
                        count++
                    }
                }
                prevSum = currentSum
            }
        }
        println("Part 2: $count")
    }
}
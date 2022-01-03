typealias Board = Array<IntArray>
class Day04 {
    fun execute() {
        val inputString = Utils.getInput("day04.txt")
        val lines = inputString.split('\n', limit = 2)
        val numbers = lines[0].split(',').map { it.toInt() }

        val boardsStringArray = lines[1].trim().split("\n\n")
        val boards = parseBoards(boardsStringArray)
//        part01(numbers, boards)
        part02(numbers, boards)
    }

    fun part01(nums: List<Int>, boards: List<Board>) {
        val calledNums = mutableListOf<Int>()
        nums.forEach {
            calledNums.add(it)

            boards.forEachIndexed { index, board ->
                if (isWinner(calledNums, board)) {
                    println("Board $index won with last called number: ${calledNums.last()}")
                    getWinnerValue(board, calledNums)
                    return
                }
            }
        }
    }

    fun part02(nums: List<Int>, boards: List<Board>) {
        val calledNums = mutableListOf<Int>()
        var currentBoards = boards
        nums.forEach {
            calledNums.add(it)

            currentBoards = currentBoards.filter { board ->
                if (isWinner(calledNums, board)) {
                    println("Board won with last called number: ${calledNums.last()}")
                    getWinnerValue(board, calledNums)
                    return@filter false
                }
                return@filter true
            }
        }
    }

    private fun getWinnerValue(board: Board, calledNums: List<Int>) {
        var sum = 0
        board.forEach { row ->
            row.forEach {
                if (!calledNums.contains(it)) {
                    sum += it
                }
            }
        }
        println("Winner score: ${sum * calledNums.last()}")
    }

    private fun parseBoards(boardsStringArray: List<String>): List<Board> {
        return boardsStringArray.map { boardString ->
            val lines = boardString.trim().split('\n')

            // for each row, parse the numbers
            lines.map { line ->
                line.trim().split("\\s+".toRegex()).map {
                    it.trim().toInt()
                }.toIntArray()
            }.toTypedArray()
        }
    }

    private fun isWinner(numbers: List<Int>, board: Board): Boolean {
        // Check rows
        val rowWinner = board.any { row ->
            row.all {
                numbers.contains(it)
            }
        }

        var columnWinner = false
        for (i in 0 until 5) {
            if (board.all { row ->
                numbers.contains(row[i])
            }) {
                columnWinner = true
            }
        }
        return rowWinner || columnWinner
    }
}
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.abs

const val gridSize = 1000

class Day05 {
    fun execute() {
        val inputString = Utils.getInput("day05.txt")

        val segments = inputString.lines().map { line ->
            val (start, end) = line.split("->")
            val (startX, startY) = start.trim().split(",")
            val (endX, endY) = end.trim().split(",")
            LineSegment(startX.toInt(), startY.toInt(), endX.toInt(), endY.toInt())
        }
        part01(segments)
        part02(segments)
    }

    private fun part01(segments: List<LineSegment>) {
        val grid = Array(gridSize) { IntArray(gridSize) }

        for (s in segments) {
            if (s.startX == s.endX) {
                val x = s.startX
                val y1 = min(s.startY, s.endY)
                val y2 = max(s.startY, s.endY)
                for (i in y1..y2) {
                    grid[x][i]++
                }
            } else if (s.startY == s.endY) {
                val y = s.startY
                val x1 = min(s.startX, s.endX)
                val x2 = max(s.startX, s.endX)
                for (i in x1..x2) {
                    grid[i][y]++
                }
            }
        }

        var count = 0
        for (rowNumbers in grid) {
            for (num in rowNumbers) {
                if (num >= 2) count++
            }
        }

//        printGrid(grid)
        println("Part 1: $count")
    }

    private fun part02(segments: List<LineSegment>) {
        val grid = Array(gridSize) { IntArray(gridSize) }

        for (s in segments) {
            if (s.startX == s.endX) {
                val x = s.startX
                val y1 = min(s.startY, s.endY)
                val y2 = max(s.startY, s.endY)
                for (i in y1..y2) {
                    grid[x][i]++
                }
            } else if (s.startY == s.endY) {
                val y = s.startY
                val x1 = min(s.startX, s.endX)
                val x2 = max(s.startX, s.endX)
                for (i in x1..x2) {
                    grid[i][y]++
                }
            } else {
                val x1 = s.startX
                val x2 = s.endX
                val y1 = s.startY
                val y2 = s.endY

                val length = abs(x2 - x1)

                val xIncreasing = x2 > x1
                val yIncreasing = y2 > y1

                for (i in 0..length) {
                    val x = if (xIncreasing) x1 + i else x1 - i
                    val y = if (yIncreasing) y1 + i else y1 - i

                    grid[x][y]++
                }
            }
        }

        var count = 0
        for (rowNumbers in grid) {
            for (num in rowNumbers) {
                if (num >= 2) count++
            }
        }
//        printGrid(grid)
        println("Part 2: $count")
    }

    private fun printGrid(grid: Array<IntArray>) {
        for (rowNumbers in grid) {
            for (num in rowNumbers) {
                if (num < 10) print(num) else print("*")
            }
            print('\n')
        }
    }
}

data class LineSegment(
    val startX: Int,
    val startY: Int,
    val endX: Int,
    val endY: Int
)
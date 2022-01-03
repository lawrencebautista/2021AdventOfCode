class Day03 {
    fun execute() {
        val inputString = Utils.getInput("day03.txt")
        val lines = inputString.split('\n')
        part01(lines)
        part02(lines)
    }

    private fun part01(lines: List<String>) {
        val digits = lines[0].length
        val oneCountArray = Array(digits) { 0 }

        lines.forEach { line ->
            for (i in 0 until digits) {
                if (line[i] == '1') {
                    oneCountArray[i]++
                }
            }
        }

        var g = ""
        var e = ""
        oneCountArray.forEach {
            if (it > lines.size / 2) {
                g += "1"
                e += "0"
            } else {
                g += "0"
                e += "1"
            }
        }
        val gamma = Integer.parseInt(g, 2)
        val epsilon = Integer.parseInt(e, 2)
        println("Part1: ${gamma * epsilon}")
    }

    fun part02(lines: List<String>) {
        val oxy = Integer.parseInt(calculate(lines, true), 2)
        val co = Integer.parseInt(calculate(lines, false), 2)

        println("Oxy: $oxy, Co: $co")
        println("Part2: ${oxy*co}")
    }

    private fun calculate(lines: List<String>, keepMost: Boolean): String {
        val digits = lines[0].length

        var currentLines = lines
        for (i in 0 until digits) {
            var zeroCount = 0
            var oneCount = 0

            currentLines.forEach {
                if (it[i] == '0') {
                    zeroCount++
                } else if (it[i] == '1') {
                    oneCount++
                }
            }

            val charOfInterest = if (oneCount >= zeroCount) {
                if (keepMost) '1'
                else '0'
            } else {
                if (keepMost) '0'
                else '1'
            }

            currentLines = currentLines.filter {
                it[i] == charOfInterest
            }

            if (currentLines.size == 1) {
                return currentLines[0]
            }
        }
        throw IllegalStateException("No lines remaining")
    }
}

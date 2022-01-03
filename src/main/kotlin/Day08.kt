import java.util.*

class Day08 {
    fun execute() {
        val inputString = Utils.getInput("day08.txt")
        val lines = inputString.split('\n').map {
            val (patternString, outputString) = it.split('|')
            val patterns = patternString.trim().split(' ')
            val outputs = outputString.trim().split(' ')
            Line(patterns, outputs)
        }

        part01(lines)
        part02(lines)
    }

    private fun part01(lines: List<Line>) {
        val total = lines.sumOf { line ->
            val count = line.outputs.count {
                it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7
            }
            count
        }
        println("Part 1: $total")
    }

    private fun part02(lines: List<Line>) {
        var currentSum = 0

        // deductive logic:
        // 1 has 2 chars, 7 has 3 chars, 4 has 4 chars, 8 has 7 chars -- eliminates 1,4,7,8
        // 6 has 6 chars, only ONE shared with 1 -- eliminates 6
        // all 5 of 5's chars are in 6 -- eliminates 5
        // all 5 of 5's chars also in 9, but not 0 -- eliminates 9, 0
        // all 5 of 3's chars in 9, but not 2 -- eliminates 2, 3

        lines.forEach { line ->
            val patterns = line.patterns.toMutableList()
            val outputs = line.outputs
            val digits = Array(10) { "" }

            patterns.removeIf {
                when (it.length) {
                    2 -> {
                        digits[1] = it
                       return@removeIf true
                    }
                    3 -> {
                        digits[7] = it
                        return@removeIf true
                    }
                    4 -> {
                        digits[4] = it
                        return@removeIf true
                    }
                    7 -> {
                        digits[8] = it
                        return@removeIf true
                    }
                }
                false
            }

            digits[6] = patterns.find {
                if (it.length != 6) return@find false

                val oneChars = digits[1].toCharArray()
                var matchingCount = 0
                oneChars.forEach { c ->
                    if (it.contains(c)) matchingCount++
                }
                return@find matchingCount == 1
            } ?: ""

            patterns.remove(digits[6])

            digits[5] = patterns.find {
                val sixChars = digits[6].toSet()
                return@find sixChars.containsAll(it.toSet())
            } ?: ""

            patterns.remove(digits[5])

            digits[9] = patterns.find {
                if (it.length != 6) return@find false
                val patternChars = it.toSet()
                return@find patternChars.containsAll(digits[5].toSet())
            } ?: ""

            patterns.remove(digits[9])

            digits[0] = patterns.find {
                it.length == 6
            } ?: ""

            patterns.remove(digits[0])

            digits[3] = patterns.find {
                val nineChars = digits[9].toSet()
                return@find nineChars.containsAll(it.toSet())
            } ?: ""

            patterns.remove(digits[3])

            digits[2] = patterns[0]

            val sortedDigits = digits.map {
                val arr = it.toCharArray()
                arr.sort()
                String(arr)
            }
            val sortedOutputs = outputs.map {
                val arr = it.toCharArray()
                arr.sort()
                String(arr)
            }

            currentSum += decodeOutput(sortedOutputs, sortedDigits)
        }
        println("Part 2: $currentSum")
    }

    private fun decodeOutput(outputs: List<String>, digits: List<String>): Int {
        val value = digits.indexOf(outputs[0])*1000 + digits.indexOf(outputs[1])*100 + digits.indexOf(outputs[2])*10 + digits.indexOf(outputs[3])
        return value
    }

    data class Line(
        val patterns: List<String>,
        val outputs: List<String>
    )
}
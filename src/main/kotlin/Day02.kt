class Day02 {
    fun execute() {
        val inputString = Utils.getInput("day02.txt")
        val commandList = inputString.split('\n')
        part01(commandList)
        part02(commandList)
    }
    private fun part01(commandList: List<String>) {
        var depth = 0
        var distance = 0
        commandList.forEach { command ->
            val tokens = command.split(' ')
            val value = tokens[1].toInt()
            val direction = tokens[0]
            when (direction) {
                "forward" -> distance += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }

        println("Depth: $depth, Distance: $distance, Total: ${depth * distance}")
    }

    private fun part02(commandList: List<String>) {
        var aim = 0
        var distance = 0
        var depth = 0
        commandList.forEach { command ->
            val tokens = command.split(' ')
            val value = tokens[1].toInt()
            val direction = tokens[0]
            when (direction) {
                "forward" -> {
                    distance += value
                    depth += (aim * value)
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        println("Depth: $depth, Distance: $distance, Total: ${depth * distance}")
    }
}
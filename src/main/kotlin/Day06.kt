class Day06 {
    fun execute() {
        val inputString = Utils.getInput("day06.txt")
        val fish = inputString.split(',')
            .map {
                it.toInt()
            }

        part01(fish)
        part02(fish)
    }

    private fun part01(fish: List<Int>) {
        val currentFish = fish.toMutableList()
        val totalDays = 80
        for (i in 0 until totalDays) {
            var fishToAdd = 0
            currentFish.forEachIndexed { index, value ->
                if (value == 0) {
                    currentFish[index] = 6
                    fishToAdd++
                } else {
                    currentFish[index]--
                }
            }
            currentFish.addAll(Array(fishToAdd) {
                8
            })
        }
        println("Part 1: ${currentFish.size}")
    }

    private fun part02(fish: List<Int>) {
        // Would require too much memory
        // Can do it mathematically
        // Every fish Reproduces in 7 days,
        // Total fish after n days assuming one fish with initial value 6: f(n) = 1 + (n/7)
        // Starting fish are either 0,1,2,3,4,5, or 6
        // Total fish after n days assuming one fish with initial value m: f(n,m) = 1 + ((n + (6 - m))/7) = 1 + (6+n-m)/7
        // Now account for its children too
        // f(n, m) =

        // SCRAP ALL THAT
        // can simplify fish list as an array of [# of 0's, # of 1's... # of 8's]

        var fishCounts = LongArray(9)
        fish.forEach {
            fishCounts[it]++
        }

        val totalDays = 256
        for (i in 0 until totalDays) {
            val newFishCounts = LongArray(9)
            fishCounts.forEachIndexed { index, value ->
                if (index == 0) {
                    newFishCounts[8] += value
                    newFishCounts[6] += value
                } else {
                    newFishCounts[index-1] += value
                }
            }
            fishCounts = newFishCounts
        }

        val total = fishCounts.sum()

        println("Part 2: $total")
    }
}
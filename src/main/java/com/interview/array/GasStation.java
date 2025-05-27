package com.interview.array;

/*
 * Problem Link: https://leetcode.com/problems/gas-station/ (100% runtime solution)
 * Solution Explanation: Based on the TechDose solution on YouTube - Circular Tour Problem
 * Video Link: https://www.youtube.com/watch?v=zcnVaVJkLhY
 *
 * Problem Category: Medium, Must Do, Google, Top150
 *
 * Related Problems:
 *  - Maximize the Topmost Element After K Moves: https://leetcode.com/problems/maximize-the-topmost-element-after-k-moves/ Medium
 * - Minimum Moves to Equal Array Elements II: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/ (Medium)
 * - Lonely Pixel II: https://leetcode.com/problems/lonely-pixel-ii/ (Medium)
 * - Find the Shortest Superstring: https://leetcode.com/problems/find-the-shortest-superstring/ (Hard)
 *
 * Problem Statement:
 * There are `n` gas stations arranged in a circular route. At each station `i`, you have:
 * - `gas[i]`: amount of gas available at station `i`
 * - `cost[i]`: amount of gas needed to travel from station `i` to station `i+1`
 *
 * You start with an empty tank and need to find a starting station index where you can complete a full circle.
 * If a solution exists, it is guaranteed to be unique; otherwise, return -1.
 *
 * Examples:
 * Example 1:
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Starting at station 3, you can make a full circular tour back to station 3.
 *
 * Example 2:
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * No starting station allows you to complete a full circular route.
 *
 * Constraints:
 * - `gas.length == n`
 * - `cost.length == n`
 * - 1 <= n <= 10^5
 * - 0 <= gas[i], cost[i] <= 10^4
 */
public class GasStation {

    /*
     * 1. Brute Force Solution
     *
     * Explanation:
     * In this approach, we examine each gas station as a potential starting point. For each
     * starting station, we attempt to travel to each subsequent station in a circular manner,
     * checking if we have enough gas to make it to the next station. If we run out of gas at
     * any point, we discard that starting point.
     *
     * Time Complexity:
     * O(N^2)
     *
     * We use a nested loop: the outer loop runs N times (for each starting station), and the
     * inner loop runs up to N times (to traverse all stations).
     */
    public class BruitforceApprach {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;

            for (int start = 0; start < n; start++) {
                int totalGas = 0;
                int totalCost = 0;
                boolean valid = true;

                for (int i = 0; i < n; i++) {
                    int index = (start + i) % n; // Circular index
                    totalGas += gas[index];
                    totalCost += cost[index];
                    if (totalGas < totalCost) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    return start; // Valid starting index found
                }
            }

            return -1; // No valid starting index found
        }
    }

    /*
     * Explanation:
     * Similar to the improved greedy approach, this solution checks the total amount of gas
     * and total costs at the beginning. If the total gas is less than the total cost, it's
     * impossible to complete the circuit. Then, it follows the same logic of checking for
     * the current gas as we iterate through the stations.
     *
     * Time Complexity:
     * O(N)
     *
     * This approach is linear, making it efficient for larger inputs.
     */
    public class BetterApproach {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int totalGas = 0;
            int totalCost = 0;

            for (int i = 0; i < gas.length; i++) {
                totalGas += gas[i];
                totalCost += cost[i];
            }

            if (totalGas < totalCost) {
                return -1; // Not enough gas to complete the circuit
            }

            int currentGas = 0;
            int startIndex = 0;

            for (int i = 0; i < gas.length; i++) {
                currentGas += gas[i] - cost[i];

                if (currentGas < 0) {
                    startIndex = i + 1; // Reset start index
                    currentGas = 0; // Reset current gas
                }
            }

            return startIndex; // Valid starting index
        }
    }
    /*
    O(N)
     */
    public static class OptimzedApproach {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int length = gas.length; // Number of gas stations
            int startingIndex = 0; // Starting index for the circuit
            int gasLeft = 0; // Amount of gas left in the tank
            int totalGasDeficit = 0; // Total gas deficit encountered

            // Iterate through each gas station
            for (int i = 0; i < length; i++) {
                gasLeft += gas[i] - cost[i]; // Update gas left after refueling and spending

                // If gas left is less than zero, we cannot start from the current index
                if (gasLeft < 0) {
                    totalGasDeficit += gasLeft; // Accumulate the deficit
                    startingIndex = i + 1; // Update starting index to the next station

                    // If we move past the last station, return -1
                    if (startingIndex == length) {
                        return -1;
                    }
                    gasLeft = 0; // Reset gas left
                }
            }

            // Check if the overall gas can cover the total cost
            if ((totalGasDeficit + gasLeft) >= 0) {
                return startingIndex; // Valid starting index found
            } else {
                return -1; // No valid starting index
            }
        }
    }

}

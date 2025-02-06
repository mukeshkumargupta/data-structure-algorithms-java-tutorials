package com.interview.bits;

/**
 * Date 04/03/2017
 * Author: Mukesh Kumar Gupta
 * Category: Easy, Must Do
 *
 * Given a non-negative integer number num. For every number i in the range 0 ≤ i ≤ num,
 * calculate the number of 1's in their binary representation and return them as an array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Problem Link: https://leetcode.com/problems/counting-bits/
 */
public class CountingBitsTillNum {

    /**
     * Brute Force Approach
     *
     * Complexity Analysis:
     * Time Complexity: O(n log n)
     * Each number takes O(log i) time to count bits, and we do this for n numbers.
     * Space Complexity: O(n) - We store results in an array.
     */
    private static class BruteForce {
        public int[] countBits(int n) {
            int[] result = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                result[i] = countOnes(i);
            }
            return result;
        }

        private int countOnes(int num) {
            int count = 0;
            while (num > 0) {
                count += (num & 1);  // Check if the last bit is 1
                num >>= 1;  // Right shift to check the next bit
            }
            return count;
        }
    }

    /**
     * Better Approach (O(n)) – Using Previously Computed Values
     *
     * Idea:
     * Instead of counting 1s from scratch for every number, we use the previously computed
     * values in the result array.
     *
     * Formula:
     * countBits(i) = countBits(i / 2) + (i & 1)
     * - If i is even, i has the same number of 1s as i / 2 (i >> 1).
     * - If i is odd, it has one extra 1 compared to i / 2.
     *
     * Time Complexity: O(n) - Each number is processed in O(1) time.
     * Space Complexity: O(n) - for storing results in the array.
     */
    private static class BetterSolution {
        public int[] countBits(int n) {
            int[] result = new int[n + 1];
            result[0] = 0; // Base case: 0 has 0 bits

            for (int i = 1; i <= n; i++) {
                result[i] = result[i >> 1] + (i & 1);
            }

            return result;
        }
    }
}

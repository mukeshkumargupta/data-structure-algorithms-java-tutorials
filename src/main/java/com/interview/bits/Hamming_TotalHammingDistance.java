package com.interview.bits;

/*
https://leetcode.com/problems/total-hamming-distance/description/
Category: Medium, Facebook, FAANG
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.



Example 1:

Input: nums = [4,14,2]
Output: 6
Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case).
The answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Example 2:

Input: nums = [4,14,4]
Output: 4


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 109
The answer for the given input will fit in a 32-bit integer.
 */
public class Hamming_TotalHammingDistance {
    /*
    🔴 Approach 1: Brute Force (O(n²))
    💡 Idea:
    - Compare every pair of numbers and compute the Hamming distance.
    - Hamming distance between two numbers is the number of differing bits in their binary representation.

    🔴 Time Complexity: O(n² * 32) ≈ O(n²), since we compare every pair and process 32 bits.
    🔵 Space Complexity: O(1), as we only use a few extra variables.
    ❌ Too slow! We compare every pair, leading to a quadratic time complexity.
    */
    private static class BruitForce {
        public int totalHammingDistance(int[] numbers) {
            int totalDistance = 0;
            int length = numbers.length;

            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    totalDistance += Integer.bitCount(numbers[i] ^ numbers[j]);
                }
            }
            return totalDistance;
        }
    }

    /*
    🟡 Approach 2: Better (O(n * 32))
    💡 Idea:
    - Instead of comparing every pair, analyze bitwise contributions.
    - Each bit position contributes `countOfOnes * countOfZeros` to the total distance.

    🔵 Time Complexity: O(n * 32) ≈ O(n), since we iterate over 32 bits for n numbers.
    🔵 Space Complexity: O(1), using only a few integer variables.
    ✅ Much faster! We avoid unnecessary comparisons by iterating bitwise.
    */
    private static class Better {
        public int totalHammingDistance(int[] numbers) {
            int totalDistance = 0;
            int length = numbers.length;

            for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
                int countOfOnes = 0;

                for (int number : numbers) {
                    countOfOnes += (number >> bitPosition) & 1; // Check if the bit is set
                }

                int countOfZeros = length - countOfOnes;
                totalDistance += countOfOnes * countOfZeros; // Each 1-0 pair contributes to the distance
            }
            return totalDistance;
        }
    }

    /*
    🟢 Approach 3: Optimized (O(n))
    💡 Idea:
    - Early exit optimization: If all numbers are 0, stop iterating.
    - Bitwise traversal optimization: Stop if the remaining numbers are 0.

    🔵 Time Complexity: O(n), as we process at most 32 bits per number.
    🔵 Space Complexity: O(1), using only integer variables.
    🚀 Fastest approach! It avoids unnecessary iterations when numbers become zero.

    🔹 Summary:
    | Approach               | Time Complexity  | Space Complexity | Notes              |
    |------------------------|-----------------|------------------|--------------------|
    | Brute Force (Pairwise) | O(n²)           | O(1)             | ❌ Too slow!       |
    | Bitwise Counting      | O(n * 32) ≈ O(n) | O(1)             | ✅ Efficient!      |
    | Early Exit Optimized  | O(n)             | O(1)             | 🚀 Fastest approach! |

    ✅ The bitwise approach (O(n)) is the best since it avoids redundant calculations.
    */
    private static class Optimal {
        public int totalHammingDistance(int[] numbers) {
            int totalDistance = 0;
            int length = numbers.length;

            for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
                int countOfOnes = 0;
                boolean hasNonZero = false;

                for (int i = 0; i < length; i++) {
                    countOfOnes += (numbers[i] >> bitPosition) & 1;
                    if (numbers[i] != 0) {
                        hasNonZero = true;
                    }
                }

                if (!hasNonZero) break; // If all numbers are zero, stop checking further

                int countOfZeros = length - countOfOnes;
                totalDistance += countOfOnes * countOfZeros;
            }
            return totalDistance;
        }
    }
}

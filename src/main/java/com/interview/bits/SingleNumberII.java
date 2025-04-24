package com.interview.bits;

/*
 * Facebook Playlist:
 * https://www.youtube.com/watch?v=ZbTXZ2_YAgI&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=7
 *
 * LeetCode Problem: https://leetcode.com/problems/single-number-ii/
 *
 * Category: Medium, Facebook, Tricky, Must Do
 *
 * Related Problem:
 * - https://leetcode.com/problems/single-number-iii/ (Medium)
 *
 * Derived Question:
 * If each number appears 5 times or 4 times except for one,
 * then take % N to find the unique number.
 *
 * Problem Statement:
 * Given an integer array nums where every element appears three times
 * except for one, which appears exactly once. Find the single element and return it.
 *
 * You must implement a solution with:
 * - Linear runtime complexity (O(N))
 * - Constant extra space (O(1))
 *
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * Constraints:
 * - 1 <= nums.length <= 3 * 10^4
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - Each element in nums appears exactly three times except for one element,
 *   which appears only once.
 */
public class SingleNumberII {

    /*
     * Let's dry run the given solution for Single Number II using bitwise manipulation.
     * We'll use the input: nums = [2,2,3,2]
     *
     * Understanding the Approach:
     *
     * 1. Bit Frequency Counting:
     *    - We maintain a 32-bit array (arr[32]) to count the frequency of each bit (0th bit, 1st bit, ..., 31st bit) across all numbers.
     *    - Each bit position is incremented whenever a 1 appears at that position across all numbers.
     *
     * 2. Modulo 3 Reduction:
     *    - Since all numbers except one appear exactly three times, any bit that appears 3k times (where k is an integer) should be ignored.
     *    - We perform arr[i] %= 3, so bits that appeared in multiples of three are zeroed out.
     *
     * 3. Reconstructing the Unique Number:
     *    - After processing all numbers, we reconstruct the unique number from arr[32] using bitwise operations.
     *
     * Step-by-Step Dry Run:
     *
     * Input: nums = [2,2,3,2]
     *
     * Binary Representation of Numbers:
     *
     * 2  =  0000 0010
     * 2  =  0000 0010
     * 3  =  0000 0011
     * 2  =  0000 0010
     *
     * Step 1: Counting Bit Frequencies
     * We iterate over each bit position (0 to 31) and count occurrences of 1s.
     *
     * Bit Position | Num1 (2) | Num2 (2) | Num3 (3) | Num4 (2) | Count  | arr[i] % 3 |
     * --------------------------------------------------------------------------------
     * 0 (LSB)     |    0     |    0     |    1     |    0     |    1   |      1      |
     * 1           |    1     |    1     |    1     |    1     |    4   |    4 % 3 = 1|
     * 2           |    0     |    0     |    0     |    0     |    0   |      0      |
     * 3 to 31     |    0     |    0     |    0     |    0     |    0   |      0      |
     *
     * Step 2: Constructing the Unique Number
     * We now reconstruct the missing number by shifting back:
     *
     * result = 0
     * result |= arr[0] << 0   →  (1 << 0) =  0000 0001  → result = 0000 0001 (1)
     * result |= arr[1] << 1   →  (1 << 1) =  0000 0010  → result = 0000 0011 (3)
     *
     * Final Output:
     * 3 ✅
     *
     * Complexity Analysis:
     * - Time Complexity: O(32 * N) = O(N)
     * - Space Complexity: O(1)
     *
     * Edge Cases Considered:
     * - All numbers appearing exactly 3 times except one
     * - Negative numbers (since we're using 32-bit representation)
     * - Zero included in the input
     * - Single-element array (base case)
     *
     * Extended Problem:
     * If the question is extended such that every number appears exactly 5 times except for one unique number,
     * then changing `% 3` to `% 5` will work.
     *
     * Explanation:
     * - Instead of counting bits modulo **3**, we will count them modulo **5**.
     * - This means that any bit that appears **5 times** will be ignored, and only the bits contributing to
     *   the unique number (which appears once) will remain.
     *
     * Updated Code:
     *
     * ```java
     * public int singleNumber(int[] nums) {
     *     int[] arr = new int[32];
     *     for (int i = 0; i < 32; i++) {
     *         for (int num : nums) {
     *             arr[i] += (num >> i) & 1;
     *             arr[i] %= 5; // Change from %3 to %5
     *         }
     *     }
     *
     *     int result = 0;
     *     for (int i = 0; i < 32; i++) {
     *         result |= arr[i] << i;
     *     }
     *
     *     return result;
     * }
     * ```
     *
     * Why It Works:
     * - Each bit is counted across all numbers.
     * - Since all numbers (except one) appear **5 times**, their contribution to the bit count will be a multiple of 5.
     * - The unique number contributes **only once**, so its bit count remains **1**, and we reconstruct it correctly.
     *
     * Complexity:
     * - **Time Complexity**: O(32 * N) = O(N)
     * - **Space Complexity**: O(1)
     *
     * This approach is **scalable**—if numbers appear **k times**, you can generalize it by replacing `% 3` with `% k`.
     */
    public int singleNumber(int[] nums) {
        /*
         * Runtime: 7 ms, faster than 15.91% of Java online submissions for Single Number II.
Memory Usage: 38.6 MB, less than 67.74% of Java online submissions for Single Number II.
         */
        
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int num : nums) {
                arr[i] += (num >> i ) & 1;
                arr[i] %= 3;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result |= arr[i] << i;
        }
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

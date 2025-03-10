package com.interview.array;

/*
 * https://leetcode.com/problems/move-zeroes/
 * Category: Easy, Facebook, Tricky
 *
 * Related Problems:
 * https://leetcode.com/problems/make-array-strictly-increasing/ (Hard)
 * https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/ (Medium)
 * https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/ (Hard)
 * https://leetcode.com/problems/dungeon-game/ (Hard)
 * https://leetcode.com/problems/meeting-rooms-ii/ (Medium)
 * https://leetcode.com/problems/find-missing-observations/ (Medium)
 *
 * Problem Statement:
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 *
 * Note: You must do this in-place without making a copy of the array.
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow-up: Could you minimize the total number of operations done?
 */
public class MoveZeroes {

    /*
     * Brute Force Approach (Extra Space)
     *
     * Idea:
     * - Create a new array.
     * - Copy all non-zero elements.
     * - Fill the remaining positions with zeroes.
     *
     * Complexity Analysis:
     * - Time Complexity: O(N) (Two passes: one for copying non-zero elements, another for copying back)
     * - Space Complexity: O(N) (Extra array used)
     *
     */
    private static class BruitForce {
        public void moveZeroes(int[] nums) {
            int[] temp = new int[nums.length];
            int index = 0;

            // Copy non-zero elements to temp array
            for (int num : nums) {
                if (num != 0) {
                    temp[index++] = num;
                }
            }

            // Copy back to original array
            for (int i = 0; i < nums.length; i++) {
                nums[i] = temp[i];
            }
        }
    }

    /*
     * Better Approach (In-place Swap, Two-Pointer)
     *
     * Idea:
     * - Iterate through the array.
     * - Whenever we find a non-zero, swap it with the first available zero.
     *
     * Complexity Analysis:
     * - Time Complexity: O(N) (Single pass)
     * - Space Complexity: O(1) (No extra space)
     *
     * Dry Run Example:
     * Input: [0,1,0,3,12]
     *
     * Dry Run Table:
     *
     * | Step | i  | j  | nums[]       |
     * |------|----|----|-------------|
     * | Start | -  | 0  | [0,1,0,3,12] |
     * | 1    | 0  | 0  | [0,1,0,3,12] |
     * | 2    | 1  | 1  | [1,0,0,3,12] |
     * | 3    | 2  | 1  | [1,0,0,3,12] |
     * | 4    | 3  | 2  | [1,3,0,0,12] |
     * | 5    | 4  | 3  | [1,3,12,0,0] |
     *
     */
    private static class Better {
        public void moveZeroes(int[] nums) {
            int j = 0; // Pointer for placing non-zero elements

            // Move non-zero elements to the front
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    j++;
                }
            }
        }
    }

    /*
     * Optimal Approach (Minimizing Swaps)
     *
     * Idea:
     * Maintain a pointer `j` for the last known zero index.
     * Swap whenever we find a non-zero.
     *
     * Complexity Analysis:
     * Time Complexity: O(N) (Optimized swaps)
     * Space Complexity: O(1)
     *
     * Comparison of Approaches:
     *
     * | Approach       | Time Complexity | Space Complexity | Notes                  |
     * |---------------|----------------|------------------|------------------------|
     * | Brute Force   | O(N)           | O(N)             | Uses extra space       |
     * | Better        | O(N)           | O(1)             | More swaps             |
     * | Optimal       | O(N)           | O(1)             | Minimizes swaps        |
     *
     * The **Optimal Approach** is the best choice as it does everything in one pass with minimal swaps.
     *
     * Dry Run Example:
     * Input: [5, 2, 6, 8, 0, 0, 9]
     *
     * Understanding the Algorithm:
     * - We maintain a pointer `j` that tracks the position to place the next non-zero element.
     * - We iterate through the array with `i` and swap when we find a non-zero.
     *
     * Dry Run Table:
     *
     * | Step | i  | j  | nums[]              | Swap?                        |
     * |------|----|----|---------------------|------------------------------|
     * | Start | -  | 0  | [5, 2, 6, 8, 0, 0, 9] | -                         |
     * | 1    | 0  | 0  | [5, 2, 6, 8, 0, 0, 9] | No (5 is already non-zero) |
     * | 2    | 1  | 1  | [5, 2, 6, 8, 0, 0, 9] | No (2 is already non-zero) |
     * | 3    | 2  | 2  | [5, 2, 6, 8, 0, 0, 9] | No (6 is already non-zero) |
     * | 4    | 3  | 3  | [5, 2, 6, 8, 0, 0, 9] | No (8 is already non-zero) |
     * | 5    | 4  | 4  | [5, 2, 6, 8, 0, 0, 9] | No (0 is zero, skip)      |
     * | 6    | 5  | 4  | [5, 2, 6, 8, 0, 0, 9] | No (0 is zero, skip)      |
     * | 7    | 6  | 4  | [5, 2, 6, 8, 9, 0, 0] | ✅ Swap 9 and 0           |
     *
     */
    private static class Optimal {
        public void moveZeroes(int[] nums) {
            int j = 0; // Pointer for the first zero

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    if (i != j) { // Only swap when necessary
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                    j++;
                }
            }
        }
    }
}

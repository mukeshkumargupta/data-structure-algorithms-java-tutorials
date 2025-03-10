package com.interview.array;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
 * Category: Easy, Tricky
 *
 * Related Problems:
 * - https://leetcode.com/problems/split-array-largest-sum/ (Hard)
 * - https://leetcode.com/problems/largest-plus-sign/ (Medium)
 * - https://leetcode.com/problems/building-boxes/ (Hard)
 *
 * Video Explanation:
 * - https://www.youtube.com/watch?v=ttznU-BTqTk
 *
 * Problem Statement:
 * You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and increment it by 1.
 * Return the minimum number of operations needed to make nums strictly increasing.
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - 1 <= nums[i] <= 10^4
 *
 * Example 1:
 * Input: nums = [1,1,1]
 * Output: 3
 * Explanation:
 * 1) Increment nums[2] → [1,1,2]
 * 2) Increment nums[1] → [1,2,2]
 * 3) Increment nums[2] → [1,2,3]
 *
 * Example 2:
 * Input: nums = [1,5,2,4,1]
 * Output: 14
 *
 * Example 3:
 * Input: nums = [8]
 * Output: 0
 *
 * Complexity Analysis:
 * - Time Complexity: O(n) (Single pass through the array)
 * - Space Complexity: O(1) (Constant extra space)
 */
public class MinimumOperationstoMaketheArrayIncreasing {

    /*
    Brute Force Approach (Recursive)
Approach
Try all possible ways of incrementing each element.
Recursively check if the array is strictly increasing.
This approach is extremely slow for large inputs.
Complexity Analysis
Time Complexity: Exponential O(2^n) (Due to recursive branching)
Space Complexity: O(n) (Recursion depth)
     */
    private static class BruitForce {
        public int minOperations(int[] nums) {
            return helper(nums, 0);
        }

        private int helper(int[] nums, int index) {
            if (index == nums.length) return 0;

            int minOps = Integer.MAX_VALUE;
            for (int inc = 0; inc <= 10_000; inc++) {
                if (index == 0 || nums[index - 1] < nums[index] + inc) {
                    nums[index] += inc;
                    minOps = Math.min(minOps, inc + helper(nums, index + 1));
                    nums[index] -= inc; // Backtrack
                }
            }
            return minOps;
        }
    }

    /*
    Better Approach (Sorting & Greedy)
    Approach
    Sort the array first.
    Make sure each element is greater than the previous one.
    Use Greedy by incrementing when necessary.
    Complexity Analysis
    Time Complexity: O(n log n) (Due to sorting)
    Space Complexity: O(1)

     */
    private static class Better {
        public int minOperations(int[] nums) {
            Arrays.sort(nums);  // Sorting the array
            int operations = 0;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= nums[i - 1]) {
                    int increment = nums[i - 1] - nums[i] + 1;
                    nums[i] += increment;
                    operations += increment;
                }
            }
            return operations;
        }
    }

    /*
     * Explanation of the Code
     * The goal of this function is to make the array strictly increasing by performing
     * the minimum number of operations, where an operation consists of incrementing an element by 1.
     *
     * Approach:
     * 1. Initialize `operations = 0` to keep track of the number of operations performed.
     * 2. Store the first element of the array in `prev` since it remains unchanged.
     * 3. Loop through the array from index `1` to `n-1`:
     *    - If `nums[i] <= prev`, it means `nums[i]` is not strictly greater than the previous number.
     *    - Compute the `increment` required to make `nums[i]` strictly greater than `prev`:
     *      `increment = (prev - nums[i] + 1)`
     *    - Update `operations` by adding `increment`.
     *    - Update `nums[i]` by adding `increment` so that it becomes strictly greater than `prev`.
     *    - Update `prev` to the new value of `nums[i]` for the next comparison.
     * 4. Return `operations` as the final result.
     *
     * 🔍 Dry Run
     * Example:
     *
     * Input:
     * int[] nums = {1, 5, 2, 4, 1};
     *
     * Step-by-step Execution:
     *
     * | Step   | i  | nums[i] | prev | Condition (nums[i] <= prev)? | Increment  | New nums[i] | Operations |
     * |--------|----|---------|------|------------------------------|------------|-------------|------------|
     * | Start  | -  | {1, 5, 2, 4, 1} | 1 | -                            | -          | -           | 0          |
     * | Step 1 | 1  | 5       | 1    | ❌ No (5 > 1)                  | -          | 5           | 0          |
     * | Step 2 | 2  | 2       | 5    | ✅ Yes (2 <= 5)                | 5 - 2 + 1 = 4 | 6       | 4          |
     * | Step 3 | 3  | 4       | 6    | ✅ Yes (4 <= 6)                | 6 - 4 + 1 = 3 | 7       | 7          |
     * | Step 4 | 4  | 1       | 7    | ✅ Yes (1 <= 7)                | 7 - 1 + 1 = 7 | 8       | 14         |
     *
     * Final Modified `nums`:
     * {1, 5, 6, 7, 8}
     *
     * Final Output:
     * return 14;
     *
     * Complexity Analysis:
     * - Time Complexity: O(n) → We traverse the array only once.
     * - Space Complexity: O(1) → We use only a few variables (`operations`, `prev`, `increment`).
     */
    private static class Optimal {
        public int minOperations(int[] nums) {
            int operations = 0;
            int prev = nums[0]; // The first element remains the same

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= prev) {
                    int increment = (prev - nums[i] + 1);
                    operations += increment;
                    nums[i] += increment;
                }
                prev = nums[i]; // Update previous value
            }
            return operations;
        }
    }

    /*
    Optimized Approach (Without Modifying nums)
    We keep track of the previous value (prev).
    If nums[i] ≤ prev, we calculate the required increment and add it to operations.
    We then update prev to nums[i] + required increment.
     */

    private static class OptimalWithOutModifyingArray {
        public int minOperations(int[] nums) {
            int operations = 0;
            int prev = nums[0]; // Start with the first element

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= prev) {
                    int increment = prev - nums[i] + 1;
                    operations += increment;
                    prev = nums[i] + increment; // Update prev
                } else {
                    prev = nums[i]; // Update prev normally
                }
            }
            return operations;
        }
    }
    
}

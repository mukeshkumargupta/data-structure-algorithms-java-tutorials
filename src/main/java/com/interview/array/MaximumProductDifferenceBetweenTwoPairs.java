package com.interview.array;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
 *
 * Category: Easy, Tricky
 *
 * The product difference between two pairs (a, b) and (c, d) is defined as:
 *      (a * b) - (c * d)
 *
 * Example Calculation:
 *      For (5, 6) and (2, 7): (5 * 6) - (2 * 7) = 16
 *
 * Given an integer array nums, choose four distinct indices w, x, y, and z such that
 * the product difference between pairs (nums[w], nums[x]) and (nums[y], nums[z]) is maximized.
 *
 * Return the maximum such product difference.
 *
 * Example 1:
 * ------------------------------------------------
 * Input: nums = [5,6,2,7,4]
 * Output: 34
 * Explanation:
 *  - Choose indices 1 and 3 for the first pair (6, 7)
 *  - Choose indices 2 and 4 for the second pair (2, 4)
 *  - The product difference is (6 * 7) - (2 * 4) = 34
 *
 * Example 2:
 * ------------------------------------------------
 * Input: nums = [4,2,5,9,7,4,8]
 * Output: 64
 * Explanation:
 *  - Choose indices 3 and 6 for the first pair (9, 8)
 *  - Choose indices 1 and 5 for the second pair (2, 4)
 *  - The product difference is (9 * 8) - (2 * 4) = 64
 *
 * Constraints:
 * ------------------------------------------------
 * - 4 <= nums.length <= 10⁴
 * - 1 <= nums[i] <= 10⁴
 *
 * Related Problems:
 * - Maximum product difference of two pairs
 * - Minimum product difference of two pairs (Try solving in O(n) time)
 */
public class MaximumProductDifferenceBetweenTwoPairs {

    /*
    Sorting Approach: Sort the array and pick the two largest and two smallest elements.
     */
    private static class Better {
        class Solution {
            public int maxProductDifference(int[] nums) {
                Arrays.sort(nums);
                int n = nums.length;
                return (nums[n - 1] * nums[n - 2]) - (nums[0] * nums[1]);
            }
        }
    }

    /*
        https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
        Category: Easy, Tricky

        Single Pass Approach:
        - Keep track of the two largest and two smallest elements in one loop.

        Complexity Analysis:
        Approach       Time Complexity   Space Complexity   Notes
        Sorting        O(n log n)        O(1)               Uses sorting, simpler to implement
        Single Pass    O(n)              O(1)               More efficient, best approach
    */

    public int maxProductDifference(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            // Find two largest numbers
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }

            // Find two smallest numbers
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return (max1 * max2) - (min1 * min2);
    }
}

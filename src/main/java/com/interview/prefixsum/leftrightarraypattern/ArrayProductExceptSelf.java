package com.interview.prefixsum.leftrightarraypattern;

/*
 * Date: 04/28/2020
 * Author: Mukesh Kumar Gupta
 *
 * Problem:
 * https://leetcode.com/problems/product-of-array-except-self/description/
 *
 * Category: Medium
 * Related:
 * https://leetcode.com/problems/trapping-rain-water/ Medium
 * https://leetcode.com/problems/maximum-product-subarray/ Medium
 * https://leetcode.com/problems/paint-house-ii/ Medium Locked
 * https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/ Hard
 * https://leetcode.com/problems/construct-product-matrix/ Medium
 *
 * Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal to
 * the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1, 2, 3, 4]
 * Output: [24, 12, 8, 6]
 *
 * Example 2:
 * Input: nums = [-1, 1, 0, -3, 3]
 * Output: [0, 0, 9, 0, 0]
 *
 * Constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 *
 * Follow-up:
 * Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 */

public class ArrayProductExceptSelf {

    /*
    For each element i, loop through the array and multiply all elements except i.
    ⏱ Complexity:
    Time: O(n^2)

    Space: O(n) (for result array)
     */
    private static class BruitForce {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];

            for (int i = 0; i < n; i++) {
                int prod = 1;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        prod *= nums[j];
                    }
                }
                result[i] = prod;
            }

            return result;
        }
    }

    /*
    🟡 Better Approach (Using prefix & suffix arrays)
    🧠 Idea:
    For every index i, the result is:
    prefix[i] * suffix[i],
    where:

    prefix[i] = product of nums[0..i-1]

    suffix[i] = product of nums[i+1..n-1]
    ⏱ Complexity:
    Time: O(n)

    Space: O(n) (prefix + suffix arrays)
     */
    private static class Better {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] prefix = new int[n];
            int[] suffix = new int[n];
            int[] result = new int[n];

            prefix[0] = 1;
            for (int i = 1; i < n; i++) {
                prefix[i] = prefix[i - 1] * nums[i - 1];
            }

            suffix[n - 1] = 1;
            for (int i = n - 2; i >= 0; i--) {
                suffix[i] = suffix[i + 1] * nums[i + 1];
            }

            for (int i = 0; i < n; i++) {
                result[i] = prefix[i] * suffix[i];
            }

            return result;
        }
    }

    /*
    🟢 Optimal Approach (O(1) extra space)
    🧠 Idea:
    Reuse the result array to store prefix products, and maintain a variable for suffix product.
    🧪 Dry Run:
    Input: [1, 2, 3, 4]

    Prefix pass: [1, 1, 2, 6]

    Suffix pass: Multiply by [24, 12, 4, 1] via reverse loop using a single suffix variable

    Result: [24, 12, 8, 6]

    ⏱ Complexity:
    Time: O(n)

    Space: O(1) (excluding the result array)
     */
    private static class Optimal {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];

            // Step 1: prefix product pass
            result[0] = 1;
            for (int i = 1; i < n; i++) {
                result[i] = result[i - 1] * nums[i - 1];
            }

            // Step 2: suffix product pass
            int suffix = 1;
            for (int i = n - 1; i >= 0; i--) {
                result[i] = result[i] * suffix;
                suffix *= nums[i];
            }

            return result;
        }
    }

}

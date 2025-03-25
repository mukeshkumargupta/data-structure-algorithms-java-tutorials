package com.interview.prefixsum;

import java.util.HashMap;
import java.util.Map;

/*
    Problem: Continuous Subarray Sum
    Category: Facebook
    All facebook tag question:
    https://leetcode.com/problem-list/7p59281/FB/?utm_source=chatgpt.com
    Link: https://leetcode.com/problems/continuous-subarray-sum/
    Video Explanation: https://www.youtube.com/watch?v=1W_HYBqvDLw
    Related:
    https://leetcode.com/problems/subarray-sum-equals-k/ Medium
    https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/ Hard
    https://leetcode.com/problems/intervals-between-identical-elements/ Medium
    https://leetcode.com/problems/apply-operations-to-make-all-array-elements-equal-to-zero/ Medium

    Problem Statement:
    Given an integer array nums and an integer k, return true if nums has a good subarray, or false otherwise.

    A good subarray satisfies:
    - Its length is at least two.
    - The sum of the elements of the subarray is a multiple of k.

    Notes:
    - A subarray is a contiguous part of the array.
    - An integer x is a multiple of k if there exists an integer n such that x = n * k (i.e., x = n * k).
    - 0 is always a multiple of k.

    Example 1:
    Input: nums = [23,2,4,6,7], k = 6
    Output: true
    Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

    Example 2:
    Input: nums = [23,2,6,4,7], k = 6
    Output: true
    Explanation: [23, 2, 6, 4, 7] forms a subarray of size 5 whose sum is 42 (a multiple of 6).

    Example 3:
    Input: nums = [23,2,6,4,7], k = 13
    Output: false

    Constraints:
    - 1 <= nums.length <= 10^5
    - 0 <= nums[i] <= 10^9
    - 0 <= sum(nums[i]) <= 2^31 - 1
    - 1 <= k <= 2^31 - 1
*/
public class ContinuousSubarraySum {
    /*
        Brute Force Approach (O(N²))

        A simple brute-force approach would be to check every possible subarray sum
        and see if it is a multiple of k.

        Algorithm:
        1. Iterate over all possible starting indices i.
        2. Iterate over all possible ending indices j > i.
        3. Compute the sum of the subarray nums[i] to nums[j].
        4. If sum % k == 0, return true.
        5. If no such subarray is found, return false.

        Time Complexity: O(N²)
        Space Complexity: O(1)

        This approach is inefficient for large arrays since it checks all subarrays.
    */
    private static class BruitForce {
        public boolean checkSubarraySumBruteForce(int[] nums, int k) {
            int n = nums.length;

            for (int i = 0; i < n - 1; i++) {
                int sum = nums[i];
                for (int j = i + 1; j < n; j++) {
                    sum += nums[j];
                    if (k == 0 ? sum == 0 : sum % k == 0) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    /*
        Optimized Approach (O(N) using HashMap)
        Instead of recomputing subarray sums, we use prefix sum + modulo to reduce the problem to an O(N) solution.

        Key Observations:
        - If the sum of a subarray sum(i, j) % k == 0, it means:
          (prefixSum[j] - prefixSum[i]) mod k = 0
          which can be rewritten as:
          prefixSum[j] mod k = prefixSum[i] mod k

        - This means if two prefix sums give the same remainder when divided by k, the subarray in between is a multiple of k.

        - We use a HashMap to store (remainder, index).
        - If the same remainder appears at different indices, the subarray between them has a sum that is a multiple of k.

        Algorithm:
        1. Maintain a prefixSum and a HashMap modMap to store (prefixSum % k, index).
        2. Iterate through nums, computing the prefix sum and its remainder when divided by k.
        3. If the remainder is already in modMap and the subarray length is at least 2, return true.
        4. Otherwise, store the remainder in modMap along with its index.
        5. Edge case: Handle k == 0 separately.

        Time Complexity: O(N)
        Space Complexity: O(N) (for storing remainder-index pairs in the HashMap)

        Dry Run Example:

        nums = [23, 2, 4, 6, 7], k = 6

        | Index | Num | Prefix Sum | Prefix Sum % 6 | Map (mod → index) | Result  |
        |-------|-----|------------|---------------|------------------|---------|
        | 0     | 23  | 23         | 23 % 6 = 5    | {0: -1, 5: 0}    | -       |
        | 1     | 2   | 25         | 25 % 6 = 1    | {0: -1, 5: 0, 1: 1} | -    |
        | 2     | 4   | 29         | 29 % 6 = 5    | Found (5 at index 0) | ✅ TRUE |

        Explanation:
        - The subarray [2, 4] (index 1 to 2) has a sum of 6, which is a multiple of 6.
        - Since modMap already had 5 at index 0, the subarray between index 1 and 2 is valid.

        Final Thoughts:
        - Brute Force (O(N²)): Simple but inefficient.
        - Optimized (O(N)): Uses prefix sum + modulo with HashMap for efficiency.
        - Key Trick: If two prefix sums have the same remainder, their in-between subarray sum is a multiple of k.
    */
    private static class Optimized {
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> modMap = new HashMap<>();
            modMap.put(0, -1);  // To handle the case where prefixSum itself is a multiple of k
            int prefixSum = 0;

            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                int remainder = (k == 0) ? prefixSum : (prefixSum % k);

                if (modMap.containsKey(remainder)) {
                    if (i - modMap.get(remainder) > 1) {
                        return true;
                    }
                } else {
                    modMap.put(remainder, i);
                }
            }

            return false;
        }
    }
}

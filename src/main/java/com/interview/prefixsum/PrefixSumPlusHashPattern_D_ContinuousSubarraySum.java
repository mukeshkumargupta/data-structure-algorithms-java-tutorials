package com.interview.prefixsum;

import java.util.HashMap;
import java.util.Map;

/*
    Problem: Continuous Subarray Sum
    Category: Facebook, Tricky, Prefix Sum + HashMap
    Link: https://leetcode.com/problems/continuous-subarray-sum/
    Video Explanation: https://www.youtube.com/watch?v=1W_HYBqvDLw

    All Facebook tagged questions:
    https://leetcode.com/problem-list/7p59281/FB/?utm_source=chatgpt.com

    Related Problems:
    - https://leetcode.com/problems/subarray-sum-equals-k/ (Medium)
    - https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/ (Hard)
    - https://leetcode.com/problems/intervals-between-identical-elements/ (Medium)
    - https://leetcode.com/problems/apply-operations-to-make-all-array-elements-equal-to-zero/ (Medium)

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
public class PrefixSumPlusHashPattern_D_ContinuousSubarraySum {
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
        public boolean checkSubarraySum(int[] nums, int k) {
            int n = nums.length;

            for (int i = 0; i < n - 1; i++) { // Start index
                int sum = nums[i]; // Initialize sum with the first element, i think it can be generalised if minimum length is asked more than two then it seems
                // first two u need to sum up and then run loop

                for (int j = i + 1; j < n; j++) { // Ensure subarray has at least 2 elements
                    sum += nums[j];

                    if (k == 0 ? sum == 0 : sum % k == 0) { // Check divisibility condition
                        return true;
                    }
                }
            }

            return false; // No valid subarray found
        }
    }

    private static class BruitForceNormalPatternWillNotWOrkBecauseOfMoreThanOneLength {
        public boolean checkSubarraySum(int[] nums, int k) {
            int n = nums.length;

            for (int i = 0; i < n - 1; i++) { // Start index
                int sum = 0; // Initialize sum with the first element

                for (int j = i; j < n; j++) { // Ensure subarray has at least 2 elements ====> this will not work as normal bruitforce pattern because more than one length required, if one lenth required then it can work
                    sum += nums[j];

                    if (k == 0 ? sum == 0 : sum % k == 0) { // Check divisibility condition
                        return true;
                    }
                }
            }

            return false; // No valid subarray found
        }
    }

    public boolean checkSubarraySumLengthGreaterThanTwo(int[] nums, int k) {//Next variant so u can scal this problem, here it can asked mimimum and maximum lenth ask well for which this criteria is met
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) { // Ensure at least 3 elements
            int sum = nums[i] + nums[i + 1]; // Initialize with first 2 elements

            for (int j = i + 2; j < n; j++) { // Start from i+2 to ensure length > 2
                sum += nums[j];

                if (k == 0 ? sum == 0 : sum % k == 0) { // Check divisibility condition
                    return true; // Found valid subarray
                }
            }
        }

        return false; // No valid subarray found
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
        /*
        🔹 Understanding the Prefix Sum & Remainder Concept
        We are tracking prefix sum modulo k to determine if a subarray sum is a multiple of k.
        If the same remainder appears again, then the subarray between these two indices sums to a multiple of k.

        🔹 Why is put(0, -1) Needed?
        Consider a case where the entire prefix sum itself is already a multiple of k at some index i.

        Example 1:

        nums = [6, 2, 4]
        k = 6

        Step-by-Step Calculation:

        | Index (i) | Element (nums[i]) | Prefix Sum | Remainder (prefixSum % k) | Seen Before? | Condition Met? |
        |-----------|-------------------|------------|---------------------------|--------------|--------------|
        | 0         | 6                 | 6          | 6 % 6 = 0                 | ✅ Yes (0 at index -1) | ✅ Valid Subarray [6] |

        At i = 0, the prefix sum itself (6) is already a multiple of k.

        Without `put(0, -1)`, our logic would not detect `[6]` as a valid subarray
        because we check `i - remainderIndexMap.get(remainder) > 1`.

        `-1` ensures that even when `i = 0`, the subarray is considered valid.

        🔹 Generalizing: How does -1 help?
        1️⃣ Ensures subarrays starting at index 0 are considered.
           - Example: `[6, 2, 4]` with `k=6` would fail without it.

        2️⃣ Avoids unnecessary conditions in the main loop.
           - Instead of handling special cases separately, we initialize `0` with `-1`, making the logic uniform.
        */
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> modMap = new HashMap<>();
            modMap.put(0, -1);  // To handle the case where prefixSum itself is a multiple of k
            int prefixSum = 0;

            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                int remainder = (k == 0) ? prefixSum : (prefixSum % k);// if k is zero then prefixSum itself is a multiple of k

                if (modMap.containsKey(remainder)) {
                    if (i - modMap.get(remainder) > 1) {//Fow two length sub array, it can be generalised
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

package com.interview.prefixsum;

import java.util.*;
/*
 * https://leetcode.com/problems/contiguous-array/
 * Video Explanation: https://www.youtube.com/watch?v=9ZyLjjk536U&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=59
 * Category: Medium, Tricky
 *
 * Related:
 * - https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/ (Medium)
 *
 * Notes:
 * - This problem can be derived further: questions may ask for both minimum and maximum subarray lengths.
 * - Try exploring with sliding window techniques as well (not yet applied here).
 *
 * Problem:
 * Given a binary array `nums`, return the maximum length of a contiguous subarray
 * with an equal number of 0 and 1.
 *
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 *
 * Example 2:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] or [1, 0] is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - nums[i] is either 0 or 1.
 */

public class PrefixSumPlusHashPattern_E_ContiguousArray {
    /*
        🔹 Approach 1: Brute Force (Nested Loops)

        🚀 Idea
        - Iterate through all subarrays.
        - Count 0s and 1s separately.
        - If the counts are equal, update the max length.

        ⏳ Complexity Analysis
        - Time Complexity: O(N²) due to nested loops.
        - Space Complexity: O(1), as we only use a few integer variables.
    */
    private static class BruitForce {
        public int findMaxLengthBruteForce(int[] nums) {
            int n = nums.length;
            int maxLength = 0;

            for (int start = 0; start < n; start++) {
                int zeroCount = 0, oneCount = 0;

                for (int end = start; end < n; end++) {
                    if (nums[end] == 0) zeroCount++;
                    else oneCount++;

                    if (zeroCount == oneCount) {
                        maxLength = Math.max(maxLength, end - start + 1);
                    }
                }
            }

            return maxLength;
        }
    }

    /*
        🔹 Approach 2: Optimized Using Prefix Sum & HashMap

        🚀 Idea
        - Convert 0 to -1, so that finding an equal count of 0s and 1s becomes finding a subarray with sum = 0.
        - Maintain a prefix sum and store its first occurrence index in a hash map.
        - If the same sum appears again, the subarray between those indices has an equal number of 0s and 1s.

        🔹 Why Hash Map Works?
        - The prefix sum helps in identifying subarrays where the number of 0s and 1s cancel out.
        - When a prefix sum repeats at two indices, the subarray between them has a sum of 0.

        ⏳ Complexity Analysis
        - Time Complexity: O(N), since we traverse the array once.
        - Space Complexity: O(N), for storing prefix sums in the HashMap.
    */
    private static class Optimised {
        public int findMaxLength(int[] nums) {
            int maxLength = 0;
            int prefixSum = 0;
            HashMap<Integer, Integer> sumIndexMap = new HashMap<>();

            // To handle cases where the subarray starts from index 0
            sumIndexMap.put(0, -1);

            for (int i = 0; i < nums.length; i++) {
                prefixSum += (nums[i] == 0) ? -1 : 1;

                if (sumIndexMap.containsKey(prefixSum)) {
                    int subarrayLength = i - sumIndexMap.get(prefixSum);
                    maxLength = Math.max(maxLength, subarrayLength);
                } else {
                    sumIndexMap.put(prefixSum, i);
                }
            }

            return maxLength;
        }
    }
    
}

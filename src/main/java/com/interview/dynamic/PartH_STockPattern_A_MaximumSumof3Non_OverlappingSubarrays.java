package com.interview.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Category: Hard, Facebook, FAANG
https://www.youtube.com/watch?v=rAzsm6tEi8Y
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.



Example 1:

Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically smaller.
Example 2:

Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]


Constraints:

1 <= nums.length <= 2 * 104
1 <= nums[i] < 216
1 <= k <= floor(nums.length / 3)
 */
public class PartH_STockPattern_A_MaximumSumof3Non_OverlappingSubarrays {
    /*
    🔴 Brute Force Approach (O(N³))
💡 Idea
Iterate over all possible subarrays of length k for the first subarray.

For each first subarray, iterate for the second subarray ensuring no overlap.

For each first and second subarray, iterate for the third subarray.

Keep track of the maximum sum and corresponding indices.
🔹 Complexity Analysis
Outer loop: O(N)

Middle loop: O(N)

Inner loop: O(N)

Summing subarrays: O(K)

⏳ Total Complexity = O(N³K) (Too slow for large inputs)
     */
private static class BruitForce {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int maxSum = 0;
        int[] result = new int[3];

        // Iterate over all possible first subarray
        for (int i = 0; i <= n - 3 * k; i++) {
            int sum1 = 0;
            for (int x = i; x < i + k; x++) sum1 += nums[x];

            // Iterate over all possible second subarray
            for (int j = i + k; j <= n - 2 * k; j++) {
                int sum2 = 0;
                for (int x = j; x < j + k; x++) sum2 += nums[x];

                // Iterate over all possible third subarray
                for (int m = j + k; m <= n - k; m++) {
                    int sum3 = 0;
                    for (int x = m; x < m + k; x++) sum3 += nums[x];

                    int total = sum1 + sum2 + sum3;
                    if (total > maxSum) {
                        maxSum = total;
                        result = new int[]{i, j, m};
                    }
                }
            }
        }
        return result;
    }
    }
    /*
    📌 Time Complexity Analysis
Let’s break down the complexity:
✅ getMaxSum function:

Each (index, subarrayCount) state is computed once due to memoization.

There are at most O(n * 3) = O(n) unique states since subarrayCount ≤ 3.

✅ getMaxSumPath function:

It follows a single traversal of the array (O(n)), selecting indices based on optimal choices.

✅ Prefix Sum Computation:

Takes O(n) time to preprocess prefix sums.

⏳ Overall Complexity: O(n)
The solution efficiently computes the maximum sum of 3 non-overlapping subarrays in O(n) time using dynamic programming + greedy backtracking. 🚀

Would you like an alternative iterative DP approach for better efficiency?
     */
    private static class Optimal {
        private int[] prefixSum;
        private int[][] memo;

        private int getMaxSum(int[] nums, int index, int subarrayCount, int k) {
            if (subarrayCount == 3) return 0; // We need exactly 3 subarrays
            if (index > nums.length - k) return 0; // Not enough elements left to form a subarray
            if (memo[index][subarrayCount] != -1) return memo[index][subarrayCount];

            // Option 1: Skip this index and move forward
            int skipCurrent = getMaxSum(nums, index + 1, subarrayCount, k);

            // Option 2: Take subarray starting at 'index'
            int takeCurrent = getMaxSum(nums, index + k, subarrayCount + 1, k)
                    + prefixSum[index + k] - prefixSum[index];

            // Store and return the maximum of both choices
            return memo[index][subarrayCount] = Math.max(skipCurrent, takeCurrent);
        }

        private void getMaxSumPath(int[] nums, int index, int subarrayCount, int k, List<Integer> result) {
            if (subarrayCount == 3 || index > nums.length - k) return;

            // Compute both choices
            int skipCurrent = getMaxSum(nums, index + 1, subarrayCount, k);
            int takeCurrent = getMaxSum(nums, index + k, subarrayCount + 1, k)
                    + prefixSum[index + k] - prefixSum[index];

            // Pick the best choice and record index if we take the current subarray
            if (takeCurrent >= skipCurrent) {
                result.add(index);
                getMaxSumPath(nums, index + k, subarrayCount + 1, k, result);
            } else {
                getMaxSumPath(nums, index + 1, subarrayCount, k, result);
            }
        }

        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int n = nums.length;
            memo = new int[n][3];
            for (int[] row : memo) Arrays.fill(row, -1);

            // Compute Prefix Sum
            prefixSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }

            // Compute max sum
            getMaxSum(nums, 0, 0, k);

            // Find indices of the 3 subarrays that form the max sum
            List<Integer> result = new ArrayList<>();
            getMaxSumPath(nums, 0, 0, k, result);

            // Convert List<Integer> to int[] before returning
            return result.stream().mapToInt(i -> i).toArray();
        }
    }
}

package com.interview.array;

/*
https://leetcode.com/problems/global-and-local-inversions/description/
 * You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
Category: Medium, Tricky
https://www.youtube.com/watch?v=lplpy8TiZFw
Related:
https://leetcode.com/problems/range-sum-query-immutable/ Easy
https://leetcode.com/problems/super-ugly-number/ Medium
https://leetcode.com/problems/stone-game/ Medium
The number of global inversions is the number of the different pairs (i, j) where:

0 <= i < j < n
nums[i] > nums[j]
The number of local inversions is the number of indices i where:

0 <= i < n - 1
nums[i] > nums[i + 1]
Return true if the number of global inversions is equal to the number of local inversions.

 

Example 1:

Input: nums = [1,0,2]
Output: true
Explanation: There is 1 global inversion and 1 local inversion.
Example 2:

Input: nums = [1,2,0]
Output: false
Explanation: There are 2 global inversions and 1 local inversion.
 

Constraints:

n == nums.length
1 <= n <= 105
0 <= nums[i] < n
All the integers of nums are unique.
nums is a permutation of all the numbers in the range [0, n - 1].
 */
public class GlobalandLocalInversions {

    /*
    Brute Force Approach (O(n²))
    Idea
    Count global inversions by checking all pairs (i, j) where i < j.
    Count local inversions by checking adjacent pairs (i, i+1).
    Compare both counts.
    Time Complexity:
    Counting local inversions → O(n)
    Counting global inversions → O(n²)
    Overall Complexity: O(n²)
         */
    private static class BruitForce {
        public boolean isIdealPermutation(int[] nums) {
            int n = nums.length;
            int globalCount = 0, localCount = 0;

            // Count local inversions (adjacent pairs)
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    localCount++;
                }
            }

            // Count global inversions (all i, j pairs where i < j)
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (nums[i] > nums[j]) {
                        globalCount++;
                    }
                }
            }

            return globalCount == localCount;
        }
    }

    /*
    Better Approach (O(n log n) using Merge Sort)
    Idea
    Use Merge Sort to count global inversions efficiently in O(n log n).
    Count local inversions separately in O(n).
    Compare both counts.
    Time Complexity:
Merge Sort: O(n log n)
Count local inversions: O(n)
Overall Complexity: O(n log n)
     */
    private static class Better {
        public boolean isIdealPermutation(int[] nums) {
            int localCount = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    localCount++;
                }
            }

            int globalCount = countGlobalInversions(nums.clone(), 0, nums.length - 1);
            return localCount == globalCount;
        }

        private int countGlobalInversions(int[] nums, int left, int right) {
            if (left >= right) return 0;
            int mid = (left + right) / 2;
            int count = countGlobalInversions(nums, left, mid) + countGlobalInversions(nums, mid + 1, right);
            count += mergeAndCount(nums, left, mid, right);
            return count;
        }

        private int mergeAndCount(int[] nums, int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int i = left, j = mid + 1, k = 0, count = 0;

            while (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) {
                    temp[k++] = nums[i++];
                } else {
                    temp[k++] = nums[j++];
                    count += (mid - i + 1);
                }
            }

            while (i <= mid) temp[k++] = nums[i++];
            while (j <= right) temp[k++] = nums[j++];

            System.arraycopy(temp, 0, nums, left, temp.length);
            return count;
        }
    }

    /*
     * Optimal Approach (O(n))
     *
     * Idea:
     * A global inversion includes both local inversions and distant inversions (i, j where j > i+1).
     * If there is any number out of place by more than one index, then global inversions will be greater than local inversions.
     * Check if nums[i] > nums[i+2] exists for any i, as this would create an extra global inversion.
     *
     * Time Complexity:
     * - Single pass through array → O(n)
     * - Overall Complexity: O(n)
     *
     * Final Comparison:
     * | Approach      | Time Complexity | Space Complexity | Notes                              |
     * |--------------|----------------|------------------|------------------------------------|
     * | Brute Force  | O(n²)          | O(1)             | Slow for large inputs             |
     * | Merge Sort   | O(n log n)     | O(n)             | Faster, but unnecessary extra space |
     * | Optimal      | O(n)           | O(1)             | Best solution 🚀                   |
     *
     * Dry Run and Explanation of the Optimal Approach (O(n)):
     * We will dry run the optimal solution on a sample input and explain the role of:
     * maxSeen = Math.max(maxSeen, nums[i]);
     *
     * Dry Run Example 1:
     * ------------------
     * Input: nums = [1, 0, 2]
     *
     * | Iteration | i | nums[i] | maxSeen (before) | maxSeen (after) | nums[i+2] | Condition (maxSeen > nums[i+2]?) |
     * |-----------|---|---------|------------------|----------------|-----------|----------------------------------|
     * | 1         | 0 | 1       | -1               | 1              | 2         | ❌ 1 > 2 (False)                 |
     *
     * ✅ Since we completed the loop without violating the condition, return true.
     *
     * Explanation:
     * - maxSeen tracks the largest value seen before checking nums[i+2].
     * - If maxSeen is ever greater than nums[i+2], it means a non-local global inversion exists.
     *
     * Dry Run Example 2:
     * ------------------
     * Input: nums = [1, 2, 0]
     *
     * | Iteration | i | nums[i] | maxSeen (before) | maxSeen (after) | nums[i+2] | Condition (maxSeen > nums[i+2]?) |
     * |-----------|---|---------|------------------|----------------|-----------|----------------------------------|
     * | 1         | 0 | 1       | -1               | 1              | 0         | ✅ 1 > 0 (True)                   |
     *
     * 🚨 Since maxSeen > nums[i+2], return false.
     *
     * Explanation:
     * - Here, maxSeen = 1, and we check nums[2] (which is 0).
     * - Since 1 > 0, it means nums[i] has a non-adjacent swap (1 was supposed to be closer to 0).
     * - This confirms an extra global inversion, so we return false.
     *
     * Why maxSeen = Math.max(maxSeen, nums[i])?
     * ----------------------------------------
     * - We need to track the maximum value encountered so far in the array before reaching nums[i+2].
     * - The reason is:
     *   - If nums[i] is large and appears before a smaller nums[i+2], then this counts as an extra global inversion.
     *   - If all global inversions were local, then nums[i+2] would never be smaller than maxSeen.
     *
     * Key Intuition:
     * - If all inversions are local, then at every step, nums[i] should not be much larger than nums[i+2].
     * - maxSeen helps us remember the largest value we've seen to detect an out-of-place value.
     *
     * Final Complexity Analysis:
     * - We only loop through nums once → O(n).
     * - We use only one extra variable → O(1) space.
     *
     * Final Summary:
     * | Condition Checked        | Meaning                                               |
     * |-------------------------|------------------------------------------------------|
     * | maxSeen > nums[i+2]     | A global inversion exists that is not a local inversion. |
     * | If never true           | All global inversions are local → return true.       |
     * | If true at any step     | Extra global inversion found → return false.        |
     *
     * Let me know if anything needs more clarification! 🚀
     */
    private static class Optimal {
        public boolean isIdealPermutation(int[] nums) {
            int maxSeen = -1;
            for (int i = 0; i < nums.length - 2; i++) {
                maxSeen = Math.max(maxSeen, nums[i]);
                if (maxSeen > nums[i + 2]) {
                    return false;
                }
            }
            return true;
        }
    }
    
}

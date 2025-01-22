package com.interview.hash;

import java.util.*;
/*
 * Problem: https://leetcode.com/problems/longest-consecutive-sequence/
 * Video Explanation: https://www.youtube.com/watch?v=qgizvmgeyUM&t=40s
 * Category: Medium, Tricky, Top 150
 * Related Problem: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/ (Medium)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Problem Statement:
 * Given an unsorted array of integers `nums`, return the length of the longest consecutive element sequence.
 * The algorithm should run in O(n) time complexity.
 *
 * Examples:
 *
 * Example 1:
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4], with length 4.
 *
 * Example 2:
 * Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
 * Output: 9
 * Explanation: The longest consecutive elements sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8], with length 9.
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 */


public class LongestConsecutiveSequence {
    /*
     * Approach 1: Brute Force
     *
     * The brute-force approach checks for the length of the consecutive sequence
     * starting from each element in the array.
     *
     * Steps:
     * 1. For each element `num` in `nums`, check if `num - 1` exists in the array.
     * 2. If `num - 1` does not exist, this indicates that `num` could be the start of a sequence.
     * 3. For each such starting number, keep counting consecutive numbers (`num + 1`, `num + 2`, ...)
     *    until no further consecutive numbers are found.
     * 4. Track the maximum length found.
     *
     * Complexity:
     * - Time Complexity: O(n^3)
     *   Each lookup in the array takes O(n) time, and this is repeated for every element.
     * - Space Complexity: O(1)
     *   This is if we exclude the input space.
     */
    public static class Bruiteforce {
        public int longestConsecutive(int[] nums) {
            int maxLength = 0;
            for (int num : nums) {
                int currentNum = num;
                int currentStreak = 1;

                while (contains(nums, currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                maxLength = Math.max(maxLength, currentStreak);
            }
            return maxLength;
        }

        // Helper method to check if an element exists in the array
        private boolean contains(int[] nums, int target) {
            for (int num : nums) {
                if (num == target) return true;
            }
            return false;
        }

    }

    /*
     * Approach 2: Sorting
     *
     * Sorting the array allows consecutive elements to be grouped together, which simplifies counting sequences.
     *
     * Steps:
     * 1. Sort the array.
     * 2. Iterate through the sorted array, counting consecutive elements.
     * 3. If there’s a break in the sequence (e.g., a gap between numbers), reset the count and update the maximum length.
     *
     * Complexity:
     * - Time Complexity: O(n log n)
     *   This is due to the sorting step.
     * - Space Complexity: O(1) if sorting is done in place, or O(n) if additional space is used for storing sorted data.
     */
    public static class BetterApproach {

        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) return 0;

            Arrays.sort(nums);

            int maxLength = 1;
            int currentStreak = 1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    if (nums[i] == nums[i - 1] + 1) {
                        currentStreak += 1;
                    } else {
                        maxLength = Math.max(maxLength, currentStreak);
                        currentStreak = 1;
                    }
                }
            }
            return Math.max(maxLength, currentStreak);
        }

    }

    /*
     * Approach 3: Optimal Solution Using Hash Set
     *
     * This approach utilizes a hash set for O(1) average-time complexity for element lookups,
     * allowing us to efficiently find the longest consecutive sequence without sorting.
     *
     * Steps:
     * 1. Insert all elements from `nums` into a hash set to enable O(1) lookups.
     * 2. Iterate through each number in `nums`. For each number `num`, check if `num - 1` exists in the set:
     *    - If `num - 1` is not in the set, `num` is the start of a new sequence.
     * 3. Starting from `num`, keep incrementing to count the length of the sequence (`num + 1`, `num + 2`, ...), until reaching a number not in the set.
     * 4. Track the maximum sequence length throughout the process.
     *
     * Complexity:
     * - Time Complexity: O(n) — Each element is inserted and looked up once in the hash set.
     * - Space Complexity: O(n) — For storing elements in the hash set.
     *
     * Summary:
     * | Approach             | Time Complexity | Space Complexity | Comments                        |
     * |----------------------|-----------------|------------------|---------------------------------|
     * | Brute Force          | O(n^3)          | O(1)            | Very slow for large inputs      |
     * | Sorting              | O(n log n)      | O(1) or O(n)    | Simpler but not optimal         |
     * | Hash Set Optimal     | O(n)            | O(n)            | Most efficient with hash lookups|
     *
     * The optimal hash set approach is ideal for its linear time complexity and simplicity, ensuring each element is only checked once, leading to efficient and concise code.
     */
    public static class OptimizedApproach {
        public int longestConsecutive(int[] nums) {
            HashSet<Integer> numSet = new HashSet<>();
            for (int num : nums) {
                numSet.add(num);
            }

            int maxLength = 0;

            for (int num : nums) {
                // Check if num is the start of a sequence
                if (!numSet.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;

                    // Count consecutive numbers
                    while (numSet.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    maxLength = Math.max(maxLength, currentStreak);
                }
            }
            return maxLength;
        }

    }

}

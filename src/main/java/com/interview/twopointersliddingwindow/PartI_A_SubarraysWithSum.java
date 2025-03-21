package com.interview.twopointersliddingwindow;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/binary-subarrays-with-sum/submissions/1533300182/
 * Category: Medium
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 *
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] is either 0 or 1.
 * 0 <= goal <= nums.length
 *
 * Related questions
 * 2302. Count Subarrays With Score Less Than K  Hard
 * 2750. Ways to Split Array Into Good Subarrays Medium
 * 3129. Find All Possible Stable Binary Arrays I Medium
 * Find All Possible Stable Binary Arrays II
 * Hard
 */
public class PartI_A_SubarraysWithSum {
        /*
    1. Brute Force Approach
        Approach:
        Iterate over all possible subarrays using two nested loops.
        For each subarray, calculate the sum.
        If the sum of the subarray equals the goal, increment the count.
        Time Complexity:
        O(n²), where n is the length of the array. This is because we are generating all subarrays and calculating their sum.
        Space Complexity:
        O(1), only using constant extra space for storing count and sum.
     */

    public int numSubarraysWithSumBruitforce(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal) {
                    count++;
                } else if(sum > goal) {
                    break;
                }
            }
        }
        return count;
    }

/*
    3. Better Approach (Prefix Sum with HashMap)
    Approach:
    Use the prefix sum concept with a hashmap to store the frequency of prefix sums.
    Traverse through the array, and for each index i, calculate the prefix sum up to index i.
    The problem boils down to checking how many times prefixSum - goal has been encountered so far because if prefixSum[i] - prefixSum[j] == goal, then the sum of the subarray nums[j+1:i] is goal.
    Use a hashmap to efficiently store and retrieve the prefix sum frequencies.
    Time Complexity:
    O(n), where n is the length of the array. The array is traversed once, and hashmap operations (insert and lookup) are done in constant time.
    Space Complexity:
    O(n), for storing the prefix sums in the hashmap.
 */

    public int numSubarraysWithSumBetter(int[] nums, int goal) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int num : nums) {
            sum += num;
            if (prefixSumMap.containsKey(sum - goal)) {
                count += prefixSumMap.get(sum - goal);
            }
            if (sum == goal) {
                count += 1;
            }
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    /*
        4. Time Complexity:
    O(n): Both the left and right pointers traverse the array once, making the time complexity linear.
    5. Space Complexity:
    O(1): Only constant extra space is used for pointers and variables.

    Note: There is one approach which talk about to generate all possible susequence and in criteria is met then increase count but time complexity is 2 power of n
     */

    // Main function to count subarrays with sum exactly equal to the goal.
    public int numSubarraysWithSum(int[] nums, int goal) {
        // We calculate the difference between subarrays with at most `goal` sum
        // and subarrays with at most `goal - 1` sum. This gives us the subarrays
        // that have exactly `goal` sum.
        return countSubarraysWithAtMostSum(nums, goal) - countSubarraysWithAtMostSum(nums, goal - 1);
    }

    // Helper function to count subarrays with sum at most equal to the given goal.
    private int countSubarraysWithAtMostSum(int[] nums, int goal) {
        // If the goal is negative, no subarray can have a valid sum, so return 0.
        if (goal < 0) return 0;

        int left = 0;  // Left pointer of the sliding window
        int subarrayCount = 0;  // Stores the total count of subarrays
        int currentSum = 0;  // Sum of the current window (subarray)
        int right = 0;
        int len = nums.length;

        // Loop through the array with the right pointer expanding the window.
        while (right < len) {
            currentSum += nums[right];  // Add the right element to the current window sum.

            // If the current sum exceeds the goal, shrink the window from the left.
            while (currentSum > goal) {
                currentSum -= nums[left];  // Remove the left element from the window.
                left++;  // Move the left pointer to the right.
            }

            // All subarrays between `left` and `right` are valid because they have
            // sum <= goal. The number of such subarrays is (right - left + 1).
            subarrayCount += (right - left + 1);
            right++;
        }

        // Return the total count of subarrays with sum at most equal to the goal.
        return subarrayCount;
    }
}

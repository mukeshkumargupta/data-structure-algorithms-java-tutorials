package com.interview.sliddingwindow.slidingWindowCountPattern;


public class C_CountnumberofNicesubarrays {


    /*
    https://www.youtube.com/watch?v=j_QOv9OT9Og&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=10
    Category: Medium
    https://leetcode.com/problems/count-number-of-nice-subarrays/
    Related:
    https://leetcode.com/problems/k-divisible-elements-subarrays/description/ Medium
    https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/ Hard
    https://leetcode.com/problems/ways-to-split-array-into-good-subarrays/description/ Medium

    Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.



Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16


Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
 */

    /*
        4. Time Complexity:
    O(n): Both the left and right pointers traverse the array once, making the time complexity linear.
    5. Space Complexity:
    O(1): Only constant extra space is used for pointers and variables.

     */
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
        int right = 0;
        int len = nums.length;
        int subarrayCount = 0;  // Stores the total count of subarrays
        int currentSum = 0;  // Sum of the current window (subarray)

        // Loop through the array with the right pointer expanding the window.
        while (right < len) {
            currentSum += nums[right] %2;  // Add the right element to the current window sum.

            // If the current sum exceeds the goal, shrink the window from the left.
            while (currentSum > goal) {
                currentSum -= nums[left] %2;  // Remove the left element from the window.
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
    public int numberOfSubarrays(int[] nums, int k) {
        return numSubarraysWithSum(nums, k);
    }


}

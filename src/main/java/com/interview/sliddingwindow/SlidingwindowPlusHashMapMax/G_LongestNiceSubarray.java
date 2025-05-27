package com.interview.sliddingwindow.SlidingwindowPlusHashMapMax;

/*
https://leetcode.com/problems/longest-nice-subarray/description/
Category: Medium, Tricky, VVImp
Related:
https://leetcode.com/problems/longest-substring-without-repeating-characters/ Medium
https://leetcode.com/problems/bitwise-and-of-numbers-range/ Medium
https://leetcode.com/problems/bitwise-ors-of-subarrays/ Medium
https://leetcode.com/problems/fruit-into-baskets/ Medium
https://leetcode.com/problems/max-consecutive-ones-iii/ Medium
https://leetcode.com/problems/get-equal-substrings-within-budget/ Medium
https://leetcode.com/problems/frequency-of-the-most-frequent-element/ Medium
https://leetcode.com/problems/longest-substring-of-all-vowels-in-order/ Medium
https://leetcode.com/problems/maximize-the-confusion-of-an-exam/ Medium
https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/ Medium

You are given an array nums consisting of positive integers.

We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.

Return the length of the longest nice subarray.

A subarray is a contiguous part of an array.

Note that subarrays of length 1 are always considered nice.



Example 1:

Input: nums = [1,3,8,48,10]
Output: 3
Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
- 3 AND 8 = 0.
- 3 AND 48 = 0.
- 8 AND 48 = 0.
It can be proven that no longer nice subarray can be obtained, so we return 3.
Example 2:

Input: nums = [3,1,5,11,13]
Output: 1
Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
 */
public class G_LongestNiceSubarray {
    /*
    Tc: O(N) ideal  2N
    Sc: O(1)
    Note: XOR and sum is same of numbers if no common set bit otherwise xor will be smaller
     than sum so add not equal check. In other words and of all those numbers will be 0
     */
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;

        // 2-Pointer: Variable size sliding window
        int maxWindowSize = 0;
        int xorSum = 0;
        int currSum = 0;

        while (right < n) {
            currSum += nums[right];
            xorSum ^= nums[right];

            while (xorSum != currSum) {
                currSum -= nums[left];//u need to subtract here
                xorSum ^= nums[left];//if u do xor with left then it will be disappread
                left++;
            }

            maxWindowSize = Math.max(maxWindowSize, right - left + 1);
            right++;
        }

        return maxWindowSize;
    }
}

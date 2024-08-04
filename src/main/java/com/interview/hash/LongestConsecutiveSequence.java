package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * https://www.youtube.com/watch?v=qgizvmgeyUM&t=40s
 * Category: Medium, Tricky, Top150
 * Time complexity O(n)
 * Space complexity O(n)
 * Related: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/ Medium
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

 

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 * 
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) { //Runtime: 13 ms, faster than 90.00% of Java online submissions for Longest Consecutive Sequence.
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longesttLCMax = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentLCMax = 1;

                while (num_set.contains(++currentNum)) {
                    currentLCMax++;
                }

                longesttLCMax = Math.max(longesttLCMax, currentLCMax);
            }
        }

        return longesttLCMax;
    }
}

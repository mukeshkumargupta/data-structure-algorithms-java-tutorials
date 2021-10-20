package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * Category: Medium, Tricky
 * Time complexity O(n)
 * Space complexity O(n)
 * Related: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/ Medium
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

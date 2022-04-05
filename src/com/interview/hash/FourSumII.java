package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/4sum-ii/
 * Category: Medium, Top150, Tricky
 * Related: https://leetcode.com/problems/walls-and-gates/ Medium
 * https://leetcode.com/problems/dice-roll-simulation/ Hard
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/ Medium
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
    Derived question: Here in place of 0, it could be 1, 2 3 sum as well etc, 
0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 

Example 1:

Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
Example 2:

Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1
 

Constraints:

n == nums1.length
n == nums2.length
n == nums3.length
n == nums4.length
1 <= n <= 200
-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
         * O(N2)
         * Runtime: 99 ms, faster than 95.45% of Java online submissions for 4Sum II.
Memory Usage: 39.4 MB, less than 50.26% of Java online submissions for 4Sum II.
         */
        int count = 0;
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int c: nums3) {
            for (int d : nums4) {
                lookup.put(c+d, lookup.getOrDefault(c+d, 0) + 1);
            } 
        }

        for (int a : nums1) {
            for (int b: nums2) {
                count += lookup.getOrDefault(-(a + b  ), 0);
            }
        }
        return count;
        
    }
    
    public int fourSumCountSlow(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
         * O(N3)
         */
        int count = 0;
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int d : nums4) {
            lookup.put(d, lookup.getOrDefault(d, 0) + 1);
        }
        for (int a : nums1) {
            for (int b: nums2) {
                for (int c : nums3) {
                    count += lookup.getOrDefault(-(a + b + c ), 0);
                }
            }
        }
        return count;
        
    }
    public int fourSumCountVerySlow(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
         * TC: O(N4)
         * 
         */
        int count = 0;
        for (int a : nums1) {
            for (int b: nums2) {
                for (int c : nums3) {
                    for (int d : nums4) {
                        if (a + b + c + d == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

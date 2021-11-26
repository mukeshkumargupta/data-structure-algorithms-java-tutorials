package com.interview.sort;

import java.util.*;
/*
 * https://leetcode.com/problems/wiggle-sort-ii/
 * https://www.youtube.com/watch?v=mwsjU6CHOb4
 * Category: Medium, Tricky, Top150
 * Related: https://leetcode.com/problems/wiggle-sort/ Medium
 * https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/ Medium
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

You may assume the input array always has a valid answer.

 

Example 1:

Input: nums = [1,5,1,1,6,4]
Output: [1,6,1,5,1,4]
Explanation: [1,4,1,5,1,6] is also accepted.
Example 2:

Input: nums = [1,3,2,2,3,1]
Output: [2,3,1,3,1,2]
 

Constraints:

1 <= nums.length <= 5 * 104
0 <= nums[i] <= 5000
It is guaranteed that there will be an answer for the given input nums.
 

Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSorII {
    public void wiggleSort(int[] nums) {
        /*
         * Runtime: 2 ms, faster than 98.66% of Java online submissions for Wiggle Sort II.
Memory Usage: 41.8 MB, less than 70.63% of Java online submissions for Wiggle Sort II.
         * TC: NLogN
         * SC: O(N)
         */
        Arrays.sort(nums);
        
        int l = nums.length;
        int i = 1; int j = l-1;
        int[] result = new int[l];
        while (i < l ) {
            result[i] = nums[j];
            i +=2;
            j--;
        }
        i = 0;
        while (i < l) {
            result[i] = nums[j];
            i +=2;
            j--;
        }
        
        for (i = 0; i < l ; i++) {
            nums[i] = result[i];
        }
            
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

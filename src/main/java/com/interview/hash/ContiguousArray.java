package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/contiguous-array/
 * https://www.youtube.com/watch?v=9ZyLjjk536U&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=59
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/ Medium
 * Derived Question: Minimum and max length both asked, try with sliding window technics (Not tried yet for this question)
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

 

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        /*
         * Runtime: 41 ms, faster than 23.96% of Java online submissions for Contiguous Array.
Memory Usage: 100 MB, less than 6.31% of Java online submissions for Contiguous Array.
Not better solution: TODO
         */
        Map<Integer, Integer> lookup = new HashMap<>();
        
        int l = nums.length;
        int sum = 0;
        int maxCount = 0;
        for (int i = 0; i < l ; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sum == 0) {
                if (maxCount  < i+1) {
                    maxCount = i+1;
                } 
            } else if (lookup.containsKey(sum)) {
                if ( (i - lookup.get(sum)) > maxCount ) {
                    maxCount =i - lookup.get(sum);
                }
            } else {
                lookup.put(sum, i);
            }
        }
        return maxCount;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

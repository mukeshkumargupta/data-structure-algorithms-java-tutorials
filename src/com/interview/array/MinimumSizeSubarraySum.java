package com.interview.array;

/*
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * Category: Medium, Tricky
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
 

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int l = nums.length;
        int start =0;
        int minW = 0;
        int currentSum = 0;
        int i = 0;
        while(currentSum < target && i < l) {
            currentSum += nums[i++];
        }
        if (currentSum >= target) {
            minW = i-start;
        }
        //Now minimize window
        for (int end = i ; end < l; end++) {
            currentSum += nums[end];
            currentSum -= nums[start++];
            //Minimise window
            while (currentSum -  nums[start] >= target) {
                currentSum = currentSum -  nums[start++];
                int w = end - start + 1; 
                if (w < minW) {
                   minW = w; 
                }
            }
            
        }
        if (i >= l)  {//If element exhasted in first attempt then minimise from previous
            //Minimise window
            while (currentSum -  nums[start] >= target) {
                currentSum = currentSum -  nums[start++];
                int w = i - start; 
                if (w < minW) {
                   minW = w; 
                }
            }
        }

        return minW;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

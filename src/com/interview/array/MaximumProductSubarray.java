package com.interview.array;

/*
 * 
 * https://leetcode.com/problems/maximum-product-subarray/
 * Category: Medium, Must Do
 * Related:
 * https://leetcode.com/problems/maximum-product-of-three-numbers/ Easy
 * https://leetcode.com/problems/subarray-product-less-than-k/ Medium
 * 152. Maximum Product Subarray
Medium

8361

257

Add to List

Share
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

It is guaranteed that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        /*
         * Runtime: 4 ms, faster than 15.08% of Java online submissions for Maximum Product Subarray.
Memory Usage: 40.1 MB, less than 12.25% of Java online submissions for Maximum Product Subarray.
         */
        int maxSoFarSum = nums[0];
        int l = nums.length;
        int maxTillSum = nums[0];
        int minTillSum = nums[0];
        for (int i = 1; i < l; i++) {
            int temp = maxTillSum;
            maxTillSum = Math.max(Math.max(nums[i], nums[i]*maxTillSum), nums[i]*minTillSum);//nums[i] is 0 case
            minTillSum = Math.min(Math.min(nums[i], nums[i]*temp), nums[i]*minTillSum);//nums[i] is 0 case
            if (maxTillSum > maxSoFarSum) {
                maxSoFarSum = maxTillSum;
            }
        }
        return maxSoFarSum;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

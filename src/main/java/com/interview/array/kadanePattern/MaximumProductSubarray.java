package com.interview.array.kadanePattern;

/*
 * 
 * https://leetcode.com/problems/maximum-product-subarray/
 * Category: Medium, Must Do, Kadane variant
 * https://leetcode.com/problems/maximum-product-subarray/discuss/1608862/JAVA-or-3-Solutions-or-Detailed-Explanation-Using-Image
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
    /*
    Try all subarrays, compute their product, and track the maximum.
    ⏱ Complexity:
    Time: O(n^2)

    Space: O(1)
     */
    private static class BruitForce {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < nums.length; i++) {
                int prod = 1;
                for (int j = i; j < nums.length; j++) {
                    prod *= nums[j];
                    max = Math.max(max, prod);
                }
            }

            return max;
        }
    }

    private static class Optimal {
        /*
         Kadane variant
         * Approach 2: Just the slight modification of previous approach. As we know that on multiplying with negative number max will become min and min will become max, so why not as soon as we encounter negative element, we swap the max and min already.
         */
        public int maxProduct(int[] nums) {//Little optimization, some comparision is reduced
        /*
         * Time complexity is O(n)
 * Space complexity is O(1)
         * Runtime: 1 ms, faster than 91.34% of Java online submissions for Maximum Product Subarray.
Memory Usage: 41.5 MB, less than 5.60% of Java online submissions for Maximum Product Subarray.
         */
            int maxSoFarProduct = nums[0];
            int l = nums.length;
            int maxTillProduct = nums[0];
            int minTillProduct = nums[0];
            for (int i = 1; i < l; i++) {
                //Just the slight modification of previous approach. As we know that on multiplying with negative number max will become min and min will become max, so why not as soon as we encounter negative element, we swap the max and min already.
                if (nums[i] < 0) {//Swap
                    int temp = maxTillProduct;
                    maxTillProduct = minTillProduct;
                    minTillProduct = temp;
                }
                maxTillProduct = Math.max(nums[i]*maxTillProduct, nums[i]);
                minTillProduct =Math.min(nums[i]*minTillProduct, nums[i]);
                maxSoFarProduct = Math.max(maxSoFarProduct, maxTillProduct);
            }
            return maxSoFarProduct;

        }
        public int maxProductM2(int[] nums) {//Lititle optimization, some comparision is reduced
        /*
         * Runtime: 4 ms, faster than 15.08% of Java online submissions for Maximum Product Subarray.
Memory Usage: 40.1 MB, less than 12.25% of Java online submissions for Maximum Product Subarray.
         */
            int maxSoFarProduct = nums[0];
            int l = nums.length;
            int maxTillProduct = nums[0];
            int minTillProduct = nums[0];
            for (int i = 1; i < l; i++) {
                int temp = maxTillProduct;
                maxTillProduct = Math.max(Math.max(nums[i]*maxTillProduct, nums[i]*minTillProduct), nums[i]); //Math.max(Math.max(nums[i], nums[i]*maxTillProduct), nums[i]*minTillProduct);//nums[i] is 0 case
                minTillProduct = Math.min(Math.min(nums[i]*minTillProduct, nums[i]*temp), nums[i]);//nums[i] is 0 case
                if (maxTillProduct > maxSoFarProduct) {
                    maxSoFarProduct = maxTillProduct;
                }
            }
            return maxSoFarProduct;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }


}

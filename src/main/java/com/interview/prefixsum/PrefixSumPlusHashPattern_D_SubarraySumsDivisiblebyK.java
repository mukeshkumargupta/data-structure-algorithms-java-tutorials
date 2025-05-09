package com.interview.prefixsum;

/*
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * https://www.youtube.com/watch?v=u9m-hnlcydk&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=60
 * 
 * Category: Medium, Tricky, prefix sum + hash map
 * Related: https://leetcode.com/problems/make-sum-divisible-by-p/
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Derived: Try another approach like cummulative sum logic same approach commulative sum equal k

 

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
Example 2:

Input: nums = [5], k = 9
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
 */
public class PrefixSumPlusHashPattern_D_SubarraySumsDivisiblebyK {
    public int subarraysDivByK(int[] nums, int k) {//Note: in video sum is taken  but take rem then take comulative sum
        /*
         * Runtime: 7 ms, faster than 79.17% of Java online submissions for Subarray Sums Divisible by K.
Memory Usage: 42 MB, less than 90.84% of Java online submissions for Subarray Sums Divisible by K.
         */
        int l = nums.length;
        int[] count = new int[k];
        int sum = 0;
        for (int elm : nums) {
            /*Remember:         
            System.out.println(-4%5);//-4
            System.out.println(-8%5);//-3 */
            sum += (elm%k + k)%k;//to avoid overflow otherwise test case will fail
            count[sum%k]++;
        }
        
        int totalCount = count[0];//for zero case special case even each single
        for (int c : count) {
            totalCount += (c*(c-1))/2;
        }
        return totalCount;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PrefixSumPlusHashPattern_D_SubarraySumsDivisiblebyK ssdk = new PrefixSumPlusHashPattern_D_SubarraySumsDivisiblebyK();
        int[] input = {4,5,0,-2,-3,1};
        ssdk.subarraysDivByK(input, 5);
    }
    
}

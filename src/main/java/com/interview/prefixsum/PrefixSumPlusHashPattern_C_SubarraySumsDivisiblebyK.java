package com.interview.prefixsum;

import java.util.HashMap;
import java.util.Map;

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
public class PrefixSumPlusHashPattern_C_SubarraySumsDivisiblebyK {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> modMap = new HashMap<>();


        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            int remainder = prefixSum % k;

            // Handle negative mod values
            if (remainder < 0) {
                remainder += k;
            }

            if (modMap.containsKey(remainder)) {
                count += modMap.get(remainder);
            }
            if (remainder == 0) {// empty prefix sum is divisible by k
                count += 1;
            }

            modMap.put(remainder, modMap.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PrefixSumPlusHashPattern_C_SubarraySumsDivisiblebyK ssdk = new PrefixSumPlusHashPattern_C_SubarraySumsDivisiblebyK();
        int[] input = {4,5,0,-2,-3,1};
        ssdk.subarraysDivByK(input, 5);
    }
    
}

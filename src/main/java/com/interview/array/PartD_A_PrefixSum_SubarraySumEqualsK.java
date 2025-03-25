package com.interview.array;
import java.util.*;
/*
 * Reference: https://leetcode.com/problems/subarray-sum-equals-k/
 * Category: Medium, Tricky, VVImp, SubarraySumEqualsK, Must Do
 * Related:
 * https://leetcode.com/problems/path-sum-iii/
 * https://www.youtube.com/watch?v=frf7qxiN2qU&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB&index=4
 * Video: https://www.youtube.com/watch?v=HbbYPQc-Oo4
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

    A subarray is a contiguous non-empty sequence of elements within an array.



    Example 1:

    Input: nums = [1,1,1], k = 2
    Output: 2
    Example 2:

    Input: nums = [1,2,3], k = 3
    Output: 2


    Constraints:

    1 <= nums.length <= 2 * 104
    -1000 <= nums[i] <= 1000
    -107 <= k <= 107
 */
public class PartD_A_PrefixSum_SubarraySumEqualsK {

   private static class Bruitforce {
        public int subarraySumBruteForce(int[] nums, int k) {
            int count = 0;
            int n = nums.length;

            for (int start = 0; start < n; start++) {
                int sum = 0;
                for (int end = start; end < n; end++) {
                    sum += nums[end]; // Compute sum of subarray
                    if (sum == k) count++;
                }
            }

            return count;
        }
    }

    private static class optimalSolution {
        public int subarraySum(int[] nums, int k) {

            int count = 0, s = 0;
            HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

            for(int i = 0; i < nums.length; i++) {
                //Commulative sum
                s += nums[i];
                if(map.containsKey(s-k))
                    count += map.get(s-k);
                if(s==k)
                    count++;

                map.put(s, map.getOrDefault(s, 0) + 1);
            }
            return count;

        }
    }

    
}

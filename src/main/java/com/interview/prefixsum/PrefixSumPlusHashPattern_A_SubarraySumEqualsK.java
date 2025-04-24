package com.interview.prefixsum;
import java.util.*;
/*
 * Reference: https://leetcode.com/problems/subarray-sum-equals-k/
 * Category: Medium, Tricky, Must Do, VVImp, SubarraySumEqualsK, Must Do, prefix sum + hash map
 * Related:
 * https://leetcode.com/problems/path-sum-iii/ same pattern
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
public class PrefixSumPlusHashPattern_A_SubarraySumEqualsK {

    /*
    ⏱️ Time Complexity:
    O(n²) — Two nested loops.

    📦 Space Complexity:
    O(1) — No extra space used apart from variables.


     */
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

    /*
    ⏱️ Time Complexity:
    O(n) — Single pass through the array.

    📦 Space Complexity:
    O(n) — In the worst case, all prefix sums are different and stored in the HashMap.
     */
    private static class optimalSolution {
        public int subarraySum(int[] nums, int k) {

            int count = 0, prefixSum = 0;
            HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();

            for(int i = 0; i < nums.length; i++) {
                //Commulative sum
                prefixSum += nums[i];
                if(map.containsKey(prefixSum-k))
                    count += map.get(prefixSum-k);
                if(prefixSum==k)
                    count++;

                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            }
            return count;

        }
    }

    
}

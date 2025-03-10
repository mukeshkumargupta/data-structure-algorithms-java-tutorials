package com.interview.hash;
import java.util.*;
/*
 * Reference: https://leetcode.com/problems/subarray-sum-equals-k/
 * Category: Medium, Tricky, VVImp, SubarraySumEqualsK, Must Do
 * Related:
 * https://leetcode.com/problems/path-sum-iii/
 * Video: https://www.youtube.com/watch?v=HbbYPQc-Oo4
 */
public class SubarraySumEqualsK {

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

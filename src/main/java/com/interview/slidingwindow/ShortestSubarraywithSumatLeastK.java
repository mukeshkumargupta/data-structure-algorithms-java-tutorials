package com.interview.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/*
 * ----------------------------------------------------------------------------------
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 * https://www.youtube.com/watch?v=K0NgGYEAkA4
 * Category: Hard, Tricky
 *
 * 💡 Concept: Prefix Sum + Monotonic Queue (Deque)
 * Derived Idea: Find all subarrays whose sum is at least K and return the shortest.
 *
 * 🔍 Problem Statement:
 * Given an integer array `nums` and an integer `k`, return the length of the shortest
 * non-empty subarray of `nums` with a sum of at least `k`. If there is no such subarray,
 * return -1.
 *
 * Note: A subarray is a contiguous part of an array.
 *
 * ✅ Related Problems:
 * - https://leetcode.com/problems/find-k-pairs-with-smallest-sums/       [Medium, VVImp]
 * - https://leetcode.com/problems/remove-comments/                       [Medium, Bad]
 * - https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/ [Medium, VImp]
 *
 * ----------------------------------------------------------------------------------
 *
 * 🔸 Examples:
 *
 * Example 1:
 * Input: nums = [1], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: nums = [1, 2], k = 4
 * Output: -1
 *
 * Example 3:
 * Input: nums = [2, -1, 2], k = 3
 * Output: 3
 *
 * ----------------------------------------------------------------------------------
 *
 * 🔒 Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^5 <= nums[i] <= 10^5
 * - 1 <= k <= 10^9
 *
 */
public class ShortestSubarraywithSumatLeastK {
    public int shortestSubarray(int[] nums, int k) {
        /*
         * Runtime: 80 ms, faster than 24.35% of Java online submissions for Shortest Subarray with Sum at Least K.
Memory Usage: 121.4 MB, less than 72.98% of Java online submissions for Shortest Subarray with Sum at Least K.
            //Since element is traverse max two so tc in
             * TC: O(N)
             * SC: O(N)
         */
        class Pair {
            int index;
            long sum;
            Pair (int index, long sum) {
                this.index = index;
                this.sum = sum;
            }
        }
        int l = nums.length;
        Deque<Pair> dq = new LinkedList<>();//index,sum
        long sum = 0;
        int shortest = Integer.MAX_VALUE;
        
        for(int i=0;i<l;++i){
            sum += (long)(nums[i]);
            if(sum>=k)  shortest = Math.min(shortest,i+1);//Sum from start to i-th index
            
            //Reduce window size to find minimum window with sum>=k
            Pair current = new Pair(Integer.MIN_VALUE, Integer.MIN_VALUE);
            while(dq.size() > 0 && (sum-dq.getFirst().sum >= k)){
                current = dq.removeFirst();
                
            }
            //Calculate new shortest (if possible)
            if(current.sum != Integer.MIN_VALUE)
                shortest = Math.min(shortest,(i-current.index));
            
            //Maintain monotonically non-decreasing order of deque
            while(dq.size() > 0 && sum<=dq.getLast().sum)
                dq.removeLast();//or removeLast
            
            dq.addLast(new Pair(i,sum));//Push i-th sum
        }
        return shortest==Integer.MAX_VALUE?-1:shortest;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

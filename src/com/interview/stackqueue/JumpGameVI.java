package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/jump-game-vi/
 * https://www.youtube.com/watch?v=LiEcBMK5PQs
 * Category: Medium, Fundamental, Must Do
 * Related: https://leetcode.com/problems/jump-game-vii/
 * You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0
 

Constraints:

1 <= nums.length, k <= 105
-104 <= nums[i] <= 104
 */
public class JumpGameVI {
    public int maxResultSlow(int[] nums, int k) {
        /*
         * TC: NlogK
         * SC: LogK
         * Explanation mentioned here: https://www.youtube.com/watch?v=LzyahBCAYfY
         * Runtime: 124 ms, faster than 11.49% of Java online submissions for Jump Game VI.
Memory Usage: 125.4 MB, less than 9.96% of Java online submissions for Jump Game VI.
         */
        int max = nums[0];
        int l = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> {
           return b[1] -a[1];
        });
        
        pq.add(new int[]{0, nums[0]});
        
        for (int i = 1; i < l; i++) {
            while(!pq.isEmpty() && i - pq.peek()[0] > k ) {
                pq.remove();
            }
            max = nums[i] + pq.peek()[1];
            pq.add(new int[] {i, max});
            
        }
        return max;

        
    }
    
    public int maxResult(int[] nums, int k) {
        /*
         * Runtime: 49 ms, faster than 53.94% of Java online submissions for Jump Game VI.
Memory Usage: 107 MB, less than 44.64% of Java online submissions for Jump Game VI.
Not understood
            TC: O(N)
            SC : O(K)
         */
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(0);
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[dq.peek()];
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.offer(i);
            if (i - dq.peek() >= k) {
                dq.removeFirst();
            }
        }
        return nums[nums.length-1];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

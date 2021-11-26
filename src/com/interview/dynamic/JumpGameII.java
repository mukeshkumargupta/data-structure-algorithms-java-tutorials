package com.interview.dynamic;

/*
 * https://leetcode.com/problems/jump-game-ii/
 * https://www.youtube.com/watch?v=BRnRPLNGWIo&t=338s
 * Category: Medium, Tricky, Must Do
 * 
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
 * 
 */
public class JumpGameII {
    public int jump(int[] nums) {//37 ms, faster than 37.16% of Java online submissions for Jump Game II.
        int l = nums.length;
        int[] T = new int[l];
        T[l-1] = 0;
        for (int i = l-2; i >= 0; i--) {
            T[i] = Integer.MAX_VALUE;
        }
        for (int i = l-2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i+1; j <= Math.min(l-1, (i + nums[i])); j++) {
                min = Math.min(min, T[j]);
            }
            if (min != Integer.MAX_VALUE) {
                T[i] = min +1;
            }
        }
        return T[0];
        
    }
}

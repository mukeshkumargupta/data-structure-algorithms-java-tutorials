package com.interview.dynamic;

/*
 * https://leetcode.com/problems/jump-game-ii/
 * Category: Medium, Tricky, Must Do
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

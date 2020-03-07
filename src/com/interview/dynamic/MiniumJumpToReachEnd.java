package com.interview.dynamic;
//Reference: https://leetcode.com/problems/jump-game-ii/
//Reference: https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps/0
//Category: Must Do
public class MiniumJumpToReachEnd {
    public int jump(int[] nums) {

        int length = nums.length;
        int output[] = new int[length];
        output[0] = 0;
        for (int i = 1; i < length; i++) {
            output[i] = Integer.MAX_VALUE;
        }
        //Now minimize jump
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[j] + j >= i) {
                    //Update min jump
                    if (output[j] + 1 >  output[i]) {
                        output[i] = output[j] + 1;
                    }
                }
            }
        }
        return output[length-1];
        
    }

}

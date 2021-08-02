package com.interview.array;

/*
 * https://leetcode.com/problems/move-zeroes/
 * Category: Easy
 */
public class MoveZeroes {
    private void moveZero(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            nums[i+1] = nums[i];
        }    
    }
    private int findNonZeroFirstIndex(int[] nums, int start, int l) {
        for (int i = start; i < l; i++) {
            if (nums[i] != 0) {
               return i; 
            }
        }
        return -1;
        
    }
    public void moveZeroes(int[] nums) {
        int l = nums.length;
        for (int i = 0; i < l ; i++) {
            if (nums[i] == 0) {
                int index = findNonZeroFirstIndex(nums, i+1, l);
                if (index != -1) {
                    int temp = nums[index];
                    moveZero(nums, i, index);
                    nums[i] = temp;
                }
                
            }
            
        }
        
    }
}

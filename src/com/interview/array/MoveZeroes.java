package com.interview.array;

/*
 * https://leetcode.com/problems/move-zeroes/
 * Category: Easy
 * Related:
 * https://leetcode.com/problems/make-array-strictly-increasing/ Hard
 * https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/ Medium
 * https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/ Hard
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
        /*
         * Runtime: 101 ms, faster than 5.11% of Java online submissions for Move Zeroes.
Memory Usage: 40.2 MB, less than 53.94% of Java online submissions for Move Zeroes.
         */
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

package com.interview.array;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * 
 * https://www.youtube.com/watch?v=L1u-R_s2Mok&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=39
 * Category: Hard, Google, Tricky, 
 * Related: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/ Easy
 * https://leetcode.com/problems/couples-holding-hands/ Hard
 */
public class FirstPositiveMissing {
 public int firstMissingPositive(int[] nums) {
        
     /*
      * Runtime: 7 ms, faster than 30.28% of Java online submissions for First Missing Positive.
Memory Usage: 118.2 MB, less than 8.31% of Java online submissions for First Missing Positive.
      */
        int l = nums.length;
        boolean oneFound = false;
        for (int i = 0; i < l; i++) {
            if (nums[i] == 1) {
                oneFound = true;
            }
        }
        if (!oneFound) {
            return 1;
        } else if (nums.length == 1) {
            return 2;
        }
        
        //this below is for if 1 is found
        for (int i = 0; i < l; i++) {
            int val = Math.abs(nums[i]);
            if (nums[i] <= 0 || nums[i] > l) {//[3,5,-1,1, 0]  for this case
                nums[i] = 1;
            }
        }
        for (int i = 0; i < l; i++) {
            int val = Math.abs(nums[i]);
            if (nums[val-1] > 0) {
                nums[val-1] = nums[val-1] * -1;
            }
        }
        
        for (int i = 0; i < l; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return l + 1;
        
    }
}

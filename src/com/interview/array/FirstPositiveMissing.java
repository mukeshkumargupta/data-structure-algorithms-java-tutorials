package com.interview.array;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * 
 * https://www.youtube.com/watch?v=L1u-R_s2Mok&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=39
 * Category: Hard, Google, Tricky, find-duplicate, Top150
 * Related: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/ Easy
 * https://leetcode.com/problems/couples-holding-hands/ Hard
 * 
 * Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.

Soln: Sorting nlogn
hashing TC: O(N) SC: O(N)
Both not accepted

 

Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
 

Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
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
        
        //this below is for if 1 is found and length is not 1
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
        return l + 1;//this case is 1 2 3 4 5 so will return 6
        
    }
}

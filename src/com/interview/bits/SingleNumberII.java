package com.interview.bits;

/*
 * Facebook playlist:
 * https://www.youtube.com/watch?v=ZbTXZ2_YAgI&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=7
 * https://leetcode.com/problems/single-number-ii/
 * https://www.youtube.com/watch?v=ZbTXZ2_YAgI&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=7
 * Category:Medium, Facebook, Tricky
 * 
 * Related: https://leetcode.com/problems/single-number-iii/ Medium
 * 
 * Derived question: each number 5 time or 4 time and one is single , then just take % of N
 * 
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,3,2]
Output: 3
Example 2:

Input: nums = [0,1,0,1,0,1,99]
Output: 99
 

Constraints:

1 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each element in nums appears exactly three times except for one element which appears once.
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        /*
         * Runtime: 7 ms, faster than 15.91% of Java online submissions for Single Number II.
Memory Usage: 38.6 MB, less than 67.74% of Java online submissions for Single Number II.
         */
        
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int num : nums) {
                arr[i] += (num >> i ) & 1;
                arr[i] %= 3;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result |= arr[i] << i;
        }
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

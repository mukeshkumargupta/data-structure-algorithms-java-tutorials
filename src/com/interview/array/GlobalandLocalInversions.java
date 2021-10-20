package com.interview.array;

/*
 * You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
Category: Medium, Tricky
https://www.youtube.com/watch?v=1QlP6cVLrII
Related:
https://leetcode.com/problems/range-sum-query-immutable/ Easy
https://leetcode.com/problems/super-ugly-number/ Medium
https://leetcode.com/problems/stone-game/ Medium
The number of global inversions is the number of the different pairs (i, j) where:

0 <= i < j < n
nums[i] > nums[j]
The number of local inversions is the number of indices i where:

0 <= i < n - 1
nums[i] > nums[i + 1]
Return true if the number of global inversions is equal to the number of local inversions.

 

Example 1:

Input: nums = [1,0,2]
Output: true
Explanation: There is 1 global inversion and 1 local inversion.
Example 2:

Input: nums = [1,2,0]
Output: false
Explanation: There are 2 global inversions and 1 local inversion.
 

Constraints:

n == nums.length
1 <= n <= 105
0 <= nums[i] < n
All the integers of nums are unique.
nums is a permutation of all the numbers in the range [0, n - 1].
 */
public class GlobalandLocalInversions {
    public boolean isIdealPermutation(int[] nums) {
        /*
         * Runtime: 2 ms, faster than 69.36% of Java online submissions for Global and Local Inversions.
Memory Usage: 49.8 MB, less than 81.21% of Java online submissions for Global and Local Inversions.
         */
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) > 1) return false;
            
        }
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

package com.interview.dynamic;

/*
 * Reference: https://leetcode.com/problems/partition-equal-subset-sum/
 * Category: Medium, Must Do
 * Time complexity - O(input.size * total_sum)
 * Space complexity - O(input.size*total_sum)
 *
 * Youtube video - https://youtu.be/s6FhG--P7z0
 * 
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
Related: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/ medium
https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/ Medium
 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int l1 = nums.length;
        
        int sum = 0;
        for (int i = 0; i < l1; i++) {
            sum += nums[i];
        }
        //if odd then not possible
        if (sum % 2 != 0) {
            return false;
        }
        int R = l1+1;
        int C = sum/2+1;
        boolean[][]T = new boolean[R][C];

        T[0][0] = true;
        //Fill first row
        /*for (int j = 1; j < C ; j++) {
            T[0][j] = false;
        }*/ // Not required because it is by default false
        //Fill first column
        for (int i = 1; i < R ; i++) {
            T[i][0] = true;
        }
        
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (nums[i-1] > j) {
                    T[i][j] = T[i-1][j];
                    
                } else {
                    T[i][j] = T[i-1][j] || T[i-1][j - nums[i-1]];
                }
            }
        }
        return T[R-1][C-1];
        
    }
}

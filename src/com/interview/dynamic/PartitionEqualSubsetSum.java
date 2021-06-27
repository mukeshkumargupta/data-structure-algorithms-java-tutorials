package com.interview.dynamic;

/*
 * Reference: https://leetcode.com/problems/partition-equal-subset-sum/
 * Category: Medium, Tricky
 * 
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 

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
        int R = l1;
        int C = sum/2;
        boolean[][]T = new boolean[R+1][C+1];

        //Fill first row
        for (int j = 1; j <= C ; j++) {
            T[0][j] = false;
        }
        //Fill first column
        for (int i = 0; i <= R ; i++) {
            T[i][0] = true;
        }
        
        for (int i = 0; i < l1; i++) {
            for (int j = 1; j <= C; j++) {
                if (nums[i] > j) {
                    T[i+1][j] = T[i][j];
                    
                } else {
                    T[i+1][j] = T[i][j] || T[i][j - nums[i]];
                }
            }
        }
        return T[R][C];
        
    }
}

package com.interview.dynamic;

/*
 * https://leetcode.com/problems/partition-array-for-maximum-sum/
 * Category: Must Do, dp, memoization, recursion
 * Related: https://leetcode.com/problems/subsequence-of-size-k-with-the-largest-even-sum/ Medium, Locked
 * 
 * 1043. Partition Array for Maximum Sum
Medium

2127

200

Add to List

Share
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 

Constraints:

1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
Accepted
47,960
Submissions
68,214
 */
public class PartitionArraforMaximumSum {
    public int maxSumAfterPartitioningUtil(int start, int end , int[] arr, int k) {
        if (start == end+1) {//length
            return 0;
        } 
        int max = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        int sum = 0;
        int len = 0;
        for (int j = start; j < Math.min(start + k, end+1); j++) {
            len++;
            max = Math.max(max, arr[j]);
            sum = len * max + maxSumAfterPartitioningUtil(j+1, end, arr, k);
            maxAns = Math.max(maxAns, sum);
        }
        return maxAns;
    }
    public int maxSumAfterPartitioning(int[] arr, int k) {
        
        return maxSumAfterPartitioningUtil(0, arr.length -1, arr, k);
        
    }
    
    public int maxSumAfterPartitioningUtilMemoization(int start, int end , int[] arr, int k, int[] dp) {
        if (start == end+1) {//length
            return 0;
        } 
        if (dp[start] != 0) {
           return dp[start];
        }
        int max = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        int sum = 0;
        int len = 0;
        for (int j = start; j < Math.min(start + k, end+1); j++) {
            len++;
            max = Math.max(max, arr[j]);
            sum = len * max + maxSumAfterPartitioningUtilMemoization(j+1, end, arr, k, dp);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[start] = maxAns;
    }
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int l = arr.length;
        int[] dp = new int[l];
        return maxSumAfterPartitioningUtilMemoization(0,  l-1, arr, k, dp);
        
    }
    
    public int maxSumAfterPartitioningTabulation(int[] arr, int k) {
        int l = arr.length;
        int[] dp = new int[l+1];
        dp[l] = 0;//not needed because already set to zero
        
        for (int i = l-1; i >=0; i--) {
            int max = Integer.MIN_VALUE;
            int maxAns = Integer.MIN_VALUE;
            int sum = 0;
            int len = 0;
            for (int j = i; j < Math.min(i + k, l); j++) {
                len++;
                max = Math.max(max, arr[j]);
                sum = len * max + dp[j+1];
                dp[i] = maxAns = Math.max(maxAns, sum);
            }
            
        }
        return dp[0];
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

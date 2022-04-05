package com.interview.dynamic;

/**
 * Date 05/02/2017
 * @author Mukesh Kumar Gupta
 * 
 * Youtube link - https://youtu.be/CE2b_-XfVDk
 * Leetcode: https://leetcode.com/problems/longest-increasing-subsequence/
 * Category: Medium, Must Do
 * 
 * Find a subsequence in given array in which the subsequence's elements are 
 * in sorted order, lowest to highest, and in which the subsequence is as long as possible
 * 
 * Solution : 
 * Dynamic Programming is used to solve this question. DP equation is 
 * if(arr[i] > arr[j]) { dp[i] = max(dp[i], dp[j] + 1 }
 * 
 * Time complexity is O(n^2).
 * Space complexity is O(n)
 * 
 * Reference 
 * http://en.wikipedia.org/wiki/Longest_increasing_subsequence
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 * Category: Must Do, Medium
 * If you want to track one more what are the sequences then take one more array and store from where it came
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        for(int i=0; i < nums.length; i++){
            dp[i] = 1;
        }
        int max = dp[0];
        for(int i=1; i < nums.length; i++){
            for(int j=0; j < i; j++){
                if(nums[i] > nums[j]){
                   dp[i] =  Math.max(dp[j] + 1 , dp[i]);
                    if (dp[i] > max) {
                       max = dp[i] ;
                    }
                }
            }
        }
        return max;
    }
}

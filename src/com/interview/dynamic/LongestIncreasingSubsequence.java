package com.interview.dynamic;

/**
 * Date 05/02/2017
 * @author Mukesh Kumar Gupta
 * 
 * Youtube link - https://youtu.be/CE2b_-XfVDk
 * Leetcode: https://leetcode.com/problems/longest-increasing-subsequence/
 * Category: Medium, Tricky
 * 
 * Find a subsequence in given array in which the subsequence's elements are 
 * in sorted order, lowest to highest, and in which the subsequence is as long as possible
 * 
 * Solution : 
 * Dynamic Programming is used to solve this question. DP equation is 
 * if(arr[i] > arr[j]) { T[i] = max(T[i], T[j] + 1 }
 * 
 * Time complexity is O(n^2).
 * Space complexity is O(n)
 * 
 * Reference 
 * http://en.wikipedia.org/wiki/Longest_increasing_subsequence
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 * Category: Must Do, Medium
 * Status: Done
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int T[] = new int[nums.length];
        for(int i=0; i < nums.length; i++){
            T[i] = 1;
        }
        int max = T[0];
        for(int i=1; i < nums.length; i++){
            for(int j=0; j < i; j++){
                if(nums[i] > nums[j]){
                   T[i] =  Math.max(T[j] + 1 , T[i]);
                    if (T[i] > max) {
                       max = T[i] ;
                    }
                }
            }
        }
        return max;
    }
}

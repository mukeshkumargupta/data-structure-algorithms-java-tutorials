package com.interview.dynamic;

/*
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 * Category: Medium, lis, Must Do
 * Related:
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/ 
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/ Easy
 * 
 * 673. Number of Longest Increasing Subsequence
Medium

3450

166

Add to List

Share
Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.

 

Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 106
Accepted
110,733
Submissions
270,849

 */
public class NumberofLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        /*
         * Runtime: 28 ms, faster than 51.88% of Java online submissions for Number of Longest Increasing Subsequence.
Memory Usage: 42 MB, less than 79.42% of Java online submissions for Number of Longest Increasing Subsequence.
         */
        int l = nums.length;
        int[] dp = new int[l];
        int[] count = new int[l];
        //make first all 1 for length 1
        
        for (int i = 0; i < l; i++) {
            dp[i] = 1;
            count[i] = 1;
        }
        int maxLength = 1;
        //now try to maximize dp length
        for (int i = 1; i < l; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        //inherit
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        //same length many getting so add it
                        count[i] += count[j];
                        
                    }
                }
                maxLength = Math.max(maxLength,dp[i]);  
            }
        }
        //System.out.println(maxLength);
        int result = 0;
        for (int i = 0; i < l; i++) {
            //System.out.println(count[i]);
            if (maxLength == dp[i]) {
                result += count[i];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

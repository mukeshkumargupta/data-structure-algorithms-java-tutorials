package com.interview.prefixsum;

/*
 * https://leetcode.com/problems/find-the-middle-index-in-array/
 * Category: Easy, Must Do, prefixsum
 * Related: 
 * https://leetcode.com/problems/find-pivot-index/ Easy
 * https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/ Easy
 * https://leetcode.com/problems/maximum-sum-score-of-array/ Medium
 * 
 * 
 * 
 * 1991. Find the Middle Index in Array
Easy

399

18

Add to List

Share
Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).

A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].

If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.

Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.

 

Example 1:

Input: nums = [2,3,-1,8,4]
Output: 3
Explanation: The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
The sum of the numbers after index 3 is: 4 = 4
Example 2:

Input: nums = [1,-1,4]
Output: 2
Explanation: The sum of the numbers before index 2 is: 1 + -1 = 0
The sum of the numbers after index 2 is: 0
Example 3:

Input: nums = [2,5]
Output: -1
Explanation: There is no valid middleIndex.
 

Constraints:

1 <= nums.length <= 100
-1000 <= nums[i] <= 1000
 

Note: This question is the same as 724: https://leetcode.com/problems/find-pivot-index/

Accepted
26,605
Submissions
40,215
 */
public class FindtheMiddleIndexinArray {
    public int findMiddleIndex(int[] nums) {
        /*
         * Bruitforce solution is take left sum array and right sum array then for each index compare but without left and right array below solution this is.
         * Runtime: 1 ms, faster than 77.29% of Java online submissions for Find the Middle Index in Array.
Memory Usage: 42.3 MB, less than 57.65% of Java online submissions for Find the Middle Index in Array.
         */
        //consider sum of left = 0, i.e. leftmost index is middle
        int leftSum = 0;
        //calculate sum of right
        int rightSum = 0;
        int l = nums.length;
        for(int i=1; i<l; i++){
            rightSum += nums[i];
        }

        if(leftSum == rightSum) {
            return 0;
        }
        
        //check if sum of left == sum of right, otherwise move the middle by 1 and check again
        // we don't need to calculate sum again, just reuse the one already calculated
        //i.e. add prev middle to left sum and subtract new middle from right sum
        for(int i=1; i<l; i++) {
            leftSum += nums[i-1];
            rightSum -= nums[i];
            if(leftSum == rightSum) {
                return i; // return the middle index if found
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

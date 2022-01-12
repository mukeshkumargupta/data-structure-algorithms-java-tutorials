package com.interview.array;

/**
 * Date 03/06/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * Category: Medium, Tricky, Top150, triplet, lis
 * Bruitforce soln is n3 must tell then proceed optimization
 * Derived question make four or five increasing sequence make three, four variable and do, lis logic can be also applied but not optimized
 * Related: https://leetcode.com/problems/count-special-quadruplets/ Easy
 *Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

 

Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 

Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1
 

Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 * Find if there exists an increasing triplet subsequence.
 * Similar method to longest increasing subsequence in nlogn time.
 *
 * 
 *
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Increasing Triplet Subsequence.
Memory Usage: 80.5 MB, less than 78.22% of Java online submissions for Increasing Triplet Subsequence.
Time complexity is O(n)
 * Space complexity is O(1)
         */
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= first) {//111111 case so equal required
                first = nums[i];
            } else if (nums[i] <= second) {
                second = nums[i];
            } else {
                return true;
            }
            
        }
        return false;
        
    }

    public static void main(String args[]) {
        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
        int input[] = {9, 10, -2, 12, 6, 7, -1};
        System.out.print(tripletSubsequence.increasingTriplet(input));
    }
}

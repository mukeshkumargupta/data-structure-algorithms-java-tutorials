package com.interview.twopointer.twopointersumpattern;
import java.util.*;

/*
 * https://leetcode.com/problems/3sum-closest/
 * Category: Medium, Must Do, Top150
 * Related: https://leetcode.com/problems/3sum-smaller/
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
 

Constraints:

3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-104 <= target <= 104
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        /*
         * Runtime: 3 ms, faster than 98.77% of Java online submissions for 3Sum Closest.
Memory Usage: 38.5 MB, less than 81.16% of Java online submissions for 3Sum Closest.
TC: O(N2)
SC: O(1)
         */
        Arrays.sort(nums);
        int l = nums.length;
        int diff = Integer.MAX_VALUE; 
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < l-2; i++) {
            int left = i+1;
            int right = l-1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(sum-target) < diff) {
                    diff = Math.abs(sum-target);
                    result = sum;
                    if (diff ==0) {
                        break;
                    }
                }

            }
            if (diff ==0) {
                break;
            }

        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

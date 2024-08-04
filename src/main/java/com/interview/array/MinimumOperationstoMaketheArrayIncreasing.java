package com.interview.array;

/*
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
 * Category: Easy, Tricky
 * https://www.youtube.com/watch?v=ttznU-BTqTk
 * Related: https://leetcode.com/problems/split-array-largest-sum/ Hard
 * https://leetcode.com/problems/largest-plus-sign/ Medium
 * https://leetcode.com/problems/building-boxes/ Hard
 * 
 * You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and increment it by 1.

For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
Return the minimum number of operations needed to make nums strictly increasing.

An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length 1 is trivially strictly increasing.

 

Example 1:

Input: nums = [1,1,1]
Output: 3
Explanation: You can do the following operations:
1) Increment nums[2], so nums becomes [1,1,2].
2) Increment nums[1], so nums becomes [1,2,2].
3) Increment nums[2], so nums becomes [1,2,3].
Example 2:

Input: nums = [1,5,2,4,1]
Output: 14
Example 3:

Input: nums = [8]
Output: 0
 

Constraints:

1 <= nums.length <= 5000
1 <= nums[i] <= 104
 */
public class MinimumOperationstoMaketheArrayIncreasing {
    public int minOperations(int[] nums) {//Runtime: 6 ms, faster than 19.43% of Java online submissions for Minimum Operations to Make the Array Increasing.
        int l = nums.length;
        int minCount = 0;
        for (int i =1; i < l; i++) {
            if (nums[i] <= nums[i-1]) {
                int change = nums[i-1] - nums[i] +1;
                minCount += change;
                nums[i] += change;
                
            }
            
        }
        return minCount;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

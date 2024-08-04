package com.interview.array;

/*
 * https://leetcode.com/problems/max-consecutive-ones/
 * https://www.youtube.com/watch?v=Mo33MjjMlyA
 * Category: Easy
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 * 
 * Related: https://leetcode.com/problems/max-consecutive-ones-ii/ Medium
 * https://leetcode.com/problems/max-consecutive-ones-iii/ Medium
 * https://leetcode.com/problems/consecutive-characters/ Easy
 * https://leetcode.com/problems/longer-contiguous-segments-of-ones-than-zeros/ Easy

 

Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Max Consecutive Ones.
Memory Usage: 40.3 MB, less than 70.23% of Java online submissions for Max Consecutive Ones.
            TC: O(N)
            SC: O(1)
         */
        int max = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                if (count > max) {
                    max = count;
                }
            } else {
                count = 0;
            }
            
        }
        return max;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

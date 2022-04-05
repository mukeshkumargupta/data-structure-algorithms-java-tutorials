package com.interview.twopointer;

/*
 * https://leetcode.com/problems/sort-array-by-parity/
 * Category: Easy, Tricky
 * Related: 
 * https://leetcode.com/problems/task-scheduler/ Medium
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/ Easy
 * https://leetcode.com/problems/car-fleet-ii/ Hard
 * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.

 

Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
Example 2:

Input: nums = [0]
Output: [0]
 

Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
 */
public class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {//1 ms, faster than 99.08% of Java online
        int l = nums.length;
        int start = 0;
        int end = l-1;
        
        while (start < end) {
            if (nums[start] % 2 ==0) {
                start++;
                continue;
            }
            
            if (nums[end] % 2 !=0) {
                end--;
                continue;
            } 
            
            //swap
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            
        }
        return nums;
        
    }
    
}

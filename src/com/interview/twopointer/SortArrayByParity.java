package com.interview.twopointer;

/*
 * https://leetcode.com/problems/sort-array-by-parity/
 * Category: Easy
 * Related: 
 * https://leetcode.com/problems/task-scheduler/ Medium
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/ Easy
 * https://leetcode.com/problems/car-fleet-ii/ Hard
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

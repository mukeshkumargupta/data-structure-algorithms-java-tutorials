package com.interview.twopointer;

/*
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * Category: Easy, Tricky
 * Related: https://leetcode.com/problems/sort-transformed-array/ Medium, Locked
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 

Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 

Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 */
public class SquaresofaSortedArray {
    public int[] sortedSquares(int[] nums) {//need to write code by yourself
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Squares of a Sorted Array.
Memory Usage: 41.3 MB, less than 46.81% of Java online submissions for Squares of a Sorted Array.
         */
        int[] squaredInts = new int[nums.length];
        
        int start = 0, end = nums.length - 1;
            
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[start] * nums[start] > nums[end] * nums[end]) {
                squaredInts[i] = nums[start]*nums[start];
                
                ++start;
            } else {
                squaredInts[i] = nums[end]*nums[end];
                
                --end;
            }
        }
        
        return squaredInts;
        
        //could be improved by saving the value of nums.length and reusing it
        //doing the same with squaring the values, and using abs values before
        //squaring 
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

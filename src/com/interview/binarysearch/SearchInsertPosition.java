package com.interview.binarysearch;

/**
 * https://leetcode.com/problems/search-insert-position/
 * https://www.youtube.com/watch?v=0A40XJH_VvE&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=8
 * Category: Easy, Must Do, Fundamental
 * Related: https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/ Medium
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/ Medium
 * https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/ Medium
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4
Example 4:

Input: nums = [1,3,5,6], target = 0
Output: 0
Example 5:

Input: nums = [1], target = 0
Output: 0
 */
public class SearchInsertPosition {
    /*
     * this will  work if no is duplicate also
     */
    public int searchInsert(int[] nums, int target) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
Memory Usage: 43.2 MB, less than 26.29% of Java online submissions for Search Insert Position.
         */
        int l = nums.length;
        int start = 0;
        int end = nums.length-1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if (mid-1 >= 0 && nums[mid-1] < target && target <= nums[mid]) {//between case
                return mid;
            } else if (target <= nums[mid]) {//left
                end = mid -1;
            } else {//right
                start = mid +1;
            }
            
        }
        return start;
    }
    /*
     * this will not work if no is duplicate
     */
    private int bst(int[] nums, int target, int start, int end) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
Memory Usage: 44.1 MB, less than 5.18% of Java online submissions for Search Insert Position
         */
        while (start <= end) {
            int mid = (start +end)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid +1;
            } else {
                end = mid -1;
            }
        }
        return start;

        
    }
    public int searchInsert(int[] nums, int target) {
        
        int l = nums.length;
        return bst(nums, target, 0, l-1);
    }
}

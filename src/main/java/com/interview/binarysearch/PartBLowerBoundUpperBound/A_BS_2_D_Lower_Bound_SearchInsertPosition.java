package com.interview.binarysearch.PartBLowerBoundUpperBound;

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
public class A_BS_2_D_Lower_Bound_SearchInsertPosition {
    /*
        Time Complexity: O(logN), where N = size of the given array.
        Reason: We are basically using the Binary Search algorithm.

        Space Complexity: O(1) as we are using no extra space.
     */
    public static int searchInsert(int [] arr, int x) {
        int n = arr.length; // size of the array
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] >= x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }
}

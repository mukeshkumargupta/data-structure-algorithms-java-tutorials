package com.interview.binarysearch;

/*
 * Date: 07/31/2017
 * Author: Mukesh Kumar Gupta
 *
 * Problem:
 * Given a sorted array of integers, find the starting and ending position of a specified target value.
 *
 * Constraints:
 * - Array `nums` is sorted in ascending order.
 * - The solution must have a runtime complexity of O(log n).
 * - If the target is not found, return [-1, -1].
 *
 * Complexity:
 * - Time Complexity: O(log n) due to binary search.
 * - Space Complexity: O(1) as no additional space is used.
 *
 * Source:
 * - https://www.youtube.com/watch?v=OE7wUUpJw6I&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=3
 * - LeetCode problem link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Tags: Medium, Must Do, Top150
 *
 * Examples:
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3, 4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1, -1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1, -1]
 */
public class FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
Memory Usage: 44.5 MB, less than 6.70% of Java online submissions for Find First and Last Position of Element in Sorted Array.
         */
        int first = firstOccurence(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = lastOccurence(nums, target);
        int[] result = new int[2];
        result[0] = first;
        result[1] = last;
        return result;
    }

    private int firstOccurence(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] != target)) { //Imp, Tricky
                return mid;
            } else if ( target <= nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private int lastOccurence(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] != target)) {//Imp, Tricky
                return mid;
            } else if (target >= nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        FindFirstandLastPositionofElementinSortedArray searchForRange = new FindFirstandLastPositionofElementinSortedArray();
        int[] nums = {0, 1, 1, 3, 6, 9, 11};
        int[] r = searchForRange.searchRange(nums, 11);
        System.out.println(r[0] + " " + r[1]);
        r = searchForRange.searchRange(nums, 0);
        System.out.println(r[0] + " " + r[1]);
    }
}

package com.interview.binarysearch;

/**
 * Date 07/31/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Time complexity O(logn)
 * Space complexity O(1)
 * 
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Category: Medium
 */
public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        int first = firstOccurence(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = lastOccurence(nums, target);
        return new int[]{first, last};
    }

    private int firstOccurence(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (nums[mid] == target && (mid == 0 || nums[mid - 1] < target)) { //Imp, Tricky
                return mid;
            } else if (nums[mid] >= target) {
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
            int mid = (start + end)/2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid + 1] > target)) {//Imp, Tricky
                return mid;
            } else if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        SearchForRange searchForRange = new SearchForRange();
        int[] nums = {0, 1, 1, 3, 6, 9, 11};
        int[] r = searchForRange.searchRange(nums, 11);
        System.out.println(r[0] + " " + r[1]);
        r = searchForRange.searchRange(nums, 0);
        System.out.println(r[0] + " " + r[1]);
    }
}

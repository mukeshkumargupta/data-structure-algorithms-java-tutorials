package com.interview.binarysearch;

/*
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
Category: top150,
 */
public class FindMinimuminRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if ((middle == 0 || nums[middle] < nums[middle - 1]) && (middle == nums.length - 1 || nums[middle] < nums[middle + 1])) {
                return nums[middle];
            }
            else if (nums[middle] > nums[high]) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;

    }
}


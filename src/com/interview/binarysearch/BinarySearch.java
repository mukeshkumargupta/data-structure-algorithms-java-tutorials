package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/binary-search/ runtime 8.29%
 * Category: Easy
 * Related: https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/ Medium
 */
public class BinarySearch {
    int bsRecursive(int[] nums, int start, int end, int target) {//8% runtime, need to find why?
        if (start > end) {
            return -1;
        }
        
        int mid = start + (end - start)/2;
        if (nums[mid] == target) {
            return mid;
        } else if (target < nums[mid]) {
            end = end -1;
        } else {
            start = start +1;
        }
        return bsRecursive(nums, start, end, target);

    }
    
    int bsIterative(int[] nums, int start, int end, int target) {//runtime 100%
        while (start <= end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] == target) return mid;
          else if (target < nums[mid]) end = mid - 1;
          else start = mid + 1;
        }
        return -1;

    }
      public int search(int[] nums, int target) {
          return bsIterative(nums, 0, nums.length - 1, target);
          //return bsRecursive(nums, 0, nums.length - 1, target);
      }
    
}

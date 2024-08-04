package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/binary-search/ runtime 8.29%
 * Category: Easy
 * Related: https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/ Medium
 */
public class BinarySearch {
    int bsRecursive(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        
        int mid = start + (end - start)/2;
        if (nums[mid] == target) {
            return mid;
        } else if (target < nums[mid]) {
            return bsRecursive(nums, start, mid-1, target);
        } else {
            return bsRecursive(nums, mid+1, end, target);
        }

    }
    public int search(int[] nums, int target) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Search.
Memory Usage: 43.1 MB, less than 67.14% of Java online submissions for Binary Search.
         */
        return bsRecursive(nums, 0, nums.length-1, target);

 
        
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

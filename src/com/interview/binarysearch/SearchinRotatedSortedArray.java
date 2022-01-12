package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array/ runtime 100%
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ Medium
 * Tag: https://leetcode.com/tag/binary-search/
 * 
 * There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104
 */
public class SearchinRotatedSortedArray {
    //Note: This logic is exactly same as findining min value, resuse exactly same code for minvlaue, here index we need to return: Problem: FindMinimuminRotatedSortedArray
    int findPivot(int[] nums, int start, int end) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
Memory Usage: 38.3 MB, less than 82.19% of Java online submissions for Search in Rotated Sorted Array.
         */

        while (start <= end) {
            int mid = start + (end - start)/2;
            if (mid-1 >= 0 && mid+1 < nums.length && nums[mid] < nums[mid-1]  && nums[mid] < nums[mid+1]) {//middle case
                return mid;
            } else if (mid == nums.length-1 && nums[mid] < nums[mid-1]) {//in last then only previous condition applied, first already check in strictly increasing
                return mid;
            }
            else if (nums[mid] < nums[0]) {
                end = mid -1; 
            } else {
                start = mid + 1;
            }
            
        }
        return -1;
        
    }
    int findPivotM2(int[] nums, int start, int end) {
        int index = -1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (mid+1 < nums.length && nums[mid] > nums[mid+1] ) {
                index = mid + 1;
                break;
            } else if(mid-1 >= 0 && nums[mid-1] > nums[mid]) {
                index = mid;
                break;
            } else if (nums[mid] > nums[start]) {
                start = mid + 1;
                
            } else {
                end = mid - 1;
            }
            
        } 
        return index;
    }
    int findIndex(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = end -1;
            } else {
                start = start +1;
            }
            
        }
        return -1;
        
    }
    public int search(int[] nums, int target) {
        int l = nums.length;
        if (l ==1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        } 
        
        //Check strictly increasing
        if (nums[0] < nums[l-1]) {
            return findIndex(nums, 0, l-1, target);
        }
        
        int pivotIndex = findPivot(nums, 0, l-1);
        if (target < nums[0]) {//Tricky if it is lesser than first half min element then it is in second half
            return findIndex(nums, pivotIndex, l-1, target);//second half
        } else {
            return findIndex(nums, 0, pivotIndex-1, target);
        }
        
    }
}

package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ 100% runtime
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/search-in-rotated-sorted-array/ Medium
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/ Hard
 * 
 */
public class FindMinimuminRotatedSortedArray {
    int findPivot(int[] nums, int start, int end) {
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
    int findMin(int[] nums, int start, int end) {
        int min = nums[0];
        
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (nums[mid] < min) {
                min = nums[mid];
                end = mid -1; 
            } else {
                start = mid + 1;
            }
            
        }
        return min;
        
    }
    public int findMin(int[] nums) {
        int l = nums.length;
        if (l == 1) {
            return nums[0];
        }
        //strictly increasing then search in 0 to n-1
        if (nums[0] < nums[l-1]) {
            return nums[0];
        }
        int pivot = findPivot(nums, 0, l-1);
        //the second part first element
        return nums[pivot];
    }
}

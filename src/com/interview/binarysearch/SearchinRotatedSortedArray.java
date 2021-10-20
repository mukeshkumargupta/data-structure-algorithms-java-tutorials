package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array/ runtime 100%
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ Medium
 * Tag: https://leetcode.com/tag/binary-search/
 */
public class SearchinRotatedSortedArray {
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

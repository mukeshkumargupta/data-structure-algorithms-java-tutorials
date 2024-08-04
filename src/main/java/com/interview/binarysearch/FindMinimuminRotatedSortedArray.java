package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ 100% runtime
 * https://www.youtube.com/watch?v=r3pMQ8-Ad5s without pivot
 * https://www.youtube.com/watch?v=oTfPJKGEHcc with help of pivot
 * Category: Medium, Must Do, Top150
 * Related: https://leetcode.com/problems/search-in-rotated-sorted-array/ Medium
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/ Hard
 * 
 * Derived question: Find maximum in ascending order is rotated  
 * 
 * Suppose an array of length n sorted in ascending order is rotated 

 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

 

Example 1:

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
Example 2:

Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
Example 3:

Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
 

Constraints:

n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
 * 
 */
public class FindMinimuminRotatedSortedArray {
    
    
    int findMin(int[] nums, int start, int end) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Minimum in Rotated Sorted Array.
Memory Usage: 37.9 MB, less than 99.82% of Java online submissions for Find Minimum in Rotated Sorted Array.
         */
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (mid-1 >= 0 && mid+1 < nums.length && nums[mid] < nums[mid-1]  && nums[mid] < nums[mid+1]) {//midle case
                return nums[mid];
            } else if (mid == nums.length-1 && nums[mid] < nums[mid-1]) {//in last then only previous condition applied, first already check in strictly increasing
                return nums[mid];
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
    /*
    Runtime
0
ms
Beats
100.00%
Analyze Complexity
Memory
42.27
MB
Beats
9.24%
     */
    int findMinM3(int[] nums, int start, int end) {//Follow this approach
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

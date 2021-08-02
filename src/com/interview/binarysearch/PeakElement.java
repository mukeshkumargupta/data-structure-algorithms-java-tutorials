package com.interview.binarysearch;

/**
 * @author Mukesh Kumar Gupta
 * Date 01/17/2107
 * A peak element is an element that is greater than its neighbors. Find index of peak element in the array.
 *
 * https://leetcode.com/problems/find-peak-element/
 * Category: Medium, Tricky, VImp
 * Related: https://leetcode.com/problems/find-a-peak-element-ii/ Medium
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/ Easy
 * Reference: https://www.youtube.com/watch?v=OINnBJTRrMU
 * 
 */
public class PeakElement {

    public int findPeakElement(int[] nums) {
        if (nums.length ==1) {
            return 0;
        }
        int start = 0; int end = nums.length -1;
        while (start <= end) {
          //System.out.println(start + ", " + end); 
          int mid = start + (end - start) / 2;
            if (mid ==0 &&  nums[mid] > nums[mid+1]) {
                return mid; 
            } else if (mid == nums.length -1 && nums[mid] > nums[mid-1]) {
                return mid;
            }
          else if (mid-1 >= 0 && mid+1 <nums.length && nums[mid] > nums[mid+1] && nums[mid] > nums[mid-1] ) return mid;
          else if (mid-1 >= 0 && nums[mid-1] > nums[mid]) end = mid - 1; //if mid is less than previous then potential candidate is first half
          else start = mid + 1;
        }
        return -1;
        
    }
}

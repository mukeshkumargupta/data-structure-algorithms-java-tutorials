package com.interview.binarysearch;

/**
 * Created by Mukesh Kumar Gupta on 3/21/16.
 */
public class MinimumInSortedRotatedArray {
    int findPivot(int[] nums, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (mid+1 < nums.length && nums[mid] > nums[mid+1]) {
                return mid;
            } else if (nums[mid] > nums[0]) {
                start = mid +1;
                
            } else {
                end = mid -1;
            }
            
        }
        return -1;
        
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
        int pivot = findPivot(nums, 0, nums.length-1);
        if (pivot == -1) {//strictly increasing then search in 0 to n-1
            return nums[0];
        } else {//the second part first element
            return nums[pivot+1];
        }
        
    }
    
    public int findMinNotMine(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
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

        return nums[low];
    }
    
    public static void main(String[] args) {
        MinimumInSortedRotatedArray min = new MinimumInSortedRotatedArray();
        int[] input = {3,4,5,1,2};
        min.findMin(input);
        
    }
}

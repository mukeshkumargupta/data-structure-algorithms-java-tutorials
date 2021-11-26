package com.interview.sort;

import java.util.Arrays;

import com.interview.array.KthElementInArray;

/**
 * Date 03/23/2017
 * @author Mukesh Kumar Gupta
 *
 * Convert an unsorted array into an array of form num[0] <= num[1] >= nums[2] =< num[3]....
 * 
 * Time complexity O(n) - This depends on KthElementInArray time
 * Space complexity O(1)
 * Category: Medium, Tricky
 * https://www.youtube.com/watch?v=eOlp2q08EDU
 *
 * https://leetcode.com/problems/wiggle-sort/
 * https://leetcode.com/problems/wiggle-sort-ii/
 */
public class WiggleSort {
    //in this version we are looking for nums[0] <= nums[1] >= nums[2] <= nums[3] and so on. Must Do
    /*
     * TC: O(N)
     * SC: O(1)
     */
    public void wiggleSort1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {//even
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }


    private void swap(int arr[],int low,int high){
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    public static void main(String args[]) {
        WiggleSort ws = new WiggleSort();
        int input[] =  {6, 2, 1, 6, 8, 9, 6};
        System.out.print(Arrays.toString(input));
    }
}

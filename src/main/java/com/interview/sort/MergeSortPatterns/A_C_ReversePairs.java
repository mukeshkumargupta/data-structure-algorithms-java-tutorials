package com.interview.sort.MergeSortPatterns;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=S6rsAlj_iB4&list=PLgUwDviBIf0rBT8io74a95xT-hDFZonNs&index=6
 * https://leetcode.com/problems/reverse-pairs/
 * Category: Hard, Must Do, Fundamental
 * video 5.50 count explanation is given
 * Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].

 

Example 1:

Input: nums = [1,3,2,3,1]
Output: 2
Example 2:

Input: nums = [2,4,3,5,1]
Output: 3
 

Constraints:

1 <= nums.length <= 5 * 104
-231 <= nums[i] <= 231 - 1
 */
public class A_C_ReversePairs {
    int merge(int[] nums, int start, int mid, int end) {
        int count = 0;
        /*for (int i = start; i <= mid; i++) {
            for (int j = end; j >= mid+1; j--) {//reverse taking time excedded
                long mul = nums[j]; //Tricky first take in long then multiply otherwise result is saved in int then passed to long
                mul = 2 * mul;
                if (nums[i] > mul) {
                    count += j - (mid+1);
                    count += 1; //for them self
                    //System.out.println(count);
                    break;
                }
            }
            
        }
        //above code time limit
        */ 
        for (int i = start; i <= mid; i++) {
            int j = mid+1;
            while (j <= end && nums[i] > 2 * (long)nums[j] ) {
                j++;
            }
            count += j - (mid+1);
            
        }
        //merge logic
        int p1 = start;
        int p2 = mid +1;
        List<Integer> temp = new ArrayList<>();
        while (p1 <= mid && p2 <= end) {
            if (nums[p1] <= nums[p2]) {
                temp.add(nums[p1]);
                p1++;
            } else {
                temp.add(nums[p2]);
                p2++;
            } 
        }
        while (p1 <= mid) {
            temp.add(nums[p1]);
            p1++;
        }
        while (p2 <= end) {
            temp.add(nums[p2]);
            p2++;
        }
        for (int i = start; i <=end; i++) {
            nums[i] = temp.get(i-start);
        }
        return count;
    }
    int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
           return 0; 
        }
        
        int mid = start + (end - start)/2;
        int count = 0; 
        count += mergeSort(nums, start, mid);
        count += mergeSort(nums, mid+1, end);
        count += merge(nums, start, mid,  end);
        return count;
    }
    public int reversePairs(int[] nums) {
        int l = nums.length;
        return mergeSort(nums, 0, l-1);
        
        
    }
    
}

package com.interview.sort;

import java.util.*;
/**
 * http://en.wikipedia.org/wiki/Merge_sort
 * Test cases
 * 1 element
 * 2 element
 * negative numbers
 * already sorted
 * reverse sorted
 * https://www.youtube.com/watch?v=S6rsAlj_iB4&list=PLgUwDviBIf0rBT8io74a95xT-hDFZonNs&index=7
 * Related: https://leetcode.com/problems/sort-an-array/ Medium
 * https://leetcode.com/problems/sort-list/ Medium
 * https://leetcode.com/problems/merge-sorted-array/ Easy
 * https://leetcode.com/problems/merge-two-sorted-lists/ Easy
 * https://leetcode.com/problems/merge-k-sorted-lists/ Hard, Hint, priority q to get min and p pointer take in array list , do inplace
 * https://www.youtube.com/watch?v=kpCesr9VXDA
 * https://leetcode.com/problems/reverse-pairs/ Hard, Brute force i know but need best solution
 * Merge sort tag:
 * https://leetcode.com/tag/merge-sort/
 * https://leetcode.com/problems/subdomain-visit-count/ Medium
 * https://leetcode.com/problems/check-if-a-string-can-break-another-string/ Medium
 * https://leetcode.com/problems/sentence-similarity-iii/ Medium
 * Category: Must Do
 */
public class MergeSort {
    void merge(int[] nums, int start, int mid, int end) {
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
    }
    void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
           return; 
        }
        
        int mid = start + (end - start)/2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);
        merge(nums, start, mid,  end);
    }
 
     public int[] sortArray(int[] nums) {
         mergeSort(nums,0, nums.length-1);
         return nums;
     }
}

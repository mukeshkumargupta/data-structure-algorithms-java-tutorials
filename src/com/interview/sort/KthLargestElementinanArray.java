package com.interview.sort;

import java.util.*;

/*
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Category: Medium, Quick Select tag
 * https://leetcode.com/tag/quickselect/
 * https://www.youtube.com/watch?v=fnbImb8lo88
 * Related: https://leetcode.com/problems/third-maximum-number/ Easy
 * https://leetcode.com/problems/wiggle-sort-ii/ Medium
 */
public class KthLargestElementinanArray {
    public int findKthLargestUsingMinHeap(int[] nums, int k) {//runtime 71%
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return a -b;
        });
        int l = nums.length;
        int counter = 0;
        for (int i = 0; i < l; i++) {
            if (counter >= k) {
                if (pq.size() > 0 && nums[i] > pq.peek()) {
                    pq.remove();
                    pq.add(nums[i]);
                    
                }
            } else {
                pq.add(nums[i]);
                counter++;
            }
            
        }
        
        return pq.peek();
        
        
    }
    
    private void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    private int partition(int A[], int start, int end) {
        int pivot = A[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (A[i] <= pivot) {
                swap(A, partitionIndex, i);
                partitionIndex++;
            }
        }
        swap(A, partitionIndex, end);
        return partitionIndex;
    }
    
    public int quickSelect(int A[], int start, int end, int k) { 
        int pivot = partition(A, start, end);
        int kthLargest = A.length-pivot-1;
        if (k > kthLargest) {
           return quickSelect(A, start, pivot - 1, k); 
        } else if (k < kthLargest) {
            return quickSelect(A, pivot + 1, end, k); 
        } else {
            return A[pivot];
        }   
    }
    public int findKthLargest(int[] nums, int k) {//Using quick select: 14%
        return quickSelect(nums, 0, nums.length-1, k-1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

package com.interview.sort;

/*
 * Reference: https://www.youtube.com/watch?v=COk73cpQbFQ&t=946s
 * https://leetcode.com/problems/sort-an-array
 * Category: Must Do
 * Related: https://leetcode.com/problems/sparse-matrix-multiplication/ Medium
 * https://leetcode.com/problems/dinner-plate-stacks/ Hard
 * https://leetcode.com/problems/minimum-absolute-sum-difference/ Medium
 * 
 */
public class QuickSort {
    
    private void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    private int partition(int A[], int start, int end) {
        int pivot = A[end];
        int partitionIndex = start;
        for (int i = start; i <= end - 1; i++) {
            if (A[i] <= pivot) {
                swap(A, partitionIndex, i);
                partitionIndex++;
            }
        }
        swap(A, partitionIndex, end);
        return partitionIndex;
        
        
    }
    
    public void sort(int A[], int start, int end) {
        if (start > end) {
            return;
        }
        int pivot = partition(A, start, end);
        sort(A, start, pivot - 1);
        sort(A, pivot + 1, end);
        
    }
    
    public int[] sortArray(int[] nums) {//runtime 6%
        sort(nums, 0, nums.length-1);
        return nums;
    }
    
    private void printArray(int arr[]) {
        for (int a : arr) {
            System.out.println(a);
        }
    }
    
    public static void main(String args[]) {
        QuickSort qs = new QuickSort();
        // int A[] = {11,19,0,-1,5,6,16,-3,6,0,14,18,7,21,18,-6,-8};
        int A[] = { 11, 9, 0, 4, 6, -1, 13 }; // dry run, not working
        // int A[] = {10, 7, 8, 9, 1, 5};
        // int A[] = {-1, 10, 7, 8, 15, 20, 9, 1, 10, 5, -1};//Not working with negative
        // element
        // int A[] = {10, 7, 8, 15, 20, 10, 10, 5};//working
        
        qs.sortArray(A);
        qs.printArray(A);
    }
}

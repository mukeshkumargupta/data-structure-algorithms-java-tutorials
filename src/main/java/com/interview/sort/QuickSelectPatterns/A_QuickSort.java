package com.interview.sort.QuickSelectPatterns;

/*
 * Reference: https://www.youtube.com/watch?v=COk73cpQbFQ&t=946s
 * https://leetcode.com/problems/sort-an-array
 * It is getting time out when element is same using quick sort but in case of merge sort it is working
 * Category: Must Do
 * Related: https://leetcode.com/problems/sparse-matrix-multiplication/ Medium
 * https://leetcode.com/problems/dinner-plate-stacks/ Hard
 * https://leetcode.com/problems/minimum-absolute-sum-difference/ Medium
 *
 * Explanation of the Code:
 * Swap Method: This method swaps two elements in the array at the given indices.
 * Partition Method: This method rearranges the elements in the array around the pivot. It places elements smaller than the pivot to its left and larger elements to its right.
 * QuickSort Method: This is the main method that recursively divides the array into two parts and sorts them.
 * sortArray Method: This is the public method that calls the recursive quickSort function. It also checks for edge cases like empty arrays or arrays with only one element.
 * printArray Method: A helper method to print the sorted array.
 * Time Complexity:
 * Best Case: O(n log n) — occurs when the pivot divides the array into two equal parts. Each recursive step reduces the problem size by half.
 * Average Case: O(n log n) — similar to the best case, this happens when the pivot tends to divide the array evenly.
 * Worst Case: O(n^2) — occurs when the pivot is the smallest or largest element in the array (e.g., when the array is already sorted or nearly sorted). In this case, the partitioning is unbalanced, and the recursion depth becomes n.
 * When to Use QuickSort:
 * QuickSort is generally preferred when you need fast sorting for large datasets, especially when the data is randomly distributed. It is efficient in practice despite its worst-case time complexity of O(n^2), especially when implemented with good pivot selection strategies (like Randomized QuickSort or Median of Three).
 * However, if you expect to frequently encounter sorted or nearly sorted data, other algorithms like MergeSort or HeapSort might be more suitable, as their time complexity is guaranteed to be O(n log n).
 *
 * Further Improvements:
 * Pivot Selection: Randomizing the pivot or using the median-of-three technique can help avoid the worst-case O(n^2) time complexity in some cases, especially if the input is mostly sorted.
 * Tail Recursion: If optimizing for space, QuickSort can be implemented with tail recursion, where the larger partition is handled iteratively to save space on the call stack.
 * This refactored version should be more readable and follows common clean code principles, such as using meaningful variable names and adding comments to explain critical parts of the logic.
 * 
 */
public class A_QuickSort {

    private void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private int partition(int A[], int start, int end) {
        int pivot = A[end];  // Choosing the last element as pivot
        int partitionIndex = start; // The position where pivot will be placed

        for (int i = start; i <= end - 1; i++) { // Iterate through the array
            if (A[i] <= pivot) { // Elements less than or equal to pivot are swapped to the left
                swap(A, partitionIndex, i);
                partitionIndex++; // Move partitionIndex forward
            }
        }

        swap(A, partitionIndex, end); // Place pivot in its correct position
        return partitionIndex; // Return final position of pivot
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
        A_QuickSort qs = new A_QuickSort();
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

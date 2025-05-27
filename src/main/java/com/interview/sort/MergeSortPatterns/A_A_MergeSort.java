package com.interview.sort.MergeSortPatterns;

import java.util.*;
/*
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
 *
 * Detailed Explanation of Merge Sort
 * Algorithm Walkthrough
 * Merge Sort is a divide-and-conquer algorithm, which splits the array into smaller subarrays, sorts each subarray, and then merges the sorted subarrays back together.
 *
 * Steps:
 *
 * Divide: The array is recursively divided into two halves.
 * Conquer: Each subarray is sorted.
 * Combine: The sorted subarrays are merged into a single sorted array.
 * Code Explanation
 * merge function:
 * This is the core of the merge step, which merges two sorted subarrays into one sorted subarray:
 *
 * Parameters:
 * nums: The array to be sorted.
 * start: The starting index of the current subarray.
 * mid: The middle index, which divides the subarrays.
 * end: The ending index of the current subarray.
 * Logic:
 * Two pointers p1 and p2 are used to iterate through the two subarrays (left and right).
 * The smaller element from the two subarrays is added to a temporary list temp.
 * Once one of the subarrays is exhausted, the remaining elements of the other subarray are added to temp.
 * The elements from temp are copied back into the original array nums.
 * mergeSort function:
 * This recursively divides the array into two halves until the base case (start >= end) is reached.
 *
 * The array is divided into two parts using the mid index.
 * The mergeSort function is recursively called on both halves.
 * Once both halves are sorted, the merge function is called to combine them.
 * sortArray function:
 * This is the main entry point for sorting an array using the mergeSort function. It simply calls mergeSort with the full range of the array.
 *
 * Time Complexity Analysis
 * Best, Worst, and Average Case Time Complexity:
 *
 * Best Case: O(n log n)
 * This occurs when the array is already sorted. In this case, the divide step still happens, but the merge step does not require any shifting since the arrays are already sorted. However, we still make recursive calls, so the time complexity remains O(n log n).
 * Worst Case: O(n log n)
 * Even in the worst case (when the array is reverse sorted or in any arbitrary order), the time complexity remains O(n log n). The array is recursively divided, and in each step, we perform a merge which takes linear time.
 * Average Case: O(n log n)
 * The average case still involves dividing the array into halves and merging them back together in O(n log n) time, regardless of the array's initial order.
 * Space Complexity:
 *
 * The space complexity of merge sort is O(n), due to the space required for the temporary list temp that stores the merged results. This is needed for the merging process.
 * Comparison with Quick Sort
 * Quick Sort:
 * Average Case Complexity: O(n log n)
 * Worst Case Complexity: O(n^2) (when the pivot selection is poor)
 * Best Case Complexity: O(n log n)
 * Space Complexity: O(log n) (due to the recursive call stack)
 * Quick Sort vs. Merge Sort:
 * Quick Sort is generally faster in practice for most datasets because it sorts in-place and does not require additional memory for temporary arrays.
 * Merge Sort is better for large datasets and when you require stable sorting (i.e., when equal elements preserve their relative order), or when you need to sort linked lists since it works well with that data structure.
 * Merge Sort guarantees O(n log n) time complexity in all cases, whereas Quick Sort can degrade to O(n^2) in the worst case if the pivot selection is poor.
 * When to Use Quick Sort vs Merge Sort:
 * Use Merge Sort when:
 *
 * Stability is important (i.e., when equal elements should maintain their original order).
 * You are dealing with linked lists since Merge Sort can be more efficient on linked lists.
 * Worst-case time complexity of O(n log n) is a priority.
 * Large datasets are involved, especially if they cannot fit into memory all at once, as Merge Sort can be modified to work externally (external sorting).
 * Use Quick Sort when:
 *
 * You want a faster, in-place sorting algorithm that performs well in practice on average.
 * You are working with small to medium datasets and care about speed over stability.
 * Space complexity is a concern, as Quick Sort uses less space (O(log n) vs. O(n)) compared to Merge Sort.
 * Summary:
 * Merge Sort provides consistent O(n log n) performance in all cases and is stable, but it requires extra space.
 * Quick Sort is typically faster for in-place sorting but can degrade to O(n^2) in the worst case.
 */
public class A_A_MergeSort {

    /**
     * Merges two sorted subarrays into a single sorted array.
     *
     * @param nums the array containing the subarrays to be merged
     * @param start the starting index of the first subarray
     * @param mid the ending index of the first subarray
     * @param end the ending index of the second subarray
     */
    private void merge(int[] nums, int start, int mid, int end) {
        int leftPointer = start;
        int rightPointer = mid + 1;
        List<Integer> mergedList = new ArrayList<>();

        // Merge the two subarrays into mergedList
        while (leftPointer <= mid && rightPointer <= end) {
            if (nums[leftPointer] <= nums[rightPointer]) {
                mergedList.add(nums[leftPointer]);
                leftPointer++;
            } else {
                mergedList.add(nums[rightPointer]);
                rightPointer++;
            }
        }

        // Add remaining elements from the left subarray
        while (leftPointer <= mid) {
            mergedList.add(nums[leftPointer]);
            leftPointer++;
        }

        // Add remaining elements from the right subarray
        while (rightPointer <= end) {
            mergedList.add(nums[rightPointer]);
            rightPointer++;
        }

        // Copy the merged elements back to the original array
        for (int i = start; i <= end; i++) {
            nums[i] = mergedList.get(i - start);
        }
    }

    /**
     * Sorts an array using the Merge Sort algorithm.
     *
     * @param nums the array to be sorted
     * @param start the starting index of the array
     * @param end the ending index of the array
     */
    private void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        // Find the middle point and recursively sort the two halves
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        // Merge the sorted halves
        merge(nums, start, mid, end);
    }

    /**
     * Sorts the given array using Merge Sort and returns the sorted array.
     *
     * @param nums the array to be sorted
     * @return the sorted array
     */
    public int[] sortArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
}

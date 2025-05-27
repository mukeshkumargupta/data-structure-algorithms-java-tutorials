package com.interview.sort.QuickSelectPatterns;

import java.util.*;

/*
 ******************************************************************
 * Problem: Kth Largest Element in an Array
 * Category: Medium, Facebook, FAANG
 *
 * Links:
 * - LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/
 * - Quick Select Tag: https://leetcode.com/tag/quickselect/
 * - YouTube Explanation: https://www.youtube.com/watch?v=fnbImb8lo88
 *
 *
 * Related Problems:
 * - Third Maximum Number (Easy): https://leetcode.com/problems/third-maximum-number/
 * - Wiggle Sort II (Medium): https://leetcode.com/problems/wiggle-sort-ii/
 * - Top K Frequent Elements (Medium): https://leetcode.com/problems/top-k-frequent-elements/
 * - Kth Largest Element in a Stream (Medium): https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * - K Closest Points to Origin (Medium): https://leetcode.com/problems/k-closest-points-to-origin/
 * - Kth Largest Integer in the Array (Easy): https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
 * - Find Subsequence of Length K with Largest Sum (Medium): https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
 * - K Highest Ranked Items Within a Price Range (Medium): https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/
 *
 * Problem Statement:
 * - Given an integer array `nums` and an integer `k`, return the `k`th largest element in the array.
 * - Note: The `k`th largest element is in the sorted order, not necessarily the `k`th distinct element.
 * - Solve it without sorting if possible.
 *
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 * Constraints:
 * - 1 ≤ k ≤ nums.length ≤ 10^5
 * - -10^4 ≤ nums[i] ≤ 10^4
 ******************************************************************/
public class C_B_KthLargestElementinanArray {
    /*
    1. Brute Force Approach (Sorting)
    Idea
    Sort the array in descending order.

    Return the k-th element in the sorted array.

    Time Complexity
    Sorting takes O(N log N).

    Accessing the k-th element takes O(1).

    Overall: O(N log N).
     */
    private static class BruitForce {
        public int findKthLargest(int[] nums, int k) {
            Arrays.sort(nums); // Sort in ascending order
            return nums[nums.length - k]; // k-th largest is at index (n-k)
        }

        public static void main(String[] args) {
            BruitForce obj = new BruitForce();
            int[] nums = {3, 2, 1, 5, 6, 4};
            int k = 2;
            System.out.println(obj.findKthLargest(nums, k)); // Output: 5
        }
    }
    private static class Better {
        /*
        2. Using Min Heap (Optimized)
        Idea
        Use a Min Heap (PriorityQueue) of size k.

        Iterate through nums, maintaining only the k largest elements.

        The top of the min heap will be the k-th largest element.

        Time Complexity
        O(N log K) (because each insertion/deletion in the heap is O(log K), and we process N elements).
         */
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            for (int num : nums) {
                minHeap.add(num);
                if (minHeap.size() > k) { // Remove smallest element to keep only k largest
                    minHeap.poll();
                }
            }
            return minHeap.peek(); // k-th largest element
        }

        public static void main(String[] args) {
            Better obj = new Better();
            int[] nums = {3, 2, 1, 5, 6, 4};
            int k = 2;
            System.out.println(obj.findKthLargest(nums, k)); // Output: 5
        }
    }

    private static class Optimal  {
        /*
         * Same pattern as QuickSort.
         *
         * Why This is Efficient?
         * - Avoids full sorting: Unlike sorting (O(N log N)), we only partition necessary parts.
         * - Uses QuickSelect: Reduces expected time complexity to O(N).
         * - ✅ Best approach for finding the k-th largest element efficiently.
         *
         * Why n - k Instead of k?
         * - We are given an unsorted array and need to find the k-th largest element.
         *
         * How QuickSelect Works:
         * - The partition function places the pivot element in its correct sorted position.
         * - Elements to the left of pivot are smaller, and elements to the right are larger.
         * - The pivot's final position (pivotIndex) determines its rank in sorted order.
         *
         * Understanding n - k:
         * - If we were sorting the array, the k-th largest element would be at index (n - k) in 0-based indexing.
         *
         * Example:
         *   nums = [3, 2, 1, 5, 6, 4], k = 2
         *   Sorted: [1, 2, 3, 4, 5, 6]
         *   2nd largest element is 5, which is at index `6 - 2 = 4`
         *
         * If the Question Asked for k-th Smallest?
         * - The k-th smallest element is at index (k - 1) in 0-based indexing.
         * - Instead of `n - k`, we simply pass `k - 1` to quickSelect.
         */
        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            return quickSelect(nums, 0, n - 1, n - k);
        }

        public int findKthSmallest(int[] nums, int k) {//Tricky IMP Implementation
            return quickSelect(nums, 0, nums.length - 1, k - 1); // k-th smallest -> index (k-1)
        }

        private int quickSelect(int[] nums, int start, int end, int k) {
            while (start <= end) {
                int pivotIndex = partition(nums, start, end);
                if (pivotIndex == k) {
                    return nums[k];
                } else if (pivotIndex < k) {
                    start = pivotIndex + 1;
                } else {
                    end = pivotIndex - 1;
                }
            }
            return -1; // Should not reach here
        }

        private int partition(int A[], int start, int end) {
            int pivot = A[end];  // Choosing last element as pivot
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

        private void swap(int A[], int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        public static void main(String[] args) {
            Optimal obj = new Optimal();
            int[] nums = {3, 2, 1, 5, 6, 4};
            int k = 2;
            System.out.println(obj.findKthLargest(nums, k)); // Output: 5
        }
    }

    private static class OptimalRandonElementRatherLastElementAways {
        private Random rand = new Random();

        public int findKthLargest(int[] nums, int k) {
            int n = nums.length;
            return quickSelect(nums, 0, n - 1, n - k);
        }

        private int quickSelect(int[] nums, int start, int end, int k) {
            if (start == end) return nums[start];

            int pivotIndex = partition(nums, start, end);

            if (pivotIndex == k) return nums[k];
            else if (pivotIndex < k) return quickSelect(nums, pivotIndex + 1, end, k);
            else return quickSelect(nums, start, pivotIndex - 1, k);
        }

        private int partition(int A[], int start, int end) {
            int pivotIndex = start + rand.nextInt(end - start + 1); // Random pivot
            swap(A, pivotIndex, end); // Move pivot to the end
            int pivot = A[end];
            int partitionIndex = start;

            for (int i = start; i < end; i++) { // Iterate through the array
                if (A[i] <= pivot) {
                    swap(A, partitionIndex, i);
                    partitionIndex++;
                }
            }
            swap(A, partitionIndex, end); // Place pivot in its correct position
            return partitionIndex;
        }

        private void swap(int A[], int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }

    
}

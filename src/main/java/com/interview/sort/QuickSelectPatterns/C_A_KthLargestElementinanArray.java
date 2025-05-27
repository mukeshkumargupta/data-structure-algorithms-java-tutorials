package com.interview.sort.QuickSelectPatterns;

public class C_A_KthLargestElementinanArray {
    private static class Optimal {

        /*
         * Same pattern as QuickSort.
         *
         * Why This is Efficient?
         * - Avoids full sorting: Unlike sorting (O(N log N)), we only partition necessary parts.
         * - Uses QuickSelect: Reduces expected time complexity to O(N).
         * - ✅ Best approach for finding the k-th smallest element efficiently.
         *
         * How QuickSelect Works:
         * - The partition function places the pivot element in its correct sorted position.
         * - Elements to the left of pivot are smaller, and elements to the right are larger.
         * - The pivot's final position (pivotIndex) determines its rank in sorted order.
         *
         * Example:
         *   nums = [3, 2, 1, 5, 6, 4], k = 2
         *   Sorted: [1, 2, 3, 4, 5, 6]
         *   2nd smallest element is 2, which is at index `2 - 1 = 1`
         *
         * If the Question Asked for k-th Largest?
         * - The k-th largest element is at index (n - k) in 0-based indexing. that is came from (n-1-(k-1)) ie n-k
         * - Instead of `n - k`, we simply pass `k - 1` to quickSelect.
         */

        /*
            ✅ Time Complexity:
            - **Average Case**:
              - The time complexity for QuickSelect is O(N) on average.
              - Each time, we partition the array around a pivot, reducing the problem size.
              - Since only one of the partitions is further processed, the average case takes linear time.

            - **Worst Case**:
              - The worst-case time complexity is O(N²).
              - This occurs when the pivot selection is poor (e.g., always the smallest or largest element), and the array is divided into very uneven partitions (one side of the partition is always empty).
              - In such cases, the algorithm behaves like BubbleSort or SelectionSort, iterating through the entire array multiple times.

            - **Best Case**:
              - The best case is O(N) when the pivot divides the array into nearly equal halves, allowing QuickSelect to work in linear time.

            📊 **Summary for Time Complexity**:
            - **Best/Average Case**: O(N)
            - **Worst Case**: O(N²)

            ✅ Space Complexity:
            - QuickSelect is an in-place algorithm, meaning it does not use any extra space for arrays or lists.
            - The only extra space used is for recursive calls in the stack, which occurs in the partitioning process. Since we're using an iterative version of QuickSelect (in the `quickSelect` method), there is no significant additional space overhead for recursion.
            - Thus, the space complexity is O(1) for the iterative approach, because we're only using a constant amount of extra space (apart from the input array).

            📦 **Summary for Space Complexity**:
            - **Space Complexity**: O(1)

            ✅ Final Complexity Summary:
            | **Method**       | **Time Complexity**     | **Space Complexity**  |
            |------------------|-------------------------|-----------------------|
            | **QuickSelect**   | O(N) (Average)          | O(1)                  |
            |                  | O(N²) (Worst)           |                       |
        */
        public int findKthSmallest(int[] nums, int k) {
            // Finding the k-th smallest element using QuickSelect
            return quickSelect(nums, 0, nums.length - 1, k - 1); // k-th smallest -> index (k-1)
        }

        private int quickSelect(int[] nums, int start, int end, int k) {
            while (start <= end) {
                int pivotIndex = partition(nums, start, end);
                if (pivotIndex == k) {
                    return nums[k]; // The element at pivotIndex is the k-th smallest
                } else if (pivotIndex < k) {
                    start = pivotIndex + 1; // Move to the right side
                } else {
                    end = pivotIndex - 1; // Move to the left side
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
            System.out.println(obj.findKthSmallest(nums, k)); // Output: 2
        }
    }
}

package com.interview.binarysearch.PartGBinarySearchonInfiniteSearchSpace;

/*
The problem "702. Search in a Sorted Array of Unknown Size" on LeetCode requires us to search for a target value in a
sorted array where the size of the array is unknown. Instead of using an array, we have access to an
ArrayReader API that allows us to get values at a specific index. If the index is out of bounds, ArrayReader.get(index) returns 2^31 - 1.
 */
public class A_SearchinaSortedInfiniteArray {
    class ArrayReader {
        private int[] arr;

        public ArrayReader(int[] arr) {
            this.arr = arr;
        }

        // Simulates the get(index) method from LeetCode
        public int get(int index) {
            if (index < 0 || index >= arr.length) {
                return Integer.MAX_VALUE; // Simulating out-of-bound access
            }
            return arr[index];
        }
    }
    /*
        Approach 1: Brute Force (Linear Search)

        Idea:
        - Since the array is sorted in ascending order, we can start from the beginning and perform a linear search.
        - If we find the target, return its index.
        - If we encounter a value greater than the target, we can stop early since all further elements will also be larger.

        Time Complexity:
        - Worst case: O(N) (if the target is at the end)
        - Best case: O(1) (if the target is found at the start)

        Why does this work?
        - Since the array is sorted, if we find a number greater than the target, we can stop searching immediately.

        Issue:
        - If the target is at a high index, this will take a long time.
    */
    private static class BruitForce {
        public int search(ArrayReader reader, int target) {
            int index = 0;
            while (true) {
                int value = reader.get(index);
                if (value == target) return index;
                if (value > target) return -1; // No need to search further
                index++;
            }
        }
    }

/*
    Approach 2: Binary Search with Exponential Expansion

    Idea:
    Since the array is sorted, binary search is a better approach.
    But we don’t know the size of the array. To handle this:
      1. Find an upper bound where target can exist (using exponential expansion).
      2. Perform binary search within this range.

    Step 1: Find the Range
    - Start with low = 0 and high = 1.
    - Double high until reader.get(high) >= target or out of bounds.

    Step 2: Perform Binary Search
    - Now, we have a valid range [low, high] where target exists.
    - Apply binary search to find target efficiently.

    Time Complexity:
    - Finding the range takes O(log N).
    - Binary search within this range takes O(log N).
    - Total Complexity: O(log N).

    Explanation of Optimized Approach:
    Step 1: Find Upper Bound
    - We double the high index (high *= 2) until we find reader.get(high) >= target.
    - This ensures that target is within [low, high].

    Step 2: Apply Binary Search
    - Now, we perform binary search within [low, high].
    - If reader.get(mid) == target, return mid.
    - If reader.get(mid) > target, search in the left half (high = mid - 1).
    - If reader.get(mid) < target, search in the right half (low = mid + 1).

    Comparison of Approaches:
    | Approach                           | Time Complexity | Space Complexity | Notes |
    |-------------------------------------|----------------|-----------------|-------|
    | Brute Force (Linear Search)        | O(N)           | O(1)            | Slow if target is far |
    | Binary Search with Expansion       | O(log N)       | O(1)            | Much faster |

    Example Walkthrough:
    Example 1:
    Input: reader = [-1,0,3,5,9,12,...], target = 9
    Output: 4

    Step 1: Finding the range
    - Start with low = 0, high = 1.
    - Expand: high = 2, reader.get(2) = 3 (still < 9).
    - Expand: high = 4, reader.get(4) = 9 (stop expanding).

    Step 2: Binary Search in range [low=2, high=4]
    - mid = (2+4)/2 = 3, reader.get(3) = 5 (less than 9).
    - low = 4.
    - mid = (4+4)/2 = 4, reader.get(4) = 9 → Found at index 4.
*/

    private static class Optimize {
        public int search(ArrayReader reader, int target) {
            // Step 1: Find search boundary using exponential expansion
            int low = 0, high = 1;
            while (reader.get(high) < target) {
                low = high;
                high *= 2; // Double the search boundary
            }

            // Step 2: Binary Search within [low, high]
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int value = reader.get(mid);

                if (value == target) return mid;
                else if (value > target) high = mid - 1;
                else low = mid + 1;
            }

            return -1; // Target not found
        }
    }
}

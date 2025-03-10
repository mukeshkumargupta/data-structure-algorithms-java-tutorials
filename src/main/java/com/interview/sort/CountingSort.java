package com.interview.sort;

/**
 * Counting Sort Implementation
 * Category: Sorting, Non-Comparison Sort, O(n + k) time, Must Do
 *
 * Reference: https://www.youtube.com/watch?v=w9njRpeuVew
 * Related:
 * - https://leetcode.com/problems/sort-characters-by-frequency/ Medium
 * - https://leetcode.com/problems/relative-sort-array/ Easy
 *
 * Example Walkthrough
 * Input Array:
 * nums = [4, 2, 2, 8, 3, 3, 1]
 *
 * Step 1: Construct count[]
 * After counting occurrences:
 *
 * Value    1   2   3   4   5   6   7   8
 * count[]  1   2   2   1   0   0   0   1
 * min = 1, max = 8
 * range = max - min + 1 = 8
 *
 * Step 2: Sorting Using count[]
 * We iterate from i = 0 to i = 7:
 *
 * Iteration (i)   count[i]    Number (i + min)    nums[] after placement
 * i = 0          1           1                   [1, _, _, _, _, _, _]
 * i = 1          2           2                   [1, 2, 2, _, _, _, _]
 * i = 2          2           3                   [1, 2, 2, 3, 3, _, _]
 * i = 3          1           4                   [1, 2, 2, 3, 3, 4, _]
 * i = 4          0           skip                [1, 2, 2, 3, 3, 4, _]
 * i = 5          0           skip                [1, 2, 2, 3, 3, 4, _]
 * i = 6          0           skip                [1, 2, 2, 3, 3, 4, _]
 * i = 7          1           8                   [1, 2, 2, 3, 3, 4, 8]
 *
 * Final Output:
 * [1, 2, 2, 3, 3, 4, 8]
 * Sorted in ascending order.
 * Stable sorting preserved relative order of equal elements.
 *
 * Complexity Analysis
 * ✅ Time Complexity: O(n + k)
 *    - Iterating over count[] takes O(k)
 *    - Reconstructing nums[] takes O(n)
 * ✅ Space Complexity: O(k)
 *    - The count[] array requires O(k) extra space.
 */

import java.util.Arrays;

public class CountingSort {

    public static int[] countingSort(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        // Step 1: Find the min and max values to determine range
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int range = max - min + 1; // Size of count array
        int[] count = new int[range];

        // Step 2: Count occurrences of each number
        for (int num : nums) {
            count[num - min]++; // Shift index to start from 0
        }

        // Step 3: Reconstruct the sorted array
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                nums[index++] = i + min;
                count[i]--;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Before Sorting: " + Arrays.toString(nums));
        int[] sortedNums = countingSort(nums);
        System.out.println("After Sorting: " + Arrays.toString(sortedNums));
    }
}

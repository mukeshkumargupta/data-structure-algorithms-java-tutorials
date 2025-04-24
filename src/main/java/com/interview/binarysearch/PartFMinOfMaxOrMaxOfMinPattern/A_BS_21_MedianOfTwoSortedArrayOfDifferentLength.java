package com.interview.binarysearch.PartFMinOfMaxOrMaxOfMinPattern;

/*
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *https://www.youtube.com/watch?v=NTop3VTjmxk
 *Category: Must Do, Hard, Top150
 *Related:
 * https://leetcode.com/problems/median-of-a-row-wise-sorted-matrix/description/ Medium
 * https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/ Easy
 *https://leetcode.com/problems/escape-a-large-maze/ Hard,
 *https://leetcode.com/problems/shift-2d-grid/ Easy
 *https://leetcode.com/problems/word-abbreviation/ Hard
 *https://leetcode.com/problems/maximum-score-words-formed-by-letters/ Hard
 *https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/ Hard
 *https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays Must try VVImp
 * Solution
 * Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
 * Try every cut in binary search way. When you cut first array at i then you cut second array at (m + n + 1)/2 - i
 * Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
 *
 * Time complexity is O(log(min(x,y))
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/4
 *
 * Explanation of cut1 and cut2 ( int cut2 = (l1 + l2 + 1) / 2 - cut1; //Tricky)
 * The expression int cut2 = (n1 + n2 + 1) / 2 - cut1 ensures that the combined left halves of nums1 and nums2 contain the correct number of elements when dividing the two arrays to find the median. Let's break down why this is the case.
 *
 * Key Idea
 * When finding the median of two sorted arrays, we are essentially looking to split the arrays into two halves such that:
 *
 * The left half contains the first half of the combined elements.
 * The right half contains the second half of the combined elements.
 * Total Elements and Partitions
 * Given two arrays:
 *
 * nums1 of length n1
 * nums2 of length n2
 * We want to split these arrays into two parts, left and right, such that:
 *
 * The left part contains the first half of the combined elements.
 * The right part contains the second half of the combined elements.
 * For arrays of lengths n1 and n2:
 *
 * The total number of elements is n1 + n2.
 * The total number of elements in the left part should be (n1 + n2 + 1) / 2 to ensure that the left part has one more element than the right part when the total number of elements is odd. This ensures the median calculation is consistent.
 * Partition Calculation
 * Partitioning nums1:
 *
 * We are performing binary search on nums1.
 * Let's say the partition index for nums1 is cut1.
 * Partitioning nums2:
 *
 * Given cut1 elements in nums1's left part, the remaining elements required in the left part must come from nums2.
 * The total number of elements required in the left part is (n1 + n2 + 1) / 2.
 * Therefore, cut2 should be (n1 + n2 + 1) / 2 - cut1.
 * Why the Formula Works
 * The formula ensures that the sum of elements in the left parts of both arrays is exactly half (or half plus one, for odd total length) of the total number of elements:
 *
 * (n1 + n2 + 1) / 2 gives the required number of elements in the left part.
 * Subtracting cut1 from this gives the number of elements to be taken from nums2 to complete the left part.
 * Example
 * Consider nums1 = [1, 3] and nums2 = [2]:
 *
 * n1 = 2, n2 = 1
 * The total number of elements is 3.
 * The required number of elements in the left part is (2 + 1 + 1) / 2 = 2.
 * Perform binary search on nums1:
 *
 * Initial low = 0, high = 2.
 * Suppose cut1 = 1 (middle index of nums1).
 * Calculate cut2 = (2 + 1 + 1) / 2 - 1 = 1.
 */
public class A_BS_21_MedianOfTwoSortedArrayOfDifferentLength {
    /**
     * Brute Force Approach (Merge and Sort)
     *
     * Idea:
     * 1. Merge both arrays into a single sorted array.
     * 2. Find the median directly from the merged array.
     *
     * Steps:
     * - Use a two-pointer merge (like in merge sort) to combine both sorted arrays into a new sorted array.
     * - Compute the median:
     *   - If the length is odd → return the middle element.
     *   - If the length is even → return the average of the two middle elements.
     *
     * Time Complexity:
     * - Merging takes O(m + n).
     * - Finding the median takes O(1).
     * - Overall Complexity: O(m + n).
     *
     * Code (Brute Force - Merge and Sort)
     */
    private static class bruitforce {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            int[] merged = new int[m + n];
            int i = 0, j = 0, k = 0;

            // Merge two sorted arrays
            while (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    merged[k++] = nums1[i++];
                } else {
                    merged[k++] = nums2[j++];
                }
            }
            while (i < m) merged[k++] = nums1[i++];
            while (j < n) merged[k++] = nums2[j++];

            // Find median
            int len = merged.length;
            if (len % 2 == 1) return merged[len / 2];  // Odd case
            return (merged[len / 2] + merged[len / 2 - 1]) / 2.0;  // Even case
        }
    }

    /**
     * Example Input:
     * -------------------
     * nums1 = [1, 3, 8]
     * nums2 = [7, 9, 10, 11]
     * Total elements: 7 (odd)
     * Required left partition size: (3 + 4 + 1) / 2 = 4
     * (We ensure nums1 is smaller for efficiency)
     *
     * Binary Search Iterations:
     * ---------------------------------------------------------------------------------------
     * | Iteration | cut1 (Partition in nums1) | cut2 (Partition in nums2) | Left Partition (nums1 & nums2) | Right Partition (nums1 & nums2) | Condition Met? | Action Taken               |
     * |-----------|--------------------------|--------------------------|--------------------------------|--------------------------------|----------------|----------------------------|
     * | 1         | 1                        | 3                        | [1] (nums1), [7, 9, 10] (nums2) | [3, 8] (nums1), [11] (nums2)  | ❌ (left2 > right1) | Move right (low = cut1 + 1 = 2) |
     * | 2         | 2                        | 2                        | [1, 3] (nums1), [7, 9] (nums2) | [8] (nums1), [10, 11] (nums2) | ❌ (left2 > right1) | Move right (low = cut1 + 1 = 3) |
     * | 3         | 3                        | 1                        | [1, 3, 8] (nums1), [7] (nums2) | [] (nums1 empty), [9, 10, 11] (nums2) | ✅ (Correct partition) | Median found |
     *
     * Checking Partition Conditions:
     * ---------------------------------------------------------------------------------------------------
     * | Iteration | left1 (nums1[cut1-1]) | left2 (nums2[cut2-1]) | right1 (nums1[cut1]) | right2 (nums2[cut2]) | Partition Condition (left1 ≤ right2 & left2 ≤ right1)? |
     * |-----------|----------------------|----------------------|----------------------|----------------------|---------------------------------------------------------|
     * | 1         | 1                    | 10                   | 3                    | 11                   | ❌ (left2 > right1, wrong partition)                  |
     * | 2         | 3                    | 9                    | 8                    | 10                   | ❌ (left2 > right1, wrong partition)                  |
     * | 3         | 8                    | 7                    | ∞ (out of bounds)    | 9                    | ✅ (Correct partition found)                           |
     *
     * Step 3: Compute Median
     * ------------------------------------
     * Since total elements = 7 (odd) → Median is max(left1, left2)
     * median = max(8, 7) = **8**
     *
     * Final Output:
     * -------------------
     * findMedianSortedArrays([1, 3, 8], [7, 9, 10, 11]) → Output: **8.0**
     */
    private static class Optimized {
        /*
         * Time complexity is O(log(min(m,n))
         * Space complexity is O(1)
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1); // Ensure nums1 is smaller

            int m = nums1.length, n = nums2.length;
            int low = 0, high = m;

            while (low <= high) {
                int cut1 = (low + high) / 2;
                int cut2 = (m + n + 1) / 2 - cut1;  // Ensures left half is larger

                int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
                int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
                int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
                int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

                if (left1 <= right2 && left2 <= right1) {  // Correct partition found
                    if ((m + n) % 2 == 0) { // Even case
                        return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                    } else { // Odd case
                        return Math.max(left1, left2);
                    }
                } else if (left1 > right2) { // Too far right, move left
                    high = cut1 - 1;
                } else { // Too far left, move right
                    low = cut1 + 1;
                }
            }
            return 0.0;
        }

        public static void main(String[] args) {
            int[] x = {1, 3, 8, 9, 15};
            int[] y = {7, 11, 19, 21, 18, 25};

            Optimized mm = new Optimized();
            mm.findMedianSortedArrays(x, y);
        }
    }



}

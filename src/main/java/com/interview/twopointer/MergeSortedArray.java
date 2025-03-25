package com.interview.twopointer;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/merge-sorted-array/
 * Category: Easy, Tricky, Top150, VImp
 * Related:
 * https://leetcode.com/problems/squares-of-a-sorted-array/ Easy
 * https://leetcode.com/problems/interval-list-intersections/ Medium
 *
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

    Merge nums1 and nums2 into a single array sorted in non-decreasing order.

    The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



    Example 1:

    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]
    Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
    The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
    Example 2:

    Input: nums1 = [1], m = 1, nums2 = [], n = 0
    Output: [1]
    Explanation: The arrays we are merging are [1] and [].
    The result of the merge is [1].
    Example 3:

    Input: nums1 = [0], m = 0, nums2 = [1], n = 1
    Output: [1]
    Explanation: The arrays we are merging are [] and [1].
    The result of the merge is [1].
    Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


    Constraints:

    nums1.length == m + n
    nums2.length == n
    0 <= m, n <= 200
    1 <= m + n <= 200
    -109 <= nums1[i], nums2[j] <= 109


    Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeSortedArray {
    /*
    Approach 1: Brute Force (O(N log N) Time, O(1) Space)
    Idea:
    Copy all elements of nums2 into the available space in nums1.
    Sort nums1 using Arrays.sort().
    ✅ Simple to implement
    ❌ Inefficient (Sorting takes O(N log N))
     */
    private static class BruitForce {
        public void mergeBruteForce(int[] nums1, int m, int[] nums2, int n) {
            // Copy nums2 into nums1 starting from index m
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }

            // Sort the merged array
            Arrays.sort(nums1);
        }
    }

    /*
    Approach 2: Two-Pointer Merge (O(M + N) Time, O(N) Space)
    Idea:
    Use an auxiliary array to merge elements from nums1 and nums2.
    Copy the merged result back to nums1.
    ✅ Linear time O(M + N)
    ❌ Uses extra space O(M + N)
     */
    private static class Better {
        public void mergeWithExtraSpace(int[] nums1, int m, int[] nums2, int n) {
            int[] temp = new int[m + n];
            int i = 0, j = 0, k = 0;

            // Merge both arrays into temp
            while (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    temp[k++] = nums1[i++];
                } else {
                    temp[k++] = nums2[j++];
                }
            }

            // Copy remaining elements from nums1
            while (i < m) {
                temp[k++] = nums1[i++];
            }

            // Copy remaining elements from nums2
            while (j < n) {
                temp[k++] = nums2[j++];
            }

            // Copy merged elements back to nums1
            System.arraycopy(temp, 0, nums1, 0, m + n);
        }
    }

/*
    Approach 3: Optimized In-Place Merge (O(M + N) Time, O(1) Space)

    Idea (Start from the End):
    - Since nums1 has space at the end, merge elements from the back.
    - Use three pointers:
      - `p1` at the end of valid elements in nums1 (`m - 1`).
      - `p2` at the end of nums2 (`n - 1`).
      - `p` at the last position of nums1 (`m + n - 1`).

    ✅ Efficient O(M + N) time
    ✅ No extra space O(1)
    ✅ Best for large inputs

    --------------------------------------
    Final Comparison:

    | Approach                         | Time Complexity    | Space Complexity | When to Use?                              |
    |-----------------------------------|--------------------|------------------|-------------------------------------------|
    | Brute Force (Sorting)            | O((M+N) log(M+N))  | O(1)             | Small inputs, quick implementation       |
    | Two-Pointer Merge (Extra Space)  | O(M + N)          | O(M + N)         | If modifying nums1 directly isn't allowed |
    | Optimized In-Place Merge (Best)   | O(M + N)          | O(1)             | Most efficient for large datasets        |

    🔹 **Final Verdict**: Use the in-place merge approach (Approach 3) for best performance!
 */

    private static class Optimized {
        public void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;  // Last valid element in nums1
            int p2 = n - 1;  // Last element in nums2
            int p = m + n - 1; // Last position in nums1

            // Merge from the back
            while (p1 >= 0 && p2 >= 0) {
                if (nums1[p1] > nums2[p2]) {
                    nums1[p--] = nums1[p1--];
                } else {
                    nums1[p--] = nums2[p2--];
                }
            }

            // If nums2 has remaining elements
            while (p2 >= 0) {
                nums1[p--] = nums2[p2--];
            }
        }
    }
}

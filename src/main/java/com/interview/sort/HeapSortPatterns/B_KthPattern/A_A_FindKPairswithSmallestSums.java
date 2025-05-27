package com.interview.sort.HeapSortPatterns.B_KthPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * https://www.youtube.com/watch?v=iY6-u0yt-Is
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * Category: Medium, Must Do, Tricky
 * Related
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/ Medium
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/ Hard
 * https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/ Hard
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

 

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 

Constraints:

1 <= nums1.length, nums2.length <= 105
-109 <= nums1[i], nums2[i] <= 109
nums1 and nums2 both are sorted in ascending order.
1 <= k <= 104
 */
public class A_A_FindKPairswithSmallestSums {
    /*
     * ✅ Approach: Min Heap (Priority Queue)
     *
     * Since both arrays are sorted, we can use a min-heap to always fetch the smallest sum pair.
     *
     * We push pairs (nums1[i], nums2[0]) into the heap for i = 0 to min(k, nums1.length) to begin with.
     *
     * Each time we pop the smallest pair (u, v), we push the next element in nums2 with the same u.
     *
     * This prevents generating all m * n pairs and keeps the algorithm efficient.
     *
     * 📊 Time & Space Complexity:
     *
     * Time Complexity:
     * O(k * log k)
     * We process at most k pairs, and each heap operation is log k.
     *
     * Space Complexity:
     * O(k) for the heap and result list.
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // Edge case
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min heap to store (sum, index in nums1, index in nums2)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add initial k pairs (nums1[i], nums2[0]) to the heap
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        // Extract the smallest pairs
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int i = curr[1], j = curr[2];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            // If there's a next element in nums2 for current nums1[i], add it to the heap
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        A_A_FindKPairswithSmallestSums obj = new A_A_FindKPairswithSmallestSums();
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        List<List<Integer>> result = obj.kSmallestPairs(nums1, nums2, k);
        System.out.println(result); // Output: [[1,2],[1,4],[1,6]]
    }
    
}

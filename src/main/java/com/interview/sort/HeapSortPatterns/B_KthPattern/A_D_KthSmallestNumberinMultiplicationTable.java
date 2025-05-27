package com.interview.sort.HeapSortPatterns.B_KthPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
Category: Hard
Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).

Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.



Example 1:


Input: m = 3, n = 3, k = 5
Output: 3
Explanation: The 5th smallest number is 3.
Example 2:


Input: m = 2, n = 3, k = 6
Output: 6
Explanation: The 6th smallest number is 6.


Constraints:

1 <= m, n <= 3 * 104
1 <= k <= m * n
 */
public class A_D_KthSmallestNumberinMultiplicationTable {

    /*
    🔹 1. Brute Force Approach
    ✅ Idea:
    Generate all elements of the m x n multiplication table.

    Sort all the elements.

    Return the k-th smallest element.

    🧮 Time Complexity:
    Generating the table: O(m * n)

    Sorting the table: O(m * n * log(m * n))

    Overall: O(m * n * log(m * n))

    📦 Space Complexity:
    O(m * n) — for storing all elements of the table in a list.
    ⚠️ Drawback:
    Not feasible when m and n are large (e.g., 30000 × 30000).
     */
    private static class BruitForce {
        public int findKthNumber(int m, int n, int k) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    list.add(i * j);
                }
            }
            Collections.sort(list);
            return list.get(k - 1);
        }
    }

    /*
    🔹 2. Better Approach – Min Heap (Priority Queue)
    ✅ Idea:
    Use a min-heap to simulate the process of finding the k-th smallest element.

    We treat each row of the table as a sorted array.
    For example, row i is: [i*1, i*2, ..., i*n]

    Initially, add the first element of each row (i.e., i * 1).

    Pop the smallest from heap, and push the next element in the same row (i.e., i * (col + 1)).

    🧮 Time Complexity:
    Worst-case: O(k * log m)

    Because we insert up to k elements in the heap and the heap size is up to m.

    📦 Space Complexity:
    O(m) — For heap containing elements of up to m rows.

    ✅ Benefit:
    More efficient than brute force.

    Useful when k is much smaller than m * n.
     */
    private static class BetterUsingMeanHeap {
        public int findKthNumber(int m, int n, int k) {//without binary search it will not pass
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            for (int i = 1; i <= Math.min(m, k); i++) {
                minHeap.offer(new int[]{i, i, 1}); // [value, row, col]
            }

            int count = 0;
            while (!minHeap.isEmpty()) {
                int[] curr = minHeap.poll();
                count++;
                if (count == k) return curr[0];
                if (curr[2] < n) {
                    minHeap.offer(new int[]{curr[1] * (curr[2] + 1), curr[1], curr[2] + 1});
                }
            }

            return -1;
        }
    }

    private static class BetterUsingMaxHeap {

    }

    /*
    🔹 3. Optimal Approach – Binary Search on Value
    ✅ Idea:
    Think of the multiplication table as a virtual sorted matrix.

    Perform binary search on the range of values, i.e., from 1 to m * n.

    For a mid-value mid, count how many numbers in the table are ≤ mid.

    If the count is < k, discard the left half; else keep searching in the left.

    🔍 How to count numbers ≤ mid:
    For row i, all values are i * j for 1 <= j <= n.
    So number of values ≤ mid in row i = min(mid / i, n)
    Sum this over all rows.

    🧮 Time Complexity:
    O(m * log(m * n))
    Because for each binary search iteration (log(m * n)), we spend O(m) time.

    📦 Space Complexity:
    O(1) — No extra space required.
    ✅ Benefits:
    Most efficient and scalable.

    Works well for large m, n, and k.

    ✅ Summary Table

    Approach	Time Complexity	Space Complexity	Suitable For
    Brute Force	O(m × n × log(m × n))	O(m × n)	Small tables only
    Min Heap	O(k × log m)	O(m)	When k is small
    Binary Search	O(m × log(m × n))	O(1)	Large inputs, best overall
     */
    private static class Optimal {//Explore binary search code
        public int findKthNumber(int m, int n, int k) {
            int low = 1, high = m * n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                int count = 0;
                for (int i = 1; i <= m; i++) {
                    count += Math.min(mid / i, n);
                }

                if (count < k) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}

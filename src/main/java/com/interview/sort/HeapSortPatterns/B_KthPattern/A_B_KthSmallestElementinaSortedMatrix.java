package com.interview.sort.HeapSortPatterns.B_KthPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
Category: Medium
https://leetcode.com/problems/find-k-pairs-with-smallest-sums/ Medium
https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/ Hard
https://leetcode.com/problems/find-k-th-smallest-pair-distance/ Hard
https://leetcode.com/problems/k-th-smallest-prime-fraction/ Medium
Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).



Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2


Follow up:

Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */
public class A_B_KthSmallestElementinaSortedMatrix {
    /*
    ✅ Approach 1: Brute Force
🔹 Idea:
Flatten the matrix into a single list.

Sort the list.

Return the k-th element (1-based index).
⏱️ Time Complexity:
O(n² log n) for sorting the flattened list. why

📦 Space Complexity:
O(n²) to store all elements.
     */

    private static class BruitForce {
        public int kthSmallest(int[][] matrix, int k) {
            List<Integer> list = new ArrayList<>();
            for (int[] row : matrix) {
                for (int num : row) {
                    list.add(num);
                }
            }
            Collections.sort(list);
            return list.get(k - 1);
        }
    }

    /*
✅ Approach 2: Min Heap (Priority Queue)
🔹 Idea:
Use a min heap to always fetch the next smallest element.

Push the first element of each row into the heap.

Pop from the heap and push the next element in the same row.
⏱️ Time Complexity:
O(k log n) because we perform k pops from a heap of size at most n. becaue k time i need to pop and total elements are n

📦 Space Complexity:
O(n) for the heap.
     */
    private static class Better {
        // Similar to k merge element pattern, means which one u remove , its next index u will put
        class Tuple {
            int val, r, c;
            public Tuple(int val, int r, int c) {
                this.val = val;
                this.r = r;
                this.c = c;
            }
        }

        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

            for (int i = 0; i < n; i++) {
                pq.offer(new Tuple(matrix[i][0], i, 0));
            }

            // Extract the smallest pairs
            while (k-- > 0 && !pq.isEmpty()) {
                Tuple t = pq.poll();
                if (k == 0) {
                    return t.val;
                }
                if (t.c < n - 1) {
                    pq.offer(new Tuple(matrix[t.r][t.c + 1], t.r, t.c + 1));// suppose same row next colum u need to put in priority queue
                }
            }
            return -1;
        }
    }

    /*
    ✅ Approach 3: Binary Search (Optimal)
🔹 Idea:
Binary search the answer space between matrix[0][0] and matrix[n-1][n-1].

For each mid, count how many elements are <= mid using a smart pointer approach per row.
⏱️ Time Complexity:
O(n log(max - min)), where max and min are matrix bounds.

📦 Space Complexity:
O(1) (no extra space used beyond variables).

✅ Summary Table

Approach	Time Complexity	Space Complexity	Notes
Brute Force	O(n² log n)	O(n²)	Simple but inefficient
Min Heap	O(k log n)	O(n)	Efficient for small k
Binary Search	O(n log(max-min))	O(1)	Optimal for large matrices
     */
    private static class Optimal {
        /*
        Not done this approach
        It is based on binary search on answers just see many questions are done in binary sarch section
         */
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int low = matrix[0][0];
            int high = matrix[n - 1][n - 1];

            while (low < high) {
                int mid = low + (high - low) / 2;
                int count = countLessEqual(matrix, mid, n);
                if (count < k) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }

        private int countLessEqual(int[][] matrix, int mid, int n) {
            int count = 0;
            int row = n - 1, col = 0;

            while (row >= 0 && col < n) {
                if (matrix[row][col] <= mid) {
                    count += row + 1;
                    col++;
                } else {
                    row--;
                }
            }

            return count;
        }
    }
}

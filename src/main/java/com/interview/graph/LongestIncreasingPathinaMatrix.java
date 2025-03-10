package com.interview.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 🔗 Problem: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * 📌 **329. Longest Increasing Path in a Matrix**
 * 🔥 **Difficulty:** Hard
 * 🏷️ **Category:** DFS, DP, Graph
 * 🎥 **Video Explanation:** https://www.youtube.com/watch?v=cZlHLMxIOMI&t=665s
 *
 * Given an `m x n` integer matrix, return the **length of the longest increasing path** in the matrix.
 *
 * 🔹 **Movement Rules:**
 * - You can move **left, right, up, or down**.
 * - **Diagonal movement is NOT allowed.**
 * - **Wrap-around is NOT allowed.**
 *
 * 📝 **Examples:**
 *
 * **Example 1:**
 * ```
 * Input: matrix = [[9,9,4],
 *                  [6,6,8],
 *                  [2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * ```
 *
 * **Example 2:**
 * ```
 * Input: matrix = [[3,4,5],
 *                  [3,2,6],
 *                  [2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * ```
 *
 * **Example 3:**
 * ```
 * Input: matrix = [[1]]
 * Output: 1
 * ```
 *
 * 🚧 **Constraints:**
 * - `m == matrix.length`
 * - `n == matrix[i].length`
 * - `1 <= m, n <= 200`
 * - `0 <= matrix[i][j] <= 2³¹ - 1`
 */
public class LongestIncreasingPathinaMatrix {
    /*
     * 1️⃣ Brute Force Approach (Recursive DFS Without Memoization)
     *
     * 🔹 Approach:
     *   - Try all possible paths from each cell using DFS.
     *   - Move in 4 directions (left, right, up, down) and check if the next cell has a greater value.
     *   - No memoization → Causes repeated computations.
     *
     * 🔹 Time Complexity: O(4^(m*n)) (Exponential, as each cell can have 4 choices)
     * 🔹 Space Complexity: O(m*n) (Stack space for DFS in the worst case)
     *
     * 🔹 Dry Run (Brute Force)
     *   Example: matrix = [[3,4,5], [3,2,6], [2,2,1]]
     *   1️⃣ Start from (0,0) → 3
     *   2️⃣ Move to (0,1) → 4
     *   3️⃣ Move to (0,2) → 5
     *   4️⃣ Move to (1,2) → 6
     *   ✅ Path [3, 4, 5, 6] found.
     *   ❌ Without memoization, the function repeats these calculations multiple times.
     */
    private static class BruitForce {
        class Solution {
            private static final int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

            public int longestIncreasingPath(int[][] matrix) {
                int m = matrix.length, n = matrix[0].length;
                int maxLen = 0;

                // Try starting from each cell
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        maxLen = Math.max(maxLen, dfs(matrix, i, j, -1));
                    }
                }
                return maxLen;
            }

            private int dfs(int[][] matrix, int i, int j, int prevValue) {
                if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] <= prevValue)
                    return 0; // Invalid move

                int maxPath = 0;
                for (int[] dir : directions) {
                    int newRow = i + dir[0], newCol = j + dir[1];
                    maxPath = Math.max(maxPath, dfs(matrix, newRow, newCol, matrix[i][j]));
                }
                return 1 + maxPath; // Add 1 for the current cell
            }
        }
    }

    /*
     * 2️⃣ Better Approach (DFS + Memoization)
     *
     * 🔹 Approach:
     *   - Instead of recomputing for every cell, store results in a cache (memoization).
     *   - Avoids redundant calculations by storing the longest path found from each cell.
     *
     * 🔹 Time Complexity: O(m*n), since each cell is computed only once.
     * 🔹 Space Complexity: O(m*n), for the cache + recursion stack.
     *
     * 🔹 Dry Run (Memoization)
     *   Example: matrix = [[3,4,5], [3,2,6], [2,2,1]]
     *   - First call from (0,0) → 3 computes [3, 4, 5, 6].
     *   - Stores it in dp[][] → Avoids recomputation.
     *   - For (1,2) → 6, instead of recomputing, fetches the stored value in O(1).
     *
     * ✅ Optimization reduces redundant calls significantly!
     */
    private static class Better {
        private static final int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        private int[][] dp;  // Memoization array

        public int longestIncreasingPath(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            dp = new int[m][n];

            int maxLen = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    maxLen = Math.max(maxLen, dfs(matrix, i, j, -1));
                }
            }
            return maxLen;
        }

        private int dfs(int[][] matrix, int i, int j, int prevValue) {
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] <= prevValue)
                return 0; // Invalid move

            if (dp[i][j] != 0) return dp[i][j]; // Return cached result

            int maxPath = 0;
            for (int[] dir : directions) {
                maxPath = Math.max(maxPath, dfs(matrix, i + dir[0], j + dir[1], matrix[i][j]));
            }

            return dp[i][j] = 1 + maxPath; // Store the result in dp
        }
    }

    /*
     * 3️⃣ Optimal Approach (Topological Sort - Kahn’s Algorithm)
     *
     * 🔹 Approach:
     *   - Think of the matrix as a DAG (Directed Acyclic Graph).
     *   - In-degree: A cell's in-degree equals the count of smaller neighbors.
     *   - Start from all in-degree = 0 cells (smallest numbers).
     *   - Process each cell level by level (similar to BFS).
     *
     * 🔹 Time Complexity: O(m*n), since each cell is processed once.
     * 🔹 Space Complexity: O(m*n), for storing in-degrees & queue.
     *
     * 🔹 Code (Topological Sort - BFS)
     *
     * 🔹 Dry Run (Topological Sort):
     *   - Start with in-degree = 0 nodes.
     *   - Process each node level by level.
     *   - BFS ensures we traverse in increasing order, efficiently computing the longest path.
     *
     * 🔹 Final Comparison:
     * | Approach                         | Time Complexity         | Space Complexity | Best for?         |
     * |----------------------------------|------------------------|-----------------|------------------|
     * | Brute Force (DFS without Memo)   | O(4^(m*n)) (Exp)       | O(m*n) (Stack)  | Small matrices   |
     * | DFS + Memoization                | O(m*n)                 | O(m*n)          | General cases    |
     * | Topological Sort (BFS)           | O(m*n)                 | O(m*n)          | Large cases      |
     *
     * ✅ Best Approach → DFS + Memoization (Simple & Efficient)
     * ✅ Topological Sort (BFS) → Alternative when thinking in DAG terms
     */
    private static class Optimal {
        public int longestIncreasingPath(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] inDegree = new int[m][n];
            Queue<int[]> queue = new LinkedList<>();
            int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

            // Step 1: Compute in-degree for each cell
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : directions) {
                        int ni = i + dir[0], nj = j + dir[1];
                        if (ni >= 0 && nj >= 0 && ni < m && nj < n && matrix[ni][nj] > matrix[i][j]) {
                            inDegree[ni][nj]++;
                        }
                    }
                }
            }

            // Step 2: Start BFS from all 0 in-degree nodes
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (inDegree[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            // Step 3: Process nodes in BFS manner
            int maxLen = 0;
            while (!queue.isEmpty()) {
                maxLen++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cell = queue.poll();
                    int x = cell[0], y = cell[1];
                    for (int[] dir : directions) {
                        int ni = x + dir[0], nj = y + dir[1];
                        if (ni >= 0 && nj >= 0 && ni < m && nj < n && matrix[ni][nj] > matrix[x][y]) {
                            if (--inDegree[ni][nj] == 0) {
                                queue.offer(new int[]{ni, nj});
                            }
                        }
                    }
                }
            }
            return maxLen;
        }
    }
}

package com.interview.graph.PartATraversal;

import java.util.*;
/*
    🔗 Problem: https://leetcode.com/problems/pacific-atlantic-water-flow/
    https://www.youtube.com/watch?v=krL3r7MY7Dc

    📌 Category: Medium, Facebook, FAANG

    🔹 Description:
    - Given an m x n matrix `heights`, where `heights[r][c]` represents the height of a cell.
    - Water can flow from a cell to its adjacent cells (up, down, left, right) if the adjacent cell's height is less than or equal to the current cell's height.
    - The Pacific Ocean touches the left and top borders.
    - The Atlantic Ocean touches the right and bottom borders.
    - Return a list of grid coordinates where rainwater can flow to both oceans.

    🔹 Example 1:
    Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
    Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

    Explanation:
    - Water from cell [0,4] can reach both Pacific and Atlantic oceans.
    - Water from cell [1,3] can reach both oceans through valid paths.
    - Similarly, other cells in the output list can reach both oceans.

    🔹 Example 2:
    Input: heights = [[1]]
    Output: [[0,0]]

    Explanation:
    - The single cell can reach both oceans.

    🔹 Constraints:
    - 1 <= m, n <= 200
    - 0 <= heights[r][c] <= 10^5
*/
public class PartFPacificAtlanticWaterFlow {

    /*
        🔹 Approach 1: Brute Force (DFS from Each Cell) - O(m * n * (m + n))

        💡 Idea:
        - For each cell in the grid, perform a DFS to check if it can reach both the Pacific and Atlantic oceans.
        - If a cell can reach both oceans, it is part of the result.

        🔴 Time Complexity: O(m * n * (m + n)) → Too slow!
        🔵 Space Complexity: O(m * n) (visited array + recursion stack)
        ❌ This approach is inefficient due to redundant DFS calls from each cell.
    */
    private static class BruitForce {
        private static final int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            int rows = heights.length, cols = heights[0].length;
            List<List<Integer>> result = new ArrayList<>();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    boolean canReachPacific = dfs(heights, row, col, new boolean[rows][cols], true);
                    boolean canReachAtlantic = dfs(heights, row, col, new boolean[rows][cols], false);
                    if (canReachPacific && canReachAtlantic) {
                        result.add(Arrays.asList(row, col));
                    }
                }
            }
            return result;
        }

        private boolean dfs(int[][] heights, int row, int col, boolean[][] visited, boolean isPacific) {
            int rows = heights.length, cols = heights[0].length;
            if (isPacific && (row == 0 || col == 0)) return true;
            if (!isPacific && (row == rows - 1 || col == cols - 1)) return true;
            if (visited[row][col]) return false;

            visited[row][col] = true;
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0], newCol = col + direction[1];
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                        heights[newRow][newCol] <= heights[row][col]) {
                    if (dfs(heights, newRow, newCol, visited, isPacific)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    /*
        🔹 Approach 2: Optimized DFS (O(m * n))

        💡 Idea:
        Instead of running DFS from every cell, we only start from the border cells:
        - Pacific Ocean → Start from top & left border
        - Atlantic Ocean → Start from bottom & right border

        ✔ Reduces redundant DFS calls
        ✔ Each cell is visited at most twice (once per ocean)

        🔵 Time Complexity: O(m * n)
        🔵 Space Complexity: O(m * n) (recursive stack + visited arrays)
        ✅ This is the best DFS approach (O(m * n)).
    */
    private static class Better {
        private static final int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            int rows = heights.length, cols = heights[0].length;
            boolean[][] pacific = new boolean[rows][cols];
            boolean[][] atlantic = new boolean[rows][cols];
            List<List<Integer>> result = new ArrayList<>();

            // Start DFS from Pacific (top & left borders) and Atlantic (bottom & right borders)
            for (int row = 0; row < rows; row++) {
                dfs(heights, row, 0, pacific);      // Pacific Left
                dfs(heights, row, cols - 1, atlantic); // Atlantic Right
            }
            for (int col = 0; col < cols; col++) {
                dfs(heights, 0, col, pacific);      // Pacific Top
                dfs(heights, rows - 1, col, atlantic); // Atlantic Bottom
            }

            // Collect cells that can reach both oceans
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (pacific[row][col] && atlantic[row][col]) {
                        result.add(Arrays.asList(row, col));
                    }
                }
            }
            return result;
        }

        private void dfs(int[][] heights, int row, int col, boolean[][] ocean) {
            ocean[row][col] = true;
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0], newCol = col + direction[1];
                if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length &&
                        !ocean[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                    dfs(heights, newRow, newCol, ocean);
                }
            }
        }
    }

    /*
        🔹 Approach 3: BFS (Breadth-First Search) - O(m * n)

        💡 Idea:
        Instead of DFS, we use BFS to explore the ocean flow level by level.

        ✔ More iterative and avoids deep recursion stack
        ✔ Uses a queue instead of recursion

        🔵 Time Complexity: O(m * n)
        🔵 Space Complexity: O(m * n) (queue storage)
        ✅ This BFS approach is as optimal as DFS but avoids deep recursion.

        🔹 Summary:
        ┌───────────────────────────────┬────────────────────┬──────────────────┬──────────────────────────────┐
        │ Approach                      │ Time Complexity    │ Space Complexity │ Notes                        │
        ├───────────────────────────────┼────────────────────┼──────────────────┼──────────────────────────────┤
        │ Brute Force (DFS from each cell) │ O(m * n * (m + n)) │ O(m * n)        │ ❌ Too slow                   │
        │ Optimized DFS (From Borders)  │ O(m * n)          │ O(m * n)        │ ✅ Best DFS                   │
        │ BFS (From Borders)            │ O(m * n)          │ O(m * n)        │ ✅ Best BFS (avoids recursion) │
        └───────────────────────────────┴────────────────────┴──────────────────┴──────────────────────────────┘
    */
    private static class Optimal {
        private static final int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            int rows = heights.length, cols = heights[0].length;
            boolean[][] pacific = new boolean[rows][cols];
            boolean[][] atlantic = new boolean[rows][cols];
            List<List<Integer>> result = new ArrayList<>();

            Queue<int[]> pacificQueue = new LinkedList<>();
            Queue<int[]> atlanticQueue = new LinkedList<>();

            // Add border cells to the BFS queue
            for (int row = 0; row < rows; row++) {
                pacificQueue.add(new int[]{row, 0});
                atlanticQueue.add(new int[]{row, cols - 1});
                pacific[row][0] = true;
                atlantic[row][cols - 1] = true;
            }
            for (int col = 0; col < cols; col++) {
                pacificQueue.add(new int[]{0, col});
                atlanticQueue.add(new int[]{rows - 1, col});
                pacific[0][col] = true;
                atlantic[rows - 1][col] = true;
            }

            // BFS for both oceans
            bfs(heights, pacific, pacificQueue);
            bfs(heights, atlantic, atlanticQueue);

            // Collect cells that can reach both oceans
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (pacific[row][col] && atlantic[row][col]) {
                        result.add(Arrays.asList(row, col));
                    }
                }
            }
            return result;
        }

        private void bfs(int[][] heights, boolean[][] ocean, Queue<int[]> queue) {
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int row = cell[0], col = cell[1];

                for (int[] direction : DIRECTIONS) {
                    int newRow = row + direction[0], newCol = col + direction[1];
                    if (newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length &&
                            !ocean[newRow][newCol] && heights[newRow][newCol] >= heights[row][col]) {
                        ocean[newRow][newCol] = true;
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }
}

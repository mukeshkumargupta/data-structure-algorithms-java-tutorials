package com.interview.graph.PartATraversal;

import java.util.*;

/**
 * https://leetcode.com/problems/number-of-islands/
 * https://leetcode.com/problems/max-area-of-island/
 */
//Explanation: https://www.youtube.com/watch?v=CGMNePwovA0&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=2&t=265s
//Done:
//Category:Medium, Must Do
//Derived Question: Matrix-based approach: try to solve using dfs and bfs to make it clear, Count all regions, find the maximum region size, find the minimum region size, find two regions with maximum distance
//Make knight tour problem that is derived from this problem
public class PartBNumberOfIsland {
    public static class BFSApproach {
        static class Point {
            int i, j;
            Point(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }

        public int numIslands(char[][] grid) {
            int numberOfIslands = 0;
            int rows = grid.length;
            if (rows == 0) return 0;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        numberOfIslands++;
                        bfs(grid, i, j, visited);
                    }
                }
            }
            return numberOfIslands;
        }

        public void bfs(char[][] grid, int i, int j, boolean[][] visited) {
            Queue<Point> queue = new LinkedList<>();
            visited[i][j] = true;
            queue.add(new Point(i, j));

            int[][] directions = { {0, 1}, {-1, 0}, {0, -1}, {1, 0} };

            while (!queue.isEmpty()) {
                Point point = queue.remove();
                for (int[] move : directions) {
                    int newI = point.i + move[0], newJ = point.j + move[1];
                    if (isValidMove(newI, newJ, grid, visited)) {
                        visited[newI][newJ] = true;
                        queue.add(new Point(newI, newJ));
                    }
                }
            }
        }

        boolean isValidMove(int i, int j, char[][] grid, boolean[][] visited) {
            return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1' && !visited[i][j];
        }

    }

    public static class DFSApproach {
        boolean isValidMove(int i, int j, char[][] grid, boolean[][] visited) {
            return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1' && !visited[i][j];
        }

        public int maxAreaOfIsland(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int rows = grid.length;
            int cols = grid[0].length;
            int maxArea = 0;
            boolean[][] visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        maxArea = Math.max(maxArea, dfs(grid, i, j, visited));
                    }
                }
            }

            return maxArea;
        }

        private int dfs(char[][] grid, int i, int j, boolean[][] visited) {
            visited[i][j] = true;
            int area = 1;
            int[][] directions = { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };

            for (int[] dir : directions) {
                int newI = i + dir[0], newJ = j + dir[1];
                if (isValidMove(newI, newJ, grid, visited)) {
                    area += dfs(grid, newI, newJ, visited);
                }
            }

            return area;
        }

        public static void main(String args[]) {
            char[][] matrix = {
                    {'1','1','0','1','0'},
                    {'1','0','0','0','1'},
                    {'0','0','0','0','0'},
                    {'1','0','1','0','1'},
                    {'1','0','0','0','0'}
            };

            DFSApproach island = new DFSApproach();
            int count = island.maxAreaOfIsland(matrix);
            System.out.println(count);  // Output: 4 (The largest island has area 4)

            // Test case with another grid
            char[][] islandGrid = {
                    {'1', '1', '0', '1', '0'},
                    {'1', '0', '0', '0', '1'},
                    {'0', '0', '0', '0', '0'},
                    {'1', '0', '1', '0', '1'},
                    {'1', '0', '0', '0', '0'}
            };
            int maxArea = island.maxAreaOfIsland(islandGrid);
            System.out.println(maxArea);  // Output: 4
        }
    }
}
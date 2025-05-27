package com.interview.graph.PartCShortestPath;

import java.util.PriorityQueue;

public class PartA_E_SwimInRisingWater {
    /**
     * Problem: 778. Swim in Rising Water
     *
     * Category: Hard
     *
     * Link: https://leetcode.com/problems/swim-in-rising-water/
     *
     * Problem Explanation:
     * You are given an n x n grid where each cell represents the water level in that cell.
     * You need to find the minimum time required to swim from the top-left corner to the bottom-right corner.
     * You can move up, down, left, or right to neighboring cells. The time is determined by the maximum height
     * encountered on the path. Use a priority queue to prioritize nodes with the smallest height to ensure
     * the safest path is explored first.
     *
     * Approach:
     * - Node Class: Represents each cell in the grid, storing its row, column, and the maximum water level encountered.
     * - Priority Queue: Implements a Dijkstra-like algorithm to prioritize cells based on the minimum water level required to reach them.
     * - Visited Array: Keeps track of cells that have been processed to avoid reprocessing.
     * - Directions: Defines possible movements (up, down, left, right) to explore neighboring cells.
     *
     * Algorithm:
     * 1. Initialize the priority queue with the starting cell (0,0) and its height as the initial effort.
     * 2. While the queue is not empty, extract the cell with the smallest current height.
     * 3. If this cell is the bottom-right corner, return the maximum height encountered to reach it.
     * 4. For each neighbor, if it hasn't been visited, calculate the maximum height required to reach it,
     *    update the priority queue, and mark it as visited.
     *
     * Detailed Explanation:
     * - Node Class: Stores the row, column, and the maximum water level required to reach a cell.
     * - Priority Queue: A min-heap prioritizes nodes with the smallest water level, ensuring the path with the
     *   minimum height is explored first.
     * - Visited Array: Tracks cells that have been processed to prevent re-processing and infinite loops.
     * - Directions: Defines movements (up, down, left, right) to explore neighboring cells.
     *
     * Time Complexity:
     * O(n^2 * log(n)), where n^2 is the number of cells and the priority queue operations dominate the complexity.
     *
     * Space Complexity:
     * O(n^2), accounting for the priority queue and visited array, which store information for all grid cells.
     *
     * This solution efficiently finds the minimum time required to swim from the top-left to the bottom-right corner
     * of the grid using a Dijkstra-like algorithm.
     */
    public static class SwimInRisingWater {

        // Node class to represent grid cells and their maximum water level encountered so far
        static class Cell {
            int row, col, maxWaterLevel;

            Cell(int row, int col, int maxWaterLevel) {
                this.row = row;
                this.col = col;
                this.maxWaterLevel = maxWaterLevel;
            }
        }

        public int swimInWater(int[][] grid) {
            int n = grid.length;

            // Directions for moving in the grid (up, down, left, right)
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            // Priority queue for Dijkstra's algorithm
            PriorityQueue<Cell> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.maxWaterLevel, b.maxWaterLevel));
            priorityQueue.offer(new SwimInRisingWater.Cell(0, 0, grid[0][0]));

            // Visited array to keep track of visited cells
            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;

            // Dijkstra-like algorithm to find the minimum maximum water level
            while (!priorityQueue.isEmpty()) {
                SwimInRisingWater.Cell current = priorityQueue.poll();
                int currentRow = current.row;
                int currentCol = current.col;
                int currentMaxWaterLevel = current.maxWaterLevel;

                // If we've reached the bottom-right corner, return the maximum water level
                if (currentRow == n - 1 && currentCol == n - 1) {
                    return currentMaxWaterLevel;
                }

                // Explore neighbors
                for (int[] direction : directions) {
                    int newRow = currentRow + direction[0];
                    int newCol = currentCol + direction[1];

                    // Check if the neighbor is within bounds and not visited
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                        // Calculate the maximum water level to reach the neighbor
                        int newMaxWaterLevel = Math.max(currentMaxWaterLevel, grid[newRow][newCol]);
                        priorityQueue.offer(new SwimInRisingWater.Cell(newRow, newCol, newMaxWaterLevel));
                        visited[newRow][newCol] = true;
                    }
                }
            }

            return -1; // Should never reach here if the input is valid
        }

        public static void main(String[] args) {
            SwimInRisingWater solver = new SwimInRisingWater();

            int[][] grid1 = {
                    {0, 2},
                    {1, 3}
            };
            System.out.println(solver.swimInWater(grid1)); // Output: 3

            int[][] grid2 = {
                    {0, 1, 2, 3, 4},
                    {24, 23, 22, 21, 5},
                    {12, 13, 14, 15, 16},
                    {11, 17, 18, 19, 20},
                    {10, 9, 8, 7, 6}
            };
            System.out.println(solver.swimInWater(grid2)); // Output: 16
        }
    }
}

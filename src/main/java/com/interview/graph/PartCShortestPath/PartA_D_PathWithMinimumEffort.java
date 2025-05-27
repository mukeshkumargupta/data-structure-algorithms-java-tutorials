package com.interview.graph.PartCShortestPath;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PartA_D_PathWithMinimumEffort {
    /*
     * Category: Medium
     *
     * Video Reference:
     * - https://www.youtube.com/watch?v=0vVofAhAYjc&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=22
     * https://leetcode.com/problems/path-with-minimum-effort/
     *
     * **Detailed Explanation**:
     *
     * 1. **Node Class**:
     *    - Represents a cell in the grid, storing its row, column, and the current effort needed to reach it.
     *
     * 2. **Priority Queue**:
     *    - Uses a min-heap to prioritize nodes with the smallest effort, implementing Dijkstra's algorithm to find the shortest path.
     *
     * 3. **Effort Matrix**:
     *    - Keeps track of the minimum effort required to reach each cell, initialized to infinity except for the starting cell, which is 0.
     *
     * 4. **Directions**:
     *    - Defines possible movements (up, down, left, right) to explore neighboring cells.
     *
     * **Algorithm Steps**:
     *
     * 1. Initialize the priority queue with the starting cell (0,0) and effort 0.
     * 2. While the queue is not empty, extract the node with the smallest effort.
     * 3. If this node is the target cell (bottom-right corner), return its effort.
     * 4. For each neighbor, calculate the new effort and update the priority queue and effort matrix if the new effort is smaller.
     *
     * **Time Complexity**:
     * - The time complexity is O(n ⋅ log(n)), where n is the total number of cells in the grid. The priority queue operations dominate the complexity.
     *
     * **Space Complexity**:
     * - The space complexity is O(n), which accounts for the priority queue and the effort matrix, both of which store information for all grid cells.
     *
     * This solution efficiently finds the path with the minimum effort from the top-left to the bottom-right corner of the grid using Dijkstra's algorithm.
     */
    public static class PathWithMinimumEffort {
        // Node class to represent grid cells and their efforts
        static class Node {
            int row, col, effort;

            Node(int row, int col, int effort) {
                this.row = row;
                this.col = col;
                this.effort = effort;
            }
        }

        public int minimumEffortPath(int[][] heights) {
            int rows = heights.length;
            int cols = heights[0].length;

            // Directions for moving in the grid (up, down, left, right)
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            // Initialize effort matrix with infinity
            int[][] effort = new int[rows][cols];
            for (int[] row : effort) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            effort[0][0] = 0; // Starting point effort is 0

            // Priority queue for Dijkstra's algorithm
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.effort - b.effort);
            priorityQueue.offer(new PathWithMinimumEffort.Node(0, 0, 0));

            // Dijkstra's algorithm
            while (!priorityQueue.isEmpty()) {
                PathWithMinimumEffort.Node currentNode = priorityQueue.poll();
                int currentRow = currentNode.row;
                int currentCol = currentNode.col;
                int currentEffort = currentNode.effort;

                // If we've reached the bottom-right corner, return the effort
                if (currentRow == rows - 1 && currentCol == cols - 1) {
                    return currentEffort;
                }

                // Explore neighbors
                for (int[] direction : directions) {
                    int newRow = currentRow + direction[0];
                    int newCol = currentCol + direction[1];

                    // Check if the neighbor is within bounds
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        // Calculate the effort to move to the neighbor
                        int newEffort = Math.max(currentEffort, Math.abs(heights[newRow][newCol] - heights[currentRow][currentCol]));

                        // If the new effort is smaller, update the effort matrix and push to the queue
                        if (newEffort < effort[newRow][newCol]) {
                            effort[newRow][newCol] = newEffort;
                            priorityQueue.offer(new PathWithMinimumEffort.Node(newRow, newCol, newEffort));
                        }
                    }
                }
            }

            return 0; // Should never reach here if the input is valid
        }

        public static void main(String[] args) {
            PathWithMinimumEffort solver = new PathWithMinimumEffort();
            int[][] heights1 = {
                    {1, 2, 2},
                    {3, 8, 2},
                    {5, 3, 5}
            };
            System.out.println(solver.minimumEffortPath(heights1)); // Output: 2

            int[][] heights2 = {
                    {1, 2, 3},
                    {3, 8, 4},
                    {5, 3, 5}
            };
            System.out.println(solver.minimumEffortPath(heights2)); // Output: 1

            int[][] heights3 = {
                    {1, 2, 1, 1, 1},
                    {1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1},
                    {1, 1, 1, 2, 1}
            };
            System.out.println(solver.minimumEffortPath(heights3)); // Output: 0
        }
    }
}

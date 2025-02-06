package com.interview.bfsdfs;

import java.util.*;

/*
https://leetcode.com/problems/01-matrix/  this proble is exaclty same as knight tour no diffrence
https://www.youtube.com/watch?v=edXdVwkYHF8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=13 videk link for 01-matrix/
 */
// Status: done
public class KnightTour {
    // Class for storing a cell's position and distance
    static class Position {
        int row;
        int column;
        int distance;

        public Position(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }
    }

    // Utility method to check if (row, column) is a safe position on the board
    static boolean isSafe(int row, int column, int boardSize) {
        return row >= 0 && row < boardSize && column >= 0 && column < boardSize;
    }

    // Method returns minimum steps required for a knight to reach the target position
    static int minimumStepsToReachTarget(int[] knightStart, int[] targetPosition, int boardSize) {
        // Directions where a knight can move
        int[][] dirs = {
                {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
                {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        };

        // Queue for BFS to store the state of the knight on the board
        Queue<Position> queue = new LinkedList<>();

        // Add starting position of the knight with 0 distance
        queue.add(new Position(knightStart[0], knightStart[1], 0));

        // Visited array to keep track of visited positions
        boolean[][] visited = new boolean[boardSize][boardSize];

        // Mark the starting position as visited
        visited[knightStart[0]][knightStart[1]] = true;

        // Process the queue until it's empty
        while (!queue.isEmpty()) {
            Position current = queue.poll();

            // If the current position matches the target position, return the distance
            if (current.row == targetPosition[0] && current.column == targetPosition[1]) {
                return current.distance;
            }

            // Explore all possible knight moves
            for (int[] dir : dirs) {
                int newRow = current.row + dir[0];
                int newColumn = current.column + dir[1];

                // If the new position is within the board and not visited
                if (isSafe(newRow, newColumn, boardSize) && !visited[newRow][newColumn]) {
                    visited[newRow][newColumn] = true;
                    queue.offer(new Position(newRow, newColumn, current.distance + 1));
                }
            }
        }

        // If the target position is unreachable, return a large number
        return Integer.MAX_VALUE;
    }

    // Driver code
    public static void main(String[] args) {
        int boardSize = 30;
        int[] knightStart = {0, 0};
        int[] targetPosition = {29, 29};

        int result = minimumStepsToReachTarget(knightStart, targetPosition, boardSize);
        System.out.println(result);
    }




    /*DerivedProblem
    https://leetcode.com/problems/01-matrix/  this proble is exaclty same as knight tour no diffrence
    class Solution {
   // Class to store cell information
    static class Cell {
        int row, col;
        int distance;

        public Cell(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    // Utility method to check if (row, col) is within matrix bounds
    static boolean isSafe(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Method to find the shortest distance of each cell from the nearest 0
    static int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] distanceMatrix = new int[rows][cols];
        Queue<Cell> queue = new LinkedList<>();

        // Initialize distanceMatrix and add cells with 0 to the queue
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    distanceMatrix[r][c] = 0;
                    queue.offer(new Cell(r, c, 0));
                } else {
                    distanceMatrix[r][c] = Integer.MAX_VALUE;
                }
            }
        }

        // Direction vectors for moving in 4 directions (up, down, left, right)
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Perform BFS to calculate the shortest distance from the nearest 0
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            int row = cell.row;
            int col = cell.col;
            int dist = cell.distance;

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isSafe(newRow, newCol, rows, cols) && distanceMatrix[newRow][newCol] > dist + 1) {
                    distanceMatrix[newRow][newCol] = dist + 1;
                    queue.offer(new Cell(newRow, newCol, dist + 1));
                }
            }
        }

        return distanceMatrix;
    }
}
     */

}


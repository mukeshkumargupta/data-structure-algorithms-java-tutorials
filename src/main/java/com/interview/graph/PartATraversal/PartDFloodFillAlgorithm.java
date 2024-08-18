package com.interview.graph.PartATraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Reference: https://leetcode.com/problems/flood-fill
 * Video: https://www.youtube.com/watch?v=RwozX--B_Xs
 * Category: Easy
 * Tricky
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with newColor.

Return the modified image after performing the flood fill.

 

Example 1:


Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
Output: [[2,2,2],[2,2,2]]
 * 
 */
public class PartDFloodFillAlgorithm {

    // Method to check if the next cell is safe to visit
    boolean isSafe(int i, int j, int R, int C, int[][] image, int iniColor) {
        return i >= 0 && i < R && j >= 0 && j < C && image[i][j] == iniColor;
    }

    // Depth First Search (DFS) method to fill the connected component
    private void dfs(int row, int col, int[][] ans, int[][] image, int newColor, int iniColor) {
        // Color the current cell with the new color
        ans[row][col] = newColor;
        int R = image.length;
        int C = image[0].length;
        // Four possible directions to move (right, left, down, up)
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int nrow = row + dir[0];
            int ncol = col + dir[1];
            if (isSafe(nrow, ncol, R, C, image, iniColor)) {
                dfs(nrow, ncol, ans, image, newColor, iniColor);
            }
        }
    }

    // Method to perform the flood fill operation
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Get initial color
        int iniColor = image[sr][sc];
        int[][] ans = image;
        // Start DFS if the new color is different from the initial color to avoid infinite loop
        if (iniColor != newColor) {
            dfs(sr, sc, ans, image, newColor, iniColor);
        }
        return ans;
    }

    static class Cell {
        int row, col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // Method to perform the flood fill operation using BFS
    public int[][] floodFillBfs(int[][] image, int sr, int sc, int newColor) {
        int iniColor = image[sr][sc];
        if (iniColor == newColor) return image; // If the initial color is the same as the new color, return the image

        int R = image.length;
        int C = image[0].length;

        // Queue to store the cells to be processed
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(sr, sc));

        // Direction vectors for moving in 4 possible directions
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // BFS traversal
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            int row = cell.row;
            int col = cell.col;
            image[row][col] = newColor;

            for (int[] dir : dirs) {
                int nrow = row + dir[0];
                int ncol = col + dir[1];

                if (isSafe(nrow, ncol, R, C, image, iniColor)) {
                    queue.add(new Cell(nrow, ncol));
                    // Mark the cell as visited by changing its color to the new color
                    image[nrow][ncol] = newColor;
                }
            }
        }

        return image;
    }

    // Main method for testing the flood fill algorithm
    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        // sr = 1, sc = 1, newColor = 2
        PartDFloodFillAlgorithm obj = new PartDFloodFillAlgorithm();
        int[][] ans = obj.floodFill(image, 1, 1, 2);
        for (int[] row : ans) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
}

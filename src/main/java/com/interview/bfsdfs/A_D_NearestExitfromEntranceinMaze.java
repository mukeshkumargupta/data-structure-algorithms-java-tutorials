package com.interview.bfsdfs;

import java.util.*;
/*
 * https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/?envType=study-plan-v2&envId=leetcode-75
 * Category: Medium, Top 75, BFS, Must Write
 * Note: Looks easy but is tricky to implement correctly (must consider edge boundaries and visited tracking)
 *
 * Problem:
 * You are given an m x n matrix `maze` (0-indexed):
 * - '.' represents an empty cell
 * - '+' represents a wall
 *
 * You are also given the `entrance` = [entranceRow, entranceCol] representing the starting cell.
 *
 * In one step, you can move **up, down, left, or right**, but:
 * - You cannot move into a wall ('+')
 * - You cannot go outside the boundaries of the maze
 *
 * An **exit** is defined as any empty cell **at the boundary** of the maze (excluding the entrance itself).
 *
 * Your task is to return the number of steps in the **shortest path to the nearest exit**,
 * or -1 if there is no such path.
 *
 * Examples:
 *
 * Example 1:
 * Input: maze = [["+", "+", ".", "+"], [".", ".", ".", "+"], ["+", "+", "+", "."]],
 *        entrance = [1, 2]
 * Output: 1
 * Explanation:
 * - Possible exits: [1,0], [0,2], [2,3]
 * - Entrance is at [1,2]. The nearest exit is [0,2], which is 1 step away.
 *
 * Example 2:
 * Input: maze = [["+", "+", "+"], [".", ".", "."], ["+", "+", "+"]],
 *        entrance = [1, 0]
 * Output: 2
 * Explanation:
 * - Only exit is at [1,2]
 * - Start at [1,0] and reach [1,2] in 2 steps
 *
 * Example 3:
 * Input: maze = [[".", "+"]], entrance = [0,0]
 * Output: -1
 * Explanation:
 * - The entrance is the only empty cell and is not at the border (not a valid exit).
 * - No exit is reachable.
 */
public class A_D_NearestExitfromEntranceinMaze {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        // Start BFS from the entrance
        queue.offer(new int[] { entrance[0], entrance[1], 0 });
        visited[entrance[0]][entrance[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1], steps = current[2];

            // If we're at a border cell (excluding entrance), return steps
            if ((row == 0 || row == rows - 1 || col == 0 || col == cols - 1) &&
                    !(row == entrance[0] && col == entrance[1])) {
                return steps;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isInBounds(newRow, newCol, rows, cols) &&
                        maze[newRow][newCol] == '.' &&
                        !visited[newRow][newCol]) {

                    visited[newRow][newCol] = true;
                    queue.offer(new int[] { newRow, newCol, steps + 1 });
                }
            }
        }

        return -1;  // No exit found
    }

    private boolean isInBounds(int i, int j, int R, int C) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }
}

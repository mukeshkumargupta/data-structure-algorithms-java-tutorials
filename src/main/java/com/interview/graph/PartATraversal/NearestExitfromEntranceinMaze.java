package com.interview.graph.PartATraversal;

import java.util.*;
/*
https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75, Must Write because easy to look but Tricky to write
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.



Example 1:


Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.
Example 2:


Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.
Example 3:


Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.
 */
public class NearestExitfromEntranceinMaze {
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

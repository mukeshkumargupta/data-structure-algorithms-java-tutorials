package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/rotting-oranges/
 * Category: Medium, Must Do, VVImp
 * Related:
 * https://leetcode.com/problems/walls-and-gates/ Medium
 * Medium

You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

 

Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 10
    grid[i][j] is 0, 1, or 2.


 */
public class RottingOranges {
    private boolean  isSafe(int i, int j, int R, int C, int[][] grid) {
        if (i >=0 && i < R && j >=0 && j < C) {
            if (grid[i][j]  == 1) {
                return true;
            }
        }
        return false;
        
    }
    private boolean  isEmpty(int i, int j, int R, int C, int[][] grid) {
        if (i >=0 && i < R && j >=0 && j < C) {
            if (grid[i][j]  == 0) {
                return true;
            }
        } else {//out of boundry then return true
           return true; 
        }
        return false;
        
    }
    
    private boolean  stillLeft(int i, int j, int R, int C, int[][] grid) {
        if (i >=0 && i < R && j >=0 && j < C) {
            if (grid[i][j]  == 1) {
                return true;
            }
        }
        return false;
        
    }
    public  int orangesRotting(int[][] grid) {
        class Point {
            int time;
            int i;
            int j;
            Point (int time, int i, int j) {
                //System.out.println(time);
                this.time = time;
                this.i = i;
                this.j = j;
            }
            
        }
        Queue<Point> q = new LinkedList<>();
        int R = grid.length;
        int C = grid[0].length;
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] ==2) {
                    q.add(new Point(0, i, j));
                    visited[i][j] = true;
                }
                boolean isAnyTrue = false;
                if (grid[i][j] ==1) {
                    //check all four dir
                    isAnyTrue = isAnyTrue || !isEmpty(i, j+1, R, C, grid);//right
                    isAnyTrue = isAnyTrue || !isEmpty(i+1, j, R, C, grid);//bottom
                    isAnyTrue = isAnyTrue || !isEmpty(i, j-1, R, C, grid);//left
                    isAnyTrue = isAnyTrue || !isEmpty(i-1, j, R, C, grid);//top
                    if (!isAnyTrue) {
                        return -1;
                    }
                    
                }
                
                
            }
            
        }

        int totalTime =0;
        //System.out.println(q.size());
        while(!q.isEmpty()) {
            Point current = q.remove();
            totalTime =  current.time;
            if (isSafe(current.i, current.j+1, R, C, grid) && !visited[current.i][current.j+1]) {//right
                q.add(new Point(current.time +1, current.i, current.j+1));
                grid[current.i][current.j+1] = 2;
                visited[current.i][current.j+1] = true;
            }
            if (isSafe(current.i+1, current.j, R, C, grid) && !visited[current.i+1][current.j]) {//bottom
                q.add(new Point(current.time +1, current.i+1, current.j));
                visited[current.i+1][current.j] = true;
                grid[current.i+1][current.j] = 2;
            }
            if (isSafe(current.i, current.j-1, R, C, grid) && !visited[current.i][current.j-1]) {//left
                q.add(new Point(current.time +1, current.i, current.j-1));
                visited[current.i][current.j-1] = true;
                grid[current.i][current.j-1] = 2;
            }
            if (isSafe(current.i-1, current.j, R, C, grid) && !visited[current.i-1][current.j]) {//top
                q.add(new Point(current.time +1, current.i-1, current.j));
                visited[current.i-1][current.j] = true;
                grid[current.i-1][current.j] = 2;
            }
            
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (stillLeft(i, j, R, C, grid)) {
                    return -1;
                }
            }
        }

        return totalTime;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] input = {{2,1,1}, {1,1,0}, {0,1,1}};
        int result = orangesRotting(input);
        
    }
    
}

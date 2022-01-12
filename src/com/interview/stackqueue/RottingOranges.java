package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/rotting-oranges/
 * https://www.youtube.com/watch?v=pUAPcVlHLKA
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
    boolean isSafe(int i, int j, int R, int C, int[][] grid)  {
        if (i >=0 && i < R && j >=0 && j < C && grid[i][j] == 1) {
            return true;
        }
        return false;
        
    }
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int R = grid.length;
        int C = grid[0].length;
        class Point {
            int i;
            int j;
            Point(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
        Queue<Point> queue = new LinkedList<>();
        int count_fresh = 0;
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                if(grid[i][j] == 2) {
                    queue.add(new Point(i, j));
                }
                if(grid[i][j] != 0) {
                    count_fresh++;
                }
            }
        }
       
        if(count_fresh == 0) return 0;
        int time = 0, rottenCount = 0;
        int[][] dir = { {0,1}, {0,-1}, {1, 0}, {-1, 0}};
        
        //bfs starting from initially rotten oranges
        while(!queue.isEmpty()) {
            int size = queue.size();
            rottenCount += size; 
            for(int k = 0 ; k < size ; k++) {
                Point point = queue.remove();
                for(int[] d : dir) {
                    int i = point.i + d[0];
                    int j = point.j + d[1];
                    
                    if(isSafe(i, j, R, C, grid))  {
                       grid[i][j] = 2;
                        queue.add(new Point(i, j)); 
                    }
                    
                    
                }
            }
            if(queue.size() != 0) {
                time++;
            }
        }
        return count_fresh == rottenCount ? time : -1;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] input = {{2,1,1}, {1,1,0}, {0,1,1}};
        RottingOranges instance = new RottingOranges();
        int result = instance.orangesRotting(input);
        
    }
    
}

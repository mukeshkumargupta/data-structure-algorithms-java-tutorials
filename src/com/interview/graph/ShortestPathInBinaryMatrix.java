package com.interview.graph;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/shortest-path-in-binary-matrix
 * Category: Medium, Must Know
 * Video:https://www.youtube.com/watch?v=CABaqOkWbgQ
 */

public class ShortestPathInBinaryMatrix {
    class Point {
        int i;
        int j;
        int d;
    }
    boolean isNotVisited(int[][] grid, boolean[][] visited, int i, int j, int R, int C) {
        if (i >= 0 && i < R && j >=0 && j < C) {
            if (grid[i][j] ==1) {
                return false;
            } else {
                if (!visited[i][j]) {
                    return true;
                }
            }
            
        }
        return false;
        
    }
    

    public int shortestPathBinaryMatrix(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        boolean[][] visited = new boolean[R][C];
        Queue<Point> q = new LinkedList<>();
        if (grid[0][0] == 1) {
            return -1;
        }
        Point p = new Point();
        p.i = 0; p.j = 0; p.d =1;
        q.add(p);
        visited[0][0] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int sizeIndex =0; sizeIndex < size; sizeIndex++) {
                Point current = q.remove();
                if (current.i == R-1 && current.j == C-1) {
                    return current.d;

                }
                //Go in all safe path in 8 direction
                int[][] dir = { {0, 1}, {-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1,-1}, {1,0}, {1, 1}};
                for (int i = 0;  i < 8; i++) {
                    for (int j = 0; j < 2; j++) {
                        int new_i = current.i + dir[i][0];
                        int new_j = current.j + dir[i][1];
                        if (isNotVisited(grid, visited, new_i, new_j, R, C )) {
                            Point newPoint = new Point();
                            newPoint.i = new_i;
                            newPoint.j = new_j;
                            newPoint.d = current.d +1;

                            q.add(newPoint);
                            visited[new_i][new_j] = true;

                        }


                    }

                }
             }
                
        }

        return -1; 
    }
    
    public static void main(String[] args) {
        int[][] input = {{0, 1}, {1, 0}};
        ShortestPathInBinaryMatrix spbm = new ShortestPathInBinaryMatrix();
        spbm.shortestPathBinaryMatrix(input);
        
    }
    
}

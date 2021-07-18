package com.interview.graph;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/shortest-path-in-binary-matrix
 * Category: Medium, Must Do
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
    

    public int shortestPathBinaryMatrixBfs(int[][] grid) {//Working
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
    
    public int shortestPathBinaryMatrixDfs(int[][] grid) { //Min will not come also if not reached to end then how to handle not sure

        if (grid[0][0] == 1) {
            return -1;
        }
        int r1 = grid.length;
        int c1 = grid[0].length;
        boolean[][] visitedGrid = new boolean[r1][c1];
        
        int[] d = new int[1];
        d[0] = 1;
        DFS(grid, 0, 0, visitedGrid, r1, c1, d);
        return d[0];
    }
    
    
    public void DFS(int[][] grid, int i, int j, boolean[][] visitedGrid, int R, int C, int[] d) {
        //Base case
        if (i == R -1 && j == C-1) {
            return;

        }
        
        if (visitedGrid[i][j]) return;
        
        visitedGrid[i][j] = true;
        
        if(isNotVisited(grid, visitedGrid,i, j+1, R, C)) {
            d[0] +=1;
            DFS(grid, i, j+1, visitedGrid, R, C, d);//right
        }
        
        if(isNotVisited(grid, visitedGrid, i+1, j, R, C)) {
            d[0] +=1;
            DFS(grid, i+1, j, visitedGrid, R, C, d);//down
        }
        
        if(isNotVisited(grid, visitedGrid, i-1, j, R, C)) {
            d[0] +=1;
            DFS(grid, i-1, j, visitedGrid, R, C, d);//up
        }
        
        if(isNotVisited(grid, visitedGrid, i, j-1, R, C)) {
            d[0] +=1;
            DFS(grid, i, j-1, visitedGrid, R, C, d);//up
        }
        
        if(isNotVisited(grid, visitedGrid, i-1, j+1, R, C)) { //right up diagonal
            d[0] +=1;
            DFS(grid, i-1, j+1, visitedGrid, R, C, d);//
        }
        
        if(isNotVisited(grid, visitedGrid, i-1, j-1, R, C)) { //left up diagonal
            d[0] +=1;
            DFS(grid, i-1, j-1, visitedGrid, R, C, d);//
        }
        
        if(isNotVisited(grid, visitedGrid, i+1, j-1, R, C)) { //left bottom diagonal
            d[0] +=1;
            DFS(grid, i+1, j-1, visitedGrid, R, C, d);//
        }
        
        if(isNotVisited(grid, visitedGrid, i+1, j+1, R, C)) { //right bottom diagonal
            d[0] +=1;
            DFS(grid, i+1, j+1, visitedGrid, R, C, d);//
        }
    }
    
    public static void main(String[] args) {
        int[][] input = {{0, 1}, {1, 0}};
        ShortestPathInBinaryMatrix spbm = new ShortestPathInBinaryMatrix();
        spbm.shortestPathBinaryMatrixBfs(input);
        
    }
    
}

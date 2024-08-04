package com.interview.graph;

import java.util.*;

/**
 https://leetcode.com/problems/number-of-islands/
 https://leetcode.com/problems/max-area-of-island/
 */
//Explanation: https://www.youtube.com/watch?v=CGMNePwovA0&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=2&t=265s
//Done:
//Category:Medium, Must Do
//Derived Question: Matrix based approach: try to solve using dfs and bfs to make it clear, Count all region, find maximum region size, find minimum region size, find two region with having maximum distance 
//Make knight tour problem that is derived of this problem
public class NumberOfIsland {
    class Point {
        int i;
        int j;
        Point (int i , int j) {
            this.i = i;
            this.j = j;
        }
    }
    public int numIslandsBfs(char[][] grid) {
        int numberOfIslands = 0;
        int r1 = grid.length;
        if (r1 == 0) return 0;
        int c1 = grid[0].length;
        boolean[][] visitedGrid = new boolean[r1][c1];
        
        for (int  i = 0; i< r1; i++)  {
            for (int j= 0; j < c1; j++) {
                if((grid[i][j] =='1') && !visitedGrid[i][j]) {
                    numberOfIslands += 1;
                    BFS(grid, i, j, visitedGrid);
                    
                }
            }    
        }

        return numberOfIslands;
    }
    
    public void BFS(char[][] grid, int i, int j, boolean[][] visitedGrid) {//runtime 19.88

        Queue<Point> queue = new LinkedList<>();
        
        visitedGrid[i][j] = true;
        queue.add(new Point(i, j));
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                Point point = queue.remove();
                int[][] moves = { {0, 1}, {-1, 0} , {0, -1}, {1, 0}};
                for (int[] move : moves) {
                    if(isNotVisited(grid, point.i+move[0], point.j+move[1], visitedGrid)) {
                        visitedGrid[point.i+move[0]][point.j+move[1]] = true;
                        queue.add(new Point(point.i+move[0], point.j+move[1]));
                    }
                }
            }


        }

    }

    boolean isSafeMove(int i, int j, int R, int C, int[][] grid, boolean[][] visited) {
        if (i >= 0 && i < R && j >= 0 && j < C && grid[i][j] == 1 && !visited[i][j]) {
            return true;
        }
        return false;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int R = grid.length;
        int C = grid[0].length;
        int maxAreaIsland = 0;
        int[] currentAreaIslandSize = new int[1];
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                currentAreaIslandSize[0] = 0;
                if (isSafeMove(i, j, R, C, grid, visited)) {
                    currentAreaIslandSize[0] += 1;
                    dfs(i, j, R, C, grid, visited, currentAreaIslandSize);
                }
                if (currentAreaIslandSize[0] > maxAreaIsland) {
                    maxAreaIsland = currentAreaIslandSize[0];
                }
            }
        }
        return maxAreaIsland;
    }

    int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    private void dfs(int i, int j, int R, int C, int[][] grid, boolean[][] visited, int[] currentAreaIslandSize) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (isSafeMove(nextI, nextJ, R, C, grid, visited)) {
                currentAreaIslandSize[0] += 1;
                dfs(nextI, nextJ, R, C, grid, visited, currentAreaIslandSize);
            }
        }
    }

    /*More optimized code
    https://chatgpt.com/c/f94c7d35-afd1-407b-b782-e24b251cc83c
    */

    boolean isSafeMove(int i, int j, int R, int C, int[][] grid, boolean[][] visited) {
        return i >= 0 && i < R && j >= 0 && j < C && grid[i][j] == 1 && !visited[i][j];
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int R = grid.length;
        int C = grid[0].length;
        int maxAreaIsland = 0;
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (isSafeMove(i, j, R, C, grid, visited)) {
                    int currentAreaIslandSize = dfs(i, j, R, C, grid, visited);
                    maxAreaIsland = Math.max(maxAreaIsland, currentAreaIslandSize);
                }
            }
        }
        return maxAreaIsland;
    }

    int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    private int dfs(int i, int j, int R, int C, int[][] grid, boolean[][] visited) {
        visited[i][j] = true;
        int area = 1; // Current cell
        for (int[] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            if (isSafeMove(nextI, nextJ, R, C, grid, visited)) {
                area += dfs(nextI, nextJ, R, C, grid, visited);
            }
        }
        return area;
    }
    
    public static void main(String args[]){
        
        char matrix[][] = {{'1','1','0','1','0'},
                          {'1','0','0','0','1'},
                          {'0','0','0','0','0'},
                          {'1','0','1','0','1'},
                          {'1','0','0','0','0'}
                        };
        NumberOfIsland island = new NumberOfIsland();
        int count = island.numIslandsDfs(matrix);
        System.out.println(count);
        island = new NumberOfIsland();//Not required to create new memory
        count = island.numIslandsBfs(matrix);
        System.out.println(count);
    }
}
                     
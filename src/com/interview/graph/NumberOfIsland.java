package com.interview.graph;

/**
 https://leetcode.com/problems/number-of-islands/
 */
//Explanation: https://www.youtube.com/watch?v=CGMNePwovA0&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=2&t=265s
//Done:
//Category:Medium
public class NumberOfIsland {

    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;
        int r1 = grid.length;
        if (r1 == 0) return 0;
        int c1 = grid[0].length;
        boolean[][] visitedGrid = new boolean[r1][c1];
        
        for (int  i = 0; i< r1; i++)  {
            for (int j= 0; j < c1; j++) {
                if((grid[i][j] =='1') && !visitedGrid[i][j]) {
                    numberOfIslands += 1;
                    DFS(grid, i, j, visitedGrid);
                    
                }
            }
    
        }

        return numberOfIslands;
    }
    
    public void DFS(char[][] grid, int i, int j, boolean[][] visitedGrid) {
        
        if (visitedGrid[i][j]) return;
        
        visitedGrid[i][j] = true;
        
        if(isNotVisited(grid, i, j+1, visitedGrid)) {
            DFS(grid, i, j+1, visitedGrid);//right
        }
        
        if(isNotVisited(grid, i+1, j, visitedGrid)) {
            DFS(grid, i+1, j, visitedGrid);//down
        }
        
        if(isNotVisited(grid, i-1, j, visitedGrid)) {
            DFS(grid, i-1, j, visitedGrid);//up
        }
        
        if(isNotVisited(grid, i, j-1, visitedGrid)) {
            DFS(grid, i, j-1, visitedGrid);//up
        }
    }
    
    public boolean isNotVisited(char[][] grid, int i, int j, boolean[][] visitedGrid) {
        int r1 = grid.length;
        int c1 = grid[0].length;
        if( (i >= 0 && i < r1) && (j>=0 && j < c1) && (grid[i][j] =='1') && !visitedGrid[i][j]) {
            return true;
        }
        
        return false;
    }
    
    public static void main(String args[]){
        
        char matrix[][] = {{'1','1','0','1','0'},
                          {'1','0','0','1','1'},
                          {'0','0','0','0','0'},
                          {'1','0','1','0','1'},
                          {'1','0','0','0','0'}
                        };
        NumberOfIsland island = new NumberOfIsland();
        int count = island.numIslands(matrix);
        System.out.println(count);
    }
}
                     
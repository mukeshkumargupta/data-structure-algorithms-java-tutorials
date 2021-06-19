package com.interview.graph;

/*
 * Reference: https://leetcode.com/problems/max-area-of-island/
 * Category: Medium
 * Related: https://leetcode.com/problems/island-perimeter/
 * https://leetcode.com/problems/largest-submatrix-with-rearrangements/
 */
public class MaxAreaofIsland {
    int currentPopulation = 0;
    private void DFS(int[][] grid, int i, int j, boolean[][] visitedGrid) {
        
        if (visitedGrid[i][j]) return;
        
        visitedGrid[i][j] = true;
        currentPopulation += 1;
        
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
    private boolean isNotVisited(int[][] grid, int i, int j, boolean[][] visitedGrid) {
        int r1 = grid.length;
        int c1 = grid[0].length;
        if( (i >= 0 && i < r1) && (j>=0 && j < c1) && (grid[i][j] == 1) && !visitedGrid[i][j]) {
            return true;
        }
        
        return false;
    }
    public int maxAreaOfIsland(int[][] grid) {
        currentPopulation = 0;
        int maximumPopulation = 0;
        int r1 = grid.length;
        if (r1 == 0) return 0;
        int c1 = grid[0].length;
        boolean[][] visitedGrid = new boolean[r1][c1];
        
        for (int  i = 0; i< r1; i++)  {
            for (int j= 0; j < c1; j++) {
                if((grid[i][j] ==1) && !visitedGrid[i][j]) {
                    currentPopulation = 0;
                    DFS(grid, i, j, visitedGrid);
                    if (currentPopulation > maximumPopulation) {
                        maximumPopulation = currentPopulation;
                    }
                    
                }
            }    
        }

        return maximumPopulation;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

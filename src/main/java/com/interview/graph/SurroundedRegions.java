package com.interview.graph;

/*
 * https://leetcode.com/problems/surrounded-regions/submissions/
 * https://www.youtube.com/watch?v=0ZJViJEdtEc
 * Category: Medium, Top150, Must Do
 * Related: https://leetcode.com/problems/walls-and-gates/ Medium
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.



Example 1:


Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
Example 2:

Input: board = [["X"]]
Output: [["X"]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
public class SurroundedRegions {
    public boolean isNotVisited(char[][] grid, int i, int j) {
        int r1 = grid.length;
        int c1 = grid[0].length;
        if( (i >= 0 && i < r1) && (j>=0 && j < c1) && (grid[i][j] =='O')) {
            return true;
        }
        
        return false;
    }
    
    public void DFS(char[][] grid, int i, int j) {

         */
        grid[i][j] = '1';
        
        if(isNotVisited(grid, i, j+1)) {
            DFS(grid, i, j+1);//right
        }
        
        if(isNotVisited(grid, i+1, j)) {
            DFS(grid, i+1, j);//down
        }
        
        if(isNotVisited(grid, i-1, j)) {
            DFS(grid, i-1, j);//up
        }
        
        if(isNotVisited(grid, i, j-1)) {
            DFS(grid, i, j-1);//up
        }
    }
    public void solve(char[][] board) {
        //Note visited is not required because you are marking as 1
        int r1 = board.length;
        if (r1 == 0) return;
        int c1 = board[0].length;
        //mark first row and last row by dfs to 1 from 0
        for (int j = 0; j < c1; j++) {
            //First row
            if (isNotVisited(board, 0, j)) {
                DFS(board, 0, j);
            }
            //Last row
            if (isNotVisited(board, r1-1, j)) {
                DFS(board, r1-1, j);
            }
        }
        
        //first column and last column
        for (int i = 1; i < r1-1; i++) {
            //First column
            if (isNotVisited(board, i, 0)) {
                DFS(board, i, 0);
            }
            //Last column
            if (isNotVisited(board, i, c1-1)) {
                DFS(board, i, c1-1);
            }
        }
        
        //Now change all 1 to 0 and 0 to X
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                if (board[i][j] == '1') {
                   board[i][j] = 'O' ;
                    
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X' ;
                }
            }
        }

        
    }
    
    public static void main(String args[]){
        SurroundedRegions fo = new SurroundedRegions();
        char board[][] = {{'X','X','X','X'},
                          {'X','O','O','X'},
                          {'X','X','O','X'},
                          {'X','O','X','X'}};
        
        fo.solve(board);
    }
}

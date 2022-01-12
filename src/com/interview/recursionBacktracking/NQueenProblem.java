package com.interview.recursionBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Date 02/20/2017
 * @author Mukesh Kumar Gupta
 *
 * Given nxn board place n queen on this board so that they dont attack each other. One solution is to find
 * any placement of queens which do not attack each other. Other solution is to find all placements of queen
 * on the board.
 *
 * Solution for https://leetcode.com/problems/n-queens/
 * Category: Hard, Must Do
 * Related: 
 * https://leetcode.com/problems/n-queens-ii/ Hard
 * https://leetcode.com/problems/grid-illumination/ Hard
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

 

Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]
 

Constraints:

1 <= n <= 9
Accepted
320,661
Submissions
581,60
 */
public class NQueenProblem {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<>();
        int leftRow[] = new int[n];
        int upperDiagonal[] = new int[2*n-1]; 
        int lowerDiagonal[] = new int[2*n-1]; 
        solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
        
        return res;
    }
    
    
    
    private void solve(int col, char[][] board, List<List<String>> res, int leftRow[], int lowerDiagonal[], int upperDiagonal[]) {
        if(col == board.length) {
            res.add(construct(board));
            return;
        }
        
        for(int row = 0; row < board.length; row++) {
            if(leftRow[row] == 0 && lowerDiagonal[row+col] == 0 && upperDiagonal[board.length -1 + col - row] == 0) {
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row+col] = 1;
                upperDiagonal[board.length-1 + col - row] = 1;
                solve(col+1, board, res, leftRow, lowerDiagonal, upperDiagonal );
                upperDiagonal[board.length - 1 + col - row] = 0;board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row+col] = 0;
                
            }
        }
    }
    
    
    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}


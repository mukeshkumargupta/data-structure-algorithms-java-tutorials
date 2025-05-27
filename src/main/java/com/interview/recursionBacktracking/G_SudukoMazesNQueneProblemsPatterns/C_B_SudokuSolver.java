package com.interview.recursionBacktracking.G_SudukoMazesNQueneProblemsPatterns;

/**
 * Date 03/24/2017
 * @author Mukesh Kumar Gupta
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * https://leetcode.com/problems/sudoku-solver/
 * Category: Hard, Must Do
 * Related: https://leetcode.com/problems/valid-sudoku/ Medium
 * https://leetcode.com/problems/unique-paths-iii/ Hard
 */
public class C_B_SudokuSolver {

    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            
                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' && 
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    public static void main(String args[]) {
        C_B_SudokuSolver ss = new C_B_SudokuSolver();
        char[][] input = new char[9][9];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                input[i][j] = '.';
            }
        }

        input[0] = "..9748...".toCharArray();
        input[1] = "7........".toCharArray();
        input[2] = ".2.1.9...".toCharArray();
        input[3] = "..7...24.".toCharArray();
        input[4] = ".64.1.59.".toCharArray();
        input[5] = ".98...3..".toCharArray();
        input[6] = "...8.3.2.".toCharArray();
        input[7] = "........6".toCharArray();
        input[8] = "...2759..".toCharArray();

        ss.solveSudoku(input);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package com.interview.recursionBacktracking.G_SudukoMazesNQueneProblemsPatterns;
import java.util.*;

/*
 * https://leetcode.com/problems/valid-sudoku/submissions/
 * https://www.youtube.com/watch?v=rJ9NFK9s_mI
 *
 * Category: Medium, Must Do, Top150, Sudoku
 *
 * Related:
 * 🔗 https://leetcode.com/problems/sudoku-solver/ (Hard)
 * https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/ Easy
 *
 * Problem Description:
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * - Each row must contain the digits 1-9 without repetition.
 * - Each column must contain the digits 1-9 without repetition.
 * - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: The 5 in the top left corner is modified to 8, causing two 8's in the top left 3x3 sub-box, making it invalid.
 *
 * Constraints:
 * - board.length == 9
 * - board[i].length == 9
 * - board[i][j] is a digit 1-9 or '.'.
 */
public class C_A_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        /*
         * Runtime: 13 ms, faster than 29.44% of Java online submissions for Valid Sudoku.
Memory Usage: 39.7 MB, less than 46.23% of Java online submissions for Valid Sudoku.
TC: O(N) where N is R*C
SC: O(N) where N is R*C
         */
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                //Check row, column and box repeated element
                if (!set.add("r" + i + board[i][j]) || !set.add("c" + j + board[i][j]) || !set.add("b" + (i/3)*3 + j/3+ board[i][j])) {
                    return false;
                }
            }
            
        }
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

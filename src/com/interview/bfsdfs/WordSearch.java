package com.interview.bfsdfs;
/*
 * Category: Medium, Top150, Must Do VVImp
 * https://www.youtube.com/watch?v=X0kX7PMOYi0&t=1145s
 * 79. Word Search
Medium

9023

345

Add to List

Share
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {
    

    public boolean exist(char[][] board, String word) {
        int r1 = board.length;
        int c1 = board[0].length;
        
        for (int  i = 0; i< r1; i++)  {
            for (int j= 0; j < c1; j++) {
                if((board[i][j] == word.charAt(0)) && DFS(board, i, j, word, 0)) {
                    return true;
                }
            }    
        }

        return false;
    }

    
    
    public boolean DFS(char[][] board, int i, int j, String word, int idx) {
        
        if (idx == word.length() -1) {
            return true;
        }
        board[i][j] += 65;//Here 65 make out of range for small and big character which lies 65 to 122, you can take more than 65 any number it will work
        
        if(isNotVisited(board, i, j+1, word, idx +1) && DFS(board, i, j+1, word, idx +1)){ //right
            return true;
        }
        
        if(isNotVisited(board, i+1, j, word, idx +1) && DFS(board, i+1, j, word, idx +1)) {//down
            return true;
        }
        
        if(isNotVisited(board, i-1, j, word, idx +1) && DFS(board, i-1, j, word, idx +1)) {//up
            return true;
        }
        
        if(isNotVisited(board, i, j-1, word, idx +1) &&  DFS(board, i, j-1, word, idx +1)) {//left
           return true;
        }
        board[i][j] -= 65;
        return false;
    }
    
 
    
    public boolean isNotVisited(char[][] board, int i, int j, String word, int idx) {
        int r1 = board.length;
        int c1 = board[0].length;
        if( (i >= 0 && i < r1) && (j>=0 && j < c1) && (board[i][j] == word.charAt(idx))) {
            return true;
        }
        
        return false;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

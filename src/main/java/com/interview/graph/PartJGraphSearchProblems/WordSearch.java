package com.interview.graph.PartJGraphSearchProblems;

/*
 * https://www.youtube.com/watch?v=X0kX7PMOYi0
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/word-search-ii/ Hard
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        /*
         * Runtime: 87 ms, faster than 89.82% of Java online submissions for Word Search.
Memory Usage: 37.3 MB, less than 58.26% of Java online submissions for Word Search.
TC: o(N) where N = R*C
TC: 4N because 4 dir going but that will be gain o(N)
SC : o(1) no visited set
         */
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
    public boolean existWithSpace(char[][] board, String word) {
        /*
         * Runtime: 97 ms, faster than 72.09% of Java online submissions for Word Search.
Memory Usage: 37.1 MB, less than 66.98% of Java online submissions for Word Search.
TC: o(N) where N = R*C
TC: 4N because 4 dir going but that will be gain o(N)
SC : o(N) because of visisted set

         */
        int r1 = board.length;
        int c1 = board[0].length;
        boolean[][] visitedGrid = new boolean[r1][c1];
        
        for (int  i = 0; i< r1; i++)  {
            for (int j= 0; j < c1; j++) {
                if((board[i][j] == word.charAt(0)) && !visitedGrid[i][j] && DFS(board, i, j, visitedGrid, word, 0)) {
                    return true;
                }
            }    
        }

        return false;
    }

    
    
    public boolean DFS(char[][] board, int i, int j, boolean[][] visitedGrid, String word, int idx) {
        
        if (idx == word.length() -1) {
            return true;
        }
        visitedGrid[i][j] = true;
        
        if(isNotVisited(board, i, j+1, visitedGrid, word, idx +1) && DFS(board, i, j+1, visitedGrid, word, idx +1)){ //right
            return true;
        }
        
        if(isNotVisited(board, i+1, j, visitedGrid, word, idx +1) && DFS(board, i+1, j, visitedGrid, word, idx +1)) {//down
            return true;
        }
        
        if(isNotVisited(board, i-1, j, visitedGrid, word, idx +1) && DFS(board, i-1, j, visitedGrid, word, idx +1)) {//up
            return true;
        }
        
        if(isNotVisited(board, i, j-1, visitedGrid, word, idx +1) &&  DFS(board, i, j-1, visitedGrid, word, idx +1)) {//left
           return true;
        }
        visitedGrid[i][j] = false;
        return false;
    }
    
 
    
    public boolean isNotVisited(char[][] board, int i, int j, boolean[][] visitedGrid, String word, int idx) {
        int r1 = board.length;
        int c1 = board[0].length;
        if( (i >= 0 && i < r1) && (j>=0 && j < c1) && (board[i][j] == word.charAt(idx)) && !visitedGrid[i][j]) {
            return true;
        }
        
        return false;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WordSearch instance = new WordSearch();
        
        char[][] board = {{'A','S','C','E'},{'S','F','C','S'},{'E','D','E','E'}};
        String word = "SEE";
        boolean result = instance.exist(board, word);
        System.out.println(result);
        
    }
    
}

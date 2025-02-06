package com.interview.recursionBacktracking;
/*
 * Category: Medium, Top150, Must Do VVImp
 * Reference: https://www.youtube.com/watch?v=X0kX7PMOYi0&t=1145s
 *
 * Problem: 79. Word Search
 * https://leetcode.com/problems/word-search/submissions/1440980892/
 * Related:
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * Given an m x n grid of characters (board) and a string (word), return true if
 * the word exists in the grid. The word can be constructed from letters of
 * sequentially adjacent cells, where adjacent cells are horizontally or vertically
 * neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 * Input:
 * board = [["A","B","C","E"],
 *           ["S","F","C","S"],
 *           ["A","D","E","E"]],
 * word = "ABCCED"
 * Output: true
 *
 * Example 2:
 * Input:
 * board = [["A","B","C","E"],
 *           ["S","F","C","S"],
 *           ["A","D","E","E"]],
 * word = "SEE"
 * Output: true
 *
 * Example 3:
 * Input:
 * board = [["A","B","C","E"],
 *           ["S","F","C","S"],
 *           ["A","D","E","E"]],
 * word = "ABCB"
 * Output: false
 *
 * Constraints:
 * - m == board.length
 * - n == board[i].length
 * - 1 <= m, n <= 6
 * - 1 <= word.length <= 15
 * - board and word consist of only lowercase and uppercase English letters.
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {

    /*
     * Solution to LeetCode's "Word Search" problem using Depth-First Search (DFS) with backtracking.
     *
     * Problem Summary:
     * Given an m x n grid (board) and a string (word), return true if the word exists in the grid.
     * The word must be constructed from sequentially adjacent cells, where each cell is either horizontally or vertically neighboring.
     * A cell cannot be reused within the same path for a single word.
     *
     * Solution Approach:
     * DFS and Backtracking:
     * 1. Start DFS from each cell in the grid, checking if the cell matches the first character of the word.
     * 2. For each cell that matches, try to find the next character by moving to adjacent cells.
     * 3. If a cell does not match, or we go out of bounds, backtrack by resetting the cell and continue with the next possibility.
     *
     * Marking Cells Temporarily:
     * - To prevent revisiting cells within the current path, mark them temporarily (e.g., by setting them to a placeholder character).
     * - Restore each cell's original value after backtracking.
     *
     * Termination:
     * - If the entire word is found, return true.
     * - If no valid path leads to the word, return false.
     *
     * Explanation:
     * exist Method:
     * - Iterates through each cell in the grid as a potential starting point.
     * - Calls the dfs method if the cell matches the first character of the word.
     *
     * dfs Method:
     * - Base Case: If index equals word.length(), the entire word has been found.
     * - Bounds and Matching: Return false if the cell is out of bounds or does not match word.charAt(index).
     * - Mark and Recurse: Temporarily mark the cell to prevent revisiting, and recursively call dfs in all four directions.
     * - Backtrack: Restore the cell's original value after exploring all paths from it.
     *
     * Time Complexity:
     * Worst Case: O(N * 3^L), where:
     * - N is the total number of cells in the grid.
     * - L is the length of the word.
     * - Each cell has up to 3 unvisited neighbors (excluding the one it came from), approximating a branching factor of 3.
     *
     * Space Complexity:
     * Auxiliary Space: O(L) for the recursion stack, where L is the length of the word.
     *
     * Key Points:
     * - This approach leverages backtracking to explore all possible paths without revisiting cells in the current path.
     * - DFS efficiently explores potential solution paths fully before backtracking.
     * - This solution is well-suited to the problem constraints and performs effectively on small to medium grid sizes.
     */

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // Check each cell as a potential starting point for DFS
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Start DFS if the first character matches
                if (board[row][col] == word.charAt(0) && dfs(board, word, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        // Base case: if the whole word is found
        if (index == word.length()) {
            return true;
        }

        // Check bounds and character match
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Temporarily mark the cell as visited
        char temp = board[row][col];
        board[row][col] = '#';

        // Explore all four possible directions
        boolean found = dfs(board, word, row + 1, col, index + 1) || // Down
                dfs(board, word, row - 1, col, index + 1) || // Up
                dfs(board, word, row, col + 1, index + 1) || // Right
                dfs(board, word, row, col - 1, index + 1);   // Left

        // Backtrack: Restore the cell's original value
        board[row][col] = temp;

        return found;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

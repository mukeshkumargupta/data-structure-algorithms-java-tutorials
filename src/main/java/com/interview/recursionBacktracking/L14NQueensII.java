package com.interview.recursionBacktracking;

import java.util.*;

/*
https://leetcode.com/problems/n-queens-ii/description/?envType=study-plan-v2&envId=top-interview-150
Category: Hard, top150,

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.



Example 1:


Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 9
 */
public class L14NQueensII {
    /*
    ✅ Time Complexity
    This is a backtracking solution, and we're trying to place one queen per column.
    At each column, we try all possible rows. But due to pruning (using leftRow, lowerDiagonal, and upperDiagonal),
    we avoid invalid placements early.

    However, in the worst case, we may still explore many permutations.

    Worst-case Time Complexity: O(N!)
    - At column 0, we have N choices.
    - At column 1, we have at most N - 1 choices (some rows are blocked due to diagonal and row conflict).
    - At column 2, we have at most N - 2 choices.
    - ...
    - So roughly, it behaves like: N × (N - 1) × (N - 2) × ... = N!

    So, in the worst case, the backtracking explores O(N!) valid queen placements.

    🔍 Note: This is a rough upper bound.
    The actual number of recursive calls will be lower because we prune branches early if a queen can’t be placed in a safe position.

    ✅ Space Complexity
    Let’s break it down:
    - leftRow         → size N
    - upperDiagonal   → size 2N - 1
    - lowerDiagonal   → size 2N - 1

    Auxiliary Space: O(N)
    - We use arrays of size at most 2N - 1.
    - The recursion stack also goes up to depth N (one queen per column), so recursive call stack space = O(N).

    ✅ Final Complexities:
    - Time Complexity: O(N!)
    - Space Complexity: O(N)
     */
    private static class WithReturn {
        public int totalNQueens(int n) {
            int[] leftRow = new int[n];
            int[] upperDiagonal = new int[2 * n - 1];
            int[] lowerDiagonal = new int[2 * n - 1];
            return countSolutions(0, n, leftRow, lowerDiagonal, upperDiagonal);
        }

        private int countSolutions(int col, int n, int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
            if (col == n) {
                return 1;
            }

            int count = 0;
            for (int row = 0; row < n; row++) {
                if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[n - 1 + col - row] == 0) {
                    leftRow[row] = 1;
                    lowerDiagonal[row + col] = 1;
                    upperDiagonal[n - 1 + col - row] = 1;

                    count += countSolutions(col + 1, n, leftRow, lowerDiagonal, upperDiagonal);

                    leftRow[row] = 0;
                    lowerDiagonal[row + col] = 0;
                    upperDiagonal[n - 1 + col - row] = 0;
                }
            }

            return count;
        }
    }

    private static class WithoutReturn {
        private int count = 0;

        public int totalNQueens(int n) {
            int[] leftRow = new int[n];
            int[] upperDiagonal = new int[2 * n - 1];
            int[] lowerDiagonal = new int[2 * n - 1];
            count = 0; // reset counter
            solve(0, n, leftRow, lowerDiagonal, upperDiagonal);
            return count;
        }

        private void solve(int col, int n, int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
            if (col == n) {
                count++;
                return;
            }

            for (int row = 0; row < n; row++) {
                if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[n - 1 + col - row] == 0) {
                    leftRow[row] = 1;
                    lowerDiagonal[row + col] = 1;
                    upperDiagonal[n - 1 + col - row] = 1;

                    solve(col + 1, n, leftRow, lowerDiagonal, upperDiagonal);

                    // backtrack
                    leftRow[row] = 0;
                    lowerDiagonal[row + col] = 0;
                    upperDiagonal[n - 1 + col - row] = 0;
                }
            }
        }
    }
/*
✅ 1. N-Queens II (Count Solutions)
Difficulty: Medium
Problem:
You are given an integer n, return the total number of distinct solutions to the n-queens puzzle.
Input: n = 4
Output: 2
Hint: Use a counter instead of a result list.
Follow-up: Can you solve it with bitmasking for space/time optimization?

✅ 2. N-Queens with Blocked Cells
Difficulty: Medium
Problem:
You are given a board of size n x n with certain cells blocked (#). Place n queens such that no two queens attack each other, and no queen is placed on a blocked cell.
Input: board = [".#..", "...#", "#...", "..#."]
Output: [["Q#..", "...Q", "#..Q", "..Q."], ...]
Hint: Skip the blocked cells during placement.

✅ 3. Unique Ways to Place k Queens on n x n Board
Difficulty: Hard
Problem:
Given n and k (k ≤ n), return the number of valid configurations to place k queens on the board so they don’t attack each other.
Input: n = 4, k = 2
Output: 6
Hint: Try a backtracking approach with column counter up to k instead of n.

✅ 4. Count All Placements for n Queens on m x m Board
Difficulty: Hard
Problem:
Given m and n (n ≤ m), find how many distinct ways you can place n queens on an m x m board without attacking each other.
Input: m = 5, n = 3
Output: X
Hint: Extend N-Queen approach to partial board coverage.

✅ 5. N-Queens – Return One Valid Configuration
Difficulty: Easy
Problem:
Return any one valid configuration of placing n queens on an n x n board.
Input: n = 4
Output: [".Q..", "...Q", "Q...", "..Q."]
Hint: Break recursion once one valid config is found.

✅ 6. N-Queens with Maximum Distance Between Queens
Difficulty: Hard
Problem:
Return the configuration that maximizes the total pairwise Manhattan distance between queens.
Input: n = 4
Output: [[...]]
Hint: After getting all valid solutions, post-process to calculate distances.

✅ 7. N-Queens with Knight Blocking Constraint
Difficulty: Hard
Problem:
Queens cannot be placed such that any two queens can reach each other with a knight’s move.
Input: n = 4
Output: ["...Q", ".Q..", "....", "Q..."]
Hint: Check knight move positions during placement.

✅ 8. Valid N-Queens Configuration Validator
Difficulty: Easy
Problem:
Given a configuration of n-queens (as a list of strings), check if it is a valid placement.
Input: ["..Q.", "Q...", "...Q", ".Q.."]
Output: true
Hint: Traverse and track row and diagonals.

✅ 9. Minimum Number of Queens to Cover All Squares
Difficulty: Hard
Problem:
Find the minimum number of queens required to cover (attack) all squares on an n x n board.
Input: n = 4
Output: 5
Hint: Try greedy or backtracking to minimize placement.

✅ 10. Visualize N-Queens as Bitmask (Optimization Version)
Difficulty: Expert
Problem:
Solve the N-Queens problem using bitmasking to improve performance.
Input: n = 12
Output: Number of solutions
Hint: Use three bit masks: columns, diagonals, anti-diagonals.
*/

    /*
    ✅ 1. N-Queens II (Count Solutions)
    Difficulty: Medium
    Problem:
    You are given an integer n, return the total number of distinct solutions to the n-queens puzzle.
    Input: n = 4
    Output: 2
    Hint: Use a counter instead of a result list.
    Time Complexity: O(N!)
    Space Complexity: O(N)
     */
    public class NQueensII {
        public int totalNQueens(int n) {
            int[] leftRow = new int[n];
            int[] lowerDiagonal = new int[2 * n - 1];
            int[] upperDiagonal = new int[2 * n - 1];
            return countSolutions(0, n, leftRow, lowerDiagonal, upperDiagonal);
        }

        private int countSolutions(int col, int n, int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
            if (col == n) return 1;
            int count = 0;
            for (int row = 0; row < n; row++) {
                if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[n - 1 + col - row] == 0) {
                    leftRow[row] = lowerDiagonal[row + col] = upperDiagonal[n - 1 + col - row] = 1;
                    count += countSolutions(col + 1, n, leftRow, lowerDiagonal, upperDiagonal);
                    leftRow[row] = lowerDiagonal[row + col] = upperDiagonal[n - 1 + col - row] = 0;
                }
            }
            return count;
        }
    }

    /*
    ✅ 2. N-Queens with Blocked Cells
        Difficulty: Medium
        Problem:
        You are given a board of size n x n with certain cells blocked (#). Place n queens such that no two queens attack each other, and no queen is placed on a blocked cell.
        Input:
        board = [".#..", "...#", "#...", "..#."]
        Output: [[“Q#..”, “...Q”, “#..Q”, “..Q.”], ...]
        Time Complexity: O(N!) worst case
        Space Complexity: O(N)
     */
    public class NQueensWithBlocked {
        public List<List<String>> solveNQueens(char[][] board) {
            int n = board.length;
            List<List<String>> res = new ArrayList<>();
            int[] leftRow = new int[n];
            int[] lowerDiagonal = new int[2 * n - 1];
            int[] upperDiagonal = new int[2 * n - 1];
            solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
            return res;
        }

        private void solve(int col, char[][] board, List<List<String>> res,
                           int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {
            int n = board.length;
            if (col == n) {
                List<String> temp = new ArrayList<>();
                for (char[] row : board) temp.add(new String(row));
                res.add(temp);
                return;
            }

            for (int row = 0; row < n; row++) {
                if (board[row][col] == '#' || leftRow[row] == 1 || lowerDiagonal[row + col] == 1 || upperDiagonal[n - 1 + col - row] == 1)
                    continue;

                board[row][col] = 'Q';
                leftRow[row] = lowerDiagonal[row + col] = upperDiagonal[n - 1 + col - row] = 1;

                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);

                board[row][col] = '.';
                leftRow[row] = lowerDiagonal[row + col] = upperDiagonal[n - 1 + col - row] = 0;
            }
        }
    }

    /*
    ✅ 3. Unique Ways to Place k Queens on n x n Board
Difficulty: Hard
Problem:
Given n and k (k ≤ n), return the number of valid configurations to place k queens on the board so they don’t attack each other.
Input: n = 4, k = 2
Output: 6
Time Complexity: O(2^N * N)
Space Complexity: O(N)


     */

    public class KQueens {
        public int placeKQueens(int n, int k) {
            return solve(0, n, k, new int[n], new int[2 * n - 1], new int[2 * n - 1], 0);
        }

        private int solve(int col, int n, int k,
                          int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal,
                          int placed) {
            if (placed == k) return 1;
            if (col == n) return 0;

            int count = 0;
            for (int row = 0; row < n; row++) {
                if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[n - 1 + col - row] == 0) {
                    leftRow[row] = lowerDiagonal[row + col] = upperDiagonal[n - 1 + col - row] = 1;
                    count += solve(col + 1, n, k, leftRow, lowerDiagonal, upperDiagonal, placed + 1);
                    leftRow[row] = lowerDiagonal[row + col] = upperDiagonal[n - 1 + col - row] = 0;
                }
            }
            // Option to skip placing a queen in this column
            count += solve(col + 1, n, k, leftRow, lowerDiagonal, upperDiagonal, placed);

            return count;
        }
    }

    /*
    ✅ 4. Count All Placements for n Queens on m x m Board
    Difficulty: Hard
    Problem:
    Given m and n (n ≤ m), find how many distinct ways you can place n queens on an m x m board without attacking each other.
    Input: m = 5, n = 3
    Output: X
    Hint: Extend N-Queen approach to partial board coverage.
    Time Complexity: O(2^m * m)
Space Complexity: O(m)
     */

    public class NQueensOnMxMBoard {
        public int countPlacements(int m, int n) {
            return dfs(0, m, n, new int[m], new int[2 * m - 1], new int[2 * m - 1], 0);
        }

        private int dfs(int col, int m, int n,
                        int[] rowUsed, int[] ld, int[] rd, int placed) {
            if (placed == n) return 1;
            if (col == m) return 0;

            int count = 0;
            for (int row = 0; row < m; row++) {
                if (rowUsed[row] == 0 && ld[row + col] == 0 && rd[m - 1 + col - row] == 0) {
                    rowUsed[row] = ld[row + col] = rd[m - 1 + col - row] = 1;
                    count += dfs(col + 1, m, n, rowUsed, ld, rd, placed + 1);
                    rowUsed[row] = ld[row + col] = rd[m - 1 + col - row] = 0;
                }
            }
            count += dfs(col + 1, m, n, rowUsed, ld, rd, placed); // skip current col
            return count;
        }
    }

    /*
    ✅ 5. N-Queens – Return One Valid Configuration
Difficulty: Easy
Problem:
Return any one valid configuration of placing n queens on an n x n board.
Input: n = 4
Output: [“.Q..”, “...Q”, “Q...”, “..Q.”]
Time Complexity: O(N!)
Space Complexity: O(N^2)
     */
    public class NQueensOneSolution {
        public List<String> solveNQueens(int n) {
            char[][] board = new char[n][n];
            for (char[] row : board) Arrays.fill(row, '.');
            List<String> res = new ArrayList<>();
            backtrack(0, board, res, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
            return res;
        }

        private boolean backtrack(int col, char[][] board, List<String> res,
                                  boolean[] row, boolean[] ld, boolean[] rd) {
            int n = board.length;
            if (col == n) {
                for (char[] r : board) res.add(new String(r));
                return true;
            }

            for (int r = 0; r < n; r++) {
                if (!row[r] && !ld[r + col] && !rd[n - 1 + col - r]) {
                    board[r][col] = 'Q';
                    row[r] = ld[r + col] = rd[n - 1 + col - r] = true;

                    if (backtrack(col + 1, board, res, row, ld, rd)) return true;

                    board[r][col] = '.';
                    row[r] = ld[r + col] = rd[n - 1 + col - r] = false;
                }
            }
            return false;
        }
    }

    /*
    ✅ 6. N-Queens with Maximum Distance Between Queens
Difficulty: Hard
Problem:
Return the configuration that maximizes the total pairwise Manhattan distance between queens.
Input: n = 4
Output: [[...]]
Time Complexity: O(N!)
Space Complexity: O(N^2)
     */

    public class NQueensMaxDistance {
        int maxDist = -1;
        List<String> bestConfig;

        public List<String> solve(int n) {
            char[][] board = new char[n][n];
            for (char[] row : board) Arrays.fill(row, '.');
            dfs(0, board, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], new ArrayList<>());
            return bestConfig;
        }

        private void dfs(int col, char[][] board, boolean[] row, boolean[] ld, boolean[] rd, List<int[]> pos) {
            int n = board.length;
            if (col == n) {
                int dist = calcDist(pos);
                if (dist > maxDist) {
                    maxDist = dist;
                    bestConfig = new ArrayList<>();
                    for (char[] r : board) bestConfig.add(new String(r));
                }
                return;
            }

            for (int r = 0; r < n; r++) {
                if (!row[r] && !ld[r + col] && !rd[n - 1 + col - r]) {
                    board[r][col] = 'Q';
                    row[r] = ld[r + col] = rd[n - 1 + col - r] = true;
                    pos.add(new int[]{r, col});

                    dfs(col + 1, board, row, ld, rd, pos);

                    pos.remove(pos.size() - 1);
                    board[r][col] = '.';
                    row[r] = ld[r + col] = rd[n - 1 + col - r] = false;
                }
            }
        }

        private int calcDist(List<int[]> pos) {
            int dist = 0;
            for (int i = 0; i < pos.size(); i++) {
                for (int j = i + 1; j < pos.size(); j++) {
                    dist += Math.abs(pos.get(i)[0] - pos.get(j)[0]) + Math.abs(pos.get(i)[1] - pos.get(j)[1]);
                }
            }
            return dist;
        }
    }

    /*
    ✅ 7. N-Queens with Knight Blocking Constraint
Difficulty: Hard
Problem:
Queens cannot be placed such that any two queens can reach each other with a knight’s move.
Input: n = 4
Output: [[“...Q”, “.Q..”, “....”, “Q...”]]
Time Complexity: O(N!)
Space Complexity: O(N^2)
     */

    public class NQueensKnightConstraint {
        int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}};

        public List<List<String>> solve(int n) {
            List<List<String>> res = new ArrayList<>();
            char[][] board = new char[n][n];
            for (char[] r : board) Arrays.fill(r, '.');
            backtrack(0, board, res, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
            return res;
        }

        private void backtrack(int col, char[][] board, List<List<String>> res,
                               boolean[] row, boolean[] ld, boolean[] rd) {
            int n = board.length;
            if (col == n) {
                List<String> temp = new ArrayList<>();
                for (char[] r : board) temp.add(new String(r));
                res.add(temp);
                return;
            }

            for (int r = 0; r < n; r++) {
                if (row[r] || ld[r + col] || rd[n - 1 + col - r] || violatesKnight(board, r, col)) continue;

                board[r][col] = 'Q';
                row[r] = ld[r + col] = rd[n - 1 + col - r] = true;

                backtrack(col + 1, board, res, row, ld, rd);

                board[r][col] = '.';
                row[r] = ld[r + col] = rd[n - 1 + col - r] = false;
            }
        }

        private boolean violatesKnight(char[][] board, int r, int c) {
            int n = board.length;
            for (int[] move : knightMoves) {
                int nr = r + move[0];
                int nc = c + move[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == 'Q') return true;
            }
            return false;
        }
    }

    /*
    ✅ 8. Valid N-Queens Configuration Validator
Difficulty: Easy
Problem:
Given a board configuration, determine if it is a valid N-Queens solution.
Input:

java
Copy
Edit
[ ".Q..",
  "...Q",
  "Q...",
  "..Q." ]
Output: true
Time Complexity: O(N^2)
Space Complexity: O(N)
     */
    public class NQueensValidator {
        public boolean isValidNQueens(List<String> board) {
            int n = board.size();
            Set<Integer> cols = new HashSet<>();
            Set<Integer> diag1 = new HashSet<>();
            Set<Integer> diag2 = new HashSet<>();
            int queenCount = 0;

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (board.get(r).charAt(c) == 'Q') {
                        if (!cols.add(c) || !diag1.add(r - c) || !diag2.add(r + c)) return false;
                        queenCount++;
                    }
                }
            }
            return queenCount == n;
        }
    }

    /*
    ✅ 9. Minimum Number of Queens to Cover All Squares
Difficulty: Hard
Problem:
Find the minimum number of queens needed to attack or cover every square on an n x n board.
Input: n = 5
Output: 5
Hint: Brute-force all queen placements and select smallest cover set.
Time Complexity: O(2^(N^2)) (brute-force)
Space Complexity: O(N^2)
     */

    public class MinimumQueensToCover {
        private int minQueens = Integer.MAX_VALUE;

        public int minQueensToCover(int n) {
            boolean[][] attacked = new boolean[n][n];
            dfs(0, 0, n, attacked, 0);
            return minQueens;
        }

        private void dfs(int r, int c, int n, boolean[][] attacked, int placed) {
            if (allCovered(attacked)) {
                minQueens = Math.min(minQueens, placed);
                return;
            }
            if (r == n) return;

            int nr = c == n - 1 ? r + 1 : r;
            int nc = c == n - 1 ? 0 : c + 1;

            dfs(nr, nc, n, attacked, placed); // skip

            boolean[][] copy = deepCopy(attacked);
            markAttack(copy, r, c);
            dfs(nr, nc, n, copy, placed + 1);
        }

        private void markAttack(boolean[][] attacked, int r, int c) {
            int n = attacked.length;
            for (int i = 0; i < n; i++) {
                attacked[r][i] = true;
                attacked[i][c] = true;
            }
            for (int i = -n; i < n; i++) {
                if (inBounds(r + i, c + i, n)) attacked[r + i][c + i] = true;
                if (inBounds(r + i, c - i, n)) attacked[r + i][c - i] = true;
            }
        }

        private boolean allCovered(boolean[][] attacked) {
            for (boolean[] row : attacked) {
                for (boolean cell : row) {
                    if (!cell) return false;
                }
            }
            return true;
        }

        private boolean inBounds(int r, int c, int n) {
            return r >= 0 && r < n && c >= 0 && c < n;
        }

        private boolean[][] deepCopy(boolean[][] grid) {
            int n = grid.length;
            boolean[][] copy = new boolean[n][n];
            for (int i = 0; i < n; i++)
                copy[i] = Arrays.copyOf(grid[i], n);
            return copy;
        }
    }

    /*
    ✅ 10. Visualize N-Queens with Bitmask Optimization
Difficulty: Medium/Hard
Problem:
Use bitmasks to solve the N-Queens problem efficiently. Return total count of valid configurations.
Input: n = 8
Output: 92
Time Complexity: O(N!) but faster due to bit operations
Space Complexity: O(1) (excluding recursion stack)
     */

    public class NQueensBitmask {
        private int count = 0;

        public int totalNQueens(int n) {
            solve(0, 0, 0, 0, n);
            return count;
        }

        private void solve(int row, int cols, int diags1, int diags2, int n) {
            if (row == n) {
                count++;
                return;
            }

            int available = ((1 << n) - 1) & ~(cols | diags1 | diags2);
            while (available != 0) {
                int pos = available & -available;
                available -= pos;
                solve(row + 1,
                        cols | pos,
                        (diags1 | pos) << 1,
                        (diags2 | pos) >> 1,
                        n);
            }
        }
    }
}

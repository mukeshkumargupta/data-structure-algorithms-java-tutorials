package com.interview.dynamic;

import java.util.Arrays;
import java.util.List;

public class PartDTwoDDPArrayProblems {
    /*
     * Reference: https://leetcode.com/problems/unique-paths/
     * https://www.youtube.com/watch?v=sdE0A2Oxofw
     * https://www.youtube.com/watch?v=rBAxUTqvlQA&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=8
     * Category: Medium, Must Do, Top150
     * Related:https://leetcode.com/problems/unique-paths-ii/ Medium
     * https://leetcode.com/problems/dungeon-game/ Hard
     * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/ Medium
     */

    public static class TotalPath {
        /* Without memoization TC:O( 2 power  (M*N))
        after memoization
        Time Complexity: O(M*N)

Reason: At max, there will be M*N calls of recursion.

Space Complexity: O((N-1)+(M-1)) + O(M*N)

Reason: We are using a recursion stack space: O((N-1)+(M-1)), here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.

         */
        public int solveUtilRecursive(int i, int j, int[][] dp) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 40.9 MB, less than 5.23% of Java online submissions for Unique Paths.
         */
            if (i == 0 && j == 0) {
                return 1;
            }

            if (i < 0 || j < 0) {
                return 0;
            }

            if (dp[i][j] != -1) {
                return dp[i][j];
            }


            int up = 0;
            int left = 0;
            if (i > 0) {
                up = solveUtilRecursive(i - 1, j, dp);
            }
            if (j > 0) {
                left = solveUtilRecursive(i, j - 1, dp);
            }
            return dp[i][j] = up + left;
        }

        public int solveRecursive(int m, int n) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 40.9 MB, less than 5.23% of Java online submissions for Unique Paths.
         */
            // Create a 2D DP array to store the results
            int[][] dp = new int[m][n];

            // Initialize the DP array with -1 to indicate uncomputed values
            for (int[] row : dp)
                Arrays.fill(row, -1);

            // Start the recursive calculation from the bottom-right cell (m-1, n-1)
            return solveUtilRecursive(m - 1, n - 1, dp);
        }

        /*
        Time Complexity: O(M*N)

        Reason: There are two nested loops

        Space Complexity: O(M*N)

        Reason: We are using an external array of size ‘M*N’’.
         */
        public int solveBottomUp(int m, int n) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
            // Create a 2D DP array to store the results
            int dp[][] = new int[m][n];

            // Initialize the DP array with -1 to indicate uncomputed values
            for (int[] row : dp)
                Arrays.fill(row, -1);

            // Loop through each cell in the grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // Base condition: If we are at the top-left cell (0, 0), there's one way to reach it.
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                        continue;
                    }

                    int up = 0;
                    int left = 0;

                    // Calculate the number of ways by moving up (if possible) and left (if possible)
                    if (i > 0)
                        up = dp[i - 1][j];
                    if (j > 0)
                        left = dp[i][j - 1];

                    // Store the total number of ways to reach the current cell in the DP array
                    dp[i][j] = up + left;
                }
            }

            // Return the number of ways to reach the bottom-right cell (m-1, n-1)
            return dp[m - 1][n - 1];

        }

        /*
        Time Complexity: O(M*N)

Reason: There are two nested loops

Space Complexity: O(N)

Reason: We are using an external array of size ‘N’ to store only one row.
Write your own version of memoization
         */
        public int solveBottomUpSpaceOptimized(int m, int n) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
            // Create an array to store the results for the previous row
            int prev[] = new int[n];

            for (int i = 0; i < m; i++) {
                // Create a temporary array to store the results for the current row
                int temp[] = new int[n];

                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) {
                        // Base condition: There's one way to reach the top-left cell (0, 0)
                        temp[j] = 1;
                        continue;
                    }

                    int up = 0;
                    int left = 0;

                    // Calculate the number of ways by moving up (if possible) and left (if possible)
                    if (i > 0)
                        up = prev[j];
                    if (j > 0)
                        left = temp[j - 1];

                    // Store the total number of ways to reach the current cell in the temporary array
                    temp[j] = up + left;
                }

                // Set the temporary array as the previous array for the next row
                prev = temp;
            }
            // Return the number of ways to reach the bottom-right cell (m-1, n-1)
            return prev[n - 1];
        }
    }

    /*
 * https://leetcode.com/problems/unique-paths-ii/
 * https://www.youtube.com/watch?v=TmhpgXScLyY&t=196s
 * Category: Medium, Must Do, next version of unique path https://leetcode.com/problems/unique-paths/
 * https://leetcode.com/problems/unique-paths-ii/
 * https://www.youtube.com/watch?v=TmhpgXScLyY&t=196s
 * Category: Medium, Must Do, next version of unique path https://leetcode.com/problems/unique-paths/
 * Related: https://leetcode.com/problems/unique-paths-iii/ Hard
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * Related: https://leetcode.com/problems/unique-paths-iii/ Hard
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.



Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1


Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
 */
    public static class UniquePathsII {
        /*
        Time Complexity: O(N*M)

Reason: At max, there will be N*M calls of recursion.

Space Complexity: O((M-1)+(N-1)) + O(N*M)

Reason: We are using a recursion stack space:O((M-1)+(N-1)), here (M-1)+(N-1) is the path length and an external DP Array of size ‘N*M’.
         */
        private int uniquePathsWithObstaclesUtilRecursive(int i, int j, int[][] obstacleGrid, int[][] dp) {
        /*Runtime: 7 ms, faster than 6.83% of Java online submissions for Unique Paths II.
        Memory Usage: 40 MB, less than 6.73% of Java online submissions for Unique Paths II.
        */
            if (obstacleGrid[i][j] ==1) {
                return 0;
            } else if (i == 0 && j == 0) {
                return 1;
            }

            if (i < 0 || j < 0) {
                return 0;
            }

            if (dp[i][j] != 0) {
                return dp[i][j];
            }


            int up = 0;
            int left = 0;
            if (i > 0) {
                up =  uniquePathsWithObstaclesUtilRecursive(i-1, j, obstacleGrid, dp);
            }
            if (j > 0) {
                left =  uniquePathsWithObstaclesUtilRecursive(i, j-1, obstacleGrid, dp);
            }
            dp[i][j] = up + left;

            return dp[i][j];

        }
        public int uniquePathsWithObstaclesRecursionPlusMemoization(int[][] obstacleGrid) {
            int R = obstacleGrid.length;
            int C = obstacleGrid[0].length;
            int[][] dp = new int[R][C];
            // Initialize the DP array with -1 to indicate uncomputed values
            for (int[] row : dp)
                Arrays.fill(row, 0);

            return uniquePathsWithObstaclesUtilRecursive(R-1, C-1, obstacleGrid, dp);

        }
        public int uniquePathsWithObstaclesBottomUp(int[][] obstacleGrid) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
Memory Usage: 40.2 MB, less than 6.73% of Java online submissions for Unique Paths II.
         */
            int R = obstacleGrid.length;
            int C = obstacleGrid[0].length;
            int[][] dp = new int[R][C];

            for(int i=0; i < R; i++){
                for(int j=0; j < C; j++){
                    if (obstacleGrid[i][j] ==1) {
                        dp[i][j] = 0;
                    } else if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else {
                        int up = 0;
                        int left = 0;
                        if (i > 0) {
                            up =  dp[i-1][j];
                        }
                        if (j > 0) {
                            left =  dp[i][j-1];
                        }
                        dp[i][j] = up + left;
                    }
                }
            }
            return dp[R-1][C-1];

        }

    /*
    Time Complexity: O(N*M)

    Reason: There are two nested loops

    Space Complexity: O(N*M)

    Reason: We are using an external array of size ‘N*M’’.
     */
        public int uniquePathsWithObstaclesSpaceOptimization(int[][] obstacleGrid) {
        /*
         * Runtime: 1 ms, faster than 48.03% of Java online submissions for Unique Paths II.
Memory Usage: 42.6 MB, less than 6.73% of Java online submissions for Unique Paths II.
         */
            int R = obstacleGrid.length;
            int C = obstacleGrid[0].length;

            int[] prev = null;
            for(int i=0; i < R; i++){
                int[] current = new int[C];
                for(int j=0; j < C; j++){
                    if (obstacleGrid[i][j] ==1) {
                        current[j] = 0;
                    } else if (i == 0 && j == 0) {
                        current[j] = 1;
                    } else {
                        int up = 0;
                        int left = 0;
                        if (i > 0) {
                            up =  prev[j];
                        }
                        if (j > 0) {
                            left = current[j-1];
                        }
                        current[j] = up + left;
                    }
                }
                prev = current;
            }
            return prev[C-1];

        }
        public static void main(String[] args) {
            // Define the maze
            int[][] maze = {
                    {0, 0, 0},
                    {0, 1, 0},
                    {0, 0, 0}
            };


            UniquePathsII instance = new UniquePathsII();

            // Calculate and print the number of paths through the maze
            System.out.println(instance.uniquePathsWithObstaclesSpaceOptimization(maze));
            System.out.println(instance.uniquePathsWithObstaclesBottomUp(maze));
            System.out.println(instance.uniquePathsWithObstaclesSpaceOptimization(maze));

        }

    }

    /**
     * https://leetcode.com/problems/minimum-path-sum/
     * https://www.youtube.com/watch?v=_rgTlyky1uQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=11
     * Category: Medium
     * Status: Tricky
     */
    public class MinCostPath {

        /*
        Time Complexity: O(N*M)

    Reason: At max, there will be N*M calls of recursion.

    Space Complexity: O((M-1)+(N-1)) + O(N*M)

    Reason: We are using a recursion stack space: O((M-1)+(N-1)), here (M-1)+(N-1) is the path length and an external DP Array of size ‘N*M’.
         */
        // Helper function to calculate the minimum sum path in the matrix
        //Derive question find max path sum , two changes could be not run but it sud be dp[i][j] = grid[i][j] + Math.max(up, left); and base case if (i < 0 || j < 0)
        //                return Integer.MIN_VALUE;
        static int minPathSumUtil(int i, int j, int[][] grid, int[][] dp) {
            // Base cases
            if (i == 0 && j == 0)
                return grid[0][0]; // If we're at the top-left cell, return its value
            if (i < 0 || j < 0)
                return Integer.MAX_VALUE; // If we're out of bounds, return a large value
            if (dp[i][j] != -1)
                return dp[i][j]; // If we've already calculated this cell, return the stored result

            // Calculate the sum of the current cell plus the minimum sum path from above and from the left
            int up = minPathSumUtil(i - 1, j, grid, dp);
            int left = minPathSumUtil(i, j - 1, grid, dp);

            // Store the minimum of the two possible paths
            return dp[i][j] = grid[i][j] + Math.min(up, left);
        }

        // Main function to calculate the minimum sum path in the matrix
        static int minPathSum(int[][] grid) {
            int R = grid.length;
            int C = grid[0].length;
            int dp[][] = new int[R][C];

            // Initialize the DP matrix with -1
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the helper function starting from the bottom-right cell
            return minPathSumUtil(R - 1, C - 1, grid, dp);
        }

        public int minPathSumTabulation(int[][] grid) {
            int R = grid.length; //Row
            int C = grid[0].length; //Column
            int i, j;
            int dp[][] = new int[R][C];
            dp[0][0] = grid[0][0];

            /* Initialize first column of total cost(dp) array */
            for (i = 1; i < R; i++)
                dp[i][0] = dp[i-1][0] + grid[i][0];

            /* Initialize first row of dp array */
            for (j = 1; j < C; j++)
                dp[0][j] = dp[0][j-1] + grid[0][j];

            /* Construct rest of the dp array */
            for (i = 1; i < R; i++)
                for (j = 1; j < C; j++)
                    dp[i][j] = Math.min(
                            dp[i-1][j],
                            dp[i][j-1]) + grid[i][j];

            return dp[R-1][C-1];
        }

        public static void main(String args[]) {
            // Define the matrix
            int matrix[][] = {
                    {5, 9, 6},
                    {11, 5, 2}
            };

            // Calculate and print the minimum sum path in the matrix
            System.out.println(minPathSum(matrix));
        }

    }

    /*
 * https://leetcode.com/problems/triangle/
 * https://www.youtube.com/watch?v=SrP-PiLSYC0&t=17s
 * Category: Medium, VImp, Tricky
 * Related:https://leetcode.com/problems/array-transformation/ Easy
 * https://leetcode.com/problems/count-all-possible-routes/ Hard
 * https://leetcode.com/problems/minimum-total-space-wasted-with-k-resizing-operations/ Medium
 *
 * Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.



Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10


Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104


Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
Accepted
387,651
Submissions
776,668
 */
    public static class TriangleFixStartingVariableEnding {
        /*
        Complexity Analysis
Time Complexity: O(N*N)

Reason: There are two nested loops

Space Complexity: O(N*N)

Reason: We are using an external array of size ‘N*N’. The stack space will be eliminated.
         */
        //Here starting point is fixed but ending is not fixed so we need to start from 0 and reach to last row
        public int minimumTotalUtil(List<List<Integer>> triangle, int i, int j, int[][] dp) {
            if (i == triangle.size() - 1) {
                return triangle.get(i).get(j);
            }

            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            //int d = triangle.get(i).get(j) + minimumTotalUtil(triangle, i + 1, j, dp);
            //int dg = triangle.get(i).get(j) + minimumTotalUtil(triangle, i + 1, j + 1, dp);
            //return dp[i][j] = Math.min(d, dg);
            //Better code
            int d = minimumTotalUtil(triangle, i + 1, j, dp);
            int dg = minimumTotalUtil(triangle, i + 1, j + 1, dp);
            return dp[i][j] = triangle.get(i).get(j) + Math.min(d, dg);

        }

        public int minimumTotalRecursive(List<List<Integer>> triangle) {
            /*
             * TC: o(n2) since n cell and each cell two option so without memoization 2 power(n2) but with memoization just n sqaure cell you need to visit(most precise n sqware /2)
             * SC: O(R) for recursion stack + o(R*C) for memoization
             * TLE
             */
            int R = triangle.size();
            int[][] dp = new int[R][R];
            for (int row[] : dp)
                Arrays.fill(row, -1);
            return minimumTotalUtil(triangle, 0, 0, dp);
        }


        /*
        Complexity Analysis
Time Complexity: O(N*N)

Reason: There are two nested loops

Space Complexity: O(N*N)

Reason: We are using an external array of size ‘N*N’. The stack space will be eliminated.
         */
        public int minimumTotalTabulation(List<List<Integer>> triangle) {
            /*
             * TC: o(R*C)
             * SC: o(R*C) for dp array
             */
            int R = triangle.size();
            int C = R;
            int[][] dp = new int[R][C];
            for (int j = 0; j < C; j++) {
                dp[R - 1][j] = triangle.get(R - 1).get(j);

            }

            //now try from R-2
            for (int i = R - 2; i >= 0; i--) {
                for (int j = i; j >= 0; j--) {
                    //int d=  triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1, j, dp);
                    //int dg =  triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1, j+1, dp);
                    //return dp[i][j] = Math.min(d, dg);
                    int d = dp[i + 1][j];
                    int dg = dp[i + 1][j + 1];
                    dp[i][j] = triangle.get(i).get(j) + Math.min(d, dg);
                }

            }
            return dp[0][0];
        }

        /*
         * Further optimized by space
         */
        public int minimumTotal(List<List<Integer>> triangle) {
            int R = triangle.size();
            int C = R;
            int[] prev = new int[C];

            for (int j = 0; j < C; j++) {
                //dp[R-1][j] = triangle.get(R-1).get(j);
                prev[j] = triangle.get(R - 1).get(j);
            }

            //now try from R-2
            for (int i = R - 2; i >= 0; i--) {
                int[] curr = new int[C];
                for (int j = i; j >= 0; j--) {

                    //int d =  triangle.get(i).get(j) + dp[i+1][j];
                    //int dg =  triangle.get(i).get(j) + dp[i+1][j+1];
                    int d = prev[j];
                    int dg = prev[j + 1];
                    //dp[i][j] = Math.min(d, dg);
                    curr[j] = triangle.get(i).get(j) + Math.min(d, dg);
                }
                prev = curr;

            }
            return prev[0];
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }

    }

    /*
    https://www.youtube.com/watch?v=N_aJ5qQbYA0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=13
    Time Complexity: O(N*N)

Reason: At max, there will be M*N calls of recursion to solve a new problem,

Space Complexity: O(N) + O(N*M)

Reason: We are using a recursion stack space: O(N), where N is the path length and an external DP Array of size ‘N*M’.
     */
    public static class MaximumFallingPathSumVariableStartingandEndingPoints {
        static int getMaxUtil(int i, int j, int C, int[][] matrix, int[][] dp) {
            // Base Conditions
            if (j < 0 || j >= C)
                return (int) Math.pow(-10, 9);//This is constrant so it will not overflow -10^4 <= matrix[i][j] <= 10^4 max 10 power -8 it can go so take less than that
            if (i == 0)
                return matrix[0][j];

            if (dp[i][j] != -1)
                return dp[i][j];

            // Calculate three possible paths: moving up, left diagonal, and right diagonal
            int up =  getMaxUtil(i - 1, j, C, matrix, dp);
            int leftDiagonal = getMaxUtil(i - 1, j - 1, C, matrix, dp);
            int rightDiagonal = getMaxUtil(i - 1, j + 1, C, matrix, dp);

            // Store the maximum of the three paths in dp
            return dp[i][j] = matrix[i][j] + Math.max(up, Math.max(leftDiagonal, rightDiagonal));
        }

        // Function to find the maximum path sum in the matrix
        static int getMaxPathSum(int[][] matrix) {
            int R = matrix.length;
            int C = matrix[0].length;

            int dp[][] = new int[R][C];
            for (int row[] : dp)
                Arrays.fill(row, -1);

            int maxi = Integer.MIN_VALUE;

            // For each starting column, find the maximum path sum and update maxi
            for (int j = 0; j < C; j++) {
                int ans = getMaxUtil(R - 1, j, C, matrix, dp);
                maxi = Math.max(maxi, ans);
            }

            return maxi;
        }

        /*
        Time Complexity: O(N*M)

        Reason: There are two nested loops

        Space Complexity: O(N*M)

        Reason: We are using an external array of size ‘N*M’. The stack space will be eliminated.
         */
        // Function to find the maximum path sum in the matrix using dynamic programming
        static int getMaxPathSumTabulation(int[][] matrix) {
            int R = matrix.length;
            int C = matrix[0].length;

            int dp[][] = new int[R][C];

            // Initializing the first row - base condition
            for (int j = 0; j < C; j++) {
                dp[0][j] = matrix[0][j];
            }

            // Calculate the maximum path sum for each cell in the matrix
            for (int i = 1; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int up = dp[i - 1][j];

                    int leftDiagonal = (int) Math.pow(-10, 9);
                    if (j - 1 >= 0) {
                        leftDiagonal = dp[i - 1][j - 1];
                    }

                    int rightDiagonal = (int) Math.pow(-10, 9);
                    if (j + 1 < C) {
                        rightDiagonal = dp[i - 1][j + 1];
                    }

                    // Store the maximum of the three paths in dp
                    dp[i][j] = matrix[i][j] + Math.max(up, Math.max(leftDiagonal, rightDiagonal));
                }
            }

            // Find the maximum value in the last row of dp
            int maxi = Integer.MIN_VALUE;
            for (int j = 0; j < C; j++) {
                maxi = Math.max(maxi, dp[R - 1][j]);
            }

            return maxi;
        }

        /*
        Space optimized and working on codeninza platform
         */
        public static int getMaxPathSumSpaceOptimized(int[][] matrix) {
            // Write your code here
            int R = matrix.length;
            int C = matrix[0].length;
            int[] prev = null;
            int[] current = new int[C];

            for(int i=0; i < C; i++){ //Starting can be from any first row
                current[i] = matrix[0][i];
            }
            prev = current;

            for(int i=1; i < R; i++){
                current = new int[C];
                for(int j=0; j < C; j++){
                    int up = prev[j];//dp[i-1][j];
                    int ld = (int) Math.pow(-10, 9);
                    if (j-1 >=0) {
                        ld = prev[j-1];//dp[i-1][j-1];
                    }
                    int rd = (int) Math.pow(-10, 9);
                    if (j+1 <= C-1) {
                        rd = prev[j+1];//dp[i-1][j+1];
                    }
                    //dp[i][j] = matrix[i][j] + Math.max(up, Math.max(ld, rd));
                    current[j] = matrix[i][j] + Math.max(up, Math.max(ld, rd));
                }
                prev = current;
            }

            int maxSum = Integer.MIN_VALUE;
            for(int i=0; i < C; i++){ //Ending can from any last row
                //maxSum = Math.max(maxSum, dp[R-1][i]);
                maxSum = Math.max(maxSum, current[i]);
            }
            return maxSum;
        }


        public static void main(String args[]) {
            int matrix[][] = {{1, 2, 10, 4},
                    {100, 3, 2, 1},
                    {1, 1, 20, 2},
                    {1, 2, 2, 1}};

            // Call the getMaxPathSum function and print the result
            System.out.println(getMaxPathSum(matrix));
        }
    }
}

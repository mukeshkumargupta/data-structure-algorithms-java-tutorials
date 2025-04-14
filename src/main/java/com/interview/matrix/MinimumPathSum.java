package com.interview.matrix;

import java.util.*;
/*
 * https://leetcode.com/problems/minimum-path-sum/description/
 *
 * Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
 * Other variant question Find all path, then find path then start from bottom right and go to 0,0
 * Reference: https://www.youtube.com/watch?v=lBRtnuxg-gU
 * Note: If it asked path direction then start from R-1 C_1
 * Category: Tricky, Medium
 * Related:
 * https://leetcode.com/problems/unique-paths/ Medium
 * https://leetcode.com/problems/dungeon-game/ Hard
 * https://leetcode.com/problems/cherry-pickup/ Hard
 * https://leetcode.com/problems/minimum-path-cost-in-a-grid/ Medium
 * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/ Medium
 * https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/ Hard
 * https://leetcode.com/problems/check-if-there-is-a-path-with-equal-number-of-0s-and-1s/ Medium
 * https://leetcode.com/problems/minimum-cost-of-a-path-with-special-roads/ Medium
 * Status: Done
 * */
public class MinimumPathSum {
    
    /* A utility function that returns minimum of 3 integers */
    private static int min(int x, int y) 
    { 
        if (x <y ) {
            return x;
        } else {
            return y;
        }
    } 
    
    private static int min(int x, int y, int z) // Will be used in variant question 
    { 
       return min(min(x, y), z);
    } 
  
    
    public int minPathSum(int[][] grid) {
        int m = grid.length; //Row
        int n = grid[0].length; //Column
        int i, j; 
        int tc[][] = new int[m][n]; 
        tc[0][0] = grid[0][0]; 
        
        /* Initialize first column of total cost(tc) array */
        for (i = 1; i < m; i++) 
            tc[i][0] = tc[i-1][0] + grid[i][0]; 
  
        /* Initialize first row of tc array */
        for (j = 1; j < n; j++) 
            tc[0][j] = tc[0][j-1] + grid[0][j]; 
  
        /* Construct rest of the tc array */
        for (i = 1; i < m; i++) 
            for (j = 1; j < n; j++) 
                tc[i][j] = min(
                               tc[i-1][j], 
                               tc[i][j-1]) + grid[i][j]; 
  
        return tc[m-1][n-1]; 
    }

    /*
     * 🚀 Approach: Dynamic Programming with Path Reconstruction
     *
     * 🔹 Idea:
     * - Use Dynamic Programming (DP) to compute the minimum path sum.
     * - Store parent pointers to reconstruct the path.
     * - Start from the bottom-right and trace back to the top-left.
     *
     * 🔹 Explanation:
     * 1️⃣ DP Table Calculation:
     *    - dp[i][j] stores the minimum sum to reach (i, j).
     *    - parent[i][j] stores where we came from:
     *      → 1 = Left
     *      → 2 = Up
     *
     * 2️⃣ Backtracking:
     *    - Start from the bottom-right (m-1, n-1).
     *    - Move left or up based on the parent matrix.
     *    - Store the path and reverse it at the end.
     */
    public List<int[]> minPathSumWithPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; // DP array to store min path sums
        int[][] parent = new int[m][n]; // Stores direction for backtracking

        // Initialize DP
        dp[0][0] = grid[0][0];

        // Fill first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
            parent[0][j] = 1; // Left
        }

        // Fill first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            parent[i][0] = 2; // Up
        }

        // Fill rest of DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                    parent[i][j] = 2; // Up
                } else {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                    parent[i][j] = 1; // Left
                }
            }
        }

        // Backtrack to find path
        List<int[]> path = new ArrayList<>();
        int i = m - 1, j = n - 1;

        while (i >= 0 && j >= 0) {
            path.add(new int[]{i, j});
            if (i == 0 && j == 0) break; // Stop at start

            if (parent[i][j] == 1) {
                j--; // Move left
            } else {
                i--; // Move up
            }
        }

        Collections.reverse(path);
        return path;
    }


    
    //Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
    public int minPathSum_V1(int[][] grid) {
        int m = grid.length; //Row
        int n = grid[0].length; //Column
        int i, j; 
        int tc[][] = new int[m][n]; 
        tc[0][0] = grid[0][0]; 
        
        /* Initialize first column of total cost(tc) array */
        for (i = 1; i < m; i++) 
            tc[i][0] = tc[i-1][0] + grid[i][0]; 
  
        /* Initialize first row of tc array */
        for (j = 1; j < n; j++) 
            tc[0][j] = tc[0][j-1] + grid[0][j]; 
  
        /* Construct rest of the tc array */
        for (i = 1; i < m; i++) 
            for (j = 1; j < n; j++) 
                tc[i][j] = min(
                               tc[i-1][j], 
                               tc[i][j-1], tc[i-1][j-1]) + grid[i][j]; 
  
        return tc[m-1][n-1]; 
    }
    
    public List<Integer> minPathSumDirection(int[][] grid) {
        int m = grid.length; //Row
        int n = grid[0].length; //Column
        int i, j; 
        int tc[][] = new int[m][n]; 
        tc[0][0] = grid[0][0]; 
        
        /* Initialize first column of total cost(tc) array */
        for (i = 1; i < m; i++) 
            tc[i][0] = tc[i-1][0] + grid[i][0]; 
  
        /* Initialize first row of tc array */
        for (j = 1; j < n; j++) 
            tc[0][j] = tc[0][j-1] + grid[0][j]; 
  
        /* Construct rest of the tc array */
        for (i = 1; i < m; i++) 
            for (j = 1; j < n; j++) 
                tc[i][j] = min(
                               tc[i-1][j], 
                               tc[i][j-1]) + grid[i][j]; 
        
        //Find path
        List<Integer> pathDirection = new ArrayList<Integer>();
        pathDirection.add(grid[m-1][n-1]);
        //Then see from where it came from total cost matrix
        i = m-1;
        j = n-1;
        //need to implement
        while(true) {
            //If both same then take minimum and then add it
            if (i-1 >= 0 && j-1 >= 0) {
                //pathDirection.add
            }
            break;
        }
        return pathDirection;
    }
    
    public static void main(String[] args) {
        /*int cost[][]= {{1, 2, 3}, 
                {4, 8, 2}, 
                {1, 5, 3}};*/
        
        int cost[][]= {{1, 2, 3}, 
                {1, 8, 2}, 
                {1, 3, 3}}; //Output should be 9 whil ecome from top to botton while in botton to top u go output will come 11 so be careful.
        MinimumPathSum mps = new MinimumPathSum();
        System.out.println(mps.minPathSum(cost)); 
        System.out.println(mps.minPathSum_V1(cost)); //Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
        
        
    }

    private static class MinPathSumWithDiagonal {
        public static List<int[]> minPathSumWithDiagonal(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];
            int[][] parent = new int[m][n]; // Stores direction (1: left, 2: up, 3: diagonal)

            // Initialize DP table
            dp[0][0] = grid[0][0];

            // Fill first row (can only come from left)
            for (int j = 1; j < n; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
                parent[0][j] = 1; // Came from left
            }

            // Fill first column (can only come from top)
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
                parent[i][0] = 2; // Came from above
            }

            // Fill the rest of the table considering left, top, and diagonal
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    int fromLeft = dp[i][j - 1];
                    int fromUp = dp[i - 1][j];
                    int fromDiagonal = dp[i - 1][j - 1];

                    // Choose the minimum path sum and track parent
                    if (fromLeft <= fromUp && fromLeft <= fromDiagonal) {
                        dp[i][j] = fromLeft + grid[i][j];
                        parent[i][j] = 1; // Came from left
                    } else if (fromUp <= fromDiagonal) {
                        dp[i][j] = fromUp + grid[i][j];
                        parent[i][j] = 2; // Came from above
                    } else {
                        dp[i][j] = fromDiagonal + grid[i][j];
                        parent[i][j] = 3; // Came from diagonal
                    }
                }
            }

            // Backtrack to construct the path
            List<int[]> path = new ArrayList<>();
            int i = m - 1, j = n - 1;
            while (i >= 0 && j >= 0) {
                path.add(new int[]{i, j});
                if (parent[i][j] == 1) {
                    j--; // Move left
                } else if (parent[i][j] == 2) {
                    i--; // Move up
                } else if (parent[i][j] == 3) {
                    i--;
                    j--; // Move diagonally
                } else {
                    break; // Reached (0,0)
                }
            }
            Collections.reverse(path);
            return path;
        }

        public static void main(String[] args) {
            int[][] grid = {
                    {1, 3, 1},
                    {1, 5, 1},
                    {4, 2, 1}
            };

            List<int[]> path = minPathSumWithDiagonal(grid);
            for (int[] p : path) {
                System.out.println(Arrays.toString(p));
            }
        }
    }
    
}

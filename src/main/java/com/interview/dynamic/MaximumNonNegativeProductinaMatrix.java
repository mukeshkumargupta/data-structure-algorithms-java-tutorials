package com.interview.dynamic;

/*
 * https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
 * Category: Medium, Tricky
 * Try to solve memoization and bfs
 * bfs way: https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/discuss/1485624/java-or-bfs
 * Related: https://leetcode.com/problems/best-meeting-point/ Hard Locked
 * https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/ Medium VImp
 * https://leetcode.com/problems/design-browser-history/ Medium VVImp
 * You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.

Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.

Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.

Notice that the modulo is performed after getting the maximum product.

 

Example 1:


Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
Output: -1
Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
Example 2:


Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
Output: 8
Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).
Example 3:


Input: grid = [[1,3],[0,-4]]
Output: 0
Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
-4 <= grid[i][j] <= 4
 * 
 */
public class MaximumNonNegativeProductinaMatrix {
    class Pair
    {
        long min, max;//min is min and max is max
        Pair(long min, long max)
        {
            this.min = min;
            this.max = max;
        }
    }
    
    int mod = (int)(1e9+7);
    public int maxProductPath(int[][] grid) 
    {
        int R = grid.length;
        int C = grid[0].length;
        Pair[][] dp = new Pair[R][C];
        
        dp[0][0] = new Pair(grid[0][0], grid[0][0]);
        for(int i=1;i<C;i++)
        {
            grid[0][i] = grid[0][i]*grid[0][i-1];
            
            dp[0][i] = new Pair(grid[0][i], grid[0][i]);
        }
        
        for(int i=1;i<R;i++)
        {
            grid[i][0] = grid[i][0]*grid[i-1][0];
            
            dp[i][0] = new Pair(grid[i][0], grid[i][0]);
        }
        
        for(int i=1;i<R;i++)
        {
            for(int j=1;j<C;j++)
            {
                long min1 = Math.min(dp[i-1][j].min, dp[i][j-1].min);
                long max1 = Math.max(dp[i-1][j].max, dp[i][j-1].max);
                if(grid[i][j]>0)
                {
                    long min2 = min1 * grid[i][j];
                    long max2 = max1 * grid[i][j];
                    dp[i][j] = new Pair(min2, max2);
                }
                else
                {
                    long min2 = max1 * grid[i][j];
                    long max2 = min1 * grid[i][j];
                    dp[i][j] = new Pair(min2, max2);
                }
            }
        }
        if(dp[R-1][C-1].max < 0)
            return -1;
        
        return (int)((dp[R-1][C-1].max)%mod);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

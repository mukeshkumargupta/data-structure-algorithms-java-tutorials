package com.interview.dynamic;

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
public class Triangle {
    public int minimumTotalUtil(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        if (i == triangle.size() -1) {
           return triangle.get(i).get(j);
        }
        
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int d=  triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1, j, dp);
        int dg =  triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1, j+1, dp);
        return dp[i][j] = Math.min(d, dg);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        /*
         * TC: o(n2) since n cell and each cell two option so without memoization 2 power(n2) but with memoization just n sqaure cell you need to visit(most precise n sqware /2)
         * SC: O(R) for recursion stack + o(R*C) for memoization
         * TLE
         */
        int R = triangle.size();
        int[][] dp = new int[R][R];
        return minimumTotalUtil(triangle, 0, 0, dp);
    }
    
    
    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        /*
         * TC: o(R*C)
         * SC: o(R*C) for dp array
         */
        int R = triangle.size();
        int C = R;
        int[][] dp = new int[R][C];
        for (int j = 0; j < C; j++) {
            dp[R-1][j] = triangle.get(R-1).get(j);
            
        }
        
        //now try from R-2
        for (int i = R-2; i >=0; i--) {
            for (int j = i; j >=0; j--) {
                //int d=  triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1, j, dp);
                //int dg =  triangle.get(i).get(j) + minimumTotalUtil(triangle, i+1, j+1, dp);
                //return dp[i][j] = Math.min(d, dg);
                int d =  triangle.get(i).get(j) + dp[i+1][j];
                int dg =  triangle.get(i).get(j) + dp[i+1][j+1];
                dp[i][j] = Math.min(d, dg);
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
        int[][] dp = new int[R][C];
        int[] prev = new int[C];
        
        for (int j = 0; j < C; j++) {
            //dp[R-1][j] = triangle.get(R-1).get(j);
            prev[j] = triangle.get(R-1).get(j); 
        }
        
        //now try from R-2
        for (int i = R-2; i >=0; i--) {
            int[] curr = new int[C];
            for (int j = i; j >=0; j--) {
                
                //int d =  triangle.get(i).get(j) + dp[i+1][j];
                //int dg =  triangle.get(i).get(j) + dp[i+1][j+1];
                int d =  triangle.get(i).get(j) + prev[j];
                int dg =  triangle.get(i).get(j) + prev[j+1];
                //dp[i][j] = Math.min(d, dg);
                curr[j] = Math.min(d, dg);
            }
            prev = curr;
            
        }
        return prev[0];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

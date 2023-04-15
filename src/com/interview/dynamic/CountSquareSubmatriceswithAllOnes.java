package com.interview.dynamic;

/*
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 * Category: Medium, Tricky, same as finding max size of square
 * Related:
 * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/ Medium, VImp
 * https://leetcode.com/problems/count-fertile-pyramids-in-a-land/ Hard, VVImp
 * 
 * 1277. Count Square Submatrices with All Ones
Medium

3274

54

Add to List

Share
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.
 

Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
Accepted
161,929
Submissions
218,262
 */
public class CountSquareSubmatriceswithAllOnes {
    public int countSquares(int[][] matrix) {
        /*
         * Runtime: 8 ms, faster than 51.14% of Java online submissions for Count Square Submatrices with All Ones.
Memory Usage: 69.9 MB, less than 41.31% of Java online submissions for Count Square Submatrices with All Ones.
         */
                
        int R = matrix.length;
        int C = matrix[0].length;
        int[][] dp = new int[R][C];
        //copy first row
       
        for (int j = 0 ; j < C; j++) {
           dp[0][j] = matrix[0][j];
        }
         //copy first column
        for (int i = 0 ; i < R; i++) {
           dp[i][0] = matrix[i][0];
        }

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                   dp[i][j] =  Math.min(Math.min(dp[i][j-1],  dp[i-1][j]), dp[i-1][j-1]) + 1;
                }
            }
        }
        
        int sum = 0;
        for (int i = 0 ; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

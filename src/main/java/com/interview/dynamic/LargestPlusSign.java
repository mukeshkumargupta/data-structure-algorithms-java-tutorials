package com.interview.dynamic;

/*
 * https://leetcode.com/problems/largest-plus-sign/
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/hand-of-straights/ Medium Good Question
 * https://leetcode.com/problems/dice-roll-simulation/ Hard Imp
 * https://leetcode.com/problems/finding-pairs-with-a-certain-sum/ Medium Try it
 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.

Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.

An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.

 

Example 1:


Input: n = 5, mines = [[4,2]]
Output: 2
Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
Example 2:


Input: n = 1, mines = [[0,0]]
Output: 0
Explanation: There is no plus sign, so return 0.
 

Constraints:

1 <= n <= 500
1 <= mines.length <= 5000
0 <= xi, yi < n
All the pairs (xi, yi) are unique.
 */
public class LargestPlusSign {
    public int orderOfLargestPlusSignSlow(int n, int[][] mines) {
        /*
         * Runtime: 1291 ms, faster than 5.11% of Java online submissions for Largest
         * Plus Sign. Memory Usage: 54.2 MB, less than 37.38% of Java online submissions
         * for Largest Plus Sign. TC(n3) SC(n2)
         */
        int[][] input = new int[n][n];
        int R = n;
        int C = n;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                input[i][j] = 1;
            }
        }
        
        for (int[] mine : mines) {
            input[mine[0]][mine[1]] = 0;
            
        }
        int maxOrder = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int leftCount = 0;
                int rightCount = 0;
                int topCount = 0;
                int bottomCount = 0;
                
                int k = j;
                while (k >= 0 && input[i][k] == 1) {
                    leftCount++;
                    k--;
                    
                }
                
                k = j;
                while (k < C && input[i][k] == 1) {
                    rightCount++;
                    k++;
                }
                
                k = i;
                while (k >= 0 && input[k][j] == 1) {
                    topCount++;
                    k--;
                }
                k = i;
                while (k < R && input[k][j] == 1) {
                    bottomCount++;
                    k++;
                }
                maxOrder = Math.max(maxOrder,
                        Math.min(Math.min(leftCount, rightCount), Math.min(topCount, bottomCount)));
            }
        }
        return maxOrder;
        
    }
    
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        /*
         * TC: O(N2)
         * SC (O(N2)
         * Runtime: 75 ms, faster than 64.86% of Java online submissions for Largest Plus Sign.
Memory Usage: 70.5 MB, less than 29.39% of Java online submissions for Largest Plus Sign.
         */
        int[][] input = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                input[i][j] = 1;
            }
        }
        
        for (int[] mine : mines) {
            input[mine[0]][mine[1]] = 0;
            
        }
        
        int[][] L = new int[N][N];
        int[][] R = new int[N][N];
        for (int i = 0; i < N; i++) {
            L[i][0] = input[i][0];
            for (int j = 1; j < N; j++) {
                L[i][j] = input[i][j] == 1 ? L[i][j - 1] + 1 : 0;
                
            }
            
            R[i][N - 1] = input[i][N - 1];
            for (int j = N - 2; j >= 0; j--)
                R[i][j] = input[i][j] == 1 ? R[i][j + 1] + 1 : 0;
        }
        
        int[][] T = new int[N][N];
        int[][] B = new int[N][N];
        
        for (int j = 0; j < N; j++) {
            B[0][j] = input[0][j];
            for (int i = 1; i < N; i++)
                B[i][j] = input[i][j] == 1 ? B[i - 1][j] + 1 : 0;
            
            T[N - 1][j] = input[N - 1][j];
            for (int i = N - 2; i >= 0; i--)
                T[i][j] = input[i][j] == 1 ? T[i + 1][j] + 1 : 0;
        }
        
        int maxOrder = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                maxOrder = Math.max(maxOrder, Math.min(Math.min(L[i][j], R[i][j]), Math.min(T[i][j], B[i][j])));
            
        return maxOrder;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

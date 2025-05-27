package com.interview.stackqueue.C_NGEPatterns;

import java.util.Stack;

/*
 * https://leetcode.com/problems/maximal-rectangle/
 * https://www.youtube.com/watch?v=g8bSdXCG-lA
 * Category: Hard, Must Do
 * Related: https://leetcode.com/problems/odd-even-jump/ Hard
 * https://leetcode.com/problems/strange-printer-ii/ Hard
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/ Medium
 * https://leetcode.com/problems/minimum-common-value/description/ Easy
 * https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/description/ Medium
 * https://leetcode.com/problems/toeplitz-matrix/description/ Easy

 * 
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = []
Output: 0
Example 3:

Input: matrix = [["0"]]
Output: 0
Example 4:

Input: matrix = [["1"]]
Output: 1
Example 5:

Input: matrix = [["0","0"]]
Output: 0
 

Constraints:

rows == matrix.length
cols == matrix[i].length
0 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
 */
public class PartF_H_MaximalRectangle {
    /*
            TC: (M*N + N * 2M)
        SC: O(N*M ) + O(N)
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        // Calculate left array
        for (int i = 0; i < n; i++) {
            // Find elements greater than arr[i] on the left
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int elm = stack.peek();
                int nse = i;
                stack.pop();
                int pse = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, heights[elm] * (nse - pse -1));
            }

            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int elm = stack.peek();
            int nse = n;
            stack.pop();
            int pse = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, heights[elm] * (nse - pse -1));
        }
        return max;

    }
    public int maximalRectangle(char[][] matrix) {
        /*
         * Related: Runtime: 12 ms, faster than 42.38% of Java online submissions for Maximal Rectangle.
Memory Usage: 41.9 MB, less than 76.26% of Java online submissions for Maximal Rectangle.
TC: O(R*C), here here time of C is histogram complexity
SC O(C)
         */
        int R = matrix.length;
        if (R == 0) {
            return 0;
        }
        int C = matrix[0].length;
        int temp[] = new int[C];
        int maxArea = 0;
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < C; j++){
                int val = matrix[i][j] - '0';
                if(val == 0){
                    temp[j] = 0;
                }else{
                    temp[j] += val;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(temp));

        }
        return maxArea;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

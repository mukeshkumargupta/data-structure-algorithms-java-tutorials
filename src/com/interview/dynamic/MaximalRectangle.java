package com.interview.dynamic;

/*
 * https://leetcode.com/problems/maximal-rectangle/
 * https://www.youtube.com/watch?v=g8bSdXCG-lA
 * Category: Hard, Must Do
 * Related: https://leetcode.com/problems/odd-even-jump/ Hard
 * https://leetcode.com/problems/strange-printer-ii/ Hard
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/ Medium

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
public class MaximalRectangle {
    public int largestRectangleArea(int[] heights) {
        /*
         * Runtime: 46 ms, faster than 41.59% of Java online submissions for Largest Rectangle in Histogram.
Memory Usage: 48.3 MB, less than 88.09% of Java online submissions for Largest Rectangle in Histogram.
         */
        int l = heights.length;
        Stack<Integer> st = new Stack<>();
        int[] leftSmall = new int[l];
        int[] rightSmall = new int[l];
        for (int i = 0; i < l; i++) {
            while (!st.empty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            
            if (st.empty()) {
               leftSmall[i] = 0;
            } else {
                leftSmall[i] = st.peek() + 1;//since we are travelling from left so next small boundry will be one more
            } 
            st.push(i);
        }
        /*for (int i = 0; i < l; i++) {
            System.out.println(leftSmall[i]);
        }*/
        
        //clear the stack to be resued
        while (!st.empty()) {
            st.pop();
        }
        //System.out.println("size: " + st.size());
        for (int i = l-1; i >= 0; i--) {
            while (!st.empty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            
            if (st.empty()) {
               rightSmall[i] = l-1;
            } else {
                rightSmall[i] = st.peek() - 1;//since we are travelling from right, so next small boundry will be one less
            } 
            st.push(i);
        }
        /*for (int i = 0; i < l; i++) {
            System.out.println(rightSmall[i]);
        }*/
        int max = 0;
        for (int i = 0; i < l; i++) {
            max = Math.max(max, heights[i]*(rightSmall[i] - leftSmall[i] + 1));
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

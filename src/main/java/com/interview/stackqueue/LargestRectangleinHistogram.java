package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * https://www.youtube.com/watch?v=X0X6G-eWgQ8
 * Category: Hard, Must Do, Top150
 * Related: https://leetcode.com/problems/maximal-rectangle/ Hard
 * https://leetcode.com/problems/maximum-score-of-a-good-subarray/ Hard
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
Accepted
418,527
Submissions
1,072,135
 */
public class LargestRectangleinHistogram {
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
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = {2,1,5,6,2,3};
        LargestRectangleinHistogram instance = new LargestRectangleinHistogram();
        instance.largestRectangleArea(input);
        
    }
    
}

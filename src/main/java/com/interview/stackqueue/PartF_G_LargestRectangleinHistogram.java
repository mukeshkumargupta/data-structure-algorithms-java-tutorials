package com.interview.stackqueue;

import java.util.Stack;
/*
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * https://www.youtube.com/watch?v=Bzat9vgD0fs&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=12
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
public class PartF_G_LargestRectangleinHistogram {

    /*
    TC: 5N
    SC: 4N
     */
    public static class LargestRectangleinHistogramBruitforce {
        // Function to find the Next Smaller Element (right bounds)
        int[] findNSE(int[] arr) {
            int n = arr.length;
            int[] right = new int[n];
            Stack<Integer> stack = new Stack<>();

            // Calculate right array
            for (int i = n - 1; i >= 0; i--) {
                // Find elements greater than or equal to arr[i] on the right
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                // If stack is empty, it means there are no smaller elements to the right
                right[i] = stack.isEmpty() ? n : stack.peek();
                stack.push(i);
            }
            return right;
        }

        // Function to find the Previous Smaller Element (left bounds)
        int[] findPSE(int[] arr) {
            int n = arr.length;
            int[] left = new int[n];
            Stack<Integer> stack = new Stack<>();

            // Calculate left array
            for (int i = 0; i < n; i++) {
                // Find elements greater than arr[i] on the left
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                // If stack is empty, it means there are no smaller elements to the left
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            return left;
        }

        public int largestRectangleArea(int[] heights) {
            int[] nse = findNSE(heights);
            int[] pse = findPSE(heights);
            int max = 0;
            for (int i = 0; i < heights.length; i++) {
                max = Math.max(max, heights[i]*(nse[i] - pse[i] - 1));
            }
            return max;

        }

    }

    public static class LargestRectangleinHistogramOptimal {

        /*
        TC: (2N)
        SC: (N)
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

    }
}



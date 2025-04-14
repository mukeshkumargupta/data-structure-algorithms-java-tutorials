package com.interview.matrix;

import java.util.*;

/*
 * Problem: Minimum Steps in Infinite Grid
 * Source: https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
 * Category: Easy, Tricky
 * Video Explanation: https://www.youtube.com/watch?v=ZNh35dFqklM&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=7
 *
 * Problem Description:
 * --------------------
 * You are in an infinite 2D grid where you can move in any of the 8 directions:
 *
 * (x, y) →
 *     (x-1, y-1), (x-1, y), (x-1, y+1),
 *     (x  , y-1),         (x  , y+1),
 *     (x+1, y-1), (x+1, y), (x+1, y+1)
 *
 * You are given a sequence of points and the order in which you need to cover them.
 * Find the minimum number of steps required to achieve it. You start from the first point.
 *
 * NOTE: This question is intentionally left slightly vague.
 *       Clarify the question by trying out a few cases in the "See Expected Output" section.
 *
 * Input Format:
 * -------------
 * Given two integer arrays A and B:
 * - A[i] represents the x-coordinate of the i-th point.
 * - B[i] represents the y-coordinate of the i-th point.
 *
 * Output Format:
 * --------------
 * Return an integer, representing the minimum number of steps.
 *
 * Example Input:
 * --------------
 * Input 1:
 * A = [0, 1, 1]
 * B = [0, 1, 2]
 *
 * Example Output:
 * ---------------
 * Output 1:
 * 2
 *
 * Explanation:
 * ------------
 * Given three points: (0, 0), (1, 1), and (1, 2).
 * - It takes 1 step to move from (0, 0) to (1, 1).
 * - It takes 1 more step to move from (1, 1) to (1, 2).
 *
 * Total steps required: 2.
 *
 * Note:
 * -----
 * - You only need to implement the given function.
 * - Do not read input; instead, use the function arguments.
 * - Do not print the output; instead, return the result as specified.
 * - Still have doubts? Check out sample codes for more details.
 */
public class MinimumStepsInInfiniteGrid {
    int stepRequired(int x1, int y1, int x2, int y2) {
        int xDiff = Math.abs(x1 -x2);
        int yDiff = Math.abs(y1 -y2);
        return Math.max(xDiff, yDiff);

    }
    public int coverPoints(ArrayList<Integer> A, ArrayList<Integer> B) {
        int l = A.size();
        int totalStep = 0;
        for (int i = 0; i < l-1; i++) {
            totalStep += stepRequired(A.get(i), B.get(i), A.get(i+1), B.get(i+1));
        }
        return totalStep;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

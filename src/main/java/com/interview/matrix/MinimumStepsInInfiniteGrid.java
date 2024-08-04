package com.interview.matrix;

import java.util.*;

/*
 * https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
 * Category: easy, Tricky
 * https://www.youtube.com/watch?v=ZNh35dFqklM&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=7
 * 
 * Problem Description

You are in an infinite 2D 

 where you can move in any of the 8 directions

 (x,y) to 
    (x-1, y-1), 
    (x-1, y)  , 
    (x-1, y+1), 
    (x  , y-1),
    (x  , y+1), 
    (x+1, y-1), 
    (x+1, y)  , 
    (x+1, y+1) 
You are given a sequence of points and the order in which you need to cover the points.. Give the minimum number of steps in which you can achieve it. You start from the first point.

NOTE: This question is intentionally left slightly vague. Clarify the question by trying out a few cases in the “See Expected Output” section.



Input Format
Given two integer arrays A and B, where A[i] is x coordinate and B[i] is y coordinate of ith point respectively.



Output Format
Return an Integer, i.e minimum number of steps.



Example Input
Input 1:

 A = [0, 1, 1]
 B = [0, 1, 2]


Example Output
Output 1:

 2


Example Explanation
Explanation 1:

 Given three points are: (0, 0), (1, 1) and (1, 2).
 It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).


Note:You only need to implement the given function. Do not read input, instead use the arguments to the function. Do not print the output, instead return values as specified. Still have a doubt? Checkout Sample Codes for more details.
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

package com.interview.matrix;

import java.util.*;

/*
 * https://www.interviewbit.com/problems/min-steps-in-infinite-grid/
 * Category: easy, Tricky
 * https://www.youtube.com/watch?v=ZNh35dFqklM&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=7
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

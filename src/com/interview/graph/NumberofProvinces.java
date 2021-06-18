package com.interview.graph;

/* 
 * Reference: https://leetcode.com/problems/number-of-provinces/
 * Category: Medium
 */
public class NumberofProvinces {
    public int findCircleNum(int[][] M) {
        int numberOfCircle = 0;
        int r1 = M[0].length;
        boolean[] visitedSet = new boolean[r1];
        for (int i = 0; i < r1; i++) {
            if (!visitedSet[i]) {
                visitedSet[i] = true;
                numberOfCircle += 1;
                DFS(M, i, visitedSet);
            }

        }

        return numberOfCircle;
    }

    public void DFS(int[][] M, int i, boolean[] visitedSet) {
        for (int j = 0; j < M[0].length; j++) {
            if ((i != j) && (M[i][j] == 1) && !visitedSet[j]) {
                visitedSet[j] = true;
                DFS(M, j, visitedSet);
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

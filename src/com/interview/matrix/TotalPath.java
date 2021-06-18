package com.interview.matrix;

/*
 * Reference: https://leetcode.com/problems/unique-paths/
 * Category: Medium
 */

public class TotalPath {
    public int uniquePaths(int m, int n) {
        int M = m;
        int N = n;
        if (M == 1 || N == 1) {
            return 1;
        }
        int output[][] = new int[M][N];
        // fill first row
        for (int c1 = 0; c1 < N; c1++) {
            output[0][c1] = 1;
        }
        // fill first column
        for (int r1 = 0; r1 < M; r1++) {
            output[r1][0] = 1;
        }
        // Fill matrix
        // Calculate
        for (int r1 = 1; r1 < M; r1++) {
            for (int c1 = 1; c1 < N; c1++) {
                output[r1][c1] = output[r1 - 1][c1] + output[r1][c1 - 1];
            }
        }
        return output[M - 1][N - 1];
        
    }
    
}

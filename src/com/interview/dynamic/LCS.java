package com.interview.dynamic;
//Reference: 
//https://practice.geeksforgeeks.org/problems/longest-common-substring/0
//Reference: https://leetcode.com/problems/longest-common-subsequence/submissions/
//Must Do

public class LCS {
    public int longestCommonSubsequence(String text1, String text2) {
        int C = text1.length()+1;
        int R = text2.length()+1;
        int outputMatrix[][] = new int[R][C];
        //Fill first row with all zero
        for (int c1 = 0; c1 < C; c1++ ) {
            outputMatrix[0][c1] = 0;
        }
        //Fill first column with all zero
        for (int r1 = 0; r1 < R; r1++ ) {
            outputMatrix[r1][0] = 0;
        }
        int findMax = 0;
        for (int i = 1; i < R; i++) {
            for (int j = 1 ; j < C; j++) {
                if (text1.charAt(j-1) == text2.charAt(i-1)) {// If both are same
                    outputMatrix[i][j] = outputMatrix[i-1][j-1] +1; 

                } else {
                    outputMatrix[i][j] = Math.max(outputMatrix[i-1][j], outputMatrix[i][j-1]);  
                }
                 if (findMax < outputMatrix[i][j]) {
                    findMax = outputMatrix[i][j];  
                 }
                
            }
        }
        return findMax;
    }
    
    public static void main(String[] args) {
    	LCS lcs = new LCS();
    	int result = lcs.longestCommonSubsequence("abcde", "ace");
    	System.out.println(result);
    }
}

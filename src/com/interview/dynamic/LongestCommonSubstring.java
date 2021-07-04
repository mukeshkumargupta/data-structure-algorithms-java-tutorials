package com.interview.dynamic;

/**
 * http://en.wikipedia.org/wiki/Longest_common_substring_problem
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 * Variant: https://leetcode.com/problems/longest-duplicate-substring/ hint if i j is same then fill 0 otherwise apply LCS but if length is hish then matrix will be more and will not work
 * Soln: https://www.youtube.com/watch?v=FQ8hcOOzQMU Category: Hard
 */
public class LongestCommonSubstring {

    /**
     * Dynamic way of calculating lcs
     */
    public int findLength(int[] nums1, int[] nums2) {
        int C = nums1.length+1;
        int R = nums2.length+1;
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
                if (nums1[j-1] == nums2[i-1]) {// If both are same
                    outputMatrix[i][j] = outputMatrix[i-1][j-1] +1; 

                } else {
                    outputMatrix[i][j] = 0;
                }
                 if (findMax < outputMatrix[i][j]) {
                    findMax = outputMatrix[i][j];  
                 }
                
            }
        }
        return findMax;
    }

    public static void main(String args[]){
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        char str1[] = "abcdef".toCharArray();
        char str2[] = "zcdemf".toCharArray();
        System.out.println(lcs.longestCommonSubstring(str1, str2));
        System.out.println(lcs.longestCommonSubstringRec(str1, str2,str1.length-1, str2.length-1,false));
    }
    
}

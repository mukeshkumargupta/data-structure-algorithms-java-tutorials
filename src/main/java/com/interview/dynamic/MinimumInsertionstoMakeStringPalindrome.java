package com.interview.dynamic;

/*
 * DP 29. Minimum Insertions to Make String Palindrome
 * https://www.youtube.com/watch?v=xPBLEj41rFU
 * Category: Medium, Application of LongestPalindromicSubsequence
 * https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbDFieWtuVmxRUFU2OEl5NnZ4bEpjalNyamVLQXxBQ3Jtc0ttOEdZZGJ3YzYtWDdYQVNqa2IzRE1LdXluWnp1LWlMOXRoTkRrSlpleUMtblBsYVRzOU4wbWtWMHNrTUFyWjFoZWxjUTVqX2xGcVZLXzNvYmtQTk9kZG5MX1hVcWhGcThYbllzWDN2X2xLNEtVNFZfbw&q=https%3A%2F%2Fbit.ly%2F3H2ZtGP
 */
public class MinimumInsertionstoMakeStringPalindrome {
    public static int minInsertion(String str) {
        // Write your code here.
        String s = str;
        int length = s.length();
        int[][] dp = new int [length][length];
        
        //for one length
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        
        //for two length
        for (int i = 0; i < length -1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
               dp[i][i+1] = 2; 
            } else {
                dp[i][i+1] = 1; 
            }
        }
        
        // Check for lengths greater than 2.
        // k is length of substring
        for (int k = 3; k <= length; ++k) {
 
            // Fix the starting index
            for (int i = 0; i < length - k + 1; ++i) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;
 
                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] =  dp[i+1][j-1] +2;
                } else {
                   dp[i][j] =  Math.max(dp[i][j-1], dp[i+1][j]); 
                }
            }
        }
        return s.length() - dp[0][length -1];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

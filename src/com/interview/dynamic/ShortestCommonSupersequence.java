package com.interview.dynamic;

/*
 * https://leetcode.com/problems/shortest-common-supersequence/
 * fromtakeyouforward channel explanaition
 * Category: Hard, LCS(subsequence) derivative, Must Do
 * Related: https://leetcode.com/problems/decode-string/ Medium VVImp
 * 'https://leetcode.com/problems/valid-parenthesis-string/ Medium VVImp
 * https://leetcode.com/problems/sum-of-subarray-minimums/ Medium VVImp
 * 
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"
 

Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.
 */
public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        /*
         * Runtime: 10 ms, faster than 97.09% of Java online submissions for Shortest Common Supersequence .
Memory Usage: 45.8 MB, less than 79.76% of Java online submissions for Shortest Common Supersequence .
         */
        int R=str1.length()+1;
          int C=str2.length()+1;
          
          int[][]  dp=new int[R+1][C+1];
          //By default all value will be zero
          
           for(int i=1;i<R;i++)
              for(int j=1;j<C;j++){
                  
                  if(str1.charAt(i-1)==str2.charAt(j-1)){
                     dp[i][j]=dp[i-1][j-1]+1;
                  }else
                      dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
              }
          
          StringBuilder sb1 = new StringBuilder();
          
        int i=R-1,j=C-1;
          
          while(i>0 && j>0){
              if(str1.charAt(i-1)==str2.charAt(j-1)){
                  sb1.append(str1.charAt(i-1));
                  i--;j--;
              }
              else if(dp[i-1][j]>dp[i][j-1]){
                       sb1.append(str1.charAt(i-1));
                      i--;
                  }else{
                       sb1.append(str2.charAt(j-1));
                      j--;
                  }
          }
          
        // to app
            while(i>0){
          sb1.append(str1.charAt(i-1));
              i--;
          }
          
          while(j>0){
          sb1.append(str2.charAt(j-1));
              j--;
          }
          return sb1.reverse().toString();
      }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

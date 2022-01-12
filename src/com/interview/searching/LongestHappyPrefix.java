package com.interview.searching;

/*
 * Reference:https://leetcode.com/problems/longest-happy-prefix/
 * Category: Hard, Prefix Suffix
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).

Given a string s, return the longest happy prefix of s. Return an empty string "" if no such prefix exists.

 

Example 1:

Input: s = "level"
Output: "l"
Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"), and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
Example 2:

Input: s = "ababab"
Output: "abab"
Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
Example 3:

Input: s = "leetcodeleet"
Output: "leet"
Example 4:

Input: s = "a"
Output: ""
 

Constraints:

1 <= s.length <= 105
s contains only lowercase English letters.
 */
public class LongestHappyPrefix {
    private String computeTemporaryArray(char pattern[]){
        
        int [] lps = new int[pattern.length];
        int j =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[j]){
                lps[i] = j + 1;
                j++;
                i++;
            }else{
                if(j != 0){
                    j = lps[j-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int max = lps[pattern.length-1]; 
        int maxIndex = pattern.length-1;
        while (max > 0) {
            sb.append(pattern[maxIndex--]);
            max--;
        }
        return sb.reverse().toString();
    }
    public String longestPrefix(String s) {
        return computeTemporaryArray(s.toCharArray());
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

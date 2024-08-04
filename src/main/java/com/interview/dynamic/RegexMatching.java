package com.interview.dynamic;
/**
 * Date 06/24/2017
 * @author Mukesh Kumar Gupta
 * 
 * Write a program to perform regex matching with * an . 
 * 
 * References : https://leetcode.com/problems/regular-expression-matching/
 * https://www.youtube.com/watch?v=l3hda49XcDE
 * Category: Hard, Must Do, Top150
 * Related: https://leetcode.com/problems/wildcard-matching/ Hard
 * 
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input: s = "mississippi", p = "mis*is*p*."
Output: false
 

Constraints:

1 <= s.length <= 20
1 <= p.length <= 30
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class RegexMatching {



    public boolean isMatch(String s, String p) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Regular Expression Matching.
Memory Usage: 37.8 MB, less than 70.81% of Java online submissions for Regular Expression Matching.
            TC: o(m,n)
            SC: o(m,n)
         */
        char [] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        int R = text.length + 1;
        int C = pattern.length + 1;
        boolean T[][] = new boolean[R][C];

        T[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int j = 1; j < C; j++) {
            if (pattern[j-1] == '*') {
                T[0][j] = T[0][j - 2];
            }
        }

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    T[i][j] = T[i][j - 2];
                    if (pattern[j-2] == '.' || pattern[j - 2] == text[i - 1]) {
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[R-1][C-1];
        
    }

    public static void main(String args[]){

    }
}

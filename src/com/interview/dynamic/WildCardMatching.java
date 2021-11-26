package com.interview.dynamic;

/**
 * Date 02/11/2017
 * @author Mukesh Kumar Gupta
 *
 * Wild car matching with ? and *
 *
 * Reference
 * https://leetcode.com/problems/wildcard-matching/
 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
 * Category: Hard, Must Do, 
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input: s = "adceb", p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input: s = "acdcb", p = "a*c?b"
Output: false
 

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
 * Related: https://leetcode.com/problems/lemonade-change/ Easy
 * https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/ Medium
 * https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/ Easy
 */
public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        char [] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        int R = text.length + 1;
        int C = pattern.length + 1;
        boolean T[][] = new boolean[R][C];

        T[0][0] = true;
        //Deals with patterns like a* or a*b* or a*b*c*
        for (int j = 1; j < C; j++) {
            if (pattern[j-1] == '*') {
                T[0][j] = T[0][j-1];
            }
        }

        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (pattern[j - 1] == '?' || pattern[j - 1] == text[i - 1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j - 1] == '*')  {
                    T[i][j] =  T[i][j-1] || T[i-1][j];
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[R-1][C-1];
        
    }


    /**
     * Recursive and slow version of wild card matching.
     */
    public boolean isMatchRecursive(String s, String p) {
        return isMatchRecursiveUtil(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean isMatchRecursiveUtil(char[] text, char[] pattern, int pos1, int pos2) {
        if (pos2 == pattern.length) {
            return text.length == pos1;
        }

        if (pattern[pos2] != '*') {
            if (pos1 < text.length && (text[pos1] == pattern[pos2]) || pattern[pos2] == '?') {
                return isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1);
            } else {
                return false;
            }
        } else {
            //if we have a***b then skip to the last *
            while (pos2 < pattern.length - 1 && pattern[pos2 + 1] == '*') {
                pos2++;
            }
            pos1--;
            while (pos1 < text.length) {
                if (isMatchRecursiveUtil(text, pattern, pos1 + 1, pos2 + 1)) {
                    return true;
                }
                pos1++;
            }
            return false;
        }
    }

    public static void main(String args[]) {
        WildCardMatching wcm = new WildCardMatching();
        System.out.println(wcm.isMatch("xbylmz", "x?y*z"));
    }
}

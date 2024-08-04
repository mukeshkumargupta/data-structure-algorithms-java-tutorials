package com.interview.dynamic;

import java.util.*;
/*
 * https://leetcode.com/problems/scramble-string/
 * https://www.youtube.com/watch?v=MDmZm_aVDF8 Same like MCM
 * Category: Hard, Must Do
 * Related:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/ Hard, VVImp
 * https://leetcode.com/problems/01-matrix/ Medium, VVImp
 * https://leetcode.com/problems/stone-game-ii/ Medium, VVImp
 * 
 * 
 * 87. Scramble String
Hard

1406

928

Add to List

Share
We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.

 

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is "rgeat" which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
Example 3:

Input: s1 = "a", s2 = "a"
Output: true
 

Constraints:

s1.length == s2.length
1 <= s1.length <= 30
s1 and s2 consist of lowercase English letters.
Accepted
143,122
Submissions
402,343
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        HashMap<String, Boolean> memo = new HashMap<>();
        
        return DFS(s1, s2, memo);
    }
    
    private boolean DFS(String s1, String s2, HashMap<String, Boolean> memo) {
        /*
         * Runtime: 10 ms, faster than 90.69% of Java online submissions for Scramble String.
Memory Usage: 43.2 MB, less than 82.86% of Java online submissions for Scramble String.
         */
        int size = s1.length();
        if(size == 1)
            return s1.equals(s2);
        
        String match = s1 + "-" + s2;
        if (memo.containsKey(match))
            return memo.get(match);
        
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int[] count1 = new int[26], count2 = new int[26];
        for (int i = 0; i < size; i++) {
            count1[chs1[i] - 'a'] ++;
            count2[chs2[i] - 'a'] ++;
        }
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                memo.put(match, false);
                return memo.get(match);
            }
        }
        
        for (int i = 1; i < size; i++) {
            String substr1 = s1.substring(0, i);
            String substr2 = s1.substring(i, size);
            // case 1 - don't swap, case 2 - swap
            if ((DFS(substr1, s2.substring(0, i), memo) && DFS(substr2, s2.substring(i, size), memo)) || (DFS(substr1, s2.substring(size - i, size), memo) && DFS(substr2, s2.substring(0, size - i), memo))) {
                memo.put(match, true);
                return memo.get(match);
            }            
        }
        
        memo.put(match, false);                
        return memo.get(match);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

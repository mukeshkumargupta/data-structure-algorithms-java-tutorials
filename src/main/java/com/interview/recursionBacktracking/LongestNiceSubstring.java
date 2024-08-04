package com.interview.recursionBacktracking;
import java.util.*;

/*
 * https://leetcode.com/problems/longest-nice-substring/
 * https://www.youtube.com/watch?v=20mjBSByOaQ
 * Category: Easy, Tricky,
 * Related: 
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/ Medium
 * https://leetcode.com/problems/longest-string-chain/ Medium
 * https://leetcode.com/problems/minimum-number-of-frogs-croaking/ Medium
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

 

Example 1:

Input: s = "YazaAay"
Output: "aAa"
Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
"aAa" is the longest nice substring.

Example 2:

Input: s = "Bb"
Output: "Bb"
Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.

Example 3:

Input: s = "c"
Output: ""
Explanation: There are no nice substrings.

Example 4:

Input: s = "dDzeE"
Output: "dD"
Explanation: Both "dD" and "eE" are the longest nice substrings.
As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 */
public class LongestNiceSubstring {
    public String longestNiceSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            charSet.add(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            int addition = s.charAt(i) > 'Z' ? -32 : 32;
            if (charSet.contains((char)(s.charAt(i) + addition))) {
                continue;
            }
            String s1 = longestNiceSubstring(s.substring(0, i));
            String s2 = longestNiceSubstring(s.substring(i+1));
            return s1.length()>= s2.length() ? s1 : s2;
        }
        return s;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

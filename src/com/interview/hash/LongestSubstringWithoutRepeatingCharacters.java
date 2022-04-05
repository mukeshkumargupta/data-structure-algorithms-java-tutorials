package com.interview.hash;
import java.util.*;

/*
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Category: Medium, Tricky, Amazon, Top150, VVImp
 * https://www.youtube.com/watch?v=qtVh-XEpsJo&t=1090s
 * Related:
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/ mEDIUM
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ Medium
 * https://leetcode.com/problems/subarrays-with-k-different-integers/ Hard
 * https://leetcode.com/problems/maximum-erasure-value/ Medium
 * https://leetcode.com/problems/number-of-equal-count-substrings/ Medium
 * Related: Find string also, so extract the string in result;
 * Given a string s, find the length of the longest substring without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0
 * 
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        /*
         * Runtime: 12 ms, faster than 53.09% of Java online submissions for Longest Substring Without Repeating Characters.
Memory Usage: 46 MB, less than 30.60% of Java online submissions for Longest Substring Without Repeating Characters.
Next challenges:
         */
        Set<Character> set = new HashSet<>();
        int l =0;
        int r = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            while (set.contains(ch)) {
               set.remove(s.charAt(l));
                l++;
            }
            set.add(ch);
            r = i;
            if (max < r-l+1) {
                max = r - l +1;  
            }
        }
        return max;

    }
}

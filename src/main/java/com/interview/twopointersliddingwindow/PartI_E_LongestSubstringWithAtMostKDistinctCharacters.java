package com.interview.twopointersliddingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 https://www.youtube.com/watch?v=teM9ZsVRQyc&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=6
 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/ (Medium)
 Category: Medium
 https://www.naukri.com/code360/problems/distinct-characters_2221410?leftPanelTabValue=SUBMISSION
 */

public class PartI_E_LongestSubstringWithAtMostKDistinctCharacters {
    /*
1. Brute Force Approach:
Approach:
Iterate over all possible substrings of the string s.
TC: N^2
*/
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> distinctChars = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                distinctChars.add(s.charAt(j));
                if (distinctChars.size() > k) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringKDistinctBetter(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0,
                right = 0, maxLength = 0,
                len = s.length();

        // Sliding window with right pointer moving
        while (right < len) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // Shrink the window if distinct characters exceed k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }

    public int lengthOfLongestSubstringKDistinctOptimal(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0,
                right = 0, maxLength = 0,
                len = s.length();

        // Sliding window with right pointer moving
        while (right < len) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // Shrink the window if distinct characters exceed k
            if (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}

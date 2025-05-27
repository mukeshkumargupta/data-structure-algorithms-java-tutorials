package com.interview.sliddingwindow.SlidingwindowPlusHashMapMax;

import java.util.HashMap;
import java.util.Map;

/*
https://www.naukri.com/code360/problems/longest-sub-string-with-k-distinct-characters_920521?leftPanelTabValue=PROBLEM
Category: Medium, Tricky, Must Do
 */
public class D_ExactlyKDistinctCharacters {
    public int lengthOfLongestSubstringExactlyKDistinct(String s, int k) {
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

            // Update the maximum length Note: if (map.size() == k) is not required because it is already checked above
            maxLength = Math.max(maxLength, right - left + 1);

            right++;
        }

        return maxLength;
    }
}

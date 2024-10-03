package com.interview.twopointersliddingwindow;

/*
https://www.youtube.com/watch?v=_eNhaDCr6P0&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=8
https://leetcode.com/problems/longest-repeating-character-replacement/submissions/1422826266/
Category: Medium
Related:
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/ Medium
https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/description/ Hard
https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/ Medium
 */
public class PartHLongestRepeatingCharacterReplacement {

    /*
    1. Brute Force Approach
        Approach:
        Generate all possible substrings of the string.
        For each substring, calculate how many character replacements are needed to make all characters the same.
        Keep track of the longest valid substring that requires at most k character changes.
        Steps:
        Iterate through all substrings using two nested loops.
        For each substring, find the most frequent character.
        Calculate how many replacements are needed to make the rest of the characters in the substring equal to the most frequent character.
        Check if the replacements are less than or equal to k.
        Return the maximum length of valid substrings.
        Time Complexity:
        O(n³) because we are generating all substrings (O(n²)) and, for each substring, counting character frequencies (O(n)).
     */
    public int characterReplacementBruitforce(String s, int k) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String substring = s.substring(i, j + 1);
                int[] freq = new int[26];
                int maxFreq = 0;

                for (char c : substring.toCharArray()) {
                    freq[c - 'A']++;
                    maxFreq = Math.max(maxFreq, freq[c - 'A']);
                }

                int replacementsNeeded = (j - i + 1) - maxFreq;
                if (replacementsNeeded <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    /*
    2. Better Approach (Substring with Frequency Map Optimization)
        Approach:
        Instead of generating all substrings, iterate through the string with two pointers (i and j), where i is the start of the substring and j is the end.
        For each valid substring s[i:j], use a frequency map to count the occurrences of each character.
        Calculate how many replacements are needed to make the substring have the same character using the most frequent character in the substring.
        If the number of replacements is less than or equal to k, update the maximum length.
     */

    public int characterReplacementBetter(String s, int k) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            int[] count = new int[26];
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                count[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, count[s.charAt(j) - 'A']);
                // Total replacements needed to make substring all same characters
                int replace = (j - i + 1) - maxFreq;
                if (replace <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    /*
    3. Optimal Approach (Sliding Window with Frequency Map)
        Approach:
        Use a sliding window technique with two pointers (left and right) to maintain the window.
        Use a frequency array (count[]) to track the frequency of characters within the current window.
        As you expand the window by moving right, calculate the number of replacements needed by considering the character with the highest frequency in the current window.
        If the number of replacements is greater than k, shrink the window by moving the left pointer.
        Track the maximum window size that requires at most k replacements.
     */

    public int characterReplacement(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        int maxFreq = 0;
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

            // If we need more than k replacements, shrink the window
            if ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            // Calculate max window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

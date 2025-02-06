package com.interview.twopointersliddingwindow;
/*
    https://www.youtube.com/watch?v=_eNhaDCr6P0&list=PLgUwDviBIf0q7vrFA_HEWcqRqMpCXzYAL&index=8
    https://leetcode.com/problems/longest-repeating-character-replacement/submissions/1422826266/
    Category: Medium
    Related:
    https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/ Medium Locked on Leetcode but unlocked on Naukri
    https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/description/ Hard
    https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/ Medium Exactly same question as longest-repeating-character-replacement just replace function name

     Problems Following This Pattern:
    Here are some problems that you should solve using this Sliding Window + Frequency Count approach:

    Problem	Difficulty	Link
    Longest Repeating Character Replacement	Medium	LeetCode #424
    Maximize the Confusion of an Exam	Medium	LeetCode #2024
    Max Consecutive Ones III	Medium	LeetCode #1004
    Binary Subarrays With Sum	Medium	LeetCode #930
    Subarray Product Less Than K	Medium	LeetCode #713
 */
public class PartD_B_A_LongestRepeatingCharacterReplacement {
    /*
        Brute Force Approach (O(N²))
    Strategy:
    Try all substrings as potential candidates.
    For each substring, count the frequency of each character.
    Check if it can be made uniform by changing at most k characters.
    Track the maximum valid length.

    Complexity Analysis
Time Complexity: O(N²) → Checking all substrings.
Space Complexity: O(1) → Using only a fixed size frequency array of 26.
     */

    private static class BruitforceApproach {
        public int characterReplacementBruteForce(String s, int k) {
            int n = s.length();
            int maxLen = 0;

            for (int i = 0; i < n; i++) {
                int[] freq = new int[26];
                int maxFreq = 0; // Track most frequent char count

                for (int j = i; j < n; j++) {
                    freq[s.charAt(j) - 'A']++;
                    maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);

                    int length = j - i + 1;
                    int changesRequired = length - maxFreq;

                    if (changesRequired <= k) {
                        maxLen = Math.max(maxLen, length);
                    }
                }
            }

            return maxLen;
        }
    }

    private static class optimalApproach {
        /*
        Optimal Approach (Sliding Window - O(N))
        Strategy:
        Use a sliding window with two pointers (l and r).
        Keep track of max frequency of any character in the window.
        If the window length - max frequency ≤ k, it means we can convert the remaining characters to the most frequent character.
        If the condition fails, shrink the window from the left.
        Complexity Analysis
        Time Complexity: O(N) → Each character is processed at most twice (once when expanding and once when contracting).
        Space Complexity: O(1) → Using only a 26-sized array.
         */

        public int characterReplacement(String s, int k) {
            int[] freq = new int[26];
            int maxFreq = 0, l = 0, r=0,
                    maxLen = 0;
            int len = s.length();

            while (r < len) {
                freq[s.charAt(r) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[s.charAt(r) - 'A']);

                // Condition: (window size - maxFreq) should be ≤ k
                while ((r - l + 1) - maxFreq > k) { //you can turn to if as well
                    freq[s.charAt(l) - 'A']--;
                    l++; // Shrink the window from left
                }

                maxLen = Math.max(maxLen, r - l + 1);
                r++;
            }

            return maxLen;
        }
    }
}

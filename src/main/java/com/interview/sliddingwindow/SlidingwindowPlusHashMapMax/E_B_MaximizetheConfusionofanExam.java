package com.interview.sliddingwindow.SlidingwindowPlusHashMapMax;

/*
 * ----------------------------------------------------------------------------------------------------
 * 🔗 LeetCode Problem:
 * https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/
 *
 * 📂 Category: Medium
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧠 Problem Statement:
 *
 * A teacher is writing a test with `n` true/false questions, with:
 * - `'T'` denoting true
 * - `'F'` denoting false
 *
 * To confuse students, the teacher wants to maximize the number of **consecutive** questions
 * with the **same answer** after changing up to `k` answers in the `answerKey`.
 *
 * You can replace any character in `answerKey` with either `'T'` or `'F'`.
 *
 * Return the **maximum number of consecutive 'T's or 'F's** after performing **at most k operations**.
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧪 Examples:
 *
 * Example 1:
 * Input: answerKey = "TTFF", k = 2
 * Output: 4
 * Explanation: Replace both 'F's with 'T's to get "TTTT"
 *
 * Example 2:
 * Input: answerKey = "TFFT", k = 1
 * Output: 3
 * Explanation:
 * - Replace 1st 'T' → 'F' => "FFFT"
 * - OR replace 2nd 'T' → 'F' => "TFFF"
 * Both lead to 3 consecutive 'F's.
 *
 * Example 3:
 * Input: answerKey = "TTFTTFTT", k = 1
 * Output: 5
 * Explanation:
 * - Replace 1st 'F' => "TTTTTFTT"
 * - OR replace 2nd 'F' => "TTFTTTTT"
 * In both cases, max consecutive 'T's = 5
 *
 * ----------------------------------------------------------------------------------------------------
 * 🔒 Constraints:
 * - n == answerKey.length
 * - 1 <= n <= 5 * 10^4
 * - answerKey[i] ∈ {'T', 'F'}
 * - 1 <= k <= n
 *
 * ----------------------------------------------------------------------------------------------------
 * 🧩 Related Problems:
 *
 * | Problem                                              | Difficulty | Link                                                                     |
 * |------------------------------------------------------|------------|--------------------------------------------------------------------------|
 * | Longest Substring with At Most K Distinct Characters | Medium     | https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ |
 * | Longest Repeating Character Replacement              | Medium     | https://leetcode.com/problems/longest-repeating-character-replacement/   |
 * | Max Consecutive Ones III                             | Medium     | https://leetcode.com/problems/max-consecutive-ones-iii/                  |
 * | Minimum Number of Days to Make m Bouquets            | Medium     | https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/ |
 * | Longest Nice Subarray                                | Medium     | https://leetcode.com/problems/longest-nice-subarray/                     |
 *
 * ----------------------------------------------------------------------------------------------------
 */
public class E_B_MaximizetheConfusionofanExam {
    private static class BruitforceApproach {
        public int maxConsecutiveAnswers(String answerKey, int k) {
            int n = answerKey.length();
            int maxLen = 0;

            for (int i = 0; i < n; i++) {
                int[] freq = new int[26];
                int maxFreq = 0; // Track most frequent char count

                for (int j = i; j < n; j++) {
                    freq[answerKey.charAt(j) - 'A']++;
                    maxFreq = Math.max(maxFreq, freq[answerKey.charAt(j) - 'A']);

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

        public int maxConsecutiveAnswers(String answerKey, int k) {
            int[] freq = new int[26];
            int maxFreq = 0, left = 0, right=0,
                    maxLen = 0;
            int len = answerKey.length();

            while (right < len) {
                freq[answerKey.charAt(right) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[answerKey.charAt(right) - 'A']);

                // Condition: (window size - maxFreq) should be ≤ k
                while ((right - left + 1) - maxFreq > k) { //you can turn to if as well
                    freq[answerKey.charAt(left) - 'A']--;
                    left++; // Shrink the window from left
                }

                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }

            return maxLen;
        }
    }



}

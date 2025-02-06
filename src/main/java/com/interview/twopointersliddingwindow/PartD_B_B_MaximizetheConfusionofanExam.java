package com.interview.twopointersliddingwindow;

public class PartD_B_B_MaximizetheConfusionofanExam {
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
            int maxFreq = 0, l = 0, r=0,
                    maxLen = 0;
            int len = answerKey.length();

            while (r < len) {
                freq[answerKey.charAt(r) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[answerKey.charAt(r) - 'A']);

                // Condition: (window size - maxFreq) should be ≤ k
                while ((r - l + 1) - maxFreq > k) { //you can turn to if as well
                    freq[answerKey.charAt(l) - 'A']--;
                    l++; // Shrink the window from left
                }

                maxLen = Math.max(maxLen, r - l + 1);
                r++;
            }

            return maxLen;
        }
    }



}

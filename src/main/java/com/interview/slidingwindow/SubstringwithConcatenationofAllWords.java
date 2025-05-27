package com.interview.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    🔗 Problem: https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
    https://www.youtube.com/watch?v=taYRJf-M25I
    📂 Category: Hard, Top150, Tricky
    Related:
    https://leetcode.com/problems/minimum-window-substring/ Hard

    🧩 Problem Description:
    You are given a string `s` and an array of strings `words`.
    All strings in `words` are of the same length.

    A *concatenated string* is a string that exactly contains all the words
    in any permutation, concatenated without any extra characters.

    ✨ Example:
    If words = ["ab", "cd", "ef"], valid concatenations include:
    "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab"
    ❌ "acdbef" is not valid (not a permutation of all words)

    🚀 Objective:
    Return a list of starting indices in `s` where a valid concatenated substring appears.

    -------------------------------
    ✅ Example 1:
    Input:
        s = "barfoothefoobarman"
        words = ["foo", "bar"]

    Output:
        [0, 9]

    Explanation:
        - Substring at index 0: "barfoo" → "bar" + "foo" ✔️
        - Substring at index 9: "foobar" → "foo" + "bar" ✔️

    -------------------------------
    ✅ Example 2:
    Input:
        s = "wordgoodgoodgoodbestword"
        words = ["word", "good", "best", "word"]

    Output:
        []

    Explanation:
        - No valid substring contains all words in any permutation.

    -------------------------------
    ✅ Example 3:
    Input:
        s = "barfoofoobarthefoobarman"
        words = ["bar", "foo", "the"]

    Output:
        [6, 9, 12]

    Explanation:
        - Index 6:  "foobarthe" ✔️
        - Index 9:  "barthefoo" ✔️
        - Index 12: "thefoobar" ✔️

    -------------------------------
    🎯 Constraints:
        • 1 <= s.length <= 10^4
        • 1 <= words.length <= 5000
        • 1 <= words[i].length <= 30
        • s and words[i] consist only of lowercase English letters.

    -------------------------------
    🔍 Sample Usage:
    String s = "barfoothefoobarman";
    String[] words = {"foo", "bar"};

    Solution sol = new Solution();
    List<Integer> indices = sol.findSubstring(s, words);

    System.out.println(indices);  // Output: [0, 9]
*/
public class SubstringwithConcatenationofAllWords {
/*
    ✅ Time Complexity

    Let:
    L = length of each word
    W = number of words
    N = length of the string s
    T = total length of the concatenated words = L * W

    🔹 Outer Loop:
        for (int offset = 0; offset < wordLen; offset++)
        - Runs L times (we check each alignment within a word-length block).

    🔹 Inner Loop:
        for (int start = offset; start + totalLen <= sLen; start += wordLen)
        - For each offset, we slide the window by word size L.
        - Runs approximately N / L times for each offset.

    🔹 Innermost Loop:
        for (int i = 0; i < numWords; i++)
        - Within each window, we extract and check W words (each of length L).

    🔸 Total Time Complexity:
        O(L * (N / L) * W) = O(N * W)

        Explanation:
        - L outer iterations
        - Each processes ~N / L substrings
        - Each substring checks W words

    ⚠️ Note:
        - In Java, `s.substring()` is O(L), but often treated as O(1)
          in complexity analysis due to JVM optimizations.

    ✅ Space Complexity

    🔹 Frequency Maps:
        - We use two hash maps (wordFreq and seen) that store up to W unique words.
        - Space = O(W)

    🔹 Output List:
        - In the worst case, every index could be valid → O(N) output size.
        - Typically much smaller in practice.

    🔸 Total Space Complexity:
        O(W + R)

        Where:
        - W = number of words (hash maps)
        - R = number of results (output list size)

        Often simplified to O(W) if R is small compared to N.

    🧠 Summary:

        Type              Complexity
        ------------------------------
        Time Complexity   O(N * W)
        Space Complexity  O(W)
*/

    private static class Better {

    }
    public List<Integer> findSubstring(String s, String[] words) {//Ignore but just read techdose video for why outer lop required
        List<Integer> result = new ArrayList<>();
        //if (s == null || words == null || words.length == 0) return result;

        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        int sLen = s.length();

        // Try all starting positions within a wordLen block
        for (int offset = 0; offset < wordLen; offset++) {
            for (int start = offset; start + totalLen <= sLen; start += wordLen) {
                Map<String, Integer> seen = new HashMap<>(wordFreq);
                boolean matched = true;

                for (int i = 0; i < numWords; i++) {
                    int wordStart = start + i * wordLen;//Tricky: first work, second word third word from start think like
                    String word = s.substring(wordStart, wordStart + wordLen);

                    if (!seen.containsKey(word) || seen.get(word) == 0) {
                        matched = false;
                        break;
                    }
                    seen.put(word, seen.get(word) - 1);
                }

                if (matched) {
                    result.add(start);
                }
            }
        }

        return result;
    }

    private static class Optimal {//Sliding window approach passing all test cases
        /*
            🧪 Dry Run

            Input:
            s = "barfoothefoobarman"
            words = ["foo", "bar"]

            wordLen = 3
            wordCount = 2
            totalLen = 6
            wordFreq = {foo: 1, bar: 1}

            We will run the loop with 3 different offsets: i = 0, 1, 2

            🌀 i = 0:
            Start at index 0:
                "bar" → valid → windowMap = {bar: 1}
                "foo" → valid → windowMap = {bar:1, foo:1}
                count = 2 → match found → add index 0 ✅

            Move right:
                "the" → not in wordFreq → reset window

            Move right:
                "foo" → valid → windowMap = {foo:1}
                "bar" → valid → windowMap = {foo:1, bar:1}
                count = 2 → match found → add index 9 ✅

            i = 1 and i = 2:
                No matches found (words don’t align properly)

            ✅ Final Output:
            [0, 9]

            📊 Time & Space Complexity

            Let:
            N = length of s
            W = number of words
            L = length of each word

            ⏱️ Time Complexity: O(N * W)
            Explanation:
                - Outer loop runs L times (offsets)
                - For each offset:
                    - You slide the window every L steps ⇒ O(N / L) iterations
                    - For each window, you may process up to W words
                - So: O(L * (N / L) * W) = O(N * W)

            💾 Space Complexity: O(W)
                - wordFreq and windowMap store up to W words
                - Result list in worst case → O(N), but usually omitted in space complexity unless very large

            Summary Table:

            Type              Complexity
            -----------------------------
            Time Complexity   O(N * W)
            Space Complexity  O(W)
        */
        public List<Integer> findSubstring(String s, String[] words) {//Clean solution
            List<Integer> result = new ArrayList<>();
            //if (s == null || words == null || words.length == 0) return result;

            int wordLen = words[0].length();
            int wordCount = words.length;

            // Frequency map of words
            Map<String, Integer> wordFreq = new HashMap<>();
            for (String word : words) {
                wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
            }

            // Slide the window with offset from 0 to wordLen - 1
            for (int i = 0; i < wordLen; i++) {
                int left = i, right = i;
                Map<String, Integer> windowMap = new HashMap<>();
                int count = 0;

                while (right + wordLen <= s.length()) {
                    String word = s.substring(right, right + wordLen);
                    right += wordLen;//Right move by word length

                    // Word exists in the original list
                    if (wordFreq.containsKey(word)) {
                        windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                        count++;

                        // More than expected, shrink the window
                        while (windowMap.get(word) > wordFreq.get(word)) {
                            String leftWord = s.substring(left, left + wordLen);
                            windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                            count--;
                            left += wordLen;
                        }

                        // All words matched
                        if (count == wordCount) {
                            result.add(left);
                        }
                    } else {
                        // Invalid word, reset the window
                        windowMap.clear();
                        count = 0;
                        left = right;
                    }
                }
            }

            return result;
        }
    }
}

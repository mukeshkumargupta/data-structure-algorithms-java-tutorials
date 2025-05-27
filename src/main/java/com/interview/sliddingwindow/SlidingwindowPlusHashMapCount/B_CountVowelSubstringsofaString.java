package com.interview.sliddingwindow.SlidingwindowPlusHashMapCount;

import java.util.*;

public class B_CountVowelSubstringsofaString {
/*
https://leetcode.com/problems/count-vowel-substrings-of-a-string/description/
category: Easy in case of bruitforce but hard in case of optimal
Related:
https://leetcode.com/problems/number-of-matching-subsequences/ Medium
https://leetcode.com/problems/subarrays-with-k-different-integers/ Hard
https://leetcode.com/problems/number-of-substrings-with-only-1s/ Medium
https://leetcode.com/problems/longest-substring-of-all-vowels-in-order/ Medium
https://leetcode.com/problems/total-appeal-of-a-string/ Hard
https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/ Medium
https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/ Medium

A substring is a contiguous (non-empty) sequence of characters within a string.

A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.

Given a string word, return the number of vowel substrings in word.



Example 1:

Input: word = "aeiouu"
Output: 2
Explanation: The vowel substrings of word are as follows (underlined):
- "aeiouu"
- "aeiouu"
Example 2:

Input: word = "unicornarihan"
Output: 0
Explanation: Not all 5 vowels are present, so there are no vowel substrings.
Example 3:

Input: word = "cuaieuouac"
Output: 7
Explanation: The vowel substrings of word are as follows (underlined):
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"


Constraints:

1 <= word.length <= 100
word consists of lowercase English letters only.

 Related:
     792. Number of Matching Subsequences
     1513. Number of Substrings With Only 1s
     1839. Longest Substring Of All Vowels in Order
 */
    public static class Bruitforce {
       /* Brute Force Approach (O(n²))
        Approach
        Iterate over all possible substrings.
        For each substring, check if:
        It contains only vowels (a, e, i, o, u).
        It contains all five vowels at least once.
        Count valid substrings.

        Time Complexity:
O(n²): We check all substrings.
O(1) space: HashSet at most stores 5 characters.


        */

        public int countVowelSubstrings(String word) {
            int count = 0;
            Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

            for (int i = 0; i < word.length(); i++) {
                Set<Character> seen = new HashSet<>();
                for (int j = i; j < word.length(); j++) {
                    char c = word.charAt(j);
                    if (!vowels.contains(c)) break; // Stop if a non-vowel appears

                    seen.add(c);
                    if (seen.size() == 5) count++; // Valid substring if all 5 vowels exist
                }
            }
            return count;
        }
    }

    /*
     * Problem: Count Vowel Substrings of a String
     *
     * Brute Force Approach (O(n²))
     * Approach:
     * - Iterate over all possible substrings.
     * - For each substring, check if:
     *   1. It contains only vowels (a, e, i, o, u).
     *   2. It contains all five vowels at least once.
     * - Count valid substrings.
     *
     * Time Complexity:
     * - O(n²): We check all substrings.
     * - O(1) space: HashSet at most stores 5 characters.
     *
     * Optimal Approach (Sliding Window) - O(n)
     * Approach:
     * - Use three pointers (start, firstConsonant, firstMissingVowel):
     *   1. `start`: Beginning of potential vowel substring.
     *   2. `firstConsonant`: First consonant seen, which breaks the substring.
     *   3. `firstMissingVowel`: First index where any vowel count goes to zero.
     * - Expand `end` and track vowel frequencies.
     * - When all vowels are present, count valid substrings.
     * - If a consonant appears, reset the window.
     *
     * Time Complexity:
     * - O(n): Each character is processed at most twice.
     * - O(1) space: HashMap stores at most 5 characters.
     *
     * Why is this optimal?
     * - Avoids redundant substring checks.
     * - Uses frequency tracking to efficiently count valid substrings.
     *
     * Conclusion:
     * - The sliding window approach efficiently solves the problem in O(n) time.
     *
     * Example Input:
     * word = "aeiouu"
     *
     * Step-by-Step Execution:
     * We will use the formula:
     * countVowelSubstrings(word) = atMostKDistinctVowels(word, 5) - atMostKDistinctVowels(word, 4)
     *
     * First Call: atMostKDistinctVowels(word, 5)
     * We find the number of substrings with at most 5 distinct vowels.
     *
     * Step | Left | Right | Window | Frequency Map | Valid Substrings
     * -----------------------------------------------------------
     * 1    | 0    | 0    | a      | {a → 1}        | 1
     * 2    | 0    | 1    | ae     | {a → 1, e → 1} | 1 + 2 (1 previous and 2 current) follow same for others
     * 3    | 0    | 2    | aei    | {a → 1, e → 1, i → 1} | 6
     * 4    | 0    | 3    | aeio   | {a → 1, e → 1, i → 1, o → 1} | 10
     * 5    | 0    | 4    | aeiou  | {a → 1, e → 1, i → 1, o → 1, u → 1} | 15
     * 6    | 0    | 5    | aeiouu | {a → 1, e → 1, i → 1, o → 1, u → 2} | 21
     * Total at most 5 distinct vowels: 21
     *
     * Second Call: atMostKDistinctVowels(word, 4)
     * Now, we count substrings with at most 4 distinct vowels.
     *
     * Step | Left | Right | Window | Frequency Map | Valid Substrings
     * -----------------------------------------------------------
     * 1    | 0    | 0    | a      | {a → 1}        | 1
     * 2    | 0    | 1    | ae     | {a → 1, e → 1} | 3
     * 3    | 0    | 2    | aei    | {a → 1, e → 1, i → 1} | 6
     * 4    | 0    | 3    | aeio   | {a → 1, e → 1, i → 1, o → 1} | 10
     * 5    | 0    | 4    | aeiou  | {a → 1, e → 1, i → 1, o → 1, u → 1} | ❌ (size > 4) SHRINK
     * 6    | 1    | 4    | eiou   | {e → 1, i → 1, o → 1, u → 1} | 14
     * 7    | 1    | 5    | eiouu  | {e → 1, i → 1, o → 1, u → 2} | 19
     * Total at most 4 distinct vowels: 19
     *
     * Final Result:
     * Result = atMostKDistinctVowels(word, 5) - atMostKDistinctVowels(word, 4)
     * Result = 21 - 19 = 2
     *
     * Thus, the number of substrings with exactly 5 distinct vowels is 2.
     *
     * Key Observations:
     * - The sliding window expands by adding characters.
     * - When too many distinct vowels appear, we shrink the left pointer.
     * - The number of valid substrings is counted as (right - left + 1).
     * - The difference of two sliding window calculations gives us substrings containing exactly 5 vowels.
     */

    public static class Optimal {
        public int countVowelSubstrings(String word) {
            return atMostKDistinctVowels(word, 5) - atMostKDistinctVowels(word, 4);
        }
        private int atMostKDistinctVowels(String word, int k) {
            int count = 0, left = 0;
            Map<Character, Integer> freq = new HashMap<>();
            Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

            for (int right = 0; right < word.length(); right++) {
                char rightChar = word.charAt(right);
                if (!vowels.contains(rightChar)) { // Reset on consonant
                    freq.clear();
                    left = right + 1;
                    continue;
                }

                freq.put(rightChar, freq.getOrDefault(rightChar, 0) + 1);

                while (freq.size() > k) { // Shrink window if too many distinct vowels
                    char leftChar = word.charAt(left);
                    freq.put(leftChar, freq.get(leftChar) - 1);
                    if (freq.get(leftChar) == 0) freq.remove(leftChar);
                    left++;
                }

                count += right - left + 1;
            }
            return count;
        }
    }
}

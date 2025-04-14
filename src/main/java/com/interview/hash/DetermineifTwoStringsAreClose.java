package com.interview.hash;

import java.util.*;
/*
https://leetcode.com/problems/determine-if-two-strings-are-close/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75
https://www.youtube.com/watch?v=EIyIbJOO_aQ
Related:
https://leetcode.com/problems/buddy-strings/ Easy
https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/ Medium
https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/ Medium
 */
public class DetermineifTwoStringsAreClose {
    /*
        ⏱ Time and Space Complexity

        ✅ Time Complexity: O(n + 26 log 26) → O(n)
        - We count frequencies in O(n)
        - We sort two fixed-size arrays (max 26 elements): O(26 log 26) = O(1)

        👉 Final Time Complexity: O(n), where n is the length of the strings

        ✅ Space Complexity: O(1)
        - Only 2 fixed arrays of size 26 are used → O(1)

        🧪 Example

        word1 = "abc", word2 = "bca"
        ✅ Same characters: a, b, c
        ✅ Frequencies can be rearranged: [1, 1, 1] == [1, 1, 1]
        → return true

        word1 = "aabb", word2 = "ccdd"
        ✅ Frequencies match [2, 2]
        ❌ But character sets differ (a,b vs c,d)
        → return false
    */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char ch : word1.toCharArray()) {
            freq1[ch - 'a']++;
        }

        for (char ch : word2.toCharArray()) {
            freq2[ch - 'a']++;
        }

        // Check character sets used in both strings
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }

        // Compare frequency patterns (not associated with character identity)
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }
/*
    Here’s a list of derived questions and variations based on the concept behind the problem Determine if Two Strings Are Close:

🔍 Core Concepts Involved
- Character Frequency Comparison
- Character Set Equality
- Sorting Frequency Arrays
- Transformation Validity between Two Strings

✅ Directly Derived Questions

1. Group Close Strings
   Leetcode: #2453
   - Group strings where each string is “close” to others in its group (based on the same transformation rule).

2. Determine if Two Strings Are Anagrams
   Leetcode: #242
   - Check if two strings are anagrams (compare frequencies, order doesn’t matter).

3. Find All Anagrams in a String
   Leetcode: #438
   - Use a sliding window to detect anagrams of a pattern in a larger string.

4. Minimum Number of Steps to Make Two Strings Anagram
   Leetcode: #1347
   - Return the number of character replacements required to make two strings anagrams.

🚀 Advanced Variations

5. Check if Strings are Transformable with Limited Operations
   - Allow a limited number of frequency swaps or transformations, and check if transformation is still possible.

6. Determine if Two Strings Can Be Made Equal by Swapping Characters
   - Similar to the Close Strings idea but allow only k swaps.

7. Compare Character Frequency Distributions
   - Given two strings, return true if they have the same multiset of frequencies (not necessarily matching characters).

🧪 Interview-Style Conceptual Variants

8. Can Characters Be Rearranged to Match Another String?
   - Frequency match without being an exact anagram (can differ in some ways but still be made similar).

9. Character Frequency Hashing / Signature Comparison
   - Often used in comparing word groups or bucket sorting strings based on frequency signature.

10. Minimum Number of Operations to Make Two Strings Equal
    - Count minimum frequency changes to convert one string to another (insert/delete allowed).

📚 Practice Platform Filters

To find more problems with similar logic, you can search:

On Leetcode / GeeksForGeeks using tags:
✅ String
✅ HashMap / Frequency Counter
✅ Sorting
✅ Hashing
✅ Greedy (for variations with limited operations)
*/
}

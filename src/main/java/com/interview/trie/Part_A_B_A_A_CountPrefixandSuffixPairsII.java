package com.interview.trie;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/description/
Category: Hard, Tricky
Related:
https://leetcode.com/problems/implement-trie-prefix-tree/ Medium
https://leetcode.com/problems/design-add-and-search-words-data-structure/ Medium
You are given a 0-indexed string array words.

Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:

isPrefixAndSuffix(str1, str2) returns true if str1 is both a prefix and a suffix of str2, and false otherwise.
For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix, but isPrefixAndSuffix("abc", "abcd") is false.

Return an integer denoting the number of index pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j]) is true.



Example 1:

Input: words = ["a","aba","ababa","aa"]
Output: 4
Explanation: In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix("a", "aba") is true.
i = 0 and j = 2 because isPrefixAndSuffix("a", "ababa") is true.
i = 0 and j = 3 because isPrefixAndSuffix("a", "aa") is true.
i = 1 and j = 2 because isPrefixAndSuffix("aba", "ababa") is true.
Therefore, the answer is 4.
Example 2:

Input: words = ["pa","papa","ma","mama"]
Output: 2
Explanation: In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix("pa", "papa") is true.
i = 2 and j = 3 because isPrefixAndSuffix("ma", "mama") is true.
Therefore, the answer is 2.
Example 3:

Input: words = ["abab","ab"]
Output: 0
Explanation: In this example, the only valid index pair is i = 0 and j = 1, and isPrefixAndSuffix("abab", "ab") is false.
Therefore, the answer is 0.


Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 105
words[i] consists only of lowercase English letters.
The sum of the lengths of all words[i] does not exceed 5 * 105.
 */
public class Part_A_B_A_A_CountPrefixandSuffixPairsII {
    /*
    ⏱️ Time and Space Complexity (Same as Before)
    ⏳ Time Complexity: O(n * k)
    n = number of words

    k = average word length

    Each insert() processes one word in O(k) time

    💾 Space Complexity: O(n * k)
    In the worst case, each character pair adds a unique node → n * k nodes

    Let me know if you want a dry run with example input like ["abc", "a", "bc", "abc"] to see how the counter builds up!
     */
    class TrieNode {
        Map<Integer, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    TrieNode root = new TrieNode();

    public int countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        for (String word : words) {
            ans += insert(word);
        }
        return (int) ans;
    }

    private int insert(String word) {
        TrieNode current = root;
        int len = word.length();
        int pairCount = 0;

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            int nodeKey = ch * 128 + word.charAt(len - 1 - i);
            TrieNode trieNode = current.children.get(nodeKey);

            if (trieNode == null) {
                trieNode = new TrieNode();
                current.children.put(nodeKey, trieNode);
            }

            current = trieNode;
            pairCount += current.count;
        }

        current.count++;
        return pairCount;
    }
}

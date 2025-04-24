package com.interview.trie;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/prefix-and-suffix-search/description/
https://www.youtube.com/watch?v=3JVlE66WxW0
Category: Hard, Tricky
Related:
https://leetcode.com/problems/design-add-and-search-words-data-structure/ Medium
Design a special dictionary that searches the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string pref, string suff) Returns the index of the word in the dictionary, which has the prefix pref and the suffix suff. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.


Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]
Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = "e".


Constraints:

1 <= words.length <= 104
1 <= words[i].length <= 7
1 <= pref.length, suff.length <= 7
words[i], pref and suff consist of lowercase English letters only.
At most 104 calls will be made to the function f.
 */
public class Part_A_B_A_A_PrefixandSuffixSearch {
    /*
     * 📊 Time & Space Complexity
     *
     * Construction Time: O(W * L^2)
     * - W = number of words
     * - L = max word length
     * - For each word, we generate (L + 1) suffixes and insert each into the Trie,
     *   resulting in roughly O(W * L^2) insertions.
     *
     * Query Time: O(P + S)
     * - P = prefix length
     * - S = suffix length
     * - We build the key as "suffix + '{' + prefix", and search it in the Trie.
     *
     * Space Complexity: O(W * L^2)
     * - Each word of length L contributes (L + 1) suffix-prefix combinations,
     *   with each key of size up to 2L + 1.
     *
     * 🔍 Explanation:
     *
     * Idea:
     * - Build a special Trie that stores keys of the form: suffix + '{' + word.
     * - The '{' character is chosen as a delimiter because it is lexicographically
     *   greater than any lowercase letter ('z' < '{').
     *
     * Example:
     * For word = "apple", we insert into the Trie:
     *   - "apple{apple"
     *   - "pple{apple"
     *   - "ple{apple"
     *   - "le{apple"
     *   - "e{apple"
     *   - "{apple"
     *
     * Query Example:
     * - For prefix = "ap", suffix = "le", we search for "le{ap" in the Trie.
     *
     * 📈 Summary:
     * - Construction: O(W * L²)
     * - Query:       O(P + S)
     * - Space:       O(W * L²)
     */
    private static class TrieNode {
        Map<Character, TrieNode> children;
        int weight;

        public TrieNode() {
            children = new HashMap<>();
            weight = -1;
        }
    }

    private final TrieNode root;

    public Part_A_B_A_A_PrefixandSuffixSearch(String[] words) {
        root = new TrieNode();
        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight];
            String combined = word + "{" + word; // '{' is just after 'z' in ASCII
            for (int i = 0; i <= word.length(); i++) {
                String suffixPrefix = word.substring(i) + "{" + word;
                insert(suffixPrefix, weight);
            }
        }
    }

    private void insert(String word, int weight) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode trieNode = current.children.get(ch);

            //if TreeNode does not exists in map then create one and put it into map
            if (trieNode == null) {
                trieNode = new TrieNode();
                current.children.put(ch, trieNode);
            }
            current = trieNode;
            current.weight = weight;
        }
    }

    public int f(String prefix, String suffix) {
        String search = suffix + "{" + prefix;
        TrieNode current = root;
        for (int i = 0; i < search.length(); i++) {
            char ch = search.charAt(i);
            TrieNode treeNode = current.children.get(ch);
            //if TreeNode does not exist for given char then return false
            if (treeNode == null) {
                return -1;
            }
            current = treeNode;
        }
        return current.weight;
    }
}

package com.interview.trie;

/*
 * https://leetcode.com/problems/longest-common-prefix/
 * Category: Easy, Must Do, Fundamental
 * Related:
 * https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/ Easy
 * https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/ Medium
 * https://leetcode.com/problems/longest-common-suffix-queries/ Hard
 * https://leetcode.com/problems/longest-common-prefix-after-at-most-one-removal/ Medium Locked
 * Other method: Using simple character compare is also done and maximize length
 * Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.

 */
public class Part_A_B_A_LongestCommonPrefix {
    /*
Brute Force Approach
Compare characters one by one across all strings.
Stop when a mismatch occurs.
Time Complexity: O(N * M)
(N = number of strings, M = length of the shortest string)
✔ Character-by-character comparison
✔ No extra space
     */
    private static class BruitForce {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";

            String prefix = strs[0];

            for (int i = 1; i < strs.length; i++) {
                while (strs[i].indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) return "";
                }
            }

            return prefix;
        }
    }

    /*
    Start with the first string as the prefix.
Trim the prefix until it matches all strings.
Time Complexity: O(N * M)
✔ Efficient for most cases
✔ Early termination if no common prefix exists
     */
    private static class BetterApproach {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";

            String prefix = strs[0];

            for (int i = 1; i < strs.length; i++) {
                while (strs[i].indexOf(prefix) != 0) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) return "";
                }
            }

            return prefix;
        }
    }

    /*
        Most Optimized (Using Trie)
        Insert all words into a Trie.
        Traverse until a node has more than one child (indicating a mismatch).
        Time Complexity: O(N * M)
        ✔ Efficient for large datasets
        ✔ Good for repeated queries
     */
    private static class Optimized {
        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            int count = 0;  // Tracks number of words passing through
        }

        private TrieNode root = new TrieNode();

        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";

            for (String word : strs) insert(word);

            return findLongestPrefix();
        }

        private void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
                current.count++;
            }
        }

        private String findLongestPrefix() {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();

            while (true) {
                int childIndex = -1;
                int childCount = 0;

                for (int i = 0; i < 26; i++) {
                    if (node.children[i] != null) {
                        childIndex = i;
                        childCount++;
                    }
                }

                if (childCount != 1) break;

                prefix.append((char) ('a' + childIndex));
                node = node.children[childIndex];
            }

            return prefix.toString();
        }
    }
    
}

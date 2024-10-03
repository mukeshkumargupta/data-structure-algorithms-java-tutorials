package com.interview.trie;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/longest-common-prefix/
 * Category: Easy, Must Do, Fundamental
 * Related: https://leetcode.com/problems/similar-rgb-color/ Easy
 * https://leetcode.com/problems/maximum-number-of-removable-characters/ Medium
 * https://leetcode.com/problems/number-of-wonderful-substrings/ Medium
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
public class Part_A_E_LongestCommonPrefix {
    // Trie Node Class
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    // Insert word into Trie
    private static void insert(TrieNode root, String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode treeNode = current.children.get(ch);
            if (treeNode == null) {
                treeNode = new TrieNode();
                current.children.put(ch, treeNode);
            }
            current = treeNode;
        }
        current.isEndOfWord = true;
    }

    // Find longest common prefix
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        TrieNode root = new TrieNode();

        // Insert all words into the Trie
        for (String word : strs) {
            insert(root, word);
        }

        // Traverse the Trie to find the longest common prefix
        StringBuilder prefix = new StringBuilder();
        TrieNode current = root;

        while (current != null && current.children.size() == 1 && !current.isEndOfWord) {
            // Get the single child
            Map.Entry<Character, TrieNode> entry = current.children.entrySet().iterator().next();
            prefix.append(entry.getKey());
            current = entry.getValue();
        }

        return prefix.toString();
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

package com.interview.trie;

/*
 * https://leetcode.com/problems/longest-word-in-dictionary/
 * https://www.youtube.com/watch?v=MbvGOab6Sfg
 * Category: Medium
 * Related: https://leetcode.com/problems/implement-magic-dictionary/ Medium Good question try it
 * https://leetcode.com/problems/longest-word-with-all-prefixes/ Medium Locked
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.

 

Example 1:

Input: words = ["w","wo","wor","worl","world"]
Output: "world"
Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:

Input: words = ["a","banana","app","appl","ap","apply","apple"]
Output: "apple"
Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 30
words[i] consists of lowercase English letters.
 */
public class LongestWordinDictionary {
    
    // trie TreeNode
    class TrieTreeNode {
        TrieTreeNode[] children;
        String str;
        
        TrieTreeNode() {
            children = new TrieTreeNode[26];
            str = null;
        }
    };
    
    String res = "";
    
    void dfsOnTrie(TrieTreeNode root) {
        for (TrieTreeNode child : root.children) {
            if (child != null && child.str != null) {
                if (child.str.length() > res.length()) {
                    res = child.str;
                }
                dfsOnTrie(child);
            }
            
        }
        
    }
    
    public void insert(TrieTreeNode root, String word) {
        TrieTreeNode current = root;
        for (int i = 0; i < word.length(); i++) {
            TrieTreeNode node = current.children[word.charAt(i) - 'a'];
            if (node == null) {
                node = new TrieTreeNode();
                current.children[word.charAt(i) - 'a'] = node;// add it
            }
            current = node;
        }
        current.str = word;
        
    }
    
    public String longestWord(String[] words) {
        /*
         * Runtime: 14 ms, faster than 69.12% of Java online submissions for Longest Word in Dictionary.
Memory Usage: 49.6 MB, less than 19.37% of Java online submissions for Longest Word in Dictionary.
Next challenges:
         */
        TrieTreeNode root = new TrieTreeNode();
        for (String word : words) {
            insert(root, word);
        }
        
        dfsOnTrie(root);
        return res;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

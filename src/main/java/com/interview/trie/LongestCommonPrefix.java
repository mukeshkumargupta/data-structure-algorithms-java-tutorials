package com.interview.trie;

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
public class LongestCommonPrefix {
    final int ALPHABET_SIZE = 26;
    
    // trie TreeNode
    class TrieTreeNode {
        TrieTreeNode[] children;
        boolean endOfWord;
        
        TrieTreeNode() {
            children = new TrieTreeNode[ALPHABET_SIZE];
            endOfWord = false;
        }
    };
    
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
        current.endOfWord = true;
        
    }
    
    int indexs;
    
    // Counts and returns the number of children of the
    // current node
    int countChildren(TrieTreeNode node) {
        int count = 0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node.children[i] != null) {
                count++;
                indexs = i;
            }
        }
        return (count);
    }
    
    // Perform a walk on the trie and return the
    // longest common prefix string
    String walkTrie(TrieTreeNode root) {
        TrieTreeNode current = root;
        indexs = 0;
        String prefix = "";
        
        while (countChildren(current) == 1 && current.endOfWord == false) {
            current = current.children[indexs];
            prefix += (char) ('a' + indexs);
        }
        return prefix;
    }
    
    public String longestCommonPrefix(String[] strs) {
        TrieTreeNode root = new TrieTreeNode();
        for (String word : strs) {
            insert(root, word);
        }
        
        return walkTrie(root);
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

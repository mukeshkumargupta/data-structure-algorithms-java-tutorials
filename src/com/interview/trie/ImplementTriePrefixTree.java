package com.interview.trie;

import java.util.*;
/*
 * https://leetcode.com/problems/implement-trie-prefix-tree
 * Note: Insertion recursive and search iterative is fast
 * Category: Medium
 * Related: https://leetcode.com/problems/design-add-and-search-words-val-structure/
 * https://leetcode.com/problems/design-search-autocomplete-system/
 * https://leetcode.com/problems/replace-words/
 * https://leetcode.com/problems/implement-magic-dictionary/
 * https://leetcode.com/problems/implement-trie-ii-prefix-tree/
 * 
 * 
 * 
 */
public class ImplementTriePrefixTree {
    private class TrieTreeNode {
        Map<Character, TrieTreeNode> children;
        boolean endOfWord;
        public TrieTreeNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieTreeNode root;
    public ImplementTriePrefixTree() {
        root = new TrieTreeNode();
    }

    /**
     * Iterative implementation of insert into trie
     */
    public void insert(String word) {
        insertRecursive(word);
    }

    /**
     * Recursive implementation of insert into trie
     */
    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }


    private void insertRecursive(TrieTreeNode current, String word, int index) {
        if (index == word.length()) {
            //if end of word is reached then mark endOfWord as true on current TreeNode
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieTreeNode TreeNode = current.children.get(ch);

        //if TreeNode does not exists in map then create one and put it into map
        if (TreeNode == null) {
            TreeNode = new TrieTreeNode();
            current.children.put(ch, TreeNode);
        }
        insertRecursive(TreeNode, word, index + 1);
    }

    /**
     * Iterative implementation of search into trie.
     */
    public boolean search(String word) {
        TrieTreeNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieTreeNode TreeNode = current.children.get(ch);
            //if TreeNode does not exist for given char then return false
            if (TreeNode == null) {
                return false;
            }
            current = TreeNode;
        }
        //return true of current's endOfWord is true else return false.
        return current.endOfWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieTreeNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            TrieTreeNode child = current.children.get(ch);
            if (child == null) {
                return false;
            }
            current = child;
        }
        return true; 
        
    }
}

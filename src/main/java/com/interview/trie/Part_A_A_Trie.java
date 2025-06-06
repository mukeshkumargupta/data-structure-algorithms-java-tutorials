package com.interview.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 04/25/2017
 * @author Mukesh Kumar Gupta
 *
 * Insert/delete/search into trie val structure
 * https://www.youtube.com/watch?v=AXjmTQ8LEoI
 * Trie tag: leetcode: https://leetcode.com/tag/trie/
 *
 * Reference
 * https://en.wikipedia.org/wiki/Trie
 * Category: Medium, Must Do, Fundamental
 */
public class Part_A_A_Trie {

    private class TrieTreeNode {
        Map<Character, TrieTreeNode> children;
        boolean endOfWord;
        public TrieTreeNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieTreeNode root;
    public Part_A_A_Trie() {
        root = new TrieTreeNode();
    }

    /**
     * Iterative implementation of insert into trie
     */
    public void insert(String word) {
        TrieTreeNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieTreeNode treeNode = current.children.get(ch);
            if (treeNode == null) {
                treeNode = new TrieTreeNode();
                current.children.put(ch, treeNode);
            }
            current = treeNode;
        }
        //mark the current TreeNodes endOfWord as true
        current.endOfWord = true;
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
        TrieTreeNode treeNode = current.children.get(ch);

        //if TreeNode does not exists in map then create one and put it into map
        if (treeNode == null) {
            treeNode = new TrieTreeNode();
            current.children.put(ch, treeNode);
        }
        insertRecursive(treeNode, word, index + 1);
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

    /**
     * Recursive implementation of search into trie.
     */
    public boolean searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }
    private boolean searchRecursive(TrieTreeNode current, String word, int index) {
        if (index == word.length()) {
            //return true of current's endOfWord is true else return false.
            return current.endOfWord;
        }
        char ch = word.charAt(index);
        TrieTreeNode treeNode = current.children.get(ch);
        //if TreeNode does not exist for given char then return false
        if (treeNode == null) {
            return false;
        }
        return searchRecursive(treeNode, word, index + 1);
    }

    /**
     * Delete word from trie.
     */
    public void delete(String word) {
        delete(root, word, 0);
    }

    /**
     * Returns true if parent should delete the mapping
     */
    private boolean delete(TrieTreeNode current, String word, int index) {
        if (index == word.length()) {
            //when end of word is reached only delete if currrent.endOfWord is true.
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieTreeNode TreeNode = current.children.get(ch);
        if (TreeNode == null) {
            return false;
        }
        boolean shouldDeleteCurrentTreeNode = delete(TreeNode, word, index + 1);

        //if true is returned then delete the mapping of character and trieTreeNode reference from map.
        if (shouldDeleteCurrentTreeNode) {
            current.children.remove(ch);
            //return true if no mappings are left in the map.
            return current.children.size() == 0;
        }
        return false;
    }
}

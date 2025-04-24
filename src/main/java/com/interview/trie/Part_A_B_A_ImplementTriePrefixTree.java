package com.interview.trie;

import java.util.*;
/*
 * https://leetcode.com/problems/implement-trie-prefix-tree
 * https://www.youtube.com/watch?v=dBGUmUQhjaM&list=PLgUwDviBIf0pcIDCZnxhv0LkHf5KzG9zp&index=1 Taken form takeyouforward
 * Note: Insertion recursive and search iterative is fast
 * Category: Medium, Fundamental, Must Do
 * Related:
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/ Medium
 * https://leetcode.com/problems/design-search-autocomplete-system/ Hard
 * https://leetcode.com/problems/replace-words/ Medium
 * https://leetcode.com/problems/implement-magic-dictionary/ Medium
 * https://leetcode.com/problems/encrypt-and-decrypt-strings/ Hard
 * https://leetcode.com/problems/implement-trie-ii-prefix-tree/ Medium Locked
 * https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/ Hard
 * https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/ Easy
 * 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 * 
 */
public class Part_A_B_A_ImplementTriePrefixTree {
    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public Part_A_B_A_ImplementTriePrefixTree() {
        root = new TrieNode();
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


    private void insertRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //if end of word is reached then mark endOfWord as true on current TreeNode
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode trieNode = current.children.get(ch);

        //if TreeNode does not exists in map then create one and put it into map
        if (trieNode == null) {
            trieNode = new TrieNode();
            current.children.put(ch, trieNode);
        }
        insertRecursive(trieNode, word, index + 1);
    }

    /**
     * Iterative implementation of search into trie.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode TreeNode = current.children.get(ch);
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
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            TrieNode child = current.children.get(ch);
            if (child == null) {
                return false;
            }
            current = child;
        }
        return true; 
        
    }
}

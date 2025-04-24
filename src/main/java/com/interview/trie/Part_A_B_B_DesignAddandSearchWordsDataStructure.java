package com.interview.trie;

/*
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * Category: Medium, Google, Facebook, Must Do
 * Video Explanation: https://www.youtube.com/watch?v=4OyefqK-LDA&list=PL1w8k37X_6L_24-tbM6l0AGEaHoLFEtQv&index=22
 * Runtime: 50 ms, faster than 66.10% of Java online submissions.
 * Memory Usage: 49.5 MB, less than 95.21% of Java online submissions.
 *
 * Related Problems:
 * - https://leetcode.com/problems/implement-trie-prefix-tree/ (Medium)
 * - https://leetcode.com/problems/prefix-and-suffix-search/ (Hard)
 * - https://leetcode.com/problems/match-substring-after-replacement/ (Hard)
 * - https://leetcode.com/problems/sum-of-prefix-scores-of-strings/ (Hard)
 * - https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/ (Hard)
 * - https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/ (Easy)
 *
 * Problem Statement:
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * WordDictionary() => Initializes the object.
 * void addWord(word) => Adds word to the data structure, it can be matched later.
 * boolean search(word) => Returns true if there is any string in the data structure that matches word or false otherwise.
 *   The word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 * Input:
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output:
 * [null,null,null,null,false,true,true,true]
 */

import java.util.*;
public class Part_A_B_B_DesignAddandSearchWordsDataStructure {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        TrieNode () {
            this.children = new HashMap<>();
            this.endOfWord = false;
        }
    }
    TrieNode root = null;

    /** Initialize your data structure here. */
    public Part_A_B_B_DesignAddandSearchWordsDataStructure() {
        root = new TrieNode();
    }
    
    
    /** Inserts a word into the trie. */
    public void addWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            TrieNode child = current.children.get(ch);
            if (child == null) {
                child = new TrieNode();
                current.children.put(ch, child);
            }
            current = child;
        }
        current.endOfWord = true; 
    }
    
    public boolean search(String word) {
       
        return searchInNode(word, root);
    }
    
    /** Returns if the word is in the trie. */
    public boolean searchInNode(String word, TrieNode node) {
        TrieNode current = node;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            //System.out.println(ch);
            if (ch == '.') {
                for(TrieNode child: current.children.values())
                    if(child != null && searchInNode(word.substring(i+1), child)) return true;
                return false;
            } else {

                TrieNode child = current.children.get(ch);
                if (child == null) {
                    return false;
                }
                current = child; 
            }

        }
        return current.endOfWord; 
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

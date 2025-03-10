package com.interview.trie;
/*
 * https://www.youtube.com/watch?v=RV0QeTyHZxo
 * Category: Medium, Must Do
 * https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/ Locked 
 * Solved: https://www.codingninjas.com/codestudio/problems/count-distinct-substrings_985292?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=0
 */
public class Part_A_E_CountDistinctSubstrings {
   static final int ALPHABET_SIZE = 26;
    
    // trie TreeNode
    static class TrieTreeNode {
        TrieTreeNode[] children;
        
        TrieTreeNode() {
            children = new TrieTreeNode[ALPHABET_SIZE];
        }
    };
    
    public static void insert(TrieTreeNode root, String word, int[] count) {
        TrieTreeNode current = root;
        
        for (int i = 0; i < word.length(); i++) {
            TrieTreeNode node = current.children[word.charAt(i) - 'a'];
            if (node == null) {
                node = new TrieTreeNode();
                current.children[word.charAt(i) - 'a'] = node;// add it
                count[0] += 1;
            }
            current = node;
        }     
    }
    public static int countDistinctSubstrings(String s) 
    {
        /*TC: O(N2)
        SC: We can say but explain like each node may have 26 then other child node have other 26 but depands upon usecase
        Using bruitforce:
           generate all cases then take set to check whether it exist or not
           TC: N2 * Log M where N is lenth of word and M is size of set
           SC: N2 * N/2 where N2 is all combinatio and supose averahe length of all word is: N/2, may be one word 1 chart and another all character
           
           Trie is always better because in try we dont save all combination in memory
            
        */
        //    Write your code here.
      TrieTreeNode root = new TrieTreeNode();
        int[] count = new int[1];
      for (int i = 0; i < s.length(); i++) {
          String word = s.substring(i);
          insert(root, word, count);
      }
        return count[0] + 1;//1 for empty string
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

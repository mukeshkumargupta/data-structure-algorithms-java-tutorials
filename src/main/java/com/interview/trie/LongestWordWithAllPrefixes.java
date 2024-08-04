package com.interview.trie;

/*
 * https://www.youtube.com/watch?v=AWnBa91lThI
 * Category: Medium, Must Do
 * https://leetcode.com/problems/longest-word-with-all-prefixes/ Locked
 * Code submitted: https://www.codingninjas.com/codestudio/problems/complete-string_2687860?utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_tries_videos&leftPanelTab=1
 */
public class LongestWordWithAllPrefixes {
    static final int ALPHABET_SIZE = 26;
    
    // trie TreeNode
    static class TrieTreeNode {
        TrieTreeNode[] children;
        boolean endOfWord;
        
        TrieTreeNode() {
            children = new TrieTreeNode[ALPHABET_SIZE];
            endOfWord = false;
        }
    };
    
    public static void insert(TrieTreeNode root, String word) {
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
    
    public static boolean checkIfPrefixExist(TrieTreeNode root, String word) {
        TrieTreeNode current = root;
        for (int i = 0; i < word.length(); i++) {
            TrieTreeNode node = current.children[word.charAt(i) - 'a'];
            if (node != null) {
                current = node;
                if (!node.endOfWord) {
                    return false;
                }
            } else {
                return false;
            }
            
        }
        return current.endOfWord;
        
    }
  public static String completeString(int n, String[] a) {
    // Write your code here.
      //make trye
      TrieTreeNode root = new TrieTreeNode();
      for (String word: a) {
          insert(root, word);
      }
      //Find longest
      String result = "";
      for (String word: a) {
          if (checkIfPrefixExist(root, word)) {
              //check if lognger
              if (word.length() > result.length()) {//if bigger then take it
                  result = word;
              } else if (word.length() == result.length() && word.compareTo(result) < 0) {//if length same 
                  result = word;
              }
              
          }
      }
      if (result.equals("")) {
          return "None";
      } else {
          return result;
      }
  }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /*
         * 3
5
ak szhkb a g hy 
5
vfjq kez vfj dotkr vfjqo 
6
n l i um ar xcfyc 
         */
        int n = 5;
        String[] input = {"ak", "szhkb", "a", "g", "hy"};
        System.out.println(LongestWordWithAllPrefixes.completeString(n, input));
    }
    
}

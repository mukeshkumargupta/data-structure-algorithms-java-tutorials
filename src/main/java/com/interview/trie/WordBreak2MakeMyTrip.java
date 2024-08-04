package com.interview.trie;
import java.util.*;

import com.interview.trie.WordBreakII.TrieNode;

import java.lang.*;
import java.io.*;

public class WordBreak2MakeMyTrip {
    /*
    Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

    Note that the same word in the dictionary may be reused multiple times in the segmentation.


    Example 1:

    Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
    Output: ["cats and dog","cat sand dog"]
    Example 2:

    Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
    Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
    Explanation: Note that you are allowed to reuse a dictionary word.
    */

    class TrieNode {
        TrieNode[] children;
        boolean endOfWords;
        TrieNode() {
            children = new TrieNode[26];
            endOfWords = false;
        }
    }
    TrieNode root;
    void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode temp = current.children[ch - 'a'];
            if (temp == null) {
                temp = new TrieNode();
                current.children[ch - 'a'] = temp;
            }
            current = temp;
        }
        current.endOfWords = true;
    }
    
    boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode temp = current.children[ch - 'a'];
            if (temp == null) {
                return false;
            }
            current = temp;
        }
        return current.endOfWords;
    }
    
    void solve(String input, String partition, int pos, List<String> result, Boolean[] dp, int l) {
        //Base case
        if (pos == l) {
            result.add(partition);
            return;

        }
        
        /*Memoization will not work here, because there is no overlapping sub problem"
        /*if (dp[pos] != null) {
            return dp[pos];
        }*/

        partition += " ";
        //Partitioning 
        //boolean found = false;
        for (int i = pos; i < l; i++) {
            String st = input.substring(pos, i+1);
            //found= search(st) && solve(input, partition + st, i+1, result, dp, l);
            if (search(st)) {
                solve(input, partition + st, i+1, result, dp, l);
            }
        }
        //return dp[pos] = found;
    }
        
    public void wordBreak(String s, List<String> wordDict) {
        root = new TrieNode();


        //TC: length(Max length of word) * No of word 
        
        for (String word: wordDict) {
            insert(word);
        }

        int l = s.length();
        List<String> result = new ArrayList<>();
        Boolean[] dp = new Boolean [l];
        for (int i = 0; i < l; i++) {
            String st = s.substring(0, i+1);
            if (search(st) ) {
                solve(s, st, i+1, result, dp, l);
            }
        }
        for (String word: result) {
            System.out.println(word);
        }
        
    }
        
        public static void main(String[] args) {
            WordBreak2MakeMyTrip instance = new WordBreak2MakeMyTrip();
            List<String> dict = new ArrayList();
            //"cat","cats","and","sand","dog"
            dict.add("cat");
            dict.add("cats");
            dict.add("and");
            dict.add("sand");
            dict.add("dog");
            String input = "catsanddog";
            instance.wordBreak(input, dict);
            
        }
}

package com.interview.trie;

import java.util.*;

/*
 * https://leetcode.com/problems/word-break-ii/
 * https://www.youtube.com/watch?v=fNVs1J2KCyo
 * Category: Hard, tricky, VImp, MakeMyTrip, Google
 * Related: https://leetcode.com/problems/concatenated-words/ Hard
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
 */
public class WordBreakII {
    ///////////////////////////////////////////////////////
    List<String> ans;//Store all valid sentences
   
    
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

    
    void wordBreakUtil(String s,String st,int pos){
        if(pos==s.length()){
            ans.add(st);
            return;
        }
        st += " ";
        for(int i=pos;i<s.length();++i){
            if(search(s.substring(pos,i+1)))
                wordBreakUtil(s,st+s.substring(pos,i+1),i+1);
        }
    }
    public List<String> wordBreak(String s, List<String> wordDict) {
        /*
         * TC: No of word * (Max length of word present) + 2 ^ M where M is length of input
         */
        ans = new ArrayList<>();
        root = new TrieNode();
        for(String word: wordDict)
            insert(word);
        
        for(int i=0;i<s.length();++i){
            if(search(s.substring(0,i+1)))
                wordBreakUtil(s,s.substring(0,i+1),i+1);
        }
        return ans;
        
    }
    
    ///////////////////////////////////////////////////////
    /*Actual code to visualize root so addition char memeber is added here but in actual it is not required
    */
    class trienode{
        char c;
        int we;
        trienode[] child;
        trienode(char c){
            we = 0;
            this.c = c;
            this.child = new trienode[26];
            for(int i=0;i<26;++i)
                child[i]=null;
        }
    };
    trienode rootApproach2;//root of TRIE
    void insertInTrieApproach2( String word){
        trienode curr = rootApproach2;
        int idx;
        for(int i=0;i<word.length();++i){
            idx = word.charAt(i)-'a';
            if(curr.child[idx] == null) {
               curr.child[idx] = new trienode(word.charAt(i)); 
            }
                
            curr = curr.child[idx];
        }
        curr.we += 1;
    }
    boolean searchInTrieApproach2(String s){
        trienode curr = rootApproach2;
        int idx;
        for(int i=0;i<s.length();++i){
            idx = s.charAt(i)-'a';
            if(curr.child[idx] == null)
                return false;
            curr = curr.child[idx];
        }
        return curr.we>0;
    }
    
    void wordBreakUtilApproach2(String s,String st,int pos){
        if(pos==s.length()){
            ans.add(st);
            return;
        }
        st += " ";
        for(int i=pos;i<s.length();++i){
            if(searchInTrie(s.substring(pos,i+1)))
                wordBreakUtilApproach2(s,st+s.substring(pos,i+1),i+1);
        }
    }
    public List<String> wordBreakApproach2(String s, List<String> wordDict) {
        /*
         * Runtime: 1 ms, faster than 95.49% of Java online submissions for Word Break II.
Memory Usage: 42.9 MB, less than 5.09% of Java online submissions for Word Break II.
         */
        ans = new ArrayList<>();
        rootApproach2 = new trienode('/');
        for(String word: wordDict)
            insertInTrie(word);
        
        for(int i=0;i<s.length();++i){
            if(searchInTrie(s.substring(0,i+1)))
                wordBreakUtil(s,s.substring(0,i+1),i+1);
        }
        return ans;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        WordBreakII instance = new WordBreakII();
        List<String> dict = new ArrayList<>();
        //"cat","cats","and","sand","dog"
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        //TC: length(Max length of word) * No of word 
        

        String input = "catsanddog";
        List<String> result = instance.wordBreak(input, dict);
        for (String word: result) {
            System.out.println(word);
            
        }
        
        
    }
    
}

package com.interview.bfsdfs;

import java.util.*;
/*
 * https://leetcode.com/problems/word-ladder/
 * https://www.youtube.com/watch?v=ZVJ3asMoZ18&t=34s
 * Category: Hard, Tricky
 * Related: https://leetcode.com/problems/word-ladder-ii/ Hard
 * https://leetcode.com/problems/minimum-genetic-mutation/ Medium
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
Accepted
695,969
Submissions
2,024,839
 */
public class WordLadder {
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /*
         * TC: 26*N(Length of word)*N(STring comparision)*W(No od words)) where N is length of word and W is no od word
         */
        Set<String> set = new HashSet();
        boolean isPresent = false; 
        // Insert all words from Dict in set
        for (String word : wordList) {
            if (endWord.compareTo(word) == 0)
                isPresent = true;
            set.add(word); // Insert word in Dict
        }
        if (isPresent == false) // endWord is not present in Dict
            return 0;
        
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int depth = 1;
        
        while (!q.isEmpty()) {
            int size = q.size(); // No of elements at a level
            for (int sz = 0; sz < size; sz++) {
                String curr = q.remove();
                // check for all possible 1 depth words
                for (int i = 0; i < curr.length(); ++i) // For each index
                {
                    StringBuilder temp1 = new StringBuilder(curr);
                    for (char c = 'a'; c <= 'z'; ++c) // Try all possible chars
                    {
                        temp1.setCharAt(i, c);
                        String temp = temp1.toString();

                        if (set.contains(temp)) {
                            if (temp.compareTo(endWord) == 0)
                                return depth + 1; // endWord found
                            
                            q.add(temp);
                            set.remove(temp);
                        }
                    }
                }
            }
            depth += 1;
        }
        return 0;
    }
    
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>();
        boolean isPresent = false; 
        for (String word : wordList) {
            if (endWord.compareTo(word) == 0)
                isPresent = true;
            set.add(word);
        }
        if (isPresent == false)
            return 0;
        
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int depth = 1;
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int sz = 0; sz < size; sz++) {
                String curr = q.remove();
                
                for (int i = 0; i < curr.length(); ++i)
                {
                    StringBuilder temp1 = new StringBuilder(curr);
                    for (char c = 'a'; c <= 'z'; ++c)
                    {
                        temp1.setCharAt(i, c);
                        String temp = temp1.toString();

                        if (set.contains(temp)) {
                            if (temp.compareTo(endWord) == 0)
                                return depth + 1;
                            
                            q.add(temp);
                            set.remove(temp);
                        }
                    }
                }
            }
            depth += 1;
        }
        return 0;
    }
 

    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

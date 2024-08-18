package com.interview.graph.PartJGraphSearchProblems;

import java.util.*;
/*
 * https://leetcode.com/problems/word-ladder/
 * https://www.youtube.com/watch?v=ZVJ3asMoZ18&t=34s
 * Category: Hard, Tricky, Top150
 * Related: https://leetcode.com/problems/word-ladder-ii/ Hard
 * https://leetcode.com/problems/minimum-genetic-mutation/ Medium
 * https://leetcode.com/problems/words-within-two-edits-of-dictionary/ Medium
 *
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
/*
        Explanation
        Initialization:

        We first check if the endWord is present in the wordList. If not, return 0 because it's impossible to transform beginWord to endWord.
        Use a Set<String> for wordList to allow quick lookup and removal of words.
        BFS Setup:

        Initialize a queue and add beginWord to it. Set depth to 1 because we start counting from the beginWord.
        BFS Traversal:

        While the queue is not empty, process each word at the current depth level.
        For each word, try to transform it by changing each character to every other possible lowercase letter.
        If the transformed word is endWord, return the current depth + 1 as the shortest path length.
        If the transformed word is in the wordSet, add it to the queue for further exploration and remove it from the set to prevent revisiting.
        Return:

        If the queue is exhausted without finding endWord, return 0, indicating no valid transformation sequence exists.
        Time Complexity
        Time Complexity:
        𝑂
        (
        𝑀
        ×
        𝑁
        )
        O(M×N), where
        𝑀
        M is the length of each word and
        𝑁
        N is the number of words in the wordList. We potentially transform each word in the list at each character position.
        Space Complexity:
        𝑂
        (
        𝑁
        ×
        𝑀
        )
        O(N×M), where
        𝑁
        N is the number of words, as we use a queue and a set to store the words.
 */
public class WordLadder {
    /*
     * TC: 26*N(Length of word)*N(STring comparision)*W(No od words)) where N is length of word and W is no od word
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Add all words from wordList to a set for quick lookup
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0; // If endWord is not in wordList, no transformation is possible
        }

        // Queue for BFS
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int depth = 1; // Start from depth 1 since we include beginWord in the path

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // Try all possible transformations of currentWord
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    // Change the current character to every other lowercase letter
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue; // Skip the original character
                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        // If the newWord is the endWord, return the depth + 1
                        if (newWord.equals(endWord)) {
                            return depth + 1;
                        }

                        // If newWord is in wordSet, add it to the queue and remove from the set
                        if (wordSet.contains(newWord)) {
                            queue.add(newWord);
                            wordSet.remove(newWord); // Remove to prevent revisiting
                        }
                    }

                    // Restore the original character
                    wordChars[j] = originalChar;
                }
            }
            depth++; // Increment depth after exploring all words at the current level
        }

        return 0; // If no transformation sequence found
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        int result = wordLadder.ladderLength(beginWord, endWord, wordList);
        System.out.println("Length of shortest transformation sequence: " + result);
    }
    
}

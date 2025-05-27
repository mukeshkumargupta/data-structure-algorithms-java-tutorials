package com.interview.graph.PartJGraphSearchProblems;

import java.util.*;

/*
 * Problem: Word Ladder
 * https://leetcode.com/problems/word-ladder/
 * Category: Hard, Tricky, Top150
 * Related Problems:
 * - https://leetcode.com/problems/word-ladder-ii/ (Hard)
 * - https://leetcode.com/problems/minimum-genetic-mutation/ (Medium)
 * - https://leetcode.com/problems/words-within-two-edits-of-dictionary/ (Medium)
 *
 * Description:
 * A transformation sequence from word `beginWord` to `endWord` using a dictionary `wordList`
 * is a sequence of words `beginWord -> s1 -> s2 -> ... -> sk` such that:
 * - Every adjacent pair of words differs by a single letter.
 * - Every `si` (1 <= i <= k) is in `wordList`. Note that `beginWord` does not need to be in `wordList`.
 * - `sk` == `endWord`.
 *
 * Return the number of words in the shortest transformation sequence from `beginWord` to `endWord`,
 * or 0 if no such sequence exists.
 *
 * Constraints:
 * - 1 <= beginWord.length <= 10
 * - endWord.length == beginWord.length
 * - 1 <= wordList.length <= 5000
 * - wordList[i].length == beginWord.length
 * - beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * - beginWord != endWord
 * - All the words in wordList are unique.
 *
 * Example:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot", "dot", "dog", "lot", "log", "cog"]
 * Output: 5
 * Explanation: The shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> "cog".
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot", "dot", "dog", "lot", "log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, so no valid transformation exists.
 */
public class WordLadder {

    /**
     * Finds the length of the shortest transformation sequence from beginWord to endWord.
     *
     * @param beginWord The starting word.
     * @param endWord The target word.
     * @param wordList The list of valid words for transformation.
     * @return The length of the shortest transformation sequence, or 0 if no sequence exists.
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

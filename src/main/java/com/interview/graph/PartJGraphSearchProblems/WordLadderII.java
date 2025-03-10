package com.interview.graph.PartJGraphSearchProblems;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=mIZJIuMpI2M&t=1s Good explanation
 * https://leetcode.com/problems/word-ladder-ii/submissions/
 * https://leetcode.com/problems/word-ladder-ii/discuss/1715172/Java-oror-Explained-in-detail-oror-DFS-%2B-BFS
 * Category: Hard, Must Do
 * 
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 1000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */
public class WordLadderII {
    /*
     * 1. Brute Force Approach - DFS (Exponential)
     *
     * Approach:
     * - Explore all possible sequences using Depth First Search (DFS).
     * - Each transformation step checks all words in `wordList`.
     * - If a transformation is valid (one-letter change), we continue recursively.
     * - Once `endWord` is reached, we compare its length with the shortest found so far.
     *
     * Complexity Analysis:
     * - Time Complexity: O(N!)
     *   - In the worst case, all permutations of words are explored.
     */
    private static class BruitForce {
        private List<List<String>> result = new ArrayList<>();
        private int minLength = Integer.MAX_VALUE;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return result; // Early exit if endWord is not in dictionary

            List<String> path = new ArrayList<>();
            path.add(beginWord);
            dfs(beginWord, endWord, wordSet, path);
            return result;
        }

        private void dfs(String current, String endWord, Set<String> wordSet, List<String> path) {
            // If the path length exceeds minLength, stop further recursion
            if (path.size() > minLength) return;

            if (current.equals(endWord)) {
                if (path.size() < minLength) {
                    result.clear();
                    minLength = path.size();
                }
                result.add(new ArrayList<>(path));
                return;
            }

            for (String neighbor : getNeighbors(current, wordSet)) {
                if (!path.contains(neighbor)) {
                    path.add(neighbor);
                    dfs(neighbor, endWord, wordSet, path);
                    path.remove(path.size() - 1);
                }
            }
        }

        private List<String> getNeighbors(String word, Set<String> wordSet) {
            List<String> neighbors = new ArrayList<>();
            char[] chars = word.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                char originalChar = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalChar) continue;
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (wordSet.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
                chars[i] = originalChar;
            }
            return neighbors;
        }
    }

    /*
     * 2. Better Approach - BFS + Backtracking
     *
     * Optimized Idea:
     * - Instead of exploring all paths, BFS finds the shortest path.
     * - Graph Construction: We build a map `adjList` where each word is linked to its neighbors.
     * - Backtracking: After BFS, we reconstruct paths using DFS.
     *
     * Time Complexity:
     * - O(N × M²)
     *   - N: Number of words in the dictionary.
     *   - M: Length of each word.
     * - BFS runs in O(NM), and backtracking takes O(NM²).
     */

    private static class Better {
        private Map<String, List<String>> adjList = new HashMap<>();
        private List<List<String>> result = new ArrayList<>();
        private int minLength = Integer.MAX_VALUE;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return result;

            // BFS to build adjacency list
            Map<String, Integer> distance = new HashMap<>();
            bfs(beginWord, endWord, wordSet, distance);

            List<String> path = new ArrayList<>();
            path.add(beginWord);
            backtrack(beginWord, endWord, distance, path);
            return result;
        }

        private void bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, Integer> distance) {
            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);
            distance.put(beginWord, 0);

            while (!queue.isEmpty()) {
                String word = queue.poll();
                int currentDistance = distance.get(word);

                for (String neighbor : getNeighbors(word, wordSet)) {
                    adjList.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(word);

                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, currentDistance + 1);
                        queue.add(neighbor);
                    }
                }
            }
        }

        private void backtrack(String current, String endWord, Map<String, Integer> distance, List<String> path) {
            if (current.equals(endWord)) {
                result.add(new ArrayList<>(path));
                return;
            }

            for (String neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (distance.get(neighbor) == distance.get(current) + 1) {
                    path.add(neighbor);
                    backtrack(neighbor, endWord, distance, path);
                    path.remove(path.size() - 1);
                }
            }
        }

        private List<String> getNeighbors(String word, Set<String> wordSet) {
            List<String> neighbors = new ArrayList<>();
            char[] chars = word.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                char originalChar = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalChar) continue;
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (wordSet.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
                chars[i] = originalChar;
            }
            return neighbors;
        }
    }
}

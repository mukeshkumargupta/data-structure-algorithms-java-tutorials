package com.interview.graph.PartBTopologicalSorting;

import java.util.*;

/*
 * 🛸 Problem: Alien Dictionary
 *
 * You are given a list of words from an alien language, where the words are sorted
 * lexicographically *according to the rules of this alien language*. The alien language
 * uses the same lowercase Latin characters ('a' to 'z'), but the order among the characters
 * is *unknown* to you.
 *
 * 🔍 Goal:
 * Your task is to **determine a valid order of characters** in the alien language based on the
 * given sorted word list.
 *
 * 🔧 Assumptions:
 * - The given list of words is already sorted as per the alien dictionary's order.
 * - The alien dictionary may have multiple valid character orderings.
 * - If it’s impossible to determine the order (e.g., due to a cycle), return an empty string.
 * - All letters in the alien language are lowercase Latin letters, but not all letters
 *   are guaranteed to appear in the input.
 *
 * ⚠️ Edge Cases:
 * - If two adjacent words are in the wrong order (e.g., "abc" and "ab"), it’s invalid.
 * - If a cycle is detected in the character dependency graph, a valid order is impossible.
 *
 * 🧠 Key Idea:
 * - The task reduces to **topological sorting** of a graph where each node is a character.
 * - We create directed edges between characters where the first difference in adjacent words
 *   implies character precedence.
 *
 * 🧱 Example:
 * Input: words = ["wrt", "wrf", "er", "ett", "rftt"]
 * Output: "wertf" (or another valid topological sort)
 * Explanation:
 * From "wrt" and "wrf", we know 't' comes before 'f'.
 * From "wrf" and "er", we know 'w' comes before 'e'.
 * From "er" and "ett", we know 'r' comes before 't'.
 * From "ett" and "rftt", we know 'e' comes before 'r'.
 * Combining these relations and topologically sorting them gives the answer.
 *
 * 🔗 Problem link: https://leetcode.com/problems/alien-dictionary/
 * 📺 Video explanation: https://www.youtube.com/watch?v=U3N_je7tWAs&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=17
 */
public class PartB_B_AlientDictionary {
    /*
     * Explanation:
     *
     * 1. Graph Construction:
     *    - We build a directed graph where each node is a character in the alien language.
     *    - By comparing adjacent words, we find the first differing character to establish
     *      a directed edge from the character in the first word to the character in the second word.
     *    - This represents the precedence (order) relationship between characters.
     *
     * 2. DFS Cycle Detection and Topological Sort:
     *    - We use DFS to traverse nodes and detect cycles.
     *    - Each node has three states: unvisited (0), visiting (in recursion stack), and visited.
     *    - If we reach a node already in the recursion stack, it means a cycle exists, so the order is invalid.
     *    - When DFS finishes exploring a node’s neighbors, we push it onto a stack.
     *    - The final topological order is the reverse of the stack (pop all elements).
     *
     * 3. BFS (Kahn’s Algorithm) for Topological Sort:
     *    - We compute the in-degree (number of incoming edges) for each node.
     *    - Initialize a queue with all nodes having in-degree 0 (no dependencies).
     *    - Repeatedly dequeue a node, add it to the result, and reduce in-degree of its neighbors.
     *    - If neighbors’ in-degree becomes zero, add them to the queue.
     *    - If we process all nodes, we get a valid topological order.
     *    - If some nodes remain unprocessed (due to cycles), return invalid.
     *
     * 4. Edge Cases:
     *    - If word2 is a prefix of word1 and appears after it, no valid order exists.
     *    - Cycles in graph make it impossible to derive a valid order.
     *
     * --------------------------------------------
     * Time Complexity:
     *
     * Let:
     *  - N = number of words
     *  - L = average length of each word
     *  - C = number of unique characters in all words (≤ 26)
     *
     * Graph Construction:
     *  - Compare adjacent pairs: O(N * L)
     *  - Each comparison at most L characters until difference found
     *
     * DFS Approach:
     *  - Building graph: O(N * L)
     *  - DFS visits each node once, and edges once: O(C + E)
     *  - In worst case, E (edges) ≤ C² (if fully connected)
     *  - Overall: O(N * L + C + E)
     *
     * BFS Approach:
     *  - Same graph construction: O(N * L)
     *  - BFS topological sort: O(C + E)
     *  - Overall: O(N * L + C + E)
     *
     * Since C ≤ 26 (letters a-z), C and E are small constants for most cases, so dominant cost is O(N * L).
     *
     * --------------------------------------------
     * Space Complexity:
     *
     * - Adjacency list: O(C + E)
     * - visited, recursion stack or in-degree maps: O(C)
     * - Stack or queue for topo order: O(C)
     * - Result string: O(C)
     *
     * Since C ≤ 26, space is effectively constant relative to input size,
     * but considering input characters, space is O(C + E).
     */

private static class DFSApproach {
    // Stack to hold the topological order of characters
    private Stack<Character> topologicalOrderStack = new Stack<>();

    // Method to perform DFS and detect cycles
    private boolean dfs(char node, Map<Character, List<Character>> adjList,
                        Map<Character, Integer> visited, Map<Character, Boolean> recStack) {
        // Mark the current node as visited and add it to the recursion stack
        visited.put(node, 1);
        recStack.put(node, true);

        // Recur for all adjacent nodes
        for (char neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
            if (visited.get(neighbor) == 0) {
                if (dfs(neighbor, adjList, visited, recStack)) {
                    return true; // Cycle detected
                }
            } else if (recStack.get(neighbor)) {
                return true; // Cycle detected
            }
        }

        // Remove the node from the recursion stack and add to the topological order
        recStack.put(node, false);
        topologicalOrderStack.push(node);
        return false;
    }

    // Method to build the graph and detect cycle
    public String alienOrder(String[] words) {
        // Build the adjacency list
        Map<Character, List<Character>> adjList = new HashMap<>();
        Set<Character> allCharacters = new HashSet<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                allCharacters.add(c);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLength = Math.min(word1.length(), word2.length());

            for (int j = 0; j < minLength; j++) {
                char char1 = word1.charAt(j);
                char char2 = word2.charAt(j);

                if (char1 != char2) {
                    if (!adjList.containsKey(char1)) {
                        adjList.put(char1, new ArrayList<>());
                    }
                    if (!adjList.containsKey(char2)) {
                        adjList.put(char2, new ArrayList<>());
                    }
                    adjList.get(char1).add(char2);
                    break;
                }
            }
        }

        // Initialize data structures
        Map<Character, Integer> visited = new HashMap<>();
        Map<Character, Boolean> recStack = new HashMap<>();

        for (char c : allCharacters) {
            visited.put(c, 0);
            recStack.put(c, false);
        }

        // Check for cycles and perform DFS
        for (char c : allCharacters) {
            if (visited.get(c) == 0) {
                if (dfs(c, adjList, visited, recStack)) {
                    return ""; // Cycle detected, invalid order
                }
            }
        }

        // Build the result string from the topological order stack
        StringBuilder sb = new StringBuilder();
        while (!topologicalOrderStack.isEmpty()) {
            sb.append(topologicalOrderStack.pop());
        }

        return sb.toString();
    }
}


private static class BFSApproach {

    //Bfs approach
        // Method to perform BFS and find the topological order
    public String alienOrder(String[] words) {
        // Build the adjacency list and compute in-degrees
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        Set<Character> allCharacters = new HashSet<>();

        // Initialize all characters and in-degree
        for (String word : words) {
            for (char c : word.toCharArray()) {
                allCharacters.add(c);
                inDegree.put(c, 0);
            }
        }

        // Build the adjacency list
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLength = Math.min(word1.length(), word2.length());

            boolean foundDifference = false;
            for (int j = 0; j < minLength; j++) {
                char char1 = word1.charAt(j);
                char char2 = word2.charAt(j);

                if (char1 != char2) {
                    if (!adjList.containsKey(char1)) {
                        adjList.put(char1, new ArrayList<>());
                    }
                    if (!adjList.containsKey(char2)) {
                        adjList.put(char2, new ArrayList<>());
                    }
                    // Add edge and update in-degrees
                    if (!adjList.get(char1).contains(char2)) {
                        adjList.get(char1).add(char2);
                        inDegree.put(char2, inDegree.get(char2) + 1);
                    }
                    foundDifference = true;
                    break;
                }
            }

            // Edge case where word2 is a prefix of word1 (e.g., "abc" and "ab")
            if (!foundDifference && word1.length() > word2.length()) {
                return ""; // Invalid order
            }
        }

        // Initialize the queue with nodes having zero in-degree
        Queue<Character> queue = new LinkedList<>();
        for (char c : allCharacters) {
            if (inDegree.get(c) == 0) {
                queue.add(c);
            }
        }

        // Perform BFS to get topological order
        StringBuilder sb = new StringBuilder();
        int visitedCount = 0;

        while (!queue.isEmpty()) {
            char current = queue.poll();
            sb.append(current);
            visitedCount++;

            // Process all adjacent nodes
            for (char neighbor : adjList.getOrDefault(current, Collections.emptyList())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If we visited all nodes, return the result
        if (visitedCount == allCharacters.size()) {
            return sb.toString();
        }

        return ""; // Cycle detected or not all nodes were visited
    }


    public static void main(String[] args) {
        BFSApproach dictionary = new BFSApproach();

        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(dictionary.alienOrder(words1)); // Output: "wertf" or other valid topological order

        String[] words2 = {"z", "x", "z"};
        System.out.println(dictionary.alienOrder(words2)); // Output: ""

        String[] words3 = {"abc", "ab"};
        System.out.println(dictionary.alienOrder(words3)); // Output: ""
    }
}


}

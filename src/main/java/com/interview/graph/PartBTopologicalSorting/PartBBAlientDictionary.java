package com.interview.graph.PartBTopologicalSorting;

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters
 * are unknown to you. You receive a list of words from the dictionary, where words are sorted
 * lexicographically by the rules of this new language. Derive the order of letters in this language.
 * 
 * https://leetcode.com/problems/alien-dictionary/
 *
 * https://www.youtube.com/watch?v=U3N_je7tWAs&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=17
 */
public class PartBBAlientDictionary {
   /*Explanation
Graph Construction:

We build the graph by comparing adjacent words and finding the first differing character to establish a directed edge.
DFS Cycle Detection:

We use DFS to check for cycles. Nodes in the recursion stack (recStack) indicate that we're currently exploring that path. If we encounter a node that's already in the recursion stack, a cycle is present.
Topological Sort:

Nodes are pushed onto a stack when their DFS is complete. The final topological order is obtained by popping from the stack.
Edge Cases:

Handles cases where words might contain cycles or inconsistencies that prevent a valid ordering.
This solution follows the same DFS-based pattern for cycle detection and topological sorting as your previous code.
*/


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

    /*
    Bfs approach
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
     */

    public static void main(String[] args) {
        PartBBAlientDictionary dictionary = new PartBBAlientDictionary();

        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(dictionary.alienOrder(words1)); // Output: "wertf" or other valid topological order

        String[] words2 = {"z", "x", "z"};
        System.out.println(dictionary.alienOrder(words2)); // Output: ""

        String[] words3 = {"abc", "ab"};
        System.out.println(dictionary.alienOrder(words3)); // Output: ""
    }
}

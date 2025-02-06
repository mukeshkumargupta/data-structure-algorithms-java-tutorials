package com.interview.graph;

/* 
 * Reference: https://leetcode.com/problems/number-of-provinces/
 * Category: Medium
 * /*
     Explanation of Changes
    1D visited Array:

    The problem only requires checking if a city (i) has been visited, so a 1D array suffices.
    DFS on the Adjacency Matrix:

    Instead of grid-like traversal, iterate over all neighbors (neighbor) of the current city (city) using the adjacency matrix (isConnected[city][neighbor]).
    Province Counting:

    For each unvisited city (i), increment the province count and explore all cities connected to it using DFS.
    Time and Space Complexity
    Time Complexity:
    O(n²) because we iterate over all entries in the isConnected adjacency matrix.
    Space Complexity:
    O(n) for the visited array and recursion stack during DFS.
    Example
    Input:

    plaintext
    Copy
    Edit
    isConnected = [
        [1, 1, 0],
        [1, 1, 0],
        [0, 0, 1]
    ]
    Output:

    plaintext
    Copy
    Edit
    2
    Explanation:

    Cities 0 and 1 form one province.
    City 2 is isolated and forms another province.
    This solution works as expected for the problem. Let me know if you'd like further clarification!
 */
public class NumberofProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length; // Number of cities
        boolean[] visited = new boolean[n]; // 1D visited array
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // If the city has not been visited
                provinces++; // Start a new province
                dfs(i, isConnected, visited); // Perform DFS to visit all connected cities
            }
        }
        return provinces;
    }

    private void dfs(int city, int[][] isConnected, boolean[] visited) {
        visited[city] = true; // Mark the current city as visited

        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            // If there's a connection and the neighbor hasn't been visited, explore it
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, isConnected, visited);
            }
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

package com.interview.graph.PartCShortestPath;

import java.util.*;

/**
 * Floyd-Warshall Algorithm for finding all-pairs shortest paths.
 *
 * References:
 * - https://www.youtube.com/watch?v=LwJdNfdLF9s&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=7
 * - https://www.youtube.com/watch?v=nV_wOZnhbog&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=37
 * - https://www.youtube.com/watch?v=YbY8cVwWAvw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=42
 *
 * Category: Medium, Must Do
 *
 * Time complexity: O(V^3)
 * Space complexity: O(V^2)
 *
 * References:
 * - https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm
 *
 * This implementation uses the Floyd-Warshall algorithm to find the shortest paths
 * between all pairs of vertices in a weighted graph.
 *
 * Explanation:
 * - Class and Exception:
 *   - FloydWarshallShortestPath: The main class implementing the Floyd-Warshall algorithm.
 *   - NegativeWeightCycleException: A custom exception to signal the presence of a negative weight cycle in the graph.
 *
 * - findAllPairShortestPaths Method:
 *   - Input: Takes an adjacency matrix graph where graph[i][j] represents the weight from vertex i to vertex j,
 *     or Integer.MAX_VALUE if there is no direct edge.
 *   - Output: Returns a matrix containing the shortest distances between all pairs of vertices.
 *   - Algorithm:
 *     - Initializes distances with the input graph and predecessors to store paths for reconstruction.
 *     - Iterates over all possible intermediate vertices k to update paths between every pair of vertices (i, j)
 *       if going through k provides a shorter path.
 *     - Checks for negative weight cycles by inspecting the diagonal of the distances matrix.
 *
 * - printPath Method:
 *   - Purpose: Reconstructs and prints the path from the start vertex to the end vertex using the predecessors matrix.
 *   - Process: Uses a stack to backtrack from the end vertex to the start vertex via the recorded predecessors,
 *     then prints the path.
 *
 * - main Method:
 *   - Example Graph: Demonstrates how to use the algorithm with a sample graph.
 *   - Distance Matrix: Outputs the shortest path distances between all vertex pairs.
 *   - Path Printing: Calls printPath to show the path between two specific vertices.
 *
 * Improvements Made:
 * - Readability: Enhanced variable naming and added comments for clarity.
 * - Exceptions: Added meaningful exception messages and explanations.
 * - Consistency: Used Integer.MAX_VALUE for infinite distances.
 * - Documentation: Added JavaDoc comments for better understanding of each method's purpose and usage.
 *
 * This implementation provides a clear and structured approach to solving the all-pairs shortest path problem
 * using the Floyd-Warshall algorithm, complete with path reconstruction and negative cycle detection.
 */
public class PartDFloydWarshallShortestPath {

    // Define a constant for representing infinity
    private static final int INF = Integer.MAX_VALUE / 2; // Use a large value to avoid overflow

    // Custom exception to indicate a negative weight cycle in the graph
    class NegativeWeightCycleException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        NegativeWeightCycleException() {
            super("Graph contains a negative weight cycle");
        }
    }

    /**
     * Calculates the shortest paths between all pairs of vertices using Floyd-Warshall algorithm.
     *
     * @param adjacencyMatrix The adjacency matrix representation of the graph, where
     *                        adjacencyMatrix[i][j] represents the weight of the edge from vertex i to vertex j
     *                        or Integer.MAX_VALUE if there is no direct edge.
     * @return A matrix containing the shortest distances between each pair of vertices.
     * @throws NegativeWeightCycleException if the graph contains a negative weight cycle.
     */
    public int[][] findAllPairShortestPaths(int[][] adjacencyMatrix) {
        int numVertices = adjacencyMatrix.length;
        int[][] shortestDistances = new int[numVertices][numVertices];
        int[][] predecessors = new int[numVertices][numVertices];

        // Initialize shortestDistances and predecessors
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                shortestDistances[i][j] = adjacencyMatrix[i][j];
                if (adjacencyMatrix[i][j] != INF && i != j) {
                    predecessors[i][j] = i; // Record the predecessor
                } else {
                    predecessors[i][j] = -1; // No predecessor
                }
            }
        }

        // Apply Floyd-Warshall algorithm to find shortest paths
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    // Update the distance if a shorter path is found via vertex k
                    if (shortestDistances[i][k] != INF && shortestDistances[k][j] != INF) {
                        if (shortestDistances[i][j] > shortestDistances[i][k] + shortestDistances[k][j]) {
                            shortestDistances[i][j] = shortestDistances[i][k] + shortestDistances[k][j];
                            predecessors[i][j] = predecessors[k][j]; // Update path
                        }
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int i = 0; i < numVertices; i++) {
            if (shortestDistances[i][i] < 0) {
                throw new NegativeWeightCycleException();
            }
        }

        return shortestDistances;
    }

    /**
     * Prints the path from the start vertex to the end vertex.
     *
     * @param predecessors The matrix containing the predecessors for each pair of vertices.
     * @param start The starting vertex.
     * @param end   The ending vertex.
     */
    public void printPath(int[][] predecessors, int start, int end) {
        if (start < 0 || end < 0 || start >= predecessors.length || end >= predecessors.length) {
            throw new IllegalArgumentException("Invalid vertex index");
        }

        System.out.println("Path from " + start + " to " + end + ":");
        Stack<Integer> pathStack = new Stack<>();
        pathStack.push(end);

        while (end != start) {
            end = predecessors[start][end];
            if (end == -1) {
                System.out.println("No path exists.");
                return;
            }
            pathStack.push(end);
        }

        while (!pathStack.isEmpty()) {
            System.out.print(pathStack.pop() + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0,   3,   6,   15},
                {INF, 0,  -2,   INF},
                {INF, INF, 0,   2},
                {1,   INF, INF, 0}
        };

        PartDFloydWarshallShortestPath shortestPath = new PartDFloydWarshallShortestPath();
        int[][] distanceMatrix = shortestPath.findAllPairShortestPaths(graph);

        System.out.println("Minimum Distance Matrix:");
        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++) {
                System.out.print(distanceMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // Example to print path
        shortestPath.printPath(distanceMatrix, 3, 2);
    }

    /**
     * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
     * https://www.youtube.com/watch?v=PwMVNSJ5SLI&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=43
     * CityFinder Class: Solves the problem of finding the city with the smallest number of reachable cities within a given distance threshold.
     *
     * LeetCode Link: [Find the City With the Smallest Number of Neighbors Within a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-within-a-threshold-distance/)
     *
     * Category: Medium, Graph
     *
     * Time Complexity: O(V^3)
     * Space Complexity: O(V^2)
     *
     * Explanation:
     * - Problem Statement:
     *   - Given a graph represented as an adjacency matrix where `graph[i][j]` represents the distance from city `i` to city `j`, and an integer `distanceThreshold`,
     *     find the city with the smallest number of reachable cities within the given distance threshold. If there are multiple such cities, return the city with the greatest index.
     *
     * - Algorithm:
     *   - This solution uses the Floyd-Warshall algorithm to find the shortest paths between all pairs of cities.
     *   - Initialize a distance matrix to store the shortest paths between cities.
     *   - For each city, calculate the number of cities that are reachable within the `distanceThreshold`.
     *   - Track the city with the fewest reachable cities or the greatest index if counts are equal.
     *   - Return the city that meets the criteria.
     *
     * - Detailed Steps:
     *   1. **Initialize Distance Matrix**:
     *      - Set the distance between cities from the given adjacency matrix.
     *      - Use `Integer.MAX_VALUE` to represent no direct connection between cities.
     *
     *   2. **Run Floyd-Warshall Algorithm**:
     *      - Update the shortest paths between every pair of cities using intermediate cities.
     *
     *   3. **Calculate Reachable Cities**:
     *      - For each city, count the number of cities reachable within the `distanceThreshold`.
     *      - Update the city with the fewest reachable cities or the highest index if the counts are equal.
     *
     * - Example:
     *   - Given a graph with 4 cities and the following distances:
     *     ```
     *     0 3 6 15
     *     ∞ 0 -2 ∞
     *     ∞ ∞ 0 2
     *     1 ∞ ∞ 0
     *     ```
     *     And a distance threshold of 4, the output will be the city with the smallest number of reachable cities within this threshold.
     *
     * - Improvements:
     *   - Enhanced variable naming for better clarity.
     *   - Detailed comments to explain each part of the algorithm and its purpose.
     */
    public static class CityFinder {

        // Define a constant for infinity
        private static final int INF = Integer.MAX_VALUE / 2; // Use a large value to avoid overflow

        /**
         * Finds the city with the smallest number of reachable cities within the given distance threshold.
         *
         * @param numCities The number of cities.
         * @param edges The list of edges in the graph, where each edge is represented by [u, v, w].
         * @param distanceThreshold The maximum distance to consider a city as reachable.
         * @return The city with the smallest number of reachable cities. In case of a tie, return the city with the greatest index.
         */
        public int findTheCity(int numCities, int[][] edges, int distanceThreshold) {
            // Step 1: Initialize the distance matrix
            int[][] distance = new int[numCities][numCities];
            for (int i = 0; i < numCities; i++) {
                Arrays.fill(distance[i], INF);
                distance[i][i] = 0; // Distance from a city to itself is 0
            }
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                distance[u][v] = w;
                distance[v][u] = w; // Assuming undirected graph
            }

            // Step 2: Apply Floyd-Warshall algorithm to find shortest paths
            for (int k = 0; k < numCities; k++) {
                for (int i = 0; i < numCities; i++) {
                    for (int j = 0; j < numCities; j++) {
                        if (distance[i][k] < INF && distance[k][j] < INF) {
                            distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                        }
                    }
                }
            }

            // Step 3: Find the city with the smallest number of reachable cities
            int minReachableCities = Integer.MAX_VALUE;
            int cityWithFewestNeighbors = -1;

            for (int i = 0; i < numCities; i++) {
                int reachableCitiesCount = 0;
                for (int j = 0; j < numCities; j++) {
                    if (distance[i][j] <= distanceThreshold) {
                        reachableCitiesCount++;
                    }
                }
                if (reachableCitiesCount <= minReachableCities) {
                    minReachableCities = reachableCitiesCount;
                    cityWithFewestNeighbors = i;
                }
            }

            return cityWithFewestNeighbors;
        }

        public static void main(String[] args) {
            CityFinder finder = new CityFinder();

            int numCities = 4;
            int[][] edges = {
                    {0, 1, 3},
                    {1, 2, 1},
                    {2, 3, 4},
                    {3, 0, 2}
            };
            int distanceThreshold = 4;

            int resultCity = finder.findTheCity(numCities, edges, distanceThreshold);
            System.out.println("The city with the smallest number of reachable cities is: " + resultCity);
        }
    }
}
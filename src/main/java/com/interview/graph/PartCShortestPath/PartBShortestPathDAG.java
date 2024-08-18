package com.interview.graph.PartCShortestPath;

import java.util.*;
/*
    Category: Medium, Must Do, VVImp, Solve using BFS or DFS
    Video: https://www.youtube.com/watch?v=ZUFQfFaU-8U&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=18
    Problem Link: https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=direct-acyclic-graph

    Explanation:
    - Node Class: Defines a class Node with vertex and weight to represent edges in the graph.
    - Topological Sort: Uses DFS to perform a topological sort of the DAG. The vertices are pushed onto a stack in the order of their finish times.
    - Shortest Path Calculation: Processes each vertex in topological order, updating the shortest path distances using the adjacency list.
    - Distance Initialization: Initializes distances to all vertices as Integer.MAX_VALUE and sets the distance from the source to itself as 0.
    - Final Distance Update: After processing all vertices, replaces distances that remain as Integer.MAX_VALUE (i.e., unreachable vertices) with -1.
    - This solution uses BFS principles with the help of topological sorting and ensures consistency with your existing pattern.

    Time and Space Complexity Analysis:
    - Time Complexity:
        - Building the Adjacency List: Involves iterating over the edges to construct the adjacency list.
          Complexity: O(M), where M is the number of edges.
        - Topological Sort: Performed using DFS. Each vertex and edge is processed once.
          Complexity: O(N + M), where N is the number of vertices and M is the number of edges.
        - Processing Vertices in Topological Order: Each vertex is processed once, and for each vertex, all its adjacent edges are processed.
          Complexity: O(N + M), similar to the complexity of topological sort.
        - Final Distance Update: Involves iterating through the distances array to replace unreachable vertices.
          Complexity: O(N).
        - Overall Time Complexity: Combining all these steps, the total time complexity is O(N + M).

    - Space Complexity:
        - Adjacency List: The space required to store the adjacency list is proportional to the number of vertices and edges.
          Complexity: O(N + M).
        - Visited Array and Distance Array: These arrays are of size N.
          Complexity: O(N).
        - Stack for Topological Sort: The stack used for storing the topological order will store all vertices.
          Complexity: O(N).
        - Overall Space Complexity: Combining these, the total space complexity is O(N + M).

    This complexity analysis ensures that the algorithm is efficient and suitable for the given constraints.
*/
public class PartBShortestPathDAG {

    // Node class to store weight and vertex
    private static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    // Method to perform topological sort using DFS
    private static void topologicalSort(int vertex, List<List<Node>> adjacencyList, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (Node neighbor : adjacencyList.get(vertex)) {
            if (!visited[neighbor.vertex]) {
                topologicalSort(neighbor.vertex, adjacencyList, visited, stack);
            }
        }
        stack.push(vertex);
    }

    // Method to find the shortest path from source to all vertices
    public static int[] shortestPath(int N, int M, int[][] edges) {
        List<List<Node>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Build the adjacency list
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            adjacencyList.get(src).add(new Node(dest, weight));
        }

        // Initialize data structures
        boolean[] visited = new boolean[N];
        Stack<Integer> stack = new Stack<>();
        int[] distances = new int[N];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0; // Distance from source to itself is 0

        // Perform topological sort
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                topologicalSort(i, adjacencyList, visited, stack);
            }
        }

        // Process vertices in topological order
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (distances[vertex] != Integer.MAX_VALUE) {
                for (Node neighbor : adjacencyList.get(vertex)) {
                    if (distances[vertex] + neighbor.weight < distances[neighbor.vertex]) {
                        distances[neighbor.vertex] = distances[vertex] + neighbor.weight;
                    }
                }
            }
        }

        // Replace unreachable vertices with -1
        for (int i = 0; i < N; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }

        return distances;
    }

    /*
    Bfs Approach
        // Node class to store weight and vertex
    static class Node {
        int weight;
        int vertex;

        Node(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }
    }

    // Method to perform BFS and find the shortest path
    public int[] shortestPath(int numVertices, int[][] edges) {
        // Build the adjacency list and in-degree array
        List<List<Node>> adj = new ArrayList<>();
        int[] inDegree = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new Node(w, v));
            inDegree[v]++;
        }

        // Initialize distance array and queue for BFS
        int[] distance = new int[numVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0; // Distance to the source node (0) is 0
        Queue<Integer> queue = new LinkedList<>();

        // Add nodes with zero in-degree to the queue
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (Node node : adj.get(u)) {
                int v = node.vertex;
                int weight = node.weight;

                // Update distance if a shorter path is found
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }

                // Decrease in-degree and add node to queue if in-degree becomes zero
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // Replace unreachable nodes' distances with -1
        for (int i = 0; i < numVertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }

        return distance;
    }
     */

    public static void main(String[] args) {
        int N1 = 4;
        int M1 = 2;
        int[][] edges1 = {{0, 1, 2}, {0, 2, 1}};
        System.out.println(Arrays.toString(shortestPath(N1, M1, edges1))); // Output: [0, 2, 1, -1]

        int N2 = 6;
        int M2 = 7;
        int[][] edges2 = {{0, 1, 2}, {0, 4, 1}, {4, 5, 4}, {4, 2, 2}, {1, 2, 3}, {2, 3, 6}, {5, 3, 1}};
        System.out.println(Arrays.toString(shortestPath(N2, M2, edges2))); // Output: [0, 2, 3, 6, 1, 5]
    }
}

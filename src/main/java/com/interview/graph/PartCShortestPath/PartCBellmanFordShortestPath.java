package com.interview.graph.PartCShortestPath;

import java.util.*;

/**
 * Bellman-Ford algorithm for finding the shortest path from a single source
 * to all other vertices in a directed graph with negative weight edges.
 * It can also detect negative weight cycles.
 *
 * Time complexity: O(EV)
 * Space complexity: O(V)
 *
 * References:
 * - https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
 * - http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 *
 * Explanation:
 * Node Class: Represents an edge in the graph, with methods to get the source, destination, and weight.
 * bellmanFord Method: Implements the Bellman-Ford algorithm:
 * Initializes distances with Integer.MAX_VALUE to represent infinity, except for the source vertex, which is set to 0.
 * Iteratively relaxes all edges numVertices - 1 times. For each edge, if the current distance to the destination vertex can be improved by taking this edge, the distance is updated.
 * After all edges have been relaxed, the algorithm checks for negative weight cycles by trying to relax the edges one more time. If any distance is updated, a negative cycle exists.
 * runBellmanFord Method: Sets up the graph by adding edges and runs the algorithm starting from vertex 0.
 * Time and Space Complexity:
 * Time Complexity: O(EV), where E is the number of edges and V is the number of vertices. This is due to the relaxation of all edges V-1 times.
 * Space Complexity: O(V), for storing the distance array.
 */
public class PartCBellmanFordShortestPath {

    // Inner class to represent an edge in the graph
    class Node {
        private int source;  // Start vertex of the edge
        private int destination; // End vertex of the edge
        private int weight;  // Weight of the edge

        Node(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        int getSource() {
            return source;
        }

        int getDestination() {
            return destination;
        }

        int getWeight() {
            return weight;
        }
    }

    // Method to find the shortest path using the Bellman-Ford algorithm
    void bellmanFord(List<Node> edges, int numVertices, int src) {
        int[] distances = new int[numVertices];

        // Initialize distances from src to all other vertices as infinity
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        // Relax all edges (numVertices - 1) times
        for (int i = 1; i <= numVertices - 1; i++) {
            for (Node edge : edges) {
                int u = edge.getSource();
                int v = edge.getDestination();
                int weight = edge.getWeight();
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        boolean hasNegativeCycle = false;
        for (Node edge : edges) {
            int u = edge.getSource();
            int v = edge.getDestination();
            int weight = edge.getWeight();
            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                hasNegativeCycle = true;
                System.out.println("Graph contains a negative weight cycle");
                break;
            }
        }

        // Print the shortest distances if no negative cycle is detected
        if (!hasNegativeCycle) {
            System.out.println("Vertex   Distance from Source");
            for (int i = 0; i < numVertices; i++) {
                System.out.println(i + " \t\t " + distances[i]);
            }
        }
    }

    // Method to setup graph and run Bellman-Ford algorithm
    void runBellmanFord() {
        int numVertices = 6;  // Number of vertices in the graph
        List<Node> edges = new ArrayList<>();

        // Add edges to the graph
        edges.add(new Node(3, 2, 6));
        edges.add(new Node(5, 3, 1));
        edges.add(new Node(0, 1, 5));
        edges.add(new Node(1, 5, -3));
        edges.add(new Node(1, 2, -2));
        edges.add(new Node(3, 4, -2));
        edges.add(new Node(2, 4, 3));

        // Run Bellman-Ford algorithm from vertex 0
        bellmanFord(edges, numVertices, 0);
    }

    public static void main(String[] args) {
        PartCBellmanFordShortestPath obj = new PartCBellmanFordShortestPath();
        obj.runBellmanFord();
    }
}
package com.interview.graph.PartDMinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
    Category: Medium, Must Do, Graph Algorithms, VVImp
    Problem Link: https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1
    Reference: https://www.youtube.com/watch?v=mJcZjjKzeqk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=45
    Related Problem: Prim's Algorithm for Minimum Spanning Tree (MST)

    Explanation:
    - This code implements Prim's Algorithm to find the sum of the weights of the edges in the Minimum Spanning Tree (MST) of a graph.
    - We use a priority queue (min-heap) to always expand the edge with the minimum weight.
    - The graph is represented using an adjacency list where each vertex has a list of its neighbors and the corresponding edge weights.
    - The algorithm starts with an arbitrary node (here, node 0) and continues to add the minimum weight edge that connects a visited node to an unvisited node until all nodes are included in the MST.
    - The total weight of the MST is returned as the result.

    Time Complexity:
    - Building the MST: O((V + E) * log V), where V is the number of vertices and E is the number of edges.
    - Each node is processed once, and each edge is processed at most twice.

    Space Complexity:
    - Priority Queue: O(V) to store the vertices.
    - Visited Array: O(V) to keep track of visited nodes.
    - Adjacency List: O(V + E) to represent the graph.

    This solution efficiently computes the sum of weights for the MST using Prim's Algorithm, ensuring optimal performance for larger graphs.
*/
public class PartAMinimumSpanningTreePrimAlgorithm {
    // Function to find the sum of weights of edges of the Minimum Spanning Tree using Prim's Algorithm.
    static int spanningTree(int vertices, ArrayList<ArrayList<ArrayList<Integer>>> adjacencyList) {
        // Priority queue to select the edge with the minimum weight.
        PriorityQueue<Node> minHeap = new PriorityQueue<>( (a, b) -> {
            return a.weight - b.weight;
        });

        int[] visited = new int[vertices]; // Array to keep track of visited vertices.
        int totalWeight = 0;

        // Start with the first node (0th index) with a weight of 0.
        minHeap.offer(new Node(0, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            int currentWeight = currentNode.weight;
            int currentVertex = currentNode.vertex;

            // Skip this node if it has already been visited.
            if (visited[currentVertex] == 1) continue;

            // Mark the node as visited and add its weight to the total.
            visited[currentVertex] = 1;
            totalWeight += currentWeight;

            // Traverse through all the adjacent nodes.
            for (ArrayList<Integer> neighbor : adjacencyList.get(currentVertex)) {
                int adjacentVertex = neighbor.get(0);
                int edgeWeight = neighbor.get(1);

                // If the adjacent node is not visited, add it to the priority queue.
                if (visited[adjacentVertex] == 0) {
                    minHeap.offer(new Node(adjacentVertex, edgeWeight));
                }
            }
        }

        return totalWeight;
    }

    // Helper class to represent a node in the priority queue.
    private static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        // Example usage
        int vertices = 4;
        ArrayList<ArrayList<ArrayList<Integer>>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        adjacencyList.get(0).add(new ArrayList<>(Arrays.asList(1, 1)));
        adjacencyList.get(0).add(new ArrayList<>(Arrays.asList(2, 2)));
        adjacencyList.get(1).add(new ArrayList<>(Arrays.asList(0, 1)));
        adjacencyList.get(1).add(new ArrayList<>(Arrays.asList(2, 3)));
        adjacencyList.get(1).add(new ArrayList<>(Arrays.asList(3, 4)));
        adjacencyList.get(2).add(new ArrayList<>(Arrays.asList(0, 2)));
        adjacencyList.get(2).add(new ArrayList<>(Arrays.asList(1, 3)));
        adjacencyList.get(2).add(new ArrayList<>(Arrays.asList(3, 5)));
        adjacencyList.get(3).add(new ArrayList<>(Arrays.asList(1, 4)));
        adjacencyList.get(3).add(new ArrayList<>(Arrays.asList(2, 5)));

        int result = spanningTree(vertices, adjacencyList);
        System.out.println("Sum of weights of MST: " + result);  // Output should be the sum of weights in MST
    }
}

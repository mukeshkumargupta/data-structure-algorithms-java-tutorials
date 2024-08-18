package com.interview.graph.PartDMinimumSpanningTree;

import java.util.*;

// Problem: Minimum Spanning Tree
// Category: Graph Theory, Algorithms
// Problem Link: https://leetcode.com/problems/minimum-spanning-tree/
// YouTube Video Link: https://www.youtube.com/watch?v=DMnDM_sxVig&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=47

// Explanation:
// Kruskal's Algorithm is used to find the Minimum Spanning Tree (MST) of a graph. It operates by
// sorting all the edges of the graph by weight and then adding the smallest edge to the MST, ensuring
// that no cycles are formed using a Disjoint Set (Union-Find) data structure.
// The algorithm ensures that the total weight of the MST is minimized while connecting all vertices.

public class PartBBKruskalAlgorithm {

    // Disjoint Set (Union-Find) class to manage the merging of sets
    static class DisjointSet {
        private int[] parent;
        private int[] rank;

        // Initialize Disjoint Set with 'n' elements
        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Find the representative (root) of the set containing 'u'
        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);  // Path compression
            }
            return parent[u];
        }

        // Union by rank to merge sets containing 'u' and 'v'
        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    // Edge class to represent a graph edge with weight
    private static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    /**
     * Finds the Minimum Spanning Tree (MST) of a graph using Kruskal's Algorithm.
     *
     * @param n Number of vertices in the graph.
     * @param edges List of edges where each edge is represented as [u, v, weight].
     * @return The total weight of the MST.
     */
    public static int kruskal(int n, List<Edge> edges) {
        // Sort edges by weight
        edges.sort(Comparator.comparingInt(e -> e.weight));

        // Initialize Disjoint Set
        DisjointSet ds = new DisjointSet(n);

        int mstWeight = 0;
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;

            // Check if the edge forms a cycle
            if (ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                mstWeight += edge.weight;
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        // Example usage
        int n = 7;  // Number of vertices
        List<Edge> edges = new ArrayList<>();

        // Add edges in the format [u, v, weight]
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        // Find the MST weight
        int mstWeight = kruskal(n, edges);
        System.out.println("Total weight of MST: " + mstWeight);
    }
}

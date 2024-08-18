package com.interview.graph.PartDMinimumSpanningTree;


/*
    Problem: Union-Find (Disjoint Set)
    Category: Data Structure, Graph
    Problem Link: [Union-Find Problems on LeetCode](https://leetcode.com/tag/union-find/)
    YouTube Video Link: https://www.youtube.com/watch?v=aBxjDBC4M1U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=46

    Explanation:
    This implementation uses the Disjoint Set (Union-Find) data structure with two key optimizations:
    1. Path Compression: Helps in flattening the structure of the tree whenever `find` is called, keeping the tree shallow and speeding up future operations.
    2. Union by Rank: Keeps the tree balanced by attaching the smaller tree under the root of the larger tree, ensuring efficient `union` operations.

    Complexity:
    - Time Complexity: O(α(n)) for both `find` and `union` operations, where α is the inverse Ackermann function. This function grows very slowly, making the operations nearly constant time.
    - Space Complexity: O(n) for storing the parent and rank arrays.

    Usage:
    - **Initialization**: Create an instance of `DisjointSet` with the number of elements.
    - **find(u)**: Finds the root representative of the set containing element `u`.
    - **union(u, v)**: Merges the sets containing elements `u` and `v`.
*/

class PartBADisjointSetByRank {
    private int[] parent;
    private int[] rank;

    /**
     * Initializes the Disjoint Set with 'n' elements.
     * Each element is its own parent initially.
     *
     * @param n Number of elements.
     */
    public PartBADisjointSetByRank(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Finds the representative (root) of the set containing 'u'.
     *
     * @param u Element whose set representative is to be found.
     * @return The root representative of the set.
     */
    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);  // Path compression
        }
        return parent[u];
    }

    /**
     * Merges the sets containing 'u' and 'v'.
     * Uses union by rank to ensure balanced trees.
     *
     * @param u First element.
     * @param v Second element.
     */
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

    public static void main(String[] args) {
        // Initialize Disjoint Set with 7 elements (0-based index)
        PartBADisjointSetByRank ds = new PartBADisjointSetByRank(7);

        // Perform union operations
        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(5, 6);
        ds.union(4, 5);

        // Check if elements 2 and 6 are in the same set
        if (ds.find(2) == ds.find(6)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        // Perform another union operation
        ds.union(2, 6);

        // Check again if elements 2 and 6 are in the same set
        if (ds.find(2) == ds.find(6)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }
}

package com.interview.graph.PartDMinimumSpanningTree;

/*
https://leetcode.com/problems/redundant-connection/description/
Category: Medium, Must Do
Related:
https://leetcode.com/problems/redundant-connection-ii/ (Hard, VVImp)
https://leetcode.com/problems/accounts-merge/ (Medium, Must Do)
https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/ Hard
https://leetcode.com/problems/shortest-cycle-in-a-graph/ Hard
n this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.



Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]


Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
 */
public class PartERedundantConnection {
/*
 🔴 Problem Summary:
 Given a tree with one extra edge, find the edge that can be removed to restore the tree property (i.e., make it acyclic and connected).
 Return the **last such edge** in input order if multiple exist.

 ✅ Brute Force: DFS for Cycle Detection on Every Edge
 -----------------------------------------------------
 Idea:
 - For each edge [u, v], remove it temporarily.
 - Use DFS to check if the graph is still connected and has no cycle.
 - If it’s still a tree → not redundant.
 - If it causes a cycle or disconnects → it’s the redundant one.

 ⏱ Time Complexity: O(N^3) – Very inefficient
 📦 Space Complexity: O(N^2)

 🔁 Better Solution: DFS While Building the Graph
 ------------------------------------------------
 Idea:
 - As we process each edge:
   - Check if a path already exists between u and v using DFS.
   - If yes → adding [u, v] creates a cycle → return it.
   - Else → add [u, v] to the adjacency list.

 ⏱ Time Complexity: O(N^2)
   - For N edges, each DFS can take O(N) time in the worst case.

 📦 Space Complexity: O(N)
   - For visited set + adjacency list

 🟢 Optimal: Union-Find (Disjoint Set Union – DSU)
 --------------------------------------------------
 Idea:
 - Use Union-Find to keep track of connected components.
 - For each edge [u, v]:
   - If find(u) == find(v) → u and v are already connected → return [u, v]
   - Else, union(u, v)

 🧠 Algorithm:
 - Initialize parent[i] = i for all nodes
 - Path compression in find() to flatten the tree
 - Union by rank/size to balance the tree

 ⏱ Time Complexity: O(N * α(N)) ≈ O(N)
   - α(N) is inverse Ackermann function → nearly constant

 📦 Space Complexity: O(N)
   - For parent and rank arrays
*/

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1); // 1-based indexing

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (!uf.union(u, v)) {
                return edge; // Cycle detected
            }
        }

        return new int[0]; // Shouldn't reach here
    }

    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]); // Path compression
            }
            return parent[u];
        }

        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) {
                return false; // Cycle detected
            }

            // Union by rank
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }

            return true;
        }
    }
}

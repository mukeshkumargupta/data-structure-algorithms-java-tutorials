package com.interview.graph.PartDMinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.



Example 1:



Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
Example 2:



Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.


Constraints:

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= ai < bi < n
1 <= weighti <= 1000
All pairs (ai, bi) are distinct.
 */
public class PartDFindCriticalandPseudoCriticalEdgesinMinimumSpanningTree {
    /*
    🔍 Explanation:
    Preprocess the edges: Add index to each edge for later reference.

    Base MST: Compute the MST cost using regular Kruskal (no force or skip).

    For each edge:

    Critical: If skipping the edge increases MST cost → it's critical.

    Pseudo-critical: If forcing the edge gives same MST cost → it's pseudo.

    🧮 Time & Space Complexity:
    Time Complexity:

    Sorting: O(E log E)

    For each edge, we run Kruskal: O(E α(N)) → so total is O(E² α(N))

    Space Complexity:

    O(N + E) for disjoint set and result storage.
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            newEdges[i][0] = edges[i][0];
            newEdges[i][1] = edges[i][1];
            newEdges[i][2] = edges[i][2];
            newEdges[i][3] = i; // original index
        }

        Arrays.sort(newEdges, Comparator.comparingInt(a -> a[2]));

        int baseMSTCost = kruskal(n, newEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (kruskal(n, newEdges, i, -1) > baseMSTCost) {
                critical.add(newEdges[i][3]);
            } else if (kruskal(n, newEdges, -1, i) == baseMSTCost) {
                pseudo.add(newEdges[i][3]);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudo);
        return result;
    }

    private int kruskal(int n, int[][] edges, int skip, int force) {
        DisjointSet ds = new DisjointSet(n);
        int cost = 0;

        if (force != -1) {
            int[] edge = edges[force];
            if (ds.union(edge[0], edge[1])) {
                cost += edge[2];
            }
        }

        for (int i = 0; i < edges.length; i++) {
            if (i == skip) continue;
            int[] edge = edges[i];
            if (ds.union(edge[0], edge[1])) {
                cost += edge[2];
            }
        }

        return ds.count == 1 ? cost : Integer.MAX_VALUE;
    }

    private static class DisjointSet {
        private int[] parent;
        private int[] rank;
        public int count;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return false;

            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            count--;
            return true;
        }
    }
}

package com.interview.graph.PartATraversal;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/count-the-number-of-complete-components/description/
Category: Medium, Must Do
Related:
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/ Medium
You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.



Example 1:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.
Example 2:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.


Constraints:

1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.
 */
public class PartGCounttheNumberofCompleteComponents {
    /*
    ⏱ Time & Space Complexity:
        Time Complexity:

        Building graph: O(E)

        DFS traversal: O(N + E)

        For each component: checking degrees → O(N)

        Total: O(N + E)

        Space Complexity:

        Adjacency list: O(N + E)

        Visited array + Component storage: O(N)
     */
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int completeCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);

                int nodes = component.size();
                int edgeCount = 0;

                for (int node : component) {
                    edgeCount += adj.get(node).size();
                }

                // Each edge is counted twice in an undirected graph
                if (edgeCount / 2 == nodes * (nodes - 1) / 2) {
                    completeCount++;
                }
            }
        }

        return completeCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }
}

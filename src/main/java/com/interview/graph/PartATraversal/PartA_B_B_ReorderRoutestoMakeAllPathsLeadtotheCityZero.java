package com.interview.graph.PartATraversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75, Tricky,
https://www.youtube.com/watch?v=m7svP1AyREk
Related:
https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/ Hard

There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.



Example 1:


Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:


Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0


Constraints:

2 <= n <= 5 * 104
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
 */
public class PartA_B_B_ReorderRoutestoMakeAllPathsLeadtotheCityZero {
    /*
    🕒 Time Complexity: O(n)
    We visit each node and edge once.

    Each connection is processed in both directions during graph creation and once during DFS.

    🧠 Space Complexity: O(n)
    graph stores all connections: O(n)

    visited array: O(n)

    Recursive call stack: up to O(n) in worst case
     */

    private static class UsingMapGraph {
        public int minReorder(int n, int[][] connections) {
            Map<Integer, List<int[]>> graph = new HashMap<>();

            // Build the graph with direction info
            for (int[] conn : connections) {
                int from = conn[0], to = conn[1];

                graph.computeIfAbsent(from, x -> new ArrayList<>()).add(new int[]{to, 1}); // original direction
                graph.computeIfAbsent(to, x -> new ArrayList<>()).add(new int[]{from, 0}); // reverse direction
            }

            boolean[] visited = new boolean[n];
            return dfs(0, graph, visited);
        }

        private int dfs(int node, Map<Integer, List<int[]>> graph, boolean[] visited) {
            visited[node] = true;
            int changes = 0;

            for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                int nextNode = neighbor[0];
                int direction = neighbor[1]; // 1 = needs change, 0 = already towards city 0

                if (!visited[nextNode]) {
                    changes += direction + dfs(nextNode, graph, visited);
                }
            }

            return changes;
        }
    }

    /*
    Since key is not kind of a, b kind of string so map based graph is not required
     */
    private static class UsingAdjList {
        int dfs(List<List<Integer[]>> adjList , boolean[] visited, int city) {
            visited[city] = true;

            int changes = 0;
            for ( Integer[] neighbour: adjList.get(city)) {
                if (!visited[neighbour[0]]) {
                    changes += dfs(adjList, visited, neighbour[0]) + neighbour[1];
                }
            }
            return changes;


        }
        public int minReorder(int n, int[][] connections) {
            List<List<Integer[]>> adjList = new ArrayList<>();
            for (int i= 0; i < n; i++) {
                adjList.add(new ArrayList<>());
            }
            boolean[] visited = new boolean[n];
            for (int[] connection: connections) {
                adjList.get(connection[0]).add(new Integer[] {connection[1], 1});//real edge
                adjList.get(connection[1]).add(new Integer[] {connection[0], 0});//not edge
            }
            return dfs(adjList, visited, 0);






        }
    }
}

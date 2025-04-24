package com.interview.graph.PartDMinimumSpanningTree;


import java.util.*;

/*
https://leetcode.com/problems/min-cost-to-connect-all-points/description/
Category: Medium
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.



Example 1:


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18


Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
 */
public class PartA_B_MinCosttoConnectAllPoints {
    /*
    📊 Time & Space Complexity
    Time Complexity:

    Edge generation: O(n²)

    Sorting edges: O(n² log n²)

    Union-Find operations: O(α(n)) per operation, nearly constant time

    Overall: O(n² log n)

    Space Complexity:

    O(n²) for storing all edges

    O(n) for disjoint set
     */
    private static class BruitForce {
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;
            List<int[]> edges = new ArrayList<>();

            // Build all possible edges with distances
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int dist = Math.abs(points[i][0] - points[j][0]) +
                            Math.abs(points[i][1] - points[j][1]);
                    edges.add(new int[]{dist, i, j});
                }
            }

            // Sort edges by weight
            Collections.sort(edges, (a, b) -> {
                return a[0] - b[0];
            });

            // Kruskal's using Disjoint Set
            DisjointSet ds = new DisjointSet(n);
            int cost = 0, count = 0;

            for (int[] edge : edges) {
                int weight = edge[0], u = edge[1], v = edge[2];
                if (ds.find(u) != ds.find(v)) {
                    ds.union(u, v);
                    cost += weight;
                    count++;
                    if (count == n - 1) break;
                }
            }

            return cost;
        }

        static class DisjointSet {
            int[] parent, rank;

            public DisjointSet(int n) {
                parent = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i++) parent[i] = i;
            }

            public int find(int u) {
                if (u != parent[u]) parent[u] = find(parent[u]);
                return parent[u];
            }

            public void union(int u, int v) {
                int rootU = find(u), rootV = find(v);
                if (rootU != rootV) {
                    if (rank[rootU] < rank[rootV]) {
                        parent[rootU] = rootV;
                    } else if (rank[rootU] > rank[rootV]) {
                        parent[rootV] = rootU;
                    } else {
                        parent[rootV] = rootU;
                        rank[rootU]++;
                    }
                }
            }
        }
    }

    private static class Optimal {
        /*
         2. Optimal - Prim’s Algorithm (Min Heap)
        No need to generate all edges. We expand the MST greedily, always adding the nearest unvisited point.
        Time Complexity:

        O(n² log n) — each node adds up to n elements to heap

        Better in practice than Kruskal for dense graphs

        Space Complexity:

        O(n) for visited + heap

        ✅ Best approach for this problem
         */
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;
            boolean[] visited = new boolean[n];
            int visitedCount = 0;
            PriorityQueue<int[]> minHeap = new PriorityQueue<>( (a, b)-> {
                return a[0] - b[0];
            });
            minHeap.offer(new int[]{0, 0}); // {cost, point index}
            int totalCost = 0;
            /*
            while (!minHeap.isEmpty()) in place of while (visitedCount < n) {
            The loop may do extra iterations after all nodes are already in the MST.

You have to manually break out after checking the visited[] array inside the loop.

Slightly less efficient, especially if there are lots of redundant nodes in the heap (common in dense graphs).
             */

            while (visitedCount < n) {
                int[] curr = minHeap.poll();
                int cost = curr[0], u = curr[1];

                if (visited[u]) continue;

                visited[u] = true;
                totalCost += cost;
                visitedCount++;

                for (int v = 0; v < n; v++) {
                    if (!visited[v]) {
                        int nextCost = Math.abs(points[u][0] - points[v][0]) +
                                Math.abs(points[u][1] - points[v][1]);
                        minHeap.offer(new int[]{nextCost, v});
                    }
                }
            }

            return totalCost;
        }
    }
}

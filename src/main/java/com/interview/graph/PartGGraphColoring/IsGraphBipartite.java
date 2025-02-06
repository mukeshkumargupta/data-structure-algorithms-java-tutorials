package com.interview.graph.PartGGraphColoring;

import java.util.*;

/**
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/is-graph-bipartite/
 * Reference: https://www.youtube.com/watch?v=-vu34sct1g8&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=10
 * Example: LeetCode 785 - Is Graph Bipartite?
 * Other methods include BFS, DFS, and Union Find.
 * https://leetcode.com/problems/is-graph-bipartite/discuss/1941723/Clean-Java-Coloring%2BUnionFind-solutions
 * Category: Medium, Must Do
 * Related Problems:
 * - https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/ (Hard)
 * - https://leetcode.com/problems/redundant-connection-ii/ (Hard, VVImp)
 * - https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/ (Hard, VImp)
 * - https://leetcode.com/problems/gcd-sort-of-an-array/ (Hard, VVImp)
 *
 * Problem: 785. Is Graph Bipartite?
 * Medium
 *
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
 * The graph has the following properties:
 * - No self-edges (graph[u] does not contain u).
 * - No parallel edges (graph[u] does not contain duplicate values).
 * - If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * - The graph may not be connected.
 *
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such
 * that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 * Example 1:
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 *
 * Example 2:
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 *
 * Constraints:
 * - graph.length == n
 * - 1 <= n <= 100
 * - 0 <= graph[u].length < n
 * - 0 <= graph[u][i] <= n - 1
 * - graph[u] does not contain u.
 * - All the values of graph[u] are unique.
 * - If graph[u] contains v, then graph[v] contains u.
 */
public class IsGraphBipartite {

    /**
     * DFS Approach:
     * In the DFS approach, we use a recursive function to traverse the graph,
     * coloring the nodes. If a conflict occurs (a node and its neighbor have the
     * same color), the graph is not bipartite.
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];  // 0: uncolored, 1: color A, -1: color B

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == 0) {  // If the neighbor is uncolored
                if (!dfs(graph, colors, neighbor, -color)) {
                    return false;
                }
            } else if (colors[neighbor] == color) {  // Conflict: same color
                return false;
            }
        }
        return true;
    }

    /**
     * BFS Approach:
     * In the BFS approach, we use a queue to traverse the graph level by level,
     * coloring nodes alternately. If a conflict occurs, the graph is not bipartite.
     */
    public boolean isBipartiteBfs(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];  // 0: uncolored, 1: color A, -1: color B
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {  // Not yet visited
                queue.offer(i);
                colors[i] = 1;  // Start coloring with color A

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : graph[node]) {
                        if (colors[neighbor] == 0) {  // If the neighbor is uncolored
                            queue.offer(neighbor);
                            colors[neighbor] = -colors[node];  // Opposite color
                        } else if (colors[neighbor] == colors[node]) {  // Conflict
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Check if the graph is three-colorable using DFS.
     */
    public boolean isThreeColorableDfs(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];  // 0: uncolored, 1: color A, 2: color B, 3: color C

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !threeColorableDfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean threeColorableDfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == 0) {  // Uncolored neighbor
                for (int nextColor = 1; nextColor <= 3; nextColor++) {
                    if (nextColor != color && threeColorableDfs(graph, colors, neighbor, nextColor)) {
                        return true;
                    }
                }
                return false;
            } else if (colors[neighbor] == color) {  // Conflict
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the graph is three-colorable using BFS.
     */
    public boolean isThreeColorableBfs(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];  // 0: uncolored, 1: color A, 2: color B, 3: color C
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {  // Not yet visited
                queue.offer(i);
                colors[i] = 1;  // Start coloring with color A

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : graph[node]) {
                        if (colors[neighbor] == 0) {  // Uncolored neighbor
                            queue.offer(neighbor);
                            colors[neighbor] = colors[node] % 3 + 1;  // Next color
                            /*
                            BFS Logic for Finding the Next Color
                            In BFS:

                            Instead of testing all colors explicitly, you derive the next color based on the current color using the formula:
                            colors[neighbor] = colors[node] % 3 + 1;
                            Here's what happens:
                            If colors[node] is 1 (Color A), the next color will be 1 % 3 + 1 = 2 (Color B).
                            If colors[node] is 2 (Color B), the next color will be 2 % 3 + 1 = 3 (Color C).
                            If colors[node] is 3 (Color C), the next color will be 3 % 3 + 1 = 1 (Color A).
                            This cycle ensures you always pick a different color than the current one, and it works because you're not brute-forcing
                            like DFS. BFS processes nodes level-by-level and assigns colors sequentially, relying on the fact that no conflict occurs as
                            long as graph properties are maintained
                             */
                        } else if (colors[neighbor] == colors[node]) {  // Conflict
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
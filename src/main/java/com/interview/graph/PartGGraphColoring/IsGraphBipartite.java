/**
 * 
 */
package com.interview.graph.PartGGraphColoring;

import java.util.*;
/**
 * @author Mukesh Kumar Gupta
 *https://leetcode.com/problems/is-graph-bipartite/
 * Reference: https://www.youtube.com/watch?v=-vu34sct1g8&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=10
 * Example: LeetCode 785 - Is Graph Bipartite?
 *Other way to solver
 *Many method mention(bfs, dfs, union find)
 *https://leetcode.com/problems/is-graph-bipartite/discuss/1941723/Clean-Java-Coloring%2BUnionFind-solutions
 *Category: Medium, Must Do
 *Related:
 * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/ Hard
 *https://leetcode.com/problems/redundant-connection-ii/ Hard, VVImp
 *https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/ Hard VImp
 *https://leetcode.com/problems/gcd-sort-of-an-array/ Hard VVImp
 *
 *785. Is Graph Bipartite?
Medium

4053

261

Add to List

Share
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

 

Example 1:


Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:


Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 

Constraints:

graph.length == n
1 <= n <= 100
0 <= graph[u].length < n
0 <= graph[u][i] <= n - 1
graph[u] does not contain u.
All the values of graph[u] are unique.
If graph[u] contains v, then graph[v] contains u.
Accepted
275,933
Submissions
547,383
 
 *
 */
public class IsGraphBipartite {
    /*
    DFS Approach:
In the DFS approach, we will use a recursive function to traverse the graph, coloring the nodes. The idea is to color the current node with one color and all its adjacent nodes with the opposite color. If we find a conflict where a node and its adjacent node have the same color, the graph is not bipartite.
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
            } else if (colors[neighbor] == color) {  // If the neighbor has the same color
                return false;
            }
        }
        return true;
    }


    /*
    BFS Approach:
In the BFS approach, we will use a queue to traverse the graph level by level, coloring nodes alternately. If we detect a conflict where a node and its adjacent node have the same color, the graph is not bipartite.
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
                            colors[neighbor] = -colors[node];  // Color with the opposite color
                        } else if (colors[neighbor] == colors[node]) {  // If the neighbor has the same color
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    /*
    Explanation:
DFS Approach: The graph is traversed recursively, and each node is colored. If a conflict is detected (a node and its neighbor have the same color), we return false.
BFS Approach: The graph is traversed level by level, coloring nodes alternately. If a conflict is detected, we return false.
Both approaches have a time complexity of O(V + E), where V is the number of vertices and E is the number of edges in the graph. These solutions are readable and align with standard graph traversal practices.
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

    /*
DFS Approach:
 */
    private boolean threeColorableDfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == 0) {  // If the neighbor is uncolored
                // Try all 3 colors for the next node
                for (int nextColor = 1; nextColor <= 3; nextColor++) {
                    if (nextColor != color && dfs(graph, colors, neighbor, nextColor)) {
                        return true;
                    }
                }
                return false;
            } else if (colors[neighbor] == color) {  // If the neighbor has the same color
                return false;
            }
        }
        return true;
    }

    /*
    BFS Approach:
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
                        if (colors[neighbor] == 0) {  // If the neighbor is uncolored
                            for (int nextColor = 1; nextColor <= 3; nextColor++) {
                                if (nextColor != colors[node]) {
                                    queue.offer(neighbor);
                                    colors[neighbor] = nextColor;
                                    break;
                                }
                            }
                        } else if (colors[neighbor] == colors[node]) {  // If the neighbor has the same color
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

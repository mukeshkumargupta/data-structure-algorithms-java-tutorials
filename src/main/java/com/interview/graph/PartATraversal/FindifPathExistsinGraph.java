package com.interview.graph.PartATraversal;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 * Category: Medium, VVImp, 
 * Related: https://leetcode.com/problems/valid-arrangement-of-pairs/ Hard
 * https://leetcode.com/problems/paths-in-maze-that-lead-to-same-room/ Medium, Locked
 * 1971. Find if Path Exists in Graph
 * This can be solved using bfs as well
Easy

896

51

Add to List

Share
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.
Accepted
83,466
Submissions
166,159
 */
public class FindifPathExistsinGraph {
    boolean found = false;
    void dfs(int src, int dest, List<Integer>[] adjList , boolean[] visited) {
        if (src == dest) {
           found = true; 
            return;
        }
        visited[src] = true;
        for (int child: adjList[src]) {
            if (!visited[child]) {
                dfs(child, dest, adjList, visited);
            }
        }
    }
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        /*
         * Runtime: 159 ms, faster than 54.86% of Java online submissions for Find if Path Exists in Graph.
Memory Usage: 217.9 MB, less than 32.85% of Java online submissions for Find if Path Exists in Graph.
         */
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        //run dfs
        
        dfs(source,destination, adjList, visited);
        return found;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

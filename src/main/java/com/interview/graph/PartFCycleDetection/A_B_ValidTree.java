package com.interview.graph.PartFCycleDetection;

import java.util.*;

/*
 * Given n TreeNodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of TreeNodes),
 * write a function to check whether these edges make up a valid tree.
 * Cycle Detection in Undirected Graph (Union-Find)
 * https://leetcode.com/problems/graph-valid-tree/
 * Category: Fundamental, Must Do
 */
public class A_B_ValidTree {

    private static class BfsApproach {
        static class Node {
            int vertex;
            int parent;
            public Node(int vertex, int parent) {
                this.vertex = vertex;
                this.parent = parent;
            }
        }

        // Method to check for a cycle in a graph using BFS
        static boolean checkForCycle(ArrayList<ArrayList<Integer>> adjacencyList, int startVertex, boolean[] visited) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(startVertex, -1));
            visited[startVertex] = true;

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                int currentVertex = node.vertex;
                int parentVertex = node.parent;

                for (Integer adjacentVertex : adjacencyList.get(currentVertex)) {
                    if (!visited[adjacentVertex]) {
                        queue.add(new Node(adjacentVertex, currentVertex));
                        visited[adjacentVertex] = true;
                    } else if (parentVertex != adjacentVertex) {
                        return true; // A cycle is detected
                    }
                }
            }
            return false; // No cycle detected
        }

        // Method to check if the graph is a valid tree
        public boolean validTreeBFS(int n, int[][] edges) {
            if (edges.length != n - 1) {
                return false; // A tree must have exactly n-1 edges
            }

            // Initialize adjacency list
            ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Populate the adjacency list with edges
            for (int[] edge : edges) {
                adjacencyList.get(edge[0]).add(edge[1]);
                adjacencyList.get(edge[1]).add(edge[0]);
            }

            // Perform BFS from the first vertex
            boolean[] visited = new boolean[n];
            if (checkForCycle(adjacencyList, 0, visited)) {
                return false; // Cycle detected
            }

            // Check if all vertices were visited (i.e., the graph is connected)
            for (boolean isVisited : visited) {
                if (!isVisited) {
                    return false; // Not all vertices are connected
                }
            }

            return true; // The graph is a valid tree
        }
    }

    private static class DfsApproach {
        // Method to perform DFS and check for cycles
        private boolean isCyclicDFS(ArrayList<ArrayList<Integer>> adjacencyList, int vertex, boolean[] visited, int parent) {
            visited[vertex] = true;

            for (Integer neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor]) {
                    // If the neighbor hasn't been visited, continue DFS
                    if (isCyclicDFS(adjacencyList, neighbor, visited, vertex)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    // If visited and not the parent, a cycle is detected
                    return true;
                }
            }
            return false;
        }

        // Method to check if the graph is a valid tree
        public boolean validTreeDFS(int n, int[][] edges) {
            if (edges.length != n - 1) {
                return false; // A valid tree must have exactly n-1 edges
            }

            // Initialize adjacency list for the graph
            ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Populate the adjacency list with edges
            for (int[] edge : edges) {
                adjacencyList.get(edge[0]).add(edge[1]);
                adjacencyList.get(edge[1]).add(edge[0]);
            }

            // Initialize visited array
            boolean[] visited = new boolean[n];

            // Perform DFS from the first vertex
            if (isCyclicDFS(adjacencyList, 0, visited, -1)) {
                return false; // Cycle detected
            }

            // Check if all vertices are visited (i.e., the graph is connected)
            for (boolean vertexVisited : visited) {
                if (!vertexVisited) {
                    return false; // Not all vertices are connected
                }
            }

            return true; // The graph is a valid tree
        }

        public static void main(String[] args) {
            DfsApproach obj = new DfsApproach();

            int[][] edges1 = {{0,1},{0,2},{0,3},{1,4}};
            int n1 = 5;
            System.out.println(obj.validTreeDFS(n1, edges1) ? "1" : "0"); // Output: 1

            int[][] edges2 = {{0,1},{1,2},{2,3},{1,3},{1,4}};
            int n2 = 5;
            System.out.println(obj.validTreeDFS(n2, edges2) ? "1" : "0"); // Output: 0
        }
    }



}


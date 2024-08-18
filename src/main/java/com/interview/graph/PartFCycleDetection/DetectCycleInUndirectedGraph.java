package com.interview.graph.PartFCycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * https://www.youtube.com/watch?v=BPlrALf1LDU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=11 basic concept
 * Given n TreeNodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of TreeNodes),
 * write a function to check whether these edges make up a valid tree.
 * https://www.youtube.com/watch?v=Qzf1a--rhp8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=6 this is for dfs bus just u can convert same code to dfs
 * Cycle Detection in Undirected Graph (Union-Find)
 * https://leetcode.com/problems/graph-valid-tree/
 * Category: Fundamental, Must Do
 */

public class DetectCycleInUndirectedGraph {

    public static class DetectCycleInUndirectedGraphBFS {
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
                        return true;
                    }
                }
            }
            return false;
        }

        // Method to detect a cycle in an undirected graph
        public boolean isCycle(int totalVertices, ArrayList<ArrayList<Integer>> adjacencyList) {
            boolean[] visited = new boolean[totalVertices];

            for (int i = 0; i < totalVertices; i++) {
                if (!visited[i]) {
                    if (checkForCycle(adjacencyList, i, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void main(String[] args) {
            ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            adjacencyList.get(1).add(2);
            adjacencyList.get(2).add(1);
            adjacencyList.get(2).add(3);
            adjacencyList.get(3).add(2);

            DetectCycleInUndirectedGraphBFS obj = new DetectCycleInUndirectedGraphBFS();
            boolean hasCycle = obj.isCycle(4, adjacencyList);
            System.out.println(hasCycle ? "1" : "0");
        }
    }

    public static class DetectCycleInUndirectedGraphDFS {
            /*
    Code convetredt to DFS
     */

        // Helper method to perform DFS and check for cycles
        private boolean isCyclicDFS(List<List<Integer>> adjacencyList, int vertex, boolean[] visited, int parent) {
            visited[vertex] = true;

            for (Integer neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor]) {
                    // Recursively perform DFS
                    if (isCyclicDFS(adjacencyList, neighbor, visited, vertex)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    // A visited node that is not the parent indicates a cycle
                    return true;
                }
            }
            return false;
        }

        // Method to detect a cycle in an undirected graph
        public boolean isCycle(int totalVertices, List<List<Integer>> adjacencyList) {
            boolean[] visited = new boolean[totalVertices];

            for (int i = 0; i < totalVertices; i++) {
                if (!visited[i]) {
                    if (isCyclicDFS(adjacencyList, i, visited, -1)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void main(String[] args) {
            List<List<Integer>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            adjacencyList.get(1).add(2);
            adjacencyList.get(2).add(1);
            adjacencyList.get(2).add(3);
            adjacencyList.get(3).add(2);

            DetectCycleInUndirectedGraphDFS obj = new DetectCycleInUndirectedGraphDFS();
            boolean hasCycle = obj.isCycle(4, adjacencyList);
            System.out.println(hasCycle ? "1" : "0");
        }
    }








}

package com.interview.graph.PartCShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PartA_B_A_NetworkDelayTime {
    /*
     * Reference: https://leetcode.com/problems/network-delay-time/
     * Category: Medium, Must Do
     * Related: Dijkstra, shortest distance from single source
     *
     * Videos:
     * - https://www.youtube.com/watch?v=t2d-XYuPfg0&t=304s
     * - https://www.youtube.com/watch?v=Sj5Z-jaE2x0
     *
     * Additional Related Problems:
     * - https://leetcode.com/problems/making-a-large-island/ (Hard)
     * - https://leetcode.com/problems/validate-binary-tree-nodes/ (Medium)
     * - https://leetcode.com/problems/number-of-operations-to-make-network-connected/ (Medium)
     *
     * Problem Description:
     * You are given a network of `n` nodes, labeled from 1 to `n`. You are also given `times`,
     * a list of travel times as directed edges `times[i] = (ui, vi, wi)`, where `ui` is the
     * source node, `vi` is the target node, and `wi` is the time it takes for a signal to
     * travel from source to target.
     *
     * We will send a signal from a given node `k`. Return the time it takes for all the `n`
     * nodes to receive the signal. If it is impossible for all the `n` nodes to receive the
     * signal, return -1.
     *
     * Example 1:
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * Output: 2
     *
     * Example 2:
     * Input: times = [[1,2,1]], n = 2, k = 1
     * Output: 1
     *
     * Example 3:
     * Input: times = [[1,2,1]], n = 2, k = 2
     * Output: -1
     *
     * Constraints:
     * 1 <= k <= n <= 100
     * 1 <= times.length <= 6000
     * times[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 0 <= wi <= 100
     * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
     */
    public static class NetworkDelayTime {
        // Node class to represent vertices and their distances
        static class Node {
            int vertex;
            int time;

            Node(int vertex, int time) {
                this.vertex = vertex;
                this.time = time;
            }
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            // Build the adjacency list
            List<List<Node>> adjacencyList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Populate the adjacency list with edges and their weights
            for (int[] edge : times) {
                int fromNode = edge[0] - 1; // Convert 1-based index to 0-based
                int toNode = edge[1] - 1; // Convert 1-based index to 0-based
                int edgeWeight = edge[2];
                adjacencyList.get(fromNode).add(new Node(toNode, edgeWeight));
            }

            // Initialize distance array and priority queue for Dijkstra's algorithm
            int[] distances = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE); // Initialize distances as infinity
            distances[k - 1] = 0; // Distance to the source node is 0
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
            priorityQueue.offer(new Node(k - 1, 0));

            // Perform Dijkstra's algorithm
            while (!priorityQueue.isEmpty()) {
                Node currentNode = priorityQueue.poll();
                int currentVertex = currentNode.vertex;
                int currentTime = currentNode.time;

                if (currentTime > distances[currentVertex]) continue; // Skip outdated entries

                for (Node neighbor : adjacencyList.get(currentVertex)) {
                    int neighborVertex = neighbor.vertex;
                    int edgeWeight = neighbor.time;

                    if (distances[currentVertex] + edgeWeight < distances[neighborVertex]) {
                        distances[neighborVertex] = distances[currentVertex] + edgeWeight;
                        priorityQueue.offer(new Node(neighborVertex, distances[neighborVertex]));
                    }
                }
            }

            // Find the maximum distance to determine the delay time
            //int maximumDelay = Arrays.stream(distances).max().orElse(Integer.MAX_VALUE);
            int maximumDelay = Integer.MIN_VALUE;
            for (int distance : distances) {
                maximumDelay = Math.max(maximumDelay, distance);
            }

            return maximumDelay == Integer.MAX_VALUE ? -1 : maximumDelay;
        }

        public static void main(String[] args) {
            NetworkDelayTime solver = new NetworkDelayTime();

            int numNodes1 = 4;
            int[][] edges1 = {{2, 1, 1}, {2, 3, 1}, {1, 3, 2}};
            int startNode1 = 2;
            System.out.println(solver.networkDelayTime(edges1, numNodes1, startNode1)); // Output: 2

            int numNodes2 = 2;
            int[][] edges2 = {{1, 2, 1}};
            int startNode2 = 1;
            System.out.println(solver.networkDelayTime(edges2, numNodes2, startNode2)); // Output: 1
        }
    }
}

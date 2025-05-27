package com.interview.graph.PartCShortestPath;

import java.util.*;

/*
 * Category: Medium, Must Do, Very Important, Solve using BFS
 *
 * Video Reference:
 * - https://www.youtube.com/watch?v=C4gxoTaI71U&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=19
 *
 * Since the graph has unit weights, BFS is an appropriate method for solving this problem without the need for sorting.
 *
 * **Explanation**:
 *
 * 1. **Adjacency List Construction**:
 * - The graph is represented using an adjacency list. Each index of the list represents a vertex, and the list at each index contains the neighboring vertices.
 *
 * 2. **Distance Initialization**:
 * - An array `distance` is initialized to store the shortest distance from the source vertex `src` to each vertex. Initially, all distances are set to -1 to indicate that they are unreachable. The distance to the source vertex itself is set to 0.
 *
 * 3. **Breadth-First Search (BFS)**:
 * - BFS is used to explore the graph level by level. Starting from the source vertex, all directly connected vertices are processed, and their distances are updated. Each newly discovered vertex's distance is set to the current vertex's distance plus one, reflecting the shortest path in an unweighted graph.
 *
 * 4. **Queue for BFS**:
 * - A queue is used to manage the BFS traversal. Vertices are added to the queue as they are discovered and processed in the order they are added.
 *
 * **Complexity Analysis**:
 *
 * - **Time Complexity**:
 *   - Building the adjacency list takes O(E) time, where E is the number of edges.
 *   - The BFS traversal processes each vertex and edge once, resulting in O(V + E) time complexity, where V is the number of vertices.
 *   - Overall, the time complexity is O(V + E).
 *
 * - **Space Complexity**:
 *   - The space complexity is O(V + E) due to the adjacency list and the distance array.
 *   - The queue used for BFS also contributes to the space complexity but is part of the O(V + E) total space requirement.
 *
 * The code efficiently computes the shortest path in an undirected graph with unit weights and handles large graphs within the given constraints.
 */
public class PartA_A_ShortestPathUndirectedGraph {
    // Method to find the shortest path from the source vertex
    public int[] shortestPath(int numVertices, int[][] edges, int src) {
        // Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }

        // Populate the adjacency list for the undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Initialize distance array and queue for BFS
        int[] distance = new int[numVertices];
        Arrays.fill(distance, -1); // Initialize distances as -1 (unreachable)
        distance[src] = 0; // Distance to the source node (src) is 0
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        // Perform BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();
            int currentDistance = distance[u];

            for (int v : adj.get(u)) {
                if (distance[v] == -1) { // Check if the vertex is unvisited
                    distance[v] = currentDistance + 1; // Update distance
                    queue.offer(v);
                }
            }
        }

        return distance;
    }

    /*
     * Category: Medium, Must Do, Very Important, Solve using BFS
     *
     * Video Reference:
     * - https://www.youtube.com/watch?v=V6H1qAeB-l4&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=20
     *
     * 1. BFS Method
     *
     * BFS Approach Explanation:
     * - **Initialize**: Create an adjacency list to represent the graph and a distance array initialized to -1 (unreachable) except for the source vertex, which is set to 0.
     * - **Queue**: Use a queue to manage the BFS traversal.
     * - **Traversal**: Process each vertex, update distances for its neighbors if they haven't been visited yet, and add these neighbors to the queue.
     * - **Complexity**: O(V + E) time and space complexity, where V is the number of vertices and E is the number of edges.
     *
     * 2. Dijkstra’s Algorithm
     *
     * Although Dijkstra's algorithm is more general and works for graphs with varying weights, it can be adapted for unit weights. However, BFS is more efficient for this specific case.
     *
     * Dijkstra's Algorithm Approach Explanation:
     * - **Initialize**: Create an adjacency list and a priority queue to manage vertices based on their shortest distance.
     * - **Distance Array**: Initialize distances from the source to Infinity except for the source itself.
     * - **Priority Queue**: Use a priority queue to always extend the shortest known path.
     * - **Relaxation**: Update distances and add updated vertices to the queue.
     *
     * 3. Standard BFS and Dijkstra’s Complexity Analysis
     *
     * **BFS**:
     * - **Time Complexity**: O(V + E)
     * - **Space Complexity**: O(V + E) for the adjacency list and queue.
     *
     * **Dijkstra’s Algorithm**:
     * - **Time Complexity**: O((V + E) log V) due to priority queue operations.
     * - **Space Complexity**: O(V + E) for the adjacency list and priority queue.
     *
     * **Conclusion**:
     * For unit-weight undirected graphs, BFS is the most straightforward and efficient method. Dijkstra’s algorithm is more general and useful for graphs with varying weights but can be overkill for unit-weight graphs.
     *
     * Dijkstra's algorithm is specifically designed to find the shortest path in a graph with non-negative weights. It is generally implemented using a priority queue, which is a standard approach for this problem. The algorithm uses a greedy approach to explore the shortest paths from a source node, ensuring that it always processes the closest (shortest distance) node next.
     *
     * **Key Characteristics of Dijkstra's Algorithm**:
     * - **Priority Queue**: Relies on a priority queue (or a min-heap) to efficiently get the next node with the smallest tentative distance. This is a crucial aspect of the algorithm's efficiency.
     * - **Greedy Strategy**: Uses a greedy strategy to always expand the shortest path first, ensuring that once a node's shortest path is determined, it is not revisited.
     * - **Non-negative Weights**: Works with graphs that have non-negative edge weights. It does not handle negative weights properly, which is why Bellman-Ford or other algorithms are used in those cases.
     *
     * **Dijkstra’s Algorithm and DFS**:
     * Dijkstra’s algorithm is not typically implemented with Depth-First Search (DFS) for the following reasons:
     * - **Different Objectives**: DFS is used to explore all possible paths and backtrack, which does not align with Dijkstra’s goal of finding the shortest path efficiently. DFS does not guarantee finding the shortest path in a weighted graph.
     * - **Priority Handling**: DFS does not inherently handle the priority of nodes based on their distances. Dijkstra’s algorithm needs a priority queue to manage the exploration of nodes based on their current shortest distance.
     * - **Efficiency**: Dijkstra’s algorithm is designed to be efficient with its time complexity of O((V + E) log V) using a priority queue, where V is the number of vertices and E is the number of edges. DFS does not provide the same efficiency for finding shortest paths, especially in graphs with a large number of nodes and edges.
     */
    public class ShortestPathUndirectedGraphDijkstra {
        static class Node {
            int vertex, wieght;

            Node(int vertex, int wieght) {
                this.vertex = vertex;
                this.wieght = wieght;
            }
        }

        public int[] shortestPath(int numVertices, int[][] edges, int src) {
            List<List<Node>> adj = new ArrayList<>(numVertices);
            for (int i = 0; i < numVertices; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adj.get(u).add(new Node(v, 1)); // Unit weight
                adj.get(v).add(new Node(u, 1)); // Undirected graph
            }
            int[] distance = new int[numVertices];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[src] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
                if (a.vertex != b.vertex) {
                    return a.wieght - b.wieght;
                } else {
                    return a.vertex - b.vertex;
                }
            });
            pq.offer(new ShortestPathUndirectedGraphDijkstra.Node(src, 0));
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int u = node.vertex;
                int currentWeight = node.wieght;

                if (currentWeight > distance[u]) continue; // Skip outdated entries
                for (Node neighbor : adj.get(u)) {
                    int v = neighbor.vertex;
                    int weight = neighbor.wieght;
                    if (distance[u] + weight < distance[v]) {
                        distance[v] = distance[u] + weight;
                        pq.offer(new Node(v, distance[v]));
                    }
                }
            }
            for (int i = 0; i < distance.length; i++) {
                if (distance[i] == Integer.MAX_VALUE) distance[i] = -1;
            }
            return distance;
        }

        public static void main(String[] args) {
            PartA_A_ShortestPathUndirectedGraph solver = new PartA_A_ShortestPathUndirectedGraph();

            int numVertices1 = 9;
            int[][] edges1 = {{0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6}, {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}};
            int src1 = 0;
            System.out.println(Arrays.toString(solver.shortestPath(numVertices1, edges1, src1))); // Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]

            int numVertices2 = 4;
            int[][] edges2 = {{1, 3}, {3, 0}};
            int src2 = 3;
            System.out.println(Arrays.toString(solver.shortestPath(numVertices2, edges2, src2))); // Output: [1, 1, -1, 0]
        }
    }











}

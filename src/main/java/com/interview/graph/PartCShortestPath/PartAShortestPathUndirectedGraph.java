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
public class PartAShortestPathUndirectedGraph {
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
            PartAShortestPathUndirectedGraph solver = new PartAShortestPathUndirectedGraph();

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
            int maximumDelay = Arrays.stream(distances).max().orElse(Integer.MAX_VALUE);

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


    /*
     * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
     * https://www.youtube.com/watch?v=PwMVNSJ5SLI&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=43
     * Category: Medium, Dijkstra, BFS, This floyd warshal algorithms
     * Related:
     * https://leetcode.com/problems/second-minimum-time-to-reach-destination/
     *
     * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
     *
     * There are n cities numbered from 0 to n-1. Given the array edges where
     * edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge
     * between cities fromi and toi, and given the integer distanceThreshold.
     *
     * Return the city with the smallest number of cities that are reachable
     * through some path and whose distance is at most distanceThreshold.
     * If there are multiple such cities, return the city with the greatest number.
     *
     * Notice that the distance of a path connecting cities i and j is equal to the
     * sum of the edges' weights along that path.
     *
     * Example 1:
     * Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
     * Output: 3
     * Explanation: The neighboring cities at a distanceThreshold = 4 for each city are:
     * City 0 -> [City 1, City 2]
     * City 1 -> [City 0, City 2, City 3]
     * City 2 -> [City 0, City 1, City 3]
     * City 3 -> [City 1, City 2]
     * Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4,
     * but we have to return city 3 since it has the greatest number.
     *
     * Example 2:
     * Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
     * Output: 0
     * Explanation: The neighboring cities at a distanceThreshold = 2 for each city are:
     * City 0 -> [City 1]
     * City 1 -> [City 0, City 4]
     * City 2 -> [City 3, City 4]
     * City 3 -> [City 2, City 4]
     * City 4 -> [City 1, City 2, City 3]
     * The city 0 has 1 neighboring city at a distanceThreshold = 2.
     *
     * Constraints:
     * 2 <= n <= 100
     * 1 <= edges.length <= n * (n - 1) / 2
     * edges[i].length == 3
     * 0 <= fromi < toi < n
     * 1 <= weighti, distanceThreshold <= 10^4
     * All pairs (fromi, toi) are distinct.
     *
     * Accepted: 38,051
     * Submissions: 74,391
     */
    public static class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

        // Node class to represent a city and its travel time
        private class Node {
            int city, travelTime;

            Node(int city, int travelTime) {
                this.city = city;
                this.travelTime = travelTime;
            }
        }

        public int findTheCity(int numCities, int[][] edges, int distanceThreshold) {
            // Initialize the adjacency list to store neighbors and travel times
            ArrayList<ArrayList<Node>> adjacencyList = new ArrayList<>();
            for (int i = 0; i < numCities; i++) {
                adjacencyList.add(new ArrayList<>());
            }

            // Populate the adjacency list with bidirectional edges and their weights
            for (int[] edge : edges) {
                int fromCity = edge[0], toCity = edge[1], travelTime = edge[2];
                adjacencyList.get(fromCity).add(new Node(toCity, travelTime));
                adjacencyList.get(toCity).add(new Node(fromCity, travelTime));
            }

            int minReachableCities = Integer.MAX_VALUE;
            int cityWithFewestNeighbors = -1;

            // Apply Dijkstra's algorithm from each city
            for (int startCity = 0; startCity < numCities; startCity++) {
                PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.travelTime - b.travelTime);
                int[] shortestDistances = new int[numCities];
                Arrays.fill(shortestDistances, Integer.MAX_VALUE);
                shortestDistances[startCity] = 0;

                int reachableCitiesCount = 0;
                minHeap.add(new Node(startCity, 0));

                while (!minHeap.isEmpty()) {
                    Node currentNode = minHeap.poll();
                    int currentCity = currentNode.city;
                    int currentTravelTime = currentNode.travelTime;

                    if (currentTravelTime > distanceThreshold) break;

                    reachableCitiesCount++;

                    for (Node neighbor : adjacencyList.get(currentCity)) {
                        int newDistance = currentTravelTime + neighbor.travelTime;
                        if (newDistance < shortestDistances[neighbor.city]) {
                            shortestDistances[neighbor.city] = newDistance;
                            minHeap.add(new Node(neighbor.city, newDistance));
                        }
                    }
                }

                // Update the city with the fewest neighbors or the greatest index if counts are equal
                if (reachableCitiesCount - 1 <= minReachableCities) {// adjusts the count to exclude the city itself if necessary.
                    minReachableCities = reachableCitiesCount - 1;
                    cityWithFewestNeighbors = startCity;
                }
            }

            return cityWithFewestNeighbors;
        }

        public static void main(String[] args) {
            FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance solver = new FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance();

            int numCities1 = 4;
            int[][] edges1 = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
            int distanceThreshold1 = 4;
            System.out.println(solver.findTheCity(numCities1, edges1, distanceThreshold1)); // Output: 3

            int numCities2 = 5;
            int[][] edges2 = {{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
            int distanceThreshold2 = 2;
            System.out.println(solver.findTheCity(numCities2, edges2, distanceThreshold2)); // Output: 0
        }
    }
    /*
     * Category: Medium
     *
     * Video Reference:
     * - https://www.youtube.com/watch?v=0vVofAhAYjc&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=22
     * https://leetcode.com/problems/path-with-minimum-effort/
     *
     * **Detailed Explanation**:
     *
     * 1. **Node Class**:
     *    - Represents a cell in the grid, storing its row, column, and the current effort needed to reach it.
     *
     * 2. **Priority Queue**:
     *    - Uses a min-heap to prioritize nodes with the smallest effort, implementing Dijkstra's algorithm to find the shortest path.
     *
     * 3. **Effort Matrix**:
     *    - Keeps track of the minimum effort required to reach each cell, initialized to infinity except for the starting cell, which is 0.
     *
     * 4. **Directions**:
     *    - Defines possible movements (up, down, left, right) to explore neighboring cells.
     *
     * **Algorithm Steps**:
     *
     * 1. Initialize the priority queue with the starting cell (0,0) and effort 0.
     * 2. While the queue is not empty, extract the node with the smallest effort.
     * 3. If this node is the target cell (bottom-right corner), return its effort.
     * 4. For each neighbor, calculate the new effort and update the priority queue and effort matrix if the new effort is smaller.
     *
     * **Time Complexity**:
     * - The time complexity is O(n ⋅ log(n)), where n is the total number of cells in the grid. The priority queue operations dominate the complexity.
     *
     * **Space Complexity**:
     * - The space complexity is O(n), which accounts for the priority queue and the effort matrix, both of which store information for all grid cells.
     *
     * This solution efficiently finds the path with the minimum effort from the top-left to the bottom-right corner of the grid using Dijkstra's algorithm.
     */
    public static class PathWithMinimumEffort {
        // Node class to represent grid cells and their efforts
        static class Node {
            int row, col, effort;

            Node(int row, int col, int effort) {
                this.row = row;
                this.col = col;
                this.effort = effort;
            }
        }

        public int minimumEffortPath(int[][] heights) {
            int rows = heights.length;
            int cols = heights[0].length;

            // Directions for moving in the grid (up, down, left, right)
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            // Initialize effort matrix with infinity
            int[][] effort = new int[rows][cols];
            for (int[] row : effort) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            effort[0][0] = 0; // Starting point effort is 0

            // Priority queue for Dijkstra's algorithm
            PriorityQueue<PathWithMinimumEffort.Node> priorityQueue = new PriorityQueue<>((a, b) -> a.effort - b.effort);
            priorityQueue.offer(new PathWithMinimumEffort.Node(0, 0, 0));

            // Dijkstra's algorithm
            while (!priorityQueue.isEmpty()) {
                PathWithMinimumEffort.Node currentNode = priorityQueue.poll();
                int currentRow = currentNode.row;
                int currentCol = currentNode.col;
                int currentEffort = currentNode.effort;

                // If we've reached the bottom-right corner, return the effort
                if (currentRow == rows - 1 && currentCol == cols - 1) {
                    return currentEffort;
                }

                // Explore neighbors
                for (int[] direction : directions) {
                    int newRow = currentRow + direction[0];
                    int newCol = currentCol + direction[1];

                    // Check if the neighbor is within bounds
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        // Calculate the effort to move to the neighbor
                        int newEffort = Math.max(currentEffort, Math.abs(heights[newRow][newCol] - heights[currentRow][currentCol]));

                        // If the new effort is smaller, update the effort matrix and push to the queue
                        if (newEffort < effort[newRow][newCol]) {
                            effort[newRow][newCol] = newEffort;
                            priorityQueue.offer(new PathWithMinimumEffort.Node(newRow, newCol, newEffort));
                        }
                    }
                }
            }

            return 0; // Should never reach here if the input is valid
        }

        public static void main(String[] args) {
            PathWithMinimumEffort solver = new PathWithMinimumEffort();
            int[][] heights1 = {
                    {1, 2, 2},
                    {3, 8, 2},
                    {5, 3, 5}
            };
            System.out.println(solver.minimumEffortPath(heights1)); // Output: 2

            int[][] heights2 = {
                    {1, 2, 3},
                    {3, 8, 4},
                    {5, 3, 5}
            };
            System.out.println(solver.minimumEffortPath(heights2)); // Output: 1

            int[][] heights3 = {
                    {1, 2, 1, 1, 1},
                    {1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1},
                    {1, 2, 1, 2, 1},
                    {1, 1, 1, 2, 1}
            };
            System.out.println(solver.minimumEffortPath(heights3)); // Output: 0
        }
    }

    /**
     * Problem: 778. Swim in Rising Water
     *
     * Category: Hard
     *
     * Link: https://leetcode.com/problems/swim-in-rising-water/
     *
     * Problem Explanation:
     * You are given an n x n grid where each cell represents the water level in that cell.
     * You need to find the minimum time required to swim from the top-left corner to the bottom-right corner.
     * You can move up, down, left, or right to neighboring cells. The time is determined by the maximum height
     * encountered on the path. Use a priority queue to prioritize nodes with the smallest height to ensure
     * the safest path is explored first.
     *
     * Approach:
     * - Node Class: Represents each cell in the grid, storing its row, column, and the maximum water level encountered.
     * - Priority Queue: Implements a Dijkstra-like algorithm to prioritize cells based on the minimum water level required to reach them.
     * - Visited Array: Keeps track of cells that have been processed to avoid reprocessing.
     * - Directions: Defines possible movements (up, down, left, right) to explore neighboring cells.
     *
     * Algorithm:
     * 1. Initialize the priority queue with the starting cell (0,0) and its height as the initial effort.
     * 2. While the queue is not empty, extract the cell with the smallest current height.
     * 3. If this cell is the bottom-right corner, return the maximum height encountered to reach it.
     * 4. For each neighbor, if it hasn't been visited, calculate the maximum height required to reach it,
     *    update the priority queue, and mark it as visited.
     *
     * Detailed Explanation:
     * - Node Class: Stores the row, column, and the maximum water level required to reach a cell.
     * - Priority Queue: A min-heap prioritizes nodes with the smallest water level, ensuring the path with the
     *   minimum height is explored first.
     * - Visited Array: Tracks cells that have been processed to prevent re-processing and infinite loops.
     * - Directions: Defines movements (up, down, left, right) to explore neighboring cells.
     *
     * Time Complexity:
     * O(n^2 * log(n)), where n^2 is the number of cells and the priority queue operations dominate the complexity.
     *
     * Space Complexity:
     * O(n^2), accounting for the priority queue and visited array, which store information for all grid cells.
     *
     * This solution efficiently finds the minimum time required to swim from the top-left to the bottom-right corner
     * of the grid using a Dijkstra-like algorithm.
     */
    public static class SwimInRisingWater {

        // Node class to represent grid cells and their maximum water level encountered so far
        static class Cell {
            int row, col, maxWaterLevel;

            Cell(int row, int col, int maxWaterLevel) {
                this.row = row;
                this.col = col;
                this.maxWaterLevel = maxWaterLevel;
            }
        }

        public int swimInWater(int[][] grid) {
            int n = grid.length;

            // Directions for moving in the grid (up, down, left, right)
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            // Priority queue for Dijkstra's algorithm
            PriorityQueue<SwimInRisingWater.Cell> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.maxWaterLevel, b.maxWaterLevel));
            priorityQueue.offer(new SwimInRisingWater.Cell(0, 0, grid[0][0]));

            // Visited array to keep track of visited cells
            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;

            // Dijkstra-like algorithm to find the minimum maximum water level
            while (!priorityQueue.isEmpty()) {
                SwimInRisingWater.Cell current = priorityQueue.poll();
                int currentRow = current.row;
                int currentCol = current.col;
                int currentMaxWaterLevel = current.maxWaterLevel;

                // If we've reached the bottom-right corner, return the maximum water level
                if (currentRow == n - 1 && currentCol == n - 1) {
                    return currentMaxWaterLevel;
                }

                // Explore neighbors
                for (int[] direction : directions) {
                    int newRow = currentRow + direction[0];
                    int newCol = currentCol + direction[1];

                    // Check if the neighbor is within bounds and not visited
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                        // Calculate the maximum water level to reach the neighbor
                        int newMaxWaterLevel = Math.max(currentMaxWaterLevel, grid[newRow][newCol]);
                        priorityQueue.offer(new SwimInRisingWater.Cell(newRow, newCol, newMaxWaterLevel));
                        visited[newRow][newCol] = true;
                    }
                }
            }

            return -1; // Should never reach here if the input is valid
        }

        public static void main(String[] args) {
            SwimInRisingWater solver = new SwimInRisingWater();

            int[][] grid1 = {
                    {0, 2},
                    {1, 3}
            };
            System.out.println(solver.swimInWater(grid1)); // Output: 3

            int[][] grid2 = {
                    {0, 1, 2, 3, 4},
                    {24, 23, 22, 21, 5},
                    {12, 13, 14, 15, 16},
                    {11, 17, 18, 19, 20},
                    {10, 9, 8, 7, 6}
            };
            System.out.println(solver.swimInWater(grid2)); // Output: 16
        }
    }

    /**
     * Solution for finding the cheapest flight within k stops.
     * Problem: https://leetcode.com/problems/cheapest-flights-within-k-stops/
     * Category: Medium, Must Do, VVImp, Solve using BFS or DFS
     * Explanation videos:
     * - BFS approach: https://www.youtube.com/watch?v=IQOG3w4abAg
     * - DFS + Pruning: https://www.youtube.com/watch?v=60RbWlDFsmI
     * - Another DFS approach: https://www.youtube.com/watch?v=71xQSBWUupU
     *
     * Problem Statement:
     * There are n cities connected by some number of flights. You are given an array flights where
     * flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
     * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
     * If there is no such route, return -1.
     *
     * Examples:
     * 1. Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
     *    Output: 700
     *    Explanation: The optimal path with at most 1 stop from city 0 to 3 is 100 + 600 = 700.
     *    The path [0,1,2,3] is invalid as it uses 2 stops.
     *
     * 2. Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
     *    Output: 200
     *    Explanation: The optimal path with at most 1 stop from city 0 to 2 is 100 + 100 = 200.
     *
     * 3. Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
     *    Output: 500
     *    Explanation: The optimal path with no stops from city 0 to 2 is 500.
     *
     * Constraints:
     * - 1 <= n <= 100
     * - 0 <= flights.length <= (n * (n - 1) / 2)
     * - flights[i].length == 3
     * - 0 <= fromi, toi < n
     * - fromi != toi
     * - 1 <= pricei <= 10^4
     * - There will not be any multiple flights between two cities.
     * - 0 <= src, dst, k < n
     * - src != dst
     * - Accepted: 227,566
     * - Submissions: 631,092
     */
    public static class CheapestFlightsWithinKStops {

        // Class representing a flight node with destination, cost, and number of stops
        static class FlightNode {
            int destinationCity;   // Destination city
            int flightCost;         // Cost of the flight
            int stopsTaken;         // Number of stops taken

            FlightNode(int destinationCity, int flightCost, int stopsTaken) {
                this.destinationCity = destinationCity;
                this.flightCost = flightCost;
                this.stopsTaken = stopsTaken;
            }
        }

        /**
         * Finds the cheapest price to travel from the start city to the destination city with at most k stops.
         * This method uses a modified Dijkstra's algorithm.
         *
         * @param totalCities Total number of cities
         * @param flightDetails Array of flight details where each flight is represented as [fromCity, toCity, price]
         * @param startCity Starting city
         * @param destinationCity Target city
         * @param maxStops Maximum number of allowable stops
         * @return Minimum cost to reach the destination city or -1 if no valid route exists
         */
        public int findCheapestPrice(int totalCities, int[][] flightDetails, int startCity, int destinationCity, int maxStops) {
            // Build adjacency list for the graph
            List<List<FlightNode>> adjacencyList = new ArrayList<>(totalCities);
            for (int i = 0; i < totalCities; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            for (int[] flight : flightDetails) {
                int fromCity = flight[0];
                int toCity = flight[1];
                int cost = flight[2];
                adjacencyList.get(fromCity).add(new FlightNode(toCity, cost, 0)); // 0 stops initially
            }

            // Initialize minimum cost array and priority queue
            int[] minimumCost = new int[totalCities];
            Arrays.fill(minimumCost, Integer.MAX_VALUE); // Set initial costs to infinity
            minimumCost[startCity] = 0;

            PriorityQueue<FlightNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.flightCost));
            queue.offer(new FlightNode(startCity, 0, 0)); // (city, cost, stops)

            // Perform modified Dijkstra's algorithm
            while (!queue.isEmpty()) {
                FlightNode current = queue.poll();
                int currentCity = current.destinationCity;
                int currentCost = current.flightCost;
                int currentStops = current.stopsTaken;

                // If the destination city is reached within allowed stops, return the cost
                if (currentCity == destinationCity) return currentCost;

                // Skip if the number of stops exceeds the maximum allowed
                if (currentStops > maxStops) continue;

                // Explore neighboring cities
                for (FlightNode neighbor : adjacencyList.get(currentCity)) {
                    int nextCity = neighbor.destinationCity;
                    int nextCost = neighbor.flightCost;
                    int totalCost = currentCost + nextCost;

                    // Update the cost if a cheaper route is found within the stop limit
                    if (totalCost < minimumCost[nextCity]) {
                        minimumCost[nextCity] = totalCost;
                        queue.offer(new FlightNode(nextCity, totalCost, currentStops + 1));
                    }
                }
            }

            return -1; // Return -1 if no valid route is found
        }

        /**
         * Finds the cheapest price to travel from the start city to the destination city with at most k stops using DFS and pruning.
         *
         * @param totalCities Total number of cities
         * @param flightDetails Array of flight details where each flight is represented as [fromCity, toCity, price]
         * @param startCity Starting city
         * @param destinationCity Target city
         * @param maxStops Maximum number of allowable stops
         * @return Minimum cost to reach the destination city or -1 if no valid route exists
         */
        public int findCheapestPriceDFS(int totalCities, int[][] flightDetails, int startCity, int destinationCity, int maxStops) {
            // Build adjacency list and cost matrix
            List<Integer>[] adjacencyList = new ArrayList[totalCities];
            int[][] costMatrix = new int[totalCities][totalCities];
            for (int i = 0; i < totalCities; i++) {
                adjacencyList[i] = new ArrayList<>();
            }
            for (int[] flight : flightDetails) {
                adjacencyList[flight[0]].add(flight[1]);
                costMatrix[flight[0]][flight[1]] = flight[2];
            }

            boolean[] visited = new boolean[totalCities];
            int[] minimumCost = new int[1];
            minimumCost[0] = Integer.MAX_VALUE;

            // Start DFS with pruning
            performDFS(adjacencyList, costMatrix, startCity, destinationCity, maxStops, minimumCost, 0, visited);

            return minimumCost[0] == Integer.MAX_VALUE ? -1 : minimumCost[0];
        }

        private void performDFS(List<Integer>[] adjacencyList, int[][] costMatrix, int currentCity, int destinationCity, int remainingStops, int[] minimumCost, int currentCost, boolean[] visited) {
            if (remainingStops < -1) return; // Base case: too many stops

            if (currentCity == destinationCity) {
                minimumCost[0] = Math.min(minimumCost[0], currentCost);
                return;
            }

            visited[currentCity] = true;
            for (int neighbor : adjacencyList[currentCity]) {
                if (!visited[neighbor] && currentCost + costMatrix[currentCity][neighbor] <= minimumCost[0]) {
                    performDFS(adjacencyList, costMatrix, neighbor, destinationCity, remainingStops - 1, minimumCost, currentCost + costMatrix[currentCity][neighbor], visited);
                }
            }
            visited[currentCity] = false; // Backtrack
        }

        public static void main(String[] args) {
            CheapestFlightsWithinKStops solver = new CheapestFlightsWithinKStops();
            int[][] flights1 = {
                    {0, 1, 100},
                    {1, 2, 100},
                    {2, 0, 100},
                    {1, 3, 600},
                    {2, 3, 200}
            };
            System.out.println(solver.findCheapestPrice(4, flights1, 0, 3, 1)); // Output: 700

            int[][] flights2 = {
                    {0, 1, 100},
                    {1, 2, 100},
                    {0, 2, 500}
            };
            System.out.println(solver.findCheapestPrice(3, flights2, 0, 2, 1)); // Output: 200

            int[][] flights3 = {
                    {0, 1, 100},
                    {1, 2, 100},
                    {0, 2, 500}
            };
            System.out.println(solver.findCheapestPrice(3, flights3, 0, 2, 0)); // Output: 500
        }
    }

}

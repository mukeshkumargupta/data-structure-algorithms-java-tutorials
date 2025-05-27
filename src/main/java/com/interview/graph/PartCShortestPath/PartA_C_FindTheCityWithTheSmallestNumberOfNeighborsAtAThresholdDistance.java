package com.interview.graph.PartCShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PartA_C_FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
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
}

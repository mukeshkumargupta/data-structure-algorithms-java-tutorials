package com.interview.graph.PartCShortestPath;

import java.util.*;

public class PartA_F_CheapestFlightsWithinKStops {
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

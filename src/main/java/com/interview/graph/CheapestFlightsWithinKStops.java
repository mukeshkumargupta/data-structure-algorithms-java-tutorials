package com.interview.graph;

/*
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * Category: Medium, Must Do, VVImp, Solve using BFS or DFS
 * Very good explanation using bfs
 * https://www.youtube.com/watch?v=IQOG3w4abAg
 * DFS + Pruning which is slow
 * https://www.youtube.com/watch?v=60RbWlDFsmI
 * This solution also not passing
 * https://www.youtube.com/watch?v=71xQSBWUupU
 * 787. Cheapest Flights Within K Stops
 Category : Medium
 Related: 

4764

212

Add to List

Share
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 

Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
Accepted
227,566
Submissions
631,092
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /*
         * BFS approach
         * GEtting TLE
         * Use this approach to avoid TLE
         * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
         */
        // Build adjucency list
        //Each node has pair src and cost
        List<Integer[]>[] adjList = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            adjList[flight[0]].add(new Integer[]{flight[1], flight[2]});
        }
        
        
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a,b) -> {
            return a.get(0) - b.get(0);
        });
        List<Integer> node = new ArrayList<>();
        node.add(0);//cost
        node.add(src);
        node.add(k+1);//edge 1 extra for 1 stop
        pq.add(node);
        
        while(!pq.isEmpty()) {
            
            List<Integer> currentNode = pq.remove();
            int cost = currentNode.get(0);
            int CurrentNodeSource = currentNode.get(1);
            int e = currentNode.get(2);
            if (CurrentNodeSource == dst) {
                return cost;
            }
            
            if (e > 0) {
                for (Integer[] pair : adjList[CurrentNodeSource]) {
                    node = new ArrayList<>();
                    node.add(cost + pair[1]);//cost
                    node.add(pair[0]);
                    node.add(e-1);//edge 1 extra for 1 stop
                    
                    pq.add(node);
                }
                
            }  
        }
        
        return -1;
        
    }
    
    void findCheapestPriceUtil(List<Integer>[] adjList,int [][] cost,int src,int dst,int k,int[] result,int totCost,boolean[] visited)
    {
        if(k<-1)
            return;
        if(src==dst)
        {
            result[0] = Math.min(result[0],totCost);
            return;
        }
    
        visited[src] = true;
        List<Integer> adj = adjList[src];
        for(Integer elm : adj)
        {
            if(!visited[elm] && (totCost+cost[src][elm] <= result[0]))
                findCheapestPriceUtil(adjList,cost,elm,dst,k-1,result,totCost+cost[src][elm],visited);
        }
        
        visited[src] = false;//backtrack
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /*
         * TLE: find betetr approach, this is dfs + pruning
         */
        // Build adjucency list
        List<Integer>[] adjList = new ArrayList[n];
        int [][] costMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            adjList[flight[0]].add(flight[1]);
            costMatrix[flight[0]][flight[1]] = flight[2];
        }
        
        
        boolean[] visited = new boolean[n];
        int[] result = new int[1];
        result[0] = Integer.MAX_VALUE;
        findCheapestPriceUtil(adjList,costMatrix,src,dst,k,result,0,visited);
        
        if(result[0]==Integer.MAX_VALUE)
            return -1;
        return result[0];
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

package com.interview.graph;

import java.util.*;

/*
 * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 * Category: Medium, dijakstra, bfs
 * Related:
 * https://leetcode.com/problems/second-minimum-time-to-reach-destination/
 * 
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
Medium

1120

56

Add to List

Share
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

 

Example 1:


Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
Example 2:


Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.
 

Constraints:

2 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti, distanceThreshold <= 10^4
All pairs (fromi, toi) are distinct.
Accepted
38,051
Submissions
74,391
 * 
 * 
 */

public class FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    private class Pair{
        int node,weight;
        Pair(int node,int weight){
            this.node=node;
            this.weight=weight;
        }
    }
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        /*
         * Runtime: 36 ms, faster than 42.11% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.
Memory Usage: 42.5 MB, less than 98.32% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance
         */
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int arr[]:edges){
            int u=arr[0],v=arr[1],w=arr[2];
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }
        int min=Integer.MAX_VALUE,city=-1;
        for(int src=0;src<n;src++){
            PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
                return a.weight-b.weight;
            });
            boolean[] vis=new boolean[n];
            int[] arr=new int[n];
            Arrays.fill(arr,Integer.MAX_VALUE);
            arr[src]=0;
            int count=0;
            pq.add(new Pair(src,0));
            while(pq.size()>0){
                Pair p=pq.remove();
                int node=p.node,weight=p.weight;
                if(vis[node]) continue;
                vis[node]=true;
                if(weight<=distanceThreshold)
                    count++;
                for(Pair ele:adj.get(node)){
                    if(arr[node]+ele.weight<arr[ele.node]){
                        arr[ele.node]=arr[node]+ele.weight;
                        pq.add(new Pair(ele.node,arr[ele.node]));
                    }
                }
            }
            if(count<=min){
                min=count;
                city=src;
            }
        }
        return city;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

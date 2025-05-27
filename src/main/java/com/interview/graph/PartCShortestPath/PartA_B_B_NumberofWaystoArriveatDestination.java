package com.interview.graph.PartCShortestPath;

import java.util.*;
    /*
     * Problem Link: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
     * Category: Medium, Must Do, BFS, Standard code for min-heap using BFS. See other code and modify accordingly.
     * Video Explanation: https://www.youtube.com/watch?v=1JCXqupyLoQ (Very good explanation)
     * Related Problems:
     * - https://leetcode.com/problems/second-minimum-time-to-reach-destination/ (Hard, VVImp)
     * - https://leetcode.com/problems/path-with-maximum-probability/ (Medium, VVImp)
     *
     * Problem Description:
     * You are in a city consisting of `n` intersections numbered from 0 to n - 1, with bi-directional roads between some intersections.
     * The inputs are such that you can reach any intersection from any other, and there is at most one road between any two intersections.
     *
     * You are given an integer `n` and a 2D integer array `roads` where `roads[i] = [ui, vi, timei]` means there is a road between intersections `ui` and `vi`
     * that takes `timei` minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
     *
     * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Example 1:
     * Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
     * Output: 4
     * Explanation: The shortest amount of time to go from intersection 0 to intersection 6 is 7 minutes.
     * The four ways to get there in 7 minutes are:
     * - 0 ➝ 6
     * - 0 ➝ 4 ➝ 6
     * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
     * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
     *
     * Example 2:
     * Input: n = 2, roads = [[1,0,10]]
     * Output: 1
     * Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
     *
     * Constraints:
     * - 1 <= n <= 200
     * - n - 1 <= roads.length <= n * (n - 1) / 2
     * - roads[i].length == 3
     * - 0 <= ui, vi <= n - 1
     * - 1 <= timei <= 10^9
     * - ui != vi
     * - There is at most one road connecting any two intersections.
     * - You can reach any intersection from any other intersection.
     */
public class PartA_B_B_NumberofWaystoArriveatDestination {
        private int dijkstra(int[][] roads,int n){
            /*
             * Runtime: 10 ms, faster than 86.04% of Java online submissions for Number of Ways to Arrive at Destination.
Memory Usage: 49.3 MB, less than 88.34% of Java online submissions for Number of Ways to Arrive at Destination.
             */
            //long mod=(int)1e9+7;
            long mod=(long)1e9+7;
            //PriorityQueue is used for sorting by time
            Queue<long[]> pq = new PriorityQueue<>((l, r) -> Long.compare(l[1], r[1]));

            //Number of ways to reach a vertex from 0 in minimum time
            long[] ways=new long[n];
            //Distance array to store the minimum time taken to reach a vertex
            long[] dist=new long[n];
            //Filling dist array with infinite distance.
            // Arrays.fill(dist,(long)1e18);
            Arrays.fill(dist,Long.MAX_VALUE);
            dist[0]=0;
            //Number of ways to reach 0 is 1.
            ways[0]=1;
            //We have to form graph roads array.
            ArrayList<long[]>[] graph=new ArrayList[n];
            for(int i=0;i<graph.length;i++) graph[i]=new ArrayList<>();
            for(int[] road:roads){
                graph[road[0]].add(new long[]{road[1],road[2]});
                graph[road[1]].add(new long[]{road[0],road[2]});
            }
            pq.add(new long[]{0,0});
            //Normal Dijkstra bfs is implemented.
            while(pq.size() >0){
                long[] ele=pq.remove();
                long dis=ele[1];
                //Node value we are on(Parent Node).
                long node=ele[0];
                for(long[] child:graph[(int)node]){
                    //Adjancent Node weight from parent.
                    long wt=child[1];
                    //Adjacent Node to parent
                    long adjNode=child[0];
                    //If the wt+dis (i.e time here) is less than already time taken then will update dist[(int)adjNode] and number of ways will be equal to ways[(int)node]
                    if(wt+dis<dist[(int)adjNode]){
                        dist[(int)adjNode]=wt+dis;
                        ways[(int)adjNode]=ways[(int)node];
                        pq.add(new long[]{adjNode,dist[(int)adjNode]});
                    }
                    //if wt+dis (i.e time here) is equal to already taken time then we will add it in ways array.
                    else if(wt+dis==dist[(int)adjNode]){
                        ways[(int)adjNode]=(ways[(int)node]+ways[(int)adjNode])%mod; //I did not understood
                    }
                }
            }
            return (int)ways[n-1];
        }

    
        public int countPaths(int n, int[][] roads) {
            return dijkstra(roads,n);        
        }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

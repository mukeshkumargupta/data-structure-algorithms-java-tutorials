package com.interview.graph;

/*
 * Reference: https://leetcode.com/problems/network-delay-time/
 * Category:Medium, Must Know
 * Related: Dijakshtra, shortest distance from single source
 * Video: https://www.youtube.com/watch?v=t2d-XYuPfg0&t=304s
 * https://www.youtube.com/watch?v=Sj5Z-jaE2x0
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 

Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {
    int minDistanceIndex(int[] d, boolean[] visited, int n) {
        int min = Integer.MAX_VALUE;
        int foundIndex = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && d[i] < min) {
                min = d[i];
                foundIndex = i;
            }
        }
        return foundIndex;
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        boolean[] visited = new boolean[n];
        int[] d = new int[n];
        
        for (int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        
        //Fill matrix
        int[][] input  = new int [n][n];
        for (int i = 0; i < n; i++) {
            for (int j=0; j< n; j++) {
                if (i ==j) {
                    input[i][j] = 0;
                } else {
                    input[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int[] edge : times) {
            input[edge[0]-1][edge[1]-1] = edge[2];
        }
        d[k-1] = 0;
        for (int i = 0 ; i < n-1; i++) {
            //Find node which has min distance
            int node = minDistanceIndex(d, visited, n);
            if (node == -1) {
               return -1; 
            }
            visited[node] = true;
            //Minimize all connected node
            for(int j = 0 ; j < n; j++) {
                if (!visited[j] && input[node][j] != Integer.MAX_VALUE && d[node] + input[node][j] < d[j]) {
                    d[j] = d[node] + input[node][j];
                }
            }
        }
        /*for (int val : d) {
            System.out.print(val + " ");
        }*/
        //find max in distance
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (d[i] == Integer.MAX_VALUE) {
                return -1;
            } else if (d[i] > max) {
                max = d[i];
            } 
        }
        return max;
    }
    
}

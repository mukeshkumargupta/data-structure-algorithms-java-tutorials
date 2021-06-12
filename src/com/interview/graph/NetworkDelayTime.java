package com.interview.graph;

/*
 * Reference: https://leetcode.com/problems/network-delay-time/
 * Category:Medium
 * Related: Dijakshtra, shortest distance from single source
 * Video: https://www.youtube.com/watch?v=t2d-XYuPfg0&t=304s
 * https://www.youtube.com/watch?v=Sj5Z-jaE2x0
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
        int[] parentLookup = new int[n];
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

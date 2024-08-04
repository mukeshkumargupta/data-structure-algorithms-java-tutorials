package com.interview.graph;

import java.util.*;

/**
 * Date 11/05/2017
 * 
 * @author Mukesh Kumar Gupta
 *
 *         Write program for Bellman Ford algorithm to find single source
 *         shortest path in directed graph. Bellman ford works with negative
 *         edges as well unlike Dijksra's algorithm. If there is negative weight
 *         cycle it detects it.
 *         https://www.youtube.com/watch?v=75yC1vbS8S8&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=71
 *         Category: Medium, Must Do
 *         Related:
 *         https://leetcode.com/problems/cheapest-flights-within-k-stops/ Medium
 *         https://leetcode.com/problems/path-with-maximum-probability/ Medium
 *
 *         Time complexity - O(EV) Space complexity - O(V)
 *
 *         References
 *         https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
 *         http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 */

public class BellmanFordShortestPath {
    
    class Node {
        private int u;
        private int v;
        private int weight;
        
        Node(int _u, int _v, int _w) {
            u = _u;
            v = _v;
            weight = _w;
        }
        
        Node() {
        }
        
        int getV() {
            return v;
        }
        
        int getU() {
            return u;
        }
        
        int getWeight() {
            return weight;
        }
        
    }
    
    void bellmanFord(ArrayList<Node> edges, int N, int src) {
        int dist[] = new int[N];
        for (int i = 0; i < N; i++)
            dist[i] = 10000000;//Better take Integer.MAX_VALUE;
        
        dist[src] = 0;
        
        for (int i = 1; i <= N - 1; i++) {
            for (Node node : edges) {
                if (dist[node.getU()] + node.getWeight() < dist[node.getV()]) {
                    dist[node.getV()] = dist[node.getU()] + node.getWeight();
                }
            }
        }
        
        int fl = 0;
        for (Node node : edges) {
            if (dist[node.getU()] + node.getWeight() < dist[node.getV()]) {
                fl = 1;
                System.out.println("Negative Cycle");
                break;
            }
        }
        
        if (fl == 0) {
            for (int i = 0; i < N; i++) {
                System.out.println(i + " " + dist[i]);
            }
        }
    }
    
    void runBelManFord() {
        int n = 6;
        ArrayList<Node> adj = new ArrayList<Node>();
        
        adj.add(new Node(3, 2, 6));
        adj.add(new Node(5, 3, 1));
        adj.add(new Node(0, 1, 5));
        adj.add(new Node(1, 5, -3));
        adj.add(new Node(1, 2, -2));
        adj.add(new Node(3, 4, -2));
        adj.add(new Node(2, 4, 3));
        bellmanFord(adj, n, 0);
    }
    
    public static void main(String args[]) {
        BellmanFordShortestPath obj = new BellmanFordShortestPath();
        obj.runBelManFord();
        
    }
}

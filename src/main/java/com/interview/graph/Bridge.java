package com.interview.graph;

import java.util.*;


/**
 http://www.geeksforgeeks.org/bridge-in-a-graph/
 https://www.youtube.com/watch?v=2rjZH0-2lhk&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=64
 Category: Must Do
 */
public class Bridge {
        private void dfs(int node, int parent, int vis[], int tin[], int low[], ArrayList<ArrayList<Integer>> adj, int timer) {
            vis[node] = 1; 
            tin[node] = low[node] = timer++; 

            for(Integer it: adj.get(node)) {
                if(it == parent) continue; //need to understand carefully this check

                if(vis[it] == 0) {
                    dfs(it, node, vis, tin, low, adj, timer); 
                    low[node] = Math.min(low[node], low[it]); 

                    if(low[it] > tin[node]) {
                        System.out.println(it + " " +node); 
                    }
                } else {
                    low[node] = Math.min(low[node], tin[it]); 
                }
            }
        }
        void printBridges(ArrayList<ArrayList<Integer>> adj, int n)
        {
            int vis[] = new int[n]; 
            int tin[] = new int[n];
            int low[] = new int[n]; 

            int timer = 0; 
            for(int i = 0;i<n;i++) {
                if(vis[i] == 0) {
                    dfs(i, -1, vis, tin, low, adj, timer); 
                }
            }
        }
        public void bridgeInGraph() {
            int n = 5;
            ArrayList<ArrayList<Integer> > adj = new ArrayList<ArrayList<Integer> >();
            
            for (int i = 0; i < n; i++) 
                adj.add(new ArrayList<Integer>());
                
            adj.get(0).add(1);
            adj.get(1).add(0);

            adj.get(0).add(2);
            adj.get(2).add(0);

            adj.get(1).add(2);
            adj.get(2).add(1);

            adj.get(1).add(3);
            adj.get(3).add(1);

            adj.get(3).add(4);
            adj.get(4).add(3);
                
            printBridges(adj, n); 
        }
        public static void main(String args[])
        {
            Bridge obj = new Bridge(); 
            obj.bridgeInGraph(); 

        }

}

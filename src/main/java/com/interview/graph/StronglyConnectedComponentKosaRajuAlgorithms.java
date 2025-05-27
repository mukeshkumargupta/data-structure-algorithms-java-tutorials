package com.interview.graph;

import java.util.*;

/**
 * Date 10/01/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a directed graph, find all strongly connected components in this graph.
 * We are going to use Kosaraju's algorithm to find strongly connected component.
 * https://www.youtube.com/watch?v=V8qIqJxCioo&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=69
 * Category: Medium, Must Do
 * 
 * Related:
 * https://leetcode.com/problems/longest-consecutive-sequence/ Medium
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/ Hard
 * https://leetcode.com/problems/number-of-provinces/ Medium, Soln: Simple DFS, already done
 *
 * Algorithm
 * Create a order of vertices by finish time in decreasing order.
 * Reverse the graph
 * Do a DFS on reverse graph by finish time of vertex and created strongly connected
 * components.
 *
 * Runtime complexity - O(V + E)
 * Space complexity - O(V)
 *
 * References
 * https://en.wikipedia.org/wiki/Strongly_connected_component
 * http://www.geeksforgeeks.org/strongly-connected-components/
 */
public class StronglyConnectedComponentKosaRajuAlgorithms {
        private void dfs(int node, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj, int vis[]) {
            vis[node] = 1;
            for(Integer it : adj.get(node)) {
                if(vis[it] == 0) {
                    dfs(it, st, adj, vis); 
                }
            }

            st.push(node); 
        }

        private void revDfs(int node, ArrayList<ArrayList<Integer>> transpose, int vis[]) {
            vis[node] = 1;
            System.out.print(node + " "); 
            for(Integer it : transpose.get(node)) {
                if(vis[it] == 0) {
                    revDfs(it, transpose, vis); 
                }
            }
        }

        void kosaRaju(ArrayList<ArrayList<Integer>> adj, int n)
        {
            int vis[] = new int[n]; 
            Stack<Integer> st = new Stack<Integer>(); 
            for(int i = 0;i<n;i++) {
                if(vis[i] == 0) {
                    dfs(i, st, adj, vis); 
                }
            }

            ArrayList<ArrayList<Integer> > transpose = new ArrayList<ArrayList<Integer> >();
            
            for (int i = 0; i < n; i++) 
                transpose.add(new ArrayList<Integer>());

            for(int i = 0;i<n;i++) {
                vis[i] = 0; 
                for(Integer it: adj.get(i)) {
                    transpose.get(it).add(i); 
                }
            }

            while(st.size() > 0) {
                int node = st.peek(); 
                st.pop(); 
                if(vis[node] == 0) {
                    System.out.print("SCC: "); 
                    revDfs(node, transpose, vis);
                    System.out.println(); 
                }
            }

        }
        void runSCC() {
            int n = 5;
            ArrayList<ArrayList<Integer> > adj = new ArrayList<ArrayList<Integer> >();
            
            for (int i = 0; i < n; i++) 
                adj.add(new ArrayList<Integer>());
                
            adj.get(0).add(1);
            adj.get(1).add(2);
            adj.get(2).add(0);
            adj.get(1).add(3);
            adj.get(3).add(4);
            kosaRaju(adj, n); 
        }
        public static void main(String args[])
        {
            StronglyConnectedComponentKosaRajuAlgorithms obj = new StronglyConnectedComponentKosaRajuAlgorithms();
            obj.runSCC(); 
            
        }

    /*
    5 5 
    0 1 
    1 2 
    2 0 
    1 3 
    3 4 
    */
}

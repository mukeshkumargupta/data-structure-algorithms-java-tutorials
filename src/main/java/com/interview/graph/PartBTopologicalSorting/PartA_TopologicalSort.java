package com.interview.graph.PartBTopologicalSorting;

import java.util.*;

/**
 * Date 08/20/2017
 * @author Mukesh Kumar Gupta
 * https://www.youtube.com/watch?v=Yh6EFazXipA&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=55
 * Category: Medium, Must Do
 *
 */
public class PartA_TopologicalSort {

    static void findTopoSort(int node, int vis[], ArrayList<ArrayList<Integer>> adj, Stack<Integer> st) {
        vis[node] = 1; 
        for(Integer it: adj.get(node)) {
            if(vis[it] == 0) {
                findTopoSort(it, vis, adj, st); 
            } 
        }
        st.push(node); 
    }
    static int[] topoSort(int N, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<Integer>(); 
        int vis[] = new int[N]; 
        
        for(int i = 0;i<N;i++) {
            if(vis[i] == 0) {
                findTopoSort(i, vis, adj, st);
            }
        }
        
        int topo[] = new int[N];
        int ind = 0; 
        while(!st.isEmpty()) {
            topo[ind++] = st.pop();
        }
        // for(int i = 0;i<N;i++) System.out.println(topo[i] + " "); 
        return topo; 
    }
}

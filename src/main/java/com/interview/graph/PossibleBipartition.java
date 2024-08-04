package com.interview.graph;

import java.util.*;

/*
 * https://leetcode.com/problems/possible-bipartition/
 * Category: Medium, Must Do
 * https://www.youtube.com/watch?v=0ACfAqs8mm0&list=PLIA-9QRQ0RqFtv70kQcM7y0Gjt5wjGW90&index=34
 * Related:
 * https://leetcode.com/problems/serialize-and-deserialize-bst/ Medium
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/ Medium
 * https://leetcode.com/problems/find-center-of-star-graph/ Easy
 * https://leetcode.com/problems/mini-parser/ Medium Bad
 * https://leetcode.com/problems/loud-and-rich/ Medium Imp
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/ Medium

 */
public class PossibleBipartition {
    public static boolean possibleBipartition(int n, int[][] dislikes) {//Runtime: 11 ms, faster than 98.34% of Java online submissions for Possible Bipartition.
        public boolean possibleBipartition(int n, int[][] dislikes) {
            List<Integer>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }
            
            //Adjacency list
            for (int[] dislike : dislikes) {
                adjList[dislike[0]-1].add(dislike[1]-1);
                adjList[dislike[1]-1].add(dislike[0]-1);
            }
            
            int[] color = new int[n];
            /*for (int i = 0; i < n; i++) {
                color[i] = -1;
            }*/
            Arrays.fill(color, -1);
            //Queue<Integer> q = new LinkedList<>();
            
            for (int i = 0; i < n; i++) {
                if (color[i] == -1) {
                    Queue<Integer> q = new LinkedList<>();
                    color[i] = 1;
                    //System.out.println(i + "-> " + currentColor);
                    q.add(i);
                    
                    //bfs code
                    while(!q.isEmpty()) {
                        int node = q.remove();
                        for (Integer child : adjList[node]) {
                            
                            if (color[child] == color[node]) {//conflict then return false;
                                return false;
                            }
                            
                            //Try to color if not color or if already coloured then opposite with current colour
                            if (color[child] == -1) {
                               color[child] =  1 -  color[node];
                                q.add(child);
                                //System.out.println(elm + "-> " + adjColor);
                            }
                        }
                        
                    }
                }
            }
            return true;
        }
        
    }
    
    
    public boolean possibleBipartition(int n, int[][] dislikes) {
        /*
         * Runtime: 11 ms, faster than 98.14% of Java online submissions for Possible Bipartition.
Memory Usage: 49.8 MB, less than 91.68% of Java online submissions for Possible Bipartition.
         */
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        //Adjacency list
        for (int[] dislike : dislikes) {
            adjList[dislike[0]-1].add(dislike[1]-1);
            adjList[dislike[1]-1].add(dislike[0]-1);
        }
        
        int color[] = new int[n];
        Arrays.fill(color,-1);
        
        for(int i=0;i<n;i++){
            if(color[i] == -1){
                color[i] = 1;
                if(!dfs(adjList,color,i)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean dfs(List<Integer>[] graph,int color[],int node){
        for(int child : graph[node]){
            if (color[child] == color[node]) return false;
            
            if(color[child] == -1){//not visited
                color[child] = 1 - color[node];
                if(!dfs(graph,color,child)){
                    return false;
                }
            }

        }
        return true;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] input = {{1, 2}, {1, 3}, {2, 3}};
        possibleBipartition(3, input);
    }
    
}

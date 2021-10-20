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

 */
public class PossibleBipartition {
    public static boolean possibleBipartition(int n, int[][] dislikes) {//Runtime: 11 ms, faster than 98.34% of Java online submissions for Possible Bipartition.
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
        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                color[i] = 1;
                //System.out.println(i + "-> " + currentColor);
                q.add(i);
                
                //bfs code
                while(!q.isEmpty()) {
                    int current = q.remove();
                    int currentColor = color[current];
                    int adjColor = 1 - currentColor;
                    List<Integer> adj = adjList[current];
                    for (Integer elm : adj) {
                        //Try to color if not color or if already coloured then opposite with current colour
                        if (color[elm] == -1) {
                           color[elm] =  adjColor;
                            q.add(elm);
                            //System.out.println(elm + "-> " + adjColor);
                        } else {
                            if (color[elm] == currentColor) {//conflict then return false;
                                return false;
                            }
                        }
                        
                    }
                    
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

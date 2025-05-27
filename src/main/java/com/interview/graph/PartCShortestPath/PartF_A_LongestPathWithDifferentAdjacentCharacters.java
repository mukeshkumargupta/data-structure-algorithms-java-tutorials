package com.interview.graph.PartCShortestPath;

import java.util.ArrayList;
import java.util.List;/*
/**
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
 * Category: Hard, Tricky, Graph DFS
 * Video Explanation: https://www.youtube.com/watch?v=yTflID2AMm8
 *
 * 2246. Longest Path With Different Adjacent Characters
 *
 * You are given a tree (connected, acyclic graph) with n nodes,
 * represented by an array `parent`, where parent[i] is the parent of node i.
 * The root of the tree is node 0, so parent[0] == -1.
 *
 * Each node has a character assigned from the string `s`, where s[i] is the character for node i.
 *
 * Return the length of the longest path in the tree such that
 * no two adjacent nodes have the same character.
 *
 * Constraints:
 * - n == parent.length == s.length
 * - 1 <= n <= 10^5
 * - parent[0] == -1
 * - s consists of only lowercase English letters.
 */

public class PartF_A_LongestPathWithDifferentAdjacentCharacters {
    int[] dis = new int[100000];
    int ans = 1;
    void dfs(int src, List<Integer>[] adjList, String s) {
        dis[src] = 1;
        
        for (int child : adjList[src]) {
            dfs(child, adjList, s);
            if (s.charAt(src) != s.charAt(child)) {
                //Post order
                ans = Math.max(ans, dis[src] + dis[child]);//including root we can get max
                dis[src] = Math.max(dis[src] , dis[child]+1);//Find max of all child so dis[src] is found yet and dis[child]+1 is from one of the path max
            }

            
        }
    }
    public int longestPath(int[] parent, String s) {
        /*
         * Runtime: 185 ms, faster than 41.98% of Java online submissions for Longest Path With Different Adjacent Characters.
Memory Usage: 149.2 MB, less than 19.58% of Java online submissions for Longest Path With Different Adjacent Characters.
         */
        int l = parent.length;
        List<Integer>[] adjList = new ArrayList[l];
        for (int i = 0; i < l; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for (int i = 1; i< l; i++) {
            adjList[parent[i]].add(i);
        }
        dfs(0, adjList, s);
        return ans;
        
    }
}


package com.interview.graph;

/*
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
 * Category: Hard, Tricky, graph dfs
 * https://www.youtube.com/watch?v=yTflID2AMm8
 * 2246. Longest Path With Different Adjacent Characters
Hard

254

4

Add to List

Share
You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.

You are also given a string s of length n, where s[i] is the character assigned to node i.

Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

 

Example 1:


Input: parent = [-1,0,0,1,1,2], s = "abacbe"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
It can be proven that there is no longer path that satisfies the conditions. 
Example 2:


Input: parent = [-1,0,0,0], s = "aabc"
Output: 3
Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 

Constraints:

n == parent.length == s.length
1 <= n <= 105
0 <= parent[i] <= n - 1 for all i >= 1
parent[0] == -1
parent represents a valid tree.
s consists of only lowercase English letters.
 */
public class LongestPathWithDifferentAdjacentCharacters {
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
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

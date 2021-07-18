package com.interview.graph;

import java.util.*;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * https://www.youtube.com/watch?v=f2EfGComRKM&t=622s
 * https://leetcode.com/problems/clone-graph/
 * Category: Medium
 * Related: https://leetcode.com/problems/clone-binary-tree-with-random-pointer/ Medium
 * https://leetcode.com/problems/clone-n-ary-tree/ Medium
 */
public class CloneGraph {
 // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    private void dfs(Node node,Node  copy, Map<Integer, Node> visited)
    {
        visited.put(copy.val, copy);
        for(Node curr: node.neighbors)
        {
            if(!visited.containsKey(curr.val))
            {
                Node newnode = new Node(curr.val);
                copy.neighbors.add(newnode);
                dfs(curr,newnode,visited);
            }
            else
                copy.neighbors.add(visited.get(curr.val));
        }
    }
    
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        
        Map<Integer, Node> visited = new HashMap<>();
        Node copy = new Node(node.val);
        visited.put(node.val, copy);
        //Iterate for all neighbors
        for(Node curr: node.neighbors)
        {
            if(!visited.containsKey(curr.val))
            {
                Node newnode = new Node(curr.val);
                copy.neighbors.add(newnode);
                dfs(curr,newnode,visited);
            }
            else
                copy.neighbors.add(visited.get(curr.val));
        }
        return copy;
        
    }
}

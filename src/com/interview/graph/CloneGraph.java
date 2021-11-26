package com.interview.graph;

import java.util.*;

/**
 * Clone an undirected graph. Each TreeNode in the graph contains a label and a list of its neighbors.
 * https://www.youtube.com/watch?v=f2EfGComRKM&t=622s
 * https://leetcode.com/problems/clone-graph/
 * Category: Medium, Must Do, Facebook
 * Related: https://leetcode.com/problems/clone-binary-tree-with-random-pointer/ Medium
 * https://leetcode.com/problems/clone-n-ary-tree/ Medium
 * 
 * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
Example 4:


Input: adjList = [[2],[1]]
Output: [[2],[1]]
 

Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node
 */
public class CloneGraph {
 // Definition for a TreeNode.
    class TreeNode {
        public int val;
        public List<TreeNode> neighbors;
        public TreeNode() {
            val = 0;
            neighbors = new ArrayList<TreeNode>();
        }
        public TreeNode(int _val) {
            val = _val;
            neighbors = new ArrayList<TreeNode>();
        }
        public TreeNode(int _val, ArrayList<TreeNode> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    private void dfs(TreeNode TreeNode,TreeNode  copy, Map<Integer, TreeNode> visited)
    {
        visited.put(copy.val, copy);
        for(TreeNode curr: TreeNode.neighbors)
        {
            if(!visited.containsKey(curr.val))
            {
                TreeNode newTreeNode = new TreeNode(curr.val);
                copy.neighbors.add(newTreeNode);
                dfs(curr,newTreeNode,visited);
            }
            else
                copy.neighbors.add(visited.get(curr.val));
        }
    }
    
    public TreeNode cloneGraph(TreeNode TreeNode) {
        /*
         * Runtime: 57 ms, faster than 10.56% of Java online submissions for Clone Graph.
Memory Usage: 40 MB, less than 32.86% of Java online submissions for Clone Graph.
         */
        if(TreeNode == null)
            return null;
        
        Map<Integer, TreeNode> visited = new HashMap<>();
        TreeNode copy = new TreeNode(TreeNode.val);
        visited.put(TreeNode.val, copy);
        //Iterate for all neighbors
        for(TreeNode curr: TreeNode.neighbors)
        {
            if(!visited.containsKey(curr.val))
            {
                TreeNode newTreeNode = new TreeNode(curr.val);
                copy.neighbors.add(newTreeNode);
                dfs(curr,newTreeNode,visited);
            }
            else
                copy.neighbors.add(visited.get(curr.val));
        }
        return copy;
        
    }
}

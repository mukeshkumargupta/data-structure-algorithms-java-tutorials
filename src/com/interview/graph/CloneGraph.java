package com.interview.graph;

import java.util.*;

/**
 * Clone an undirected graph. Each TreeNode in the graph contains a label and a list of its neighbors.
 * https://www.youtube.com/watch?v=f2EfGComRKM&t=622s
 * https://leetcode.com/problems/clone-graph/
 * Category: Medium, Must Do, Facebook
 * Related: https://leetcode.com/problems/clone-binary-tree-with-random-pointer/ Medium
 * https://leetcode.com/problems/clone-n-ary-tree/ Medium
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

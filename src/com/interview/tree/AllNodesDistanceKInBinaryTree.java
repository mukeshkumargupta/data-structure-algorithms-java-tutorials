package com.interview.tree;
import java.util.*;
/*
 * Reference:https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * Category: Medium, Must Do
 * Video: https://www.youtube.com/watch?v=nPtARJ2cYrg
 */

/**
 * Definition for a binary tree TreeNode.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class AllTreeNodesDistanceKInBinaryTree {
    /*
     * Runtime: 25 ms, faster than 18.72% of Java online submissions for All Nodes Distance K in Binary Tree.
Memory Usage: 39.6 MB, less than 22.53% of Java online submissions for All Nodes Distance K in Binary Tree.
Time Complexity: O(N)O(N), where NN is the number of nodes in the given tree.

Space Complexity: O(N)O(N).
     */
    Map<TreeNode, TreeNode> lookup = new HashMap<>();
    int level = 0;
    
    private void buidLookUP(TreeNode root) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            lookup.put(root.left, root);
            buidLookUP(root.left);
        }
        
        if (root.right != null) {
            lookup.put(root.right, root);
            buidLookUP(root.right);
        }
    }
    

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        buidLookUP(root);
        //Now do bfs
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.add(target);
        visited.add(target);
        
        while (!q.isEmpty()) {
            int size = q.size();
            if (level == k) {
                while(!q.isEmpty()) {
                    result.add(q.remove().val);
                }
                return result;
                
            }
            for (int i = 0; i < size; i++) {
                TreeNode TreeNode = q.remove();
                if (TreeNode.left != null && !visited.contains(TreeNode.left)) {
                    q.add(TreeNode.left);
                    visited.add(TreeNode.left);
                }
                if (TreeNode.right != null && !visited.contains(TreeNode.right)) {
                    q.add(TreeNode.right);
                    visited.add(TreeNode.right);
                }
                if (lookup.containsKey(TreeNode) && !visited.contains(lookup.get(TreeNode))) {
                    q.add(lookup.get(TreeNode));
                    visited.add(lookup.get(TreeNode));
                }
            }
            level++;
        }
        return result;
    }
}

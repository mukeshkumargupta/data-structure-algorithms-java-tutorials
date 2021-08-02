package com.interview.tree;
import java.util.*;
/*
 * Reference:https://leetcode.com/problems/all-TreeNodes-distance-k-in-binary-tree/
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
        Set<Integer> visited = new HashSet<>();
        q.add(target);
        visited.add(target.val);
        
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
                if (TreeNode.left != null && !visited.contains(TreeNode.left.val)) {
                    q.add(TreeNode.left);
                    visited.add(TreeNode.left.val);
                }
                if (TreeNode.right != null && !visited.contains(TreeNode.right.val)) {
                    q.add(TreeNode.right);
                    visited.add(TreeNode.right.val);
                }
                if (lookup.containsKey(TreeNode) && !visited.contains(lookup.get(TreeNode).val)) {
                    q.add(lookup.get(TreeNode));
                    visited.add(lookup.get(TreeNode).val);
                }
            }
            level++;
        }
        return result;
    }
}

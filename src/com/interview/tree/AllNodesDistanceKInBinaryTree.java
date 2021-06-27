package com.interview.tree;
import java.util.*;
/*
 * Reference:https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * Category: Medium, Must Know
 * Video: https://www.youtube.com/watch?v=nPtARJ2cYrg
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class AllNodesDistanceKInBinaryTree {
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
                TreeNode node = q.remove();
                if (node.left != null && !visited.contains(node.left.val)) {
                    q.add(node.left);
                    visited.add(node.left.val);
                }
                if (node.right != null && !visited.contains(node.right.val)) {
                    q.add(node.right);
                    visited.add(node.right.val);
                }
                if (lookup.containsKey(node) && !visited.contains(lookup.get(node).val)) {
                    q.add(lookup.get(node));
                    visited.add(lookup.get(node).val);
                }
            }
            level++;
        }
        return result;
    }
}

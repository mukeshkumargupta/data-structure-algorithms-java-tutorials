package com.interview.tree;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 * Category: Medium
 * Related: Find, max, min, avg, bfs, level by level traversal
 * Video: NA
 */

/**
 * Definition for a binary tree TreeNode.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class FindLargestValueinEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        
        while (!q.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = q.size();
            for (int i = 0 ; i < size; i++) {
                TreeNode TreeNode = q.remove();
                if (TreeNode.val > max) {
                    max = TreeNode.val;
                }
                if (TreeNode.left != null) {
                    q.add(TreeNode.left);  
                }
                if (TreeNode.right != null) {
                    q.add(TreeNode.right);  
                }
                
            }
            result.add(max);    
        } 
        return result;
    }
    
}

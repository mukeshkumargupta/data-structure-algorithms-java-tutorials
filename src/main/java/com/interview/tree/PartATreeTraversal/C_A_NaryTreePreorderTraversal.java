package com.interview.tree.PartATreeTraversal;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 * Category: Easy
 */
public class C_A_NaryTreePreorderTraversal {
    private static class TreeNode {
        int val;
        List<TreeNode> children;
        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }
    List<Integer> result = new ArrayList<>();
    void preorderUtil(TreeNode root) {
        if (root == null) return;
        
        result.add(root.val);
        for (TreeNode TreeNode : root.children) {
            preorderUtil(TreeNode);
        }
        
    }
    public List<Integer> preorder(TreeNode root) {
        if (root == null) {
            return result;
        }
        preorderUtil(root);
        return result;
        
        
    }
}

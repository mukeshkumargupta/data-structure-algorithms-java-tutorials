package com.interview.tree;

/*
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 * Category: Easy
 */
public class NaryTreePreorderTraversal {
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

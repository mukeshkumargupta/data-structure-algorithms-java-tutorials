package com.interview.tree;

/*
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 * Category: Easy
 */
public class NaryTreePreorderTraversal {
    List<Integer> result = new ArrayList<>();
    void preorderUtil(Node root) {
        if (root == null) return;
        
        result.add(root.val);
        for (Node node : root.children) {
            preorderUtil(node);
        }
        
    }
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return result;
        }
        preorderUtil(root);
        return result;
        
        
    }
}

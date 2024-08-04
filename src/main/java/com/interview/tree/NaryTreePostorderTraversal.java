package com.interview.tree;

/*
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
 * Category: Easy
 */
public class NaryTreePostorderTraversal {
    List<Integer> result = new LinkedList<>();
    void postorderUtil(TreeNode root) {
        if (root == null) {
            return;
        }
        
        for (TreeNode TreeNode: root.children) {
            postorderUtil(TreeNode);
        }
        result.add(root.val);
        
    }
    public List<Integer> postorder(TreeNode root) {
        if (root == null) {
            return result;
        }
        postorderUtil(root);
        return result;
        
    }
}

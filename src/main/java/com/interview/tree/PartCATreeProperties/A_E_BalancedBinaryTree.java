package com.interview.tree.PartCATreeProperties;

import com.interview.tree.TreeNode;

/*
 * Reference: https://leetcode.com/problems/balanced-binary-tree/
 * Category: Easy, Must Do
 */
public class A_E_BalancedBinaryTree {
    private int height(TreeNode root) {
        if (root == null) return 0;

        return (1+ Math.max(height(root.left), height(root.right)));
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int diff = leftHeight > rightHeight ? leftHeight - rightHeight : rightHeight - leftHeight;

        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

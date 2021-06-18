package com.interview.tree;
/*
 * https://leetcode.com/problems/invert-binary-tree/
 * Category: Easy
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        //Swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}

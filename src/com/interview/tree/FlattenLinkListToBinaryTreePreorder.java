package com.interview.tree;

import java.util.Stack;

/**
 * Date 10/06/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a binary tree, flatten it to a linked list in-place in preorder traversal.
 *
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenLinkListToBinaryTreePreorder {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode TreeNode = stack.pop();
            if (prev != null) {
                prev.right = TreeNode;
            }
            prev = TreeNode;
            if (TreeNode.right != null) {
                stack.push(TreeNode.right);
            }
            if (TreeNode.left != null) {
                stack.push(TreeNode.left);
            }
            TreeNode.left = null;
            TreeNode.right = null;
        }
    }

    public void flatten1(TreeNode root) {
        if(root==null)
            return;
        flatten(root.left);
        flatten(root.right);
        TreeNode left  = root.left;
        TreeNode right = root.right;
        root.left  = null;
        root.right = left;
        while(root.right!=null)
            root = root.right;
        root.right = right;
    }
}

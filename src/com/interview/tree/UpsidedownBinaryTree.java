package com.interview.tree;

/**
 * Given a binary tree where all the right TreeNodes are either leaf TreeNodes with a sibling (a left TreeNode that
 * shares the same parent TreeNode) or empty, flip it upside down and turn it into a tree where the original
 * right TreeNodes turned into left leaf TreeNodes. Return the new root.
 *
 * https://leetcode.com/problems/binary-tree-upside-down/
 */
public class UpsidedownBinaryTree {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return upsideDownBinaryTree(root, null, null);
    }

    public TreeNode upsideDownBinaryTree(TreeNode root, TreeNode parent, TreeNode rightChild) {
        if (root == null) {
            return parent;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.right = parent;
        root.left = rightChild;

        return upsideDownBinaryTree(left, root, right);
    }
}

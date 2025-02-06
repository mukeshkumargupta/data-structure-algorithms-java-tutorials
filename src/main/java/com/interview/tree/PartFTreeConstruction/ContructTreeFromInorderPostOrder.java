package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Reference: https://www.youtube.com/watch?v=0r_cx1c8Z1A
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * Category: Medium, Must Do, VImp
 */
class ContructTreeFromInorderPostOrder {

    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;

        }

    }
private int postOrderIndex;
private Map<Integer, Integer> inorderIndexMap;

public TreeNode buildTree(int[] inorder, int[] postorder) {

    // Initialize the postorder index to the last element in the postorder array.
    postOrderIndex = postorder.length - 1;
    // Create a hashmap to store the value to index mappings for inorder traversal.
    inorderIndexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inorderIndexMap.put(inorder[i], i);
    }
    // Recursively build the tree.
    return buildTreeHelper(postorder, 0, inorder.length - 1);
}

private TreeNode buildTreeHelper(int[] postorder, int inStart, int inEnd) {
    // Base case: if there are no elements to construct the subtree.
    if (inStart > inEnd) {
        return null;
    }

    // The current root's value is the current element in the postorder array.
    int rootVal = postorder[postOrderIndex--];
    // Create the tree node with the root's value.
    TreeNode root = new TreeNode(rootVal);

    // Get the index of the root in the inorder traversal.
    int inIndex = inorderIndexMap.get(rootVal);

    // Recursively build the right and left subtrees.
    // Note: Build the right subtree first because postorder processes nodes in left-right-root order.
    root.right = buildTreeHelper(postorder, inIndex + 1, inEnd);
    root.left = buildTreeHelper(postorder, inStart, inIndex - 1);

    return root;
}
}

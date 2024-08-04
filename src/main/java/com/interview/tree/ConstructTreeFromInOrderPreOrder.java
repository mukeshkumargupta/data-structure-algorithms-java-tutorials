package com.interview.tree;

import java.util.*;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Category: Medium, Must Do, Fundamental, Top150
 * Note: Since search is taking time so use map here to get in order of 1
 * Related: construct all tree from inorder preorder, inorder post order, 
 * https://leetcode.com/problems/magic-squares-in-grid/ Medium Imp Concept wise
 * https://leetcode.com/problems/number-of-closed-islands/ Medium VImp
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/ Medium  VVImp
 * https://leetcode.com/problems/stone-game/ Medium
 * https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/ Medium
 * https://leetcode.com/problems/number-of-unique-flavors-after-sharing-k-candies/ Medium
 */
public class ConstructTreeFromInOrderPreOrder {
    private int preOrderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Initialize the preorder index to the first element in the preorder array.
        preOrderIndex = 0;
        // Create a hashmap to store the value to index mappings for inorder traversal.
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        // Recursively build the tree.
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int inStart, int inEnd) {
        // Base case: if there are no elements to construct the subtree.
        if (inStart > inEnd) {
            return null;
        }

        // The current root's value is the current element in the preorder array.
        int rootVal = preorder[preOrderIndex++];
        // Create the tree node with the root's value.
        TreeNode root = new TreeNode(rootVal);

        // Get the index of the root in the inorder traversal.
        int inIndex = inorderIndexMap.get(rootVal);

        // Recursively build the left and right subtrees.
        // Note: Build the left subtree first because preorder processes nodes in root-left-right order.
        root.left = buildTreeHelper(preorder, inStart, inIndex - 1);
        root.right = buildTreeHelper(preorder, inIndex + 1, inEnd);

        return root;
    }
}

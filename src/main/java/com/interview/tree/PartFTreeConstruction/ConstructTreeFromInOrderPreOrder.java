package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.TreeNode;

import java.util.*;

/**
 * Problem: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Category: Medium
 *
 * Approach:
 * - Use a HashMap to store index positions of elements in inorder for O(1) lookup.
 * - Use recursion to:
 *   1. Pick root from preorder.
 *   2. Find it in inorder and split left/right subtrees.
 *   3. Recurse on left and right subtrees.
 *
 * Example:
 * Given:
 * preorder = [3, 9, 20, 15, 7]
 * inorder = [9, 3, 15, 20, 7]
 *
 * Step-by-Step Construction:
 * 1. Root = 3 (preorder[0])
 * 2. In inorder, 3 is at index 1.
 * 3. Left subtree elements: [9], Right subtree elements: [15, 20, 7]
 * 4. Recursively build left subtree: preorder = [9], inorder = [9] → root = 9
 * 5. Recursively build right subtree: preorder = [20, 15, 7], inorder = [15, 20, 7] → root = 20
 *    - Left subtree: [15], Right subtree: [7]
 * 6. Final Tree Structure:
 *        3
 *       / \
 *      9   20
 *         /  \
 *        15   7
 *
 * Time Complexity: O(N) where N is the number of nodes.
 * Space Complexity: O(N) (HashMap + recursion stack).
 *
 * This approach efficiently constructs a binary tree from preorder and inorder traversals.
 */
public class ConstructTreeFromInOrderPreOrder {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private HashMap<Integer, Integer> inorderMap;
    private int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();

        // Store inorder values in a hashmap for quick lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return constructTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode constructTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        // Pick the current root node from preorder
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Get index of root in inorder array
        int inorderIndex = inorderMap.get(rootValue);

        // Construct left and right subtrees recursively
        root.left = constructTree(preorder, left, inorderIndex - 1);
        root.right = constructTree(preorder, inorderIndex + 1, right);

        return root;
    }
}

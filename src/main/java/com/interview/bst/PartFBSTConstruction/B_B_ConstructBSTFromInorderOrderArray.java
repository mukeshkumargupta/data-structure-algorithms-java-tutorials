package com.interview.bst.PartFBSTConstruction;

/*
     * https://www.youtube.com/watch?v=UAsLKuEMhsQ
     * Category: Medium
     * Problem: Construct BST from Inorder Traversal
     * Given a sorted inorder traversal of a BST, construct the unique BST.
     *
     * Approach:
     * Understand the Property of Inorder in BST:
     * - Inorder traversal of a BST is always sorted.
     * - The middle element of the inorder array is the root of the BST.
     * - The left half forms the left subtree.
     * - The right half forms the right subtree.
     *
     * Steps to Construct BST:
     * 1. Find the middle element → This is the root.
     * 2. Recursively construct the left subtree from elements left of the middle.
     * 3. Recursively construct the right subtree from elements right of the middle.
     *
     * Base Case:
     * - If start > end, return null (empty subtree).
     *
     * Time & Space Complexity:
     * - Time Complexity: O(N) (Each node is processed once)
     * - Space Complexity: O(logN) (Recursive stack for a balanced BST)
     */
public class B_B_ConstructBSTFromInorderOrderArray {
    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public TreeNode buildBSTFromInorder(int[] inorder) {
        return constructBST(inorder, 0, inorder.length - 1);
    }

    private TreeNode constructBST(int[] inorder, int start, int end) {
        if (start > end) return null;

        // Find middle element to make it root
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(inorder[mid]);

        // Recursively construct left and right subtrees
        root.left = constructBST(inorder, start, mid - 1);
        root.right = constructBST(inorder, mid + 1, end);

        return root;
    }
}
